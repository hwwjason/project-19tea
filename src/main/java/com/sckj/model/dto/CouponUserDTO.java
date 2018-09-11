package com.sckj.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sckj.model.CouponUser;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* 描述：优惠券用户表DTO
* @author hww
* @date 2018/09/10
*/
public class CouponUserDTO extends CouponUser{

    private String title;

    private String isOptimal;//0 否，1是  //最理想的

    private List<String> ids;

    private String isAllUser;//0否，1是  是否全部用户

    private String userPhoneStr;

    private String userPhoneFile;

    ///////////////////////////////////////////////coupon
    /**
     * FULL_REDUCE("0","满减"),WAITE_DELIVER("1","现金"),DELIVERED("2","折扣"),TAKED("3","商品");
     */
    private String couponType;


    /**
     *满金额
     */
    private BigDecimal fullMoney;

    /**
     *减金额
     */
    private BigDecimal reduceMoney;

    /**
     *有效期类型
     */
    private String timeType;

    /**
     *有效天数
     */
    private int days;


    /**
     *
     */
    @JsonFormat
    private Date starttime;



    /**
     *
     */
    private Date endtime;



    /**
     *描述
     */
    private String describes;



    /**
     *
     */
    private BigDecimal scores;

    /**
     *
     */
    private String status;



    /**
     *折扣
     */
    private BigDecimal discount;

    /**
     *连接
     */
    private String url;

    /**
     *适用类型(0，线上，1，扫码购)
     */
    private String applyType;

    /**
     *适用类型(0，商品，1，首页，2，频道)
     */
    private String jumpType;


    /**
     *指定商品（商品券）
     */
    private String productid;

    /**
     *商品名称
     */
    private String productname;

    ///////////////////////////////////////////////coupon

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String getStatus() {
        return status;
    }

    @Override
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

    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
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

    public String getIsOptimal() {
        return isOptimal;
    }

    public void setIsOptimal(String isOptimal) {
        this.isOptimal = isOptimal;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getIsAllUser() {
        return isAllUser;
    }

    public void setIsAllUser(String isAllUser) {
        this.isAllUser = isAllUser;
    }

    public String getUserPhoneStr() {
        return userPhoneStr;
    }

    public void setUserPhoneStr(String userPhoneStr) {
        this.userPhoneStr = userPhoneStr;
    }

    public String getUserPhoneFile() {
        return userPhoneFile;
    }

    public void setUserPhoneFile(String userPhoneFile) {
        this.userPhoneFile = userPhoneFile;
    }
}
