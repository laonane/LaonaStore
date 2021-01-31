package wiki.laona.core.pojo.good;

import java.io.Serializable;
import java.math.BigDecimal;

public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 商家ID
     */
    private String sellerId;
    /**
     * SPU名
     */
    private String goodsName;
    /**
     * 默认SKU
     */
    private Long defaultItemId;
    /**
     * 状态
     */
    private String auditStatus;
    /**
     * 是否上架
     */
    private String isMarketable;
    /**
     * 品牌
     */
    private Long brandId;
    /**
     * 副标题
     */
    private String caption;
    /**
     * 一级类目
     */
    private Long category1Id;
    /**
     * 二级类目
     */
    private Long category2Id;
    /**
     * 三级类目
     */
    private Long category3Id;
    /**
     * 小图
     */
    private String smallPic;
    /**
     * 商城价
     */
    private BigDecimal price;
    /**
     * 分类模板ID
     */
    private Long typeTemplateId;
    /**
     * 是否启用规格
     */
    private String isEnableSpec;
    /**
     * 是否删除
     */
    private String isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Long getDefaultItemId() {
        return defaultItemId;
    }

    public void setDefaultItemId(Long defaultItemId) {
        this.defaultItemId = defaultItemId;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(String isMarketable) {
        this.isMarketable = isMarketable == null ? null : isMarketable.trim();
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption == null ? null : caption.trim();
    }

    public Long getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(Long category1Id) {
        this.category1Id = category1Id;
    }

    public Long getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Long category2Id) {
        this.category2Id = category2Id;
    }

    public Long getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(Long category3Id) {
        this.category3Id = category3Id;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic == null ? null : smallPic.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getTypeTemplateId() {
        return typeTemplateId;
    }

    public void setTypeTemplateId(Long typeTemplateId) {
        this.typeTemplateId = typeTemplateId;
    }

    public String getIsEnableSpec() {
        return isEnableSpec;
    }

    public void setIsEnableSpec(String isEnableSpec) {
        this.isEnableSpec = isEnableSpec == null ? null : isEnableSpec.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", defaultItemId=").append(defaultItemId);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", isMarketable=").append(isMarketable);
        sb.append(", brandId=").append(brandId);
        sb.append(", caption=").append(caption);
        sb.append(", category1Id=").append(category1Id);
        sb.append(", category2Id=").append(category2Id);
        sb.append(", category3Id=").append(category3Id);
        sb.append(", smallPic=").append(smallPic);
        sb.append(", price=").append(price);
        sb.append(", typeTemplateId=").append(typeTemplateId);
        sb.append(", isEnableSpec=").append(isEnableSpec);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Goods other = (Goods) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getSellerId() == null ? other.getSellerId() == null :
                this.getSellerId().equals(other.getSellerId()))
                && (this.getGoodsName() == null ? other.getGoodsName() == null :
                this.getGoodsName().equals(other.getGoodsName()))
                && (this.getDefaultItemId() == null ? other.getDefaultItemId() == null :
                this.getDefaultItemId().equals(other.getDefaultItemId()))
                && (this.getAuditStatus() == null ? other.getAuditStatus() == null :
                this.getAuditStatus().equals(other.getAuditStatus()))
                && (this.getIsMarketable() == null ? other.getIsMarketable() == null :
                this.getIsMarketable().equals(other.getIsMarketable()))
                && (this.getBrandId() == null ? other.getBrandId() == null :
                this.getBrandId().equals(other.getBrandId()))
                && (this.getCaption() == null ? other.getCaption() == null :
                this.getCaption().equals(other.getCaption()))
                && (this.getCategory1Id() == null ? other.getCategory1Id() == null :
                this.getCategory1Id().equals(other.getCategory1Id()))
                && (this.getCategory2Id() == null ? other.getCategory2Id() == null :
                this.getCategory2Id().equals(other.getCategory2Id()))
                && (this.getCategory3Id() == null ? other.getCategory3Id() == null :
                this.getCategory3Id().equals(other.getCategory3Id()))
                && (this.getSmallPic() == null ? other.getSmallPic() == null :
                this.getSmallPic().equals(other.getSmallPic()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getTypeTemplateId() == null ? other.getTypeTemplateId() == null :
                this.getTypeTemplateId().equals(other.getTypeTemplateId()))
                && (this.getIsEnableSpec() == null ? other.getIsEnableSpec() == null :
                this.getIsEnableSpec().equals(other.getIsEnableSpec()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null :
                this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getDefaultItemId() == null) ? 0 : getDefaultItemId().hashCode());
        result = prime * result + ((getAuditStatus() == null) ? 0 : getAuditStatus().hashCode());
        result = prime * result + ((getIsMarketable() == null) ? 0 : getIsMarketable().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getCaption() == null) ? 0 : getCaption().hashCode());
        result = prime * result + ((getCategory1Id() == null) ? 0 : getCategory1Id().hashCode());
        result = prime * result + ((getCategory2Id() == null) ? 0 : getCategory2Id().hashCode());
        result = prime * result + ((getCategory3Id() == null) ? 0 : getCategory3Id().hashCode());
        result = prime * result + ((getSmallPic() == null) ? 0 : getSmallPic().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getTypeTemplateId() == null) ? 0 : getTypeTemplateId().hashCode());
        result = prime * result + ((getIsEnableSpec() == null) ? 0 : getIsEnableSpec().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}