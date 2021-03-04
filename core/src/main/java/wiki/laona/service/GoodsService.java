package wiki.laona.service;

import wiki.laona.core.pojo.entity.GoodsEntity;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.good.Goods;

/**
 * @description: goods 服务接口
 * @author: laona
 * @create: 2021-03-03 15:29
 **/
public interface GoodsService {
    /**
     * 添加商品
     *
     * @param goodsEntity 商品实体
     */
    void add(GoodsEntity goodsEntity);

    /**
     * 分页查询商品数据
     *
     * @param goods    商品信息
     * @param page     当前页
     * @param pageSize 每页多少条数据
     * @return 商品数据列表
     */
    PageResult<Goods> findPage(Goods goods, Integer page, Integer pageSize);

    /**
     * 通过 id 查找商品
     * @param id id
     * @return 商品信息，没有就 null
     */
    GoodsEntity findGoodsEntityById(Long id);

    /**
     * 更新商品实体信息
     * @param goodsEntity 商品实体
     */
    void updateGoodsEntryByGId(GoodsEntity goodsEntity);

    /**
     * 根商品id列表引删除商品
     * @param ids 商品id列表
     */
    void deleteByIds(Long[] ids);
}
