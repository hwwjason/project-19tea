package com.sckj.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

/**
* 描述：优惠券 模型
* @author hww
* @date 2018/09/07
*/
@Entity
@Table(name="sckj_coupon")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Coupon {

    /**
    *
    */
    @Id
    private String id;



    /**
    *标题
    */
    @Column(name = "title",columnDefinition = "VARCHAR")
    private String title;



    /**
    *图片
    */
    @Column(name = "img",columnDefinition = "TEXT")
    private String img;



    /**
    * FULL_REDUCE("0","满减"),WAITE_DELIVER("1","现金"),DELIVERED("2","折扣"),TAKED("3","商品");
    */
    @Column(name = "coupon_type",columnDefinition = "VARCHAR")
    private String couponType;



    /**
    *满金额
    */


    @Column(name = "full_money",columnDefinition = "DECIMAL")
    private BigDecimal fullMoney;

    /**
    *减金额
    */


    @Column(name = "reduce_money",columnDefinition = "DECIMAL")
    private BigDecimal reduceMoney;

    /**
    *库存
    */

    @Column(name = "stock",columnDefinition = "INTEGER")
    private int stock;


    /**
    *已发
    */

    @Column(name = "give",columnDefinition = "INTEGER")
    private int give;


    /**
    *有效期类型
    */
    @Column(name = "time_type",columnDefinition = "VARCHAR")
    private String timeType;



    /**
    *有效天数
    */

    @Column(name = "days",columnDefinition = "INTEGER")
    private int days;


    /**
    *
    */
    @Column(name = "starttime",columnDefinition = "TIMESTAMP")
    private Date starttime;



    /**
    *
    */
    @Column(name = "endtime",columnDefinition = "TIMESTAMP")
    private Date endtime;



    /**
    *描述
    */
    @Column(name = "describes",columnDefinition = "TEXT")
    private String describes;



    /**
    *
    */


    @Column(name = "scores",columnDefinition = "DECIMAL")
    private BigDecimal scores;

    /**
    *
    */
    @Column(name = "status",columnDefinition = "VARCHAR")
    private String status;



    /**
    *折扣
    */


    @Column(name = "discount",columnDefinition = "DECIMAL")
    private BigDecimal discount;

    /**
    *连接
    */
    @Column(name = "url",columnDefinition = "VARCHAR")
    private String url;



    /**
    *适用类型(0，线上，1，扫码购)
    */
    @Column(name = "apply_type",columnDefinition = "VARCHAR")
    private String applyType;

    /**
     *适用类型(0，商品，1，首页，2，频道)
     */
    @Column(name = "jump_type",columnDefinition = "VARCHAR")
    private String jumpType;



    /**
    *指定商品（商品券）
    */
    @Column(name = "productid",columnDefinition = "VARCHAR")
    private String productid;


    /**
    *商品名称
    */
    @Column(name = "productname",columnDefinition = "VARCHAR")
    private String productname;

    /**
     *创建时间
     */
    @Column(name = "createtime",columnDefinition = "TIMESTAMP")
    private Date createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(BigDecimal fullMoney) {
        this.fullMoney = fullMoney;
    }

    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getGive() {
        return give;
    }

    public void setGive(int give) {
        this.give = give;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public BigDecimal getScores() {
        return scores;
    }

    public void setScores(BigDecimal scores) {
        this.scores = scores;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }
}
