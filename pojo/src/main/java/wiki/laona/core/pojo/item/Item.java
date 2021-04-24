package wiki.laona.core.pojo.item;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Dynamic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * item
 * @author laona
 */
@Data
public class Item implements Serializable {
    /**
     * 商品id，同时也是商品编号
     */
    @Field
    private Long id;

    /**
     * 商品标题
     */
    @Field("item_title")
    private String title;

    /**
     * 商品卖点
     */
    private String sellPoint;

    /**
     * 商品价格，单位为：元
     */
    @Field("item_price")
    private BigDecimal price;

    private Integer stockCount;

    /**
     * 库存数量
     */
    private Integer num;

    /**
     * 商品条形码
     */
    private String barcode;

    /**
     * 商品图片
     */
    @Field("item_image")
    private String image;

    /**
     * 所属类目，叶子类目
     */
    private Long categoryid;

    /**
     * 商品状态，1-正常，2-下架，3-删除
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    @Field("item_updatetime")
    private Date updateTime;

    private String itemSn;

    private BigDecimal costPirce;

    private BigDecimal marketPrice;

    private String isDefault;

    @Field("item_goodsid")
    private Long goodsId;

    private String sellerId;

    private String cartThumbnail;

    @Field("item_category")
    private String category;

    @Field("item_brand")
    private String brand;

    private String spec;

    @Field("item_seller")
    private String seller;

    /**
     * {选择颜色:黑色，套餐：套餐1}
     * item_spec_选择颜色  -----> 黑色
     * item_spec_套餐  ------> 套餐1
     */
    @Dynamic
    @Field("item_spec_*")
    private Map<String,String> specMap;

    public Map<String, String> getSpecMap() {
        return specMap;
    }

    public void setSpecMap(Map<String, String> specMap) {
        this.specMap = specMap;
    }
    private static final long serialVersionUID = 1L;

}