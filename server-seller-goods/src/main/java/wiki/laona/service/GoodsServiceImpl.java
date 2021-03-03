package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.core.dao.good.BrandDao;
import wiki.laona.core.dao.good.GoodsDao;
import wiki.laona.core.dao.good.GoodsDescDao;
import wiki.laona.core.dao.item.ItemCatDao;
import wiki.laona.core.dao.item.ItemDao;
import wiki.laona.core.dao.seller.SellerDao;
import wiki.laona.core.pojo.entity.GoodsEntity;
import wiki.laona.core.pojo.good.Brand;
import wiki.laona.core.pojo.good.Goods;
import wiki.laona.core.pojo.item.Item;
import wiki.laona.core.pojo.item.ItemCat;
import wiki.laona.core.pojo.seller.Seller;

import java.math.BigDecimal;
import java.util.*;

/**
 * @description: 商品服务接口实现类
 * @author: laona
 * @create: 2021-03-03 15:30
 **/
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsDescDao goodsDescDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private ItemCatDao catDao;
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private SellerDao sellerDao;

    @Override
    public void add(GoodsEntity goodsEntity) {
        // 设置审核状态
        goodsEntity.getGoods().setAuditStatus("0");
        // 保存商品
        goodsDao.insertSelective(goodsEntity.getGoods());
        System.out.println("goods ====== " + goodsEntity.getGoods());

        // 获取以保存的商品 id, 保存到商品描述中
        goodsEntity.getGoodsDesc().setGoodsId(goodsEntity.getGoods().getId());
        // 保存商品描述
        goodsDescDao.insertSelective(goodsEntity.getGoodsDesc());

        insertItem(goodsEntity, goodsEntity.getGoods());
    }

    /**
     * 插入 item 到数据库
     *
     * @param goodsEntity 商品实体
     * @param goods       商品
     */
    private void insertItem(final GoodsEntity goodsEntity, final Goods goods) {
        // 勾选了规格复选框，才做处理
        if ("1".equals(goods.getIsEnableSpec())) {
            if (Objects.nonNull(goodsEntity.getItemList())) {
                // 遍历条目
                for (Item item : goodsEntity.getItemList()) {
                    // 取出商品名跟选项、选项分类进行拼接
                    StringBuilder title = new StringBuilder(goods.getGoodsName());
                    Map specMap = JSON.parseObject(item.getSpec(), Map.class);
                    Collection specValues = specMap.values();
                    for (Object specValue : specValues) {
                        title.append(specValue);
                    }
                    item.setTitle(title.toString());

                    optionItemValues(goodsEntity, item);

                    itemDao.insertSelective(item);
                }
            }
        } else {
            //没有勾选复选框, 没有库存数据, 但是我们需要初始化一条, 不然前端有可能报错
            Item item = new Item();
            //价格
            item.setPrice(new BigDecimal(Integer.MAX_VALUE));
            //库存量
            item.setNum(0);
            //初始化规格
            item.setSpec("{}");
            //标题
            item.setTitle(goodsEntity.getGoods().getGoodsName());
            //设置库存对象的属性值
            optionItemValues(goodsEntity, item);
            itemDao.insertSelective(item);
        }
    }

    /**
     * 重写 item 的部分数据
     *
     * @param goodsEntity 商品实体
     * @param item        item
     */
    private void optionItemValues(final GoodsEntity goodsEntity, final Item item) {
        //商品id
        item.setGoodsId(goodsEntity.getGoods().getId());
        //创建时间
        item.setCreateTime(new Date());
        //更新时间
        item.setUpdateTime(new Date());
        //库存状态, 默认为0, 未审核
        item.setStatus("0");
        //分类id, 库存使用商品的第三级分类最为库存分类
        item.setCategoryid(goodsEntity.getGoods().getCategory3Id());
        //分类名称
        ItemCat itemCat = catDao.selectByPrimaryKey(goodsEntity.getGoods().getCategory3Id());
        item.setCategory(itemCat.getName());
        //品牌名称
        Brand brand = brandDao.selectByPrimaryKey(goodsEntity.getGoods().getBrandId());
        item.setBrand(brand.getName());
        //卖家名称
        Seller seller = sellerDao.selectByPrimaryKey(goodsEntity.getGoods().getSellerId());
        item.setSeller(seller.getName());
        item.setSellerId(seller.getSellerId());
        //示例图片
        String itemImages = goodsEntity.getGoodsDesc().getItemImages();
        List<Map> maps = JSON.parseArray(itemImages, Map.class);

        if (maps != null && maps.size() > 0) {
            String url = String.valueOf(maps.get(0).get("url"));
            item.setImage(url);
        }
    }

}
