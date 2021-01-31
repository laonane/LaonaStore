package wiki.laona.core.pojo.seller;

import java.io.Serializable;
import java.util.Date;

public class Seller implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private String sellerId;
    /**
     * 公司名
     */
    private String name;
    /**
     * 店铺名称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * EMAIL
     */
    private String email;
    /**
     * 公司手机
     */
    private String mobile;
    /**
     * 公司电话
     */
    private String telephone;
    /**
     * 状态
     */
    private String status;
    /**
     * 详细地址
     */
    private String addressDetail;
    /**
     * 联系人姓名
     */
    private String linkmanName;
    /**
     * 联系人QQ
     */
    private String linkmanQq;
    /**
     * 联系人电话
     */
    private String linkmanMobile;
    /**
     * 联系人EMAIL
     */
    private String linkmanEmail;
    /**
     * 营业执照号
     */
    private String licenseNumber;
    /**
     * 税务登记证号
     */
    private String taxNumber;
    /**
     * 组织机构代码
     */
    private String orgNumber;
    /**
     * 公司地址
     */
    private Long address;
    /**
     * 公司LOGO图
     */
    private String logoPic;
    /**
     * 简介
     */
    private String brief;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 法定代表人
     */
    private String legalPerson;
    /**
     * 法定代表人身份证
     */
    private String legalPersonCardId;
    /**
     * 开户行账号名称
     */
    private String bankUser;
    /**
     * 开户行
     */
    private String bankName;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName == null ? null : linkmanName.trim();
    }

    public String getLinkmanQq() {
        return linkmanQq;
    }

    public void setLinkmanQq(String linkmanQq) {
        this.linkmanQq = linkmanQq == null ? null : linkmanQq.trim();
    }

    public String getLinkmanMobile() {
        return linkmanMobile;
    }

    public void setLinkmanMobile(String linkmanMobile) {
        this.linkmanMobile = linkmanMobile == null ? null : linkmanMobile.trim();
    }

    public String getLinkmanEmail() {
        return linkmanEmail;
    }

    public void setLinkmanEmail(String linkmanEmail) {
        this.linkmanEmail = linkmanEmail == null ? null : linkmanEmail.trim();
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber == null ? null : licenseNumber.trim();
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber == null ? null : taxNumber.trim();
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber == null ? null : orgNumber.trim();
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
    }

    public String getLogoPic() {
        return logoPic;
    }

    public void setLogoPic(String logoPic) {
        this.logoPic = logoPic == null ? null : logoPic.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getLegalPersonCardId() {
        return legalPersonCardId;
    }

    public void setLegalPersonCardId(String legalPersonCardId) {
        this.legalPersonCardId = legalPersonCardId == null ? null : legalPersonCardId.trim();
    }

    public String getBankUser() {
        return bankUser;
    }

    public void setBankUser(String bankUser) {
        this.bankUser = bankUser == null ? null : bankUser.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sellerId=").append(sellerId);
        sb.append(", name=").append(name);
        sb.append(", nickName=").append(nickName);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", mobile=").append(mobile);
        sb.append(", telephone=").append(telephone);
        sb.append(", status=").append(status);
        sb.append(", addressDetail=").append(addressDetail);
        sb.append(", linkmanName=").append(linkmanName);
        sb.append(", linkmanQq=").append(linkmanQq);
        sb.append(", linkmanMobile=").append(linkmanMobile);
        sb.append(", linkmanEmail=").append(linkmanEmail);
        sb.append(", licenseNumber=").append(licenseNumber);
        sb.append(", taxNumber=").append(taxNumber);
        sb.append(", orgNumber=").append(orgNumber);
        sb.append(", address=").append(address);
        sb.append(", logoPic=").append(logoPic);
        sb.append(", brief=").append(brief);
        sb.append(", createTime=").append(createTime);
        sb.append(", legalPerson=").append(legalPerson);
        sb.append(", legalPersonCardId=").append(legalPersonCardId);
        sb.append(", bankUser=").append(bankUser);
        sb.append(", bankName=").append(bankName);
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
        Seller other = (Seller) that;
        return (this.getSellerId() == null ? other.getSellerId() == null :
                this.getSellerId().equals(other.getSellerId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getNickName() == null ? other.getNickName() == null :
                this.getNickName().equals(other.getNickName()))
                && (this.getPassword() == null ? other.getPassword() == null :
                this.getPassword().equals(other.getPassword()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
                && (this.getTelephone() == null ? other.getTelephone() == null :
                this.getTelephone().equals(other.getTelephone()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getAddressDetail() == null ? other.getAddressDetail() == null :
                this.getAddressDetail().equals(other.getAddressDetail()))
                && (this.getLinkmanName() == null ? other.getLinkmanName() == null :
                this.getLinkmanName().equals(other.getLinkmanName()))
                && (this.getLinkmanQq() == null ? other.getLinkmanQq() == null :
                this.getLinkmanQq().equals(other.getLinkmanQq()))
                && (this.getLinkmanMobile() == null ? other.getLinkmanMobile() == null :
                this.getLinkmanMobile().equals(other.getLinkmanMobile()))
                && (this.getLinkmanEmail() == null ? other.getLinkmanEmail() == null :
                this.getLinkmanEmail().equals(other.getLinkmanEmail()))
                && (this.getLicenseNumber() == null ? other.getLicenseNumber() == null :
                this.getLicenseNumber().equals(other.getLicenseNumber()))
                && (this.getTaxNumber() == null ? other.getTaxNumber() == null :
                this.getTaxNumber().equals(other.getTaxNumber()))
                && (this.getOrgNumber() == null ? other.getOrgNumber() == null :
                this.getOrgNumber().equals(other.getOrgNumber()))
                && (this.getAddress() == null ? other.getAddress() == null :
                this.getAddress().equals(other.getAddress()))
                && (this.getLogoPic() == null ? other.getLogoPic() == null :
                this.getLogoPic().equals(other.getLogoPic()))
                && (this.getBrief() == null ? other.getBrief() == null : this.getBrief().equals(other.getBrief()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null :
                this.getCreateTime().equals(other.getCreateTime()))
                && (this.getLegalPerson() == null ? other.getLegalPerson() == null :
                this.getLegalPerson().equals(other.getLegalPerson()))
                && (this.getLegalPersonCardId() == null ? other.getLegalPersonCardId() == null :
                this.getLegalPersonCardId().equals(other.getLegalPersonCardId()))
                && (this.getBankUser() == null ? other.getBankUser() == null :
                this.getBankUser().equals(other.getBankUser()))
                && (this.getBankName() == null ? other.getBankName() == null :
                this.getBankName().equals(other.getBankName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAddressDetail() == null) ? 0 : getAddressDetail().hashCode());
        result = prime * result + ((getLinkmanName() == null) ? 0 : getLinkmanName().hashCode());
        result = prime * result + ((getLinkmanQq() == null) ? 0 : getLinkmanQq().hashCode());
        result = prime * result + ((getLinkmanMobile() == null) ? 0 : getLinkmanMobile().hashCode());
        result = prime * result + ((getLinkmanEmail() == null) ? 0 : getLinkmanEmail().hashCode());
        result = prime * result + ((getLicenseNumber() == null) ? 0 : getLicenseNumber().hashCode());
        result = prime * result + ((getTaxNumber() == null) ? 0 : getTaxNumber().hashCode());
        result = prime * result + ((getOrgNumber() == null) ? 0 : getOrgNumber().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getLogoPic() == null) ? 0 : getLogoPic().hashCode());
        result = prime * result + ((getBrief() == null) ? 0 : getBrief().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLegalPerson() == null) ? 0 : getLegalPerson().hashCode());
        result = prime * result + ((getLegalPersonCardId() == null) ? 0 : getLegalPersonCardId().hashCode());
        result = prime * result + ((getBankUser() == null) ? 0 : getBankUser().hashCode());
        result = prime * result + ((getBankName() == null) ? 0 : getBankName().hashCode());
        return result;
    }
}