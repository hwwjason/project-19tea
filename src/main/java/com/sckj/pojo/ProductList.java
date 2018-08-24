package com.sckj.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@MappedSuperclass
public class ProductList {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.id
     *
     * @mbg.generated
     */
    @Id
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.code
     *
     * @mbg.generated
     */
    @Column(name = "code",columnDefinition = "VARCHAR")
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.numid
     *
     * @mbg.generated
     */
    @Column(name = "numid",columnDefinition = "VARCHAR")
    private String numid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.title
     *
     * @mbg.generated
     */@Column(name = "title",columnDefinition = "VARCHAR")
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.subtitle
     *
     * @mbg.generated
     */@Column(name = "subtitle",columnDefinition = "VARCHAR")
    private String subtitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.specification
     *规格
     * @mbg.generated
     */@Column(name = "specification",columnDefinition = "VARCHAR")
    private String specification;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.original_price
     *
     * @mbg.generated
     */@Column(name = "original_price",columnDefinition = "DECIMAL")
    private BigDecimal originalPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.price
     *
     * @mbg.generated
     */@Column(name = "price",columnDefinition = "DECIMAL")
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.member_price
     *
     * @mbg.generated
     */@Column(name = "member_price",columnDefinition = "DECIMAL")
    private BigDecimal memberPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.stock
     *
     * @mbg.generated
     */@Column(name = "stock",columnDefinition = "INTEGER")
    private Integer stock;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.sell_num
     *
     * @mbg.generated
     */@Column(name = "sell_num",columnDefinition = "INTEGER")
    private Integer sellNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.supplier
     *
     * @mbg.generated
     */@Column(name = "supplier",columnDefinition = "VARCHAR")
    private String supplier;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.shelflife
     *
     * @mbg.generated
     * 保质期
     */@Column(name = "shelflife",columnDefinition = "VARCHAR")
    private String shelflife;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.tag1
     *
     * @mbg.generated
     */@Column(name = "tag1",columnDefinition = "VARCHAR")
    private String tag1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.tag2
     *
     * @mbg.generated
     */@Column(name = "tag2",columnDefinition = "VARCHAR")
    private String tag2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.IsShelves
     *
     * @mbg.generated
     */@Column(name = "isshelves",columnDefinition = "VARCHAR")
    private String isshelves;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.addtime
     *
     * @mbg.generated
     */@Column(name = "addtime",columnDefinition = "VARCHAR")
    private Date addtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.updatetime
     *
     * @mbg.generated
     */@Column(name = "updatetime",columnDefinition = "VARCHAR")
    private Date updatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.isgroupbuy
     *
     * @mbg.generated
     */@Column(name = "isgroupbuy",columnDefinition = "VARCHAR")
    private String isgroupbuy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.group_num
     *
     * @mbg.generated
     */@Column(name = "group_num",columnDefinition = "INTEGER")
    private Integer groupNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.group_price
     *
     * @mbg.generated
     */@Column(name = "group_price",columnDefinition = "DECIMAL")
    private BigDecimal groupPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.product_groupbyid
     *
     * @mbg.generated
     */@Column(name = "product_groupbyid",columnDefinition = "VARCHAR")
    private String productGroupbyid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.IsRecommend
     *
     * @mbg.generated
     */@Column(name = "isrecommend",columnDefinition = "VARCHAR")
    private String isrecommend;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.IsSpecial
     *
     * @mbg.generated
     */@Column(name = "isspecial",columnDefinition = "VARCHAR")
    private String isspecial;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.IsHot
     *
     * @mbg.generated
     */@Column(name = "ishot",columnDefinition = "VARCHAR")
    private String ishot;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.IsNew
     *
     * @mbg.generated
     */@Column(name = "isnew",columnDefinition = "VARCHAR")
    private String isnew;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.IsEarnShare
     *
     * @mbg.generated
     */@Column(name = "isearnshare",columnDefinition = "VARCHAR")
    private String isearnshare;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.Commission
     *
     * @mbg.generated
     */@Column(name = "commission",columnDefinition = "DECIMAL")
    private BigDecimal commission;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.IsFriend
     *
     * @mbg.generated
     */@Column(name = "IsFriend",columnDefinition = "VARCHAR")
    private String isfriend;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.falseSaleNum
     *
     * @mbg.generated
     */@Column(name = "falsesalenum",columnDefinition = "INTEGER")
    private Integer falsesalenum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.Status
     *
     * @mbg.generated
     */@Column(name = "status",columnDefinition = "VARCHAR")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.IsSynGjp
     *
     * @mbg.generated
     */@Column(name = "issyngjp",columnDefinition = "VARCHAR")
    private String issyngjp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.stocktime
     *
     * @mbg.generated
     */@Column(name = "stocktime",columnDefinition = "TIMESTAMP")
    private Date stocktime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.id
     *
     * @return the value of sckj_product_list.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.id
     *
     * @param id the value for sckj_product_list.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.code
     *
     * @return the value of sckj_product_list.code
     *
     * @mbg.generated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.code
     *
     * @param code the value for sckj_product_list.code
     *
     * @mbg.generated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.numid
     *
     * @return the value of sckj_product_list.numid
     *
     * @mbg.generated
     */
    public String getNumid() {
        return numid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.numid
     *
     * @param numid the value for sckj_product_list.numid
     *
     * @mbg.generated
     */
    public void setNumid(String numid) {
        this.numid = numid == null ? null : numid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.title
     *
     * @return the value of sckj_product_list.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.title
     *
     * @param title the value for sckj_product_list.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.subtitle
     *
     * @return the value of sckj_product_list.subtitle
     *
     * @mbg.generated
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.subtitle
     *
     * @param subtitle the value for sckj_product_list.subtitle
     *
     * @mbg.generated
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.specification
     *
     * @return the value of sckj_product_list.specification
     *
     * @mbg.generated
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.specification
     *
     * @param specification the value for sckj_product_list.specification
     *
     * @mbg.generated
     */
    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.original_price
     *
     * @return the value of sckj_product_list.original_price
     *
     * @mbg.generated
     */
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.original_price
     *
     * @param originalPrice the value for sckj_product_list.original_price
     *
     * @mbg.generated
     */
    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.price
     *
     * @return the value of sckj_product_list.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.price
     *
     * @param price the value for sckj_product_list.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.member_price
     *
     * @return the value of sckj_product_list.member_price
     *
     * @mbg.generated
     */
    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.member_price
     *
     * @param memberPrice the value for sckj_product_list.member_price
     *
     * @mbg.generated
     */
    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.stock
     *
     * @return the value of sckj_product_list.stock
     *
     * @mbg.generated
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.stock
     *
     * @param stock the value for sckj_product_list.stock
     *
     * @mbg.generated
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.sell_num
     *
     * @return the value of sckj_product_list.sell_num
     *
     * @mbg.generated
     */
    public Integer getSellNum() {
        return sellNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.sell_num
     *
     * @param sellNum the value for sckj_product_list.sell_num
     *
     * @mbg.generated
     */
    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.supplier
     *
     * @return the value of sckj_product_list.supplier
     *产地
     * @mbg.generated
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.supplier
     *
     * @param supplier the value for sckj_product_list.supplier
     *
     * @mbg.generated
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.shelflife
     *
     * @return the value of sckj_product_list.shelflife
     *
     * @mbg.generated
     */
    public String getShelflife() {
        return shelflife;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.shelflife
     *
     * @param shelflife the value for sckj_product_list.shelflife
     *
     * @mbg.generated
     */
    public void setShelflife(String shelflife) {
        this.shelflife = shelflife == null ? null : shelflife.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.tag1
     *
     * @return the value of sckj_product_list.tag1
     *
     * @mbg.generated
     */
    public String getTag1() {
        return tag1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.tag1
     *
     * @param tag1 the value for sckj_product_list.tag1
     *
     * @mbg.generated
     */
    public void setTag1(String tag1) {
        this.tag1 = tag1 == null ? null : tag1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.tag2
     *
     * @return the value of sckj_product_list.tag2
     *
     * @mbg.generated
     */
    public String getTag2() {
        return tag2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.tag2
     *
     * @param tag2 the value for sckj_product_list.tag2
     *
     * @mbg.generated
     */
    public void setTag2(String tag2) {
        this.tag2 = tag2 == null ? null : tag2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.IsShelves
     *
     * @return the value of sckj_product_list.IsShelves
     *
     * @mbg.generated
     */
    public String getIsshelves() {
        return isshelves;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.IsShelves
     *
     * @param isshelves the value for sckj_product_list.IsShelves
     *
     * @mbg.generated
     */
    public void setIsshelves(String isshelves) {
        this.isshelves = isshelves == null ? null : isshelves.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.addtime
     *
     * @return the value of sckj_product_list.addtime
     *
     * @mbg.generated
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.addtime
     *
     * @param addtime the value for sckj_product_list.addtime
     *
     * @mbg.generated
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.updatetime
     *
     * @return the value of sckj_product_list.updatetime
     *
     * @mbg.generated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.updatetime
     *
     * @param updatetime the value for sckj_product_list.updatetime
     *
     * @mbg.generated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.isgroupbuy
     *
     * @return the value of sckj_product_list.isgroupbuy
     *
     * @mbg.generated
     */
    public String getIsgroupbuy() {
        return isgroupbuy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.isgroupbuy
     *
     * @param isgroupbuy the value for sckj_product_list.isgroupbuy
     *
     * @mbg.generated
     */
    public void setIsgroupbuy(String isgroupbuy) {
        this.isgroupbuy = isgroupbuy == null ? null : isgroupbuy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.group_num
     *
     * @return the value of sckj_product_list.group_num
     *
     * @mbg.generated
     */
    public Integer getGroupNum() {
        return groupNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.group_num
     *
     * @param groupNum the value for sckj_product_list.group_num
     *
     * @mbg.generated
     */
    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.group_price
     *
     * @return the value of sckj_product_list.group_price
     *
     * @mbg.generated
     */
    public BigDecimal getGroupPrice() {
        return groupPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.group_price
     *
     * @param groupPrice the value for sckj_product_list.group_price
     *
     * @mbg.generated
     */
    public void setGroupPrice(BigDecimal groupPrice) {
        this.groupPrice = groupPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.product_groupbyid
     *
     * @return the value of sckj_product_list.product_groupbyid
     *
     * @mbg.generated
     */
    public String getProductGroupbyid() {
        return productGroupbyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.product_groupbyid
     *
     * @param productGroupbyid the value for sckj_product_list.product_groupbyid
     *
     * @mbg.generated
     */
    public void setProductGroupbyid(String productGroupbyid) {
        this.productGroupbyid = productGroupbyid == null ? null : productGroupbyid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.IsRecommend
     *
     * @return the value of sckj_product_list.IsRecommend
     *
     * @mbg.generated
     */
    public String getIsrecommend() {
        return isrecommend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.IsRecommend
     *
     * @param isrecommend the value for sckj_product_list.IsRecommend
     *
     * @mbg.generated
     */
    public void setIsrecommend(String isrecommend) {
        this.isrecommend = isrecommend == null ? null : isrecommend.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.IsSpecial
     *
     * @return the value of sckj_product_list.IsSpecial
     *
     * @mbg.generated
     */
    public String getIsspecial() {
        return isspecial;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.IsSpecial
     *
     * @param isspecial the value for sckj_product_list.IsSpecial
     *
     * @mbg.generated
     */
    public void setIsspecial(String isspecial) {
        this.isspecial = isspecial == null ? null : isspecial.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.IsHot
     *
     * @return the value of sckj_product_list.IsHot
     *
     * @mbg.generated
     */
    public String getIshot() {
        return ishot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.IsHot
     *
     * @param ishot the value for sckj_product_list.IsHot
     *
     * @mbg.generated
     */
    public void setIshot(String ishot) {
        this.ishot = ishot == null ? null : ishot.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.IsNew
     *
     * @return the value of sckj_product_list.IsNew
     *
     * @mbg.generated
     */
    public String getIsnew() {
        return isnew;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.IsNew
     *
     * @param isnew the value for sckj_product_list.IsNew
     *
     * @mbg.generated
     */
    public void setIsnew(String isnew) {
        this.isnew = isnew == null ? null : isnew.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.IsEarnShare
     *
     * @return the value of sckj_product_list.IsEarnShare
     *
     * @mbg.generated
     */
    public String getIsearnshare() {
        return isearnshare;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.IsEarnShare
     *
     * @param isearnshare the value for sckj_product_list.IsEarnShare
     *
     * @mbg.generated
     */
    public void setIsearnshare(String isearnshare) {
        this.isearnshare = isearnshare == null ? null : isearnshare.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.Commission
     *
     * @return the value of sckj_product_list.Commission
     *
     * @mbg.generated
     */
    public BigDecimal getCommission() {
        return commission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.Commission
     *
     * @param commission the value for sckj_product_list.Commission
     *
     * @mbg.generated
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.IsFriend
     *
     * @return the value of sckj_product_list.IsFriend
     *
     * @mbg.generated
     */
    public String getIsfriend() {
        return isfriend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.IsFriend
     *
     * @param isfriend the value for sckj_product_list.IsFriend
     *
     * @mbg.generated
     */
    public void setIsfriend(String isfriend) {
        this.isfriend = isfriend == null ? null : isfriend.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.falseSaleNum
     *
     * @return the value of sckj_product_list.falseSaleNum
     *
     * @mbg.generated
     */
    public Integer getFalsesalenum() {
        return falsesalenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.falseSaleNum
     *
     * @param falsesalenum the value for sckj_product_list.falseSaleNum
     *
     * @mbg.generated
     */
    public void setFalsesalenum(Integer falsesalenum) {
        this.falsesalenum = falsesalenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.Status
     *
     * @return the value of sckj_product_list.Status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.Status
     *
     * @param status the value for sckj_product_list.Status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.IsSynGjp
     *
     * @return the value of sckj_product_list.IsSynGjp
     *
     * @mbg.generated
     */
    public String getIssyngjp() {
        return issyngjp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.IsSynGjp
     *
     * @param issyngjp the value for sckj_product_list.IsSynGjp
     *
     * @mbg.generated
     */
    public void setIssyngjp(String issyngjp) {
        this.issyngjp = issyngjp == null ? null : issyngjp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.stocktime
     *
     * @return the value of sckj_product_list.stocktime
     *
     * @mbg.generated
     */
    public Date getStocktime() {
        return stocktime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.stocktime
     *
     * @param stocktime the value for sckj_product_list.stocktime
     *
     * @mbg.generated
     */
    public void setStocktime(Date stocktime) {
        this.stocktime = stocktime;
    }
}