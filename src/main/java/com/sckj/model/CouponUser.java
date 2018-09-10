package com.sckj.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

/**
* 描述：优惠券用户表模型
* @author hww
* @date 2018/09/10
*/
@Entity
@Table(name="sckj_coupon_user")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class CouponUser {

    /**
    *
    */
    @Id
    private String id;




    /**
    *优惠券ID
    */
    @Column(name = "couponid",columnDefinition = "VARCHAR")
    private String couponid;




    /**
    *商品订单ID
    */
    @Column(name = "product_orderid",columnDefinition = "VARCHAR")
    private String productOrderid;




    /**
    *用户ID
    */
    @Column(name = "userid",columnDefinition = "VARCHAR")
    private String userid;




    /**
    *电话号码（预留）
    */
    @Column(name = "tel",columnDefinition = "VARCHAR")
    private String tel;




    /**
    *
    */

    @Column(name = "realstarttime",columnDefinition = "TIMESTAMP")
    private Date realstarttime;


    /**
    *
    */

    @Column(name = "realendtime",columnDefinition = "TIMESTAMP")
    private Date realendtime;


    /**
    *领取时间
    */

    @Column(name = "receivetime",columnDefinition = "TIMESTAMP")
    private Date receivetime;


    /**
    *是否适用（0，否，1是）
    */
    @Column(name = "isuse",columnDefinition = "VARCHAR")
    private String isuse;




    /**
    *
    */
    @Column(name = "status",columnDefinition = "VARCHAR")
    private String status;




    /**
    *是否提醒过（0否，1是）
    */
    @Column(name = "Isremind",columnDefinition = "VARCHAR")
    private String isremind;




    /**
    *优惠券使用时间
    */

    @Column(name = "usertime",columnDefinition = "TIMESTAMP")
    private Date usertime;



    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCouponid() {
        return this.couponid;
    }

    public void setCouponid(String couponid) {
        this.couponid = couponid;
    }


    public String getProductOrderid() {
        return this.productOrderid;
    }

    public void setProductOrderid(String productOrderid) {
        this.productOrderid = productOrderid;
    }


    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public Date getRealstarttime() {
        return this.realstarttime;
    }

    public void setRealstarttime(Date realstarttime) {
        this.realstarttime = realstarttime;
    }


    public Date getRealendtime() {
        return this.realendtime;
    }

    public void setRealendtime(Date realendtime) {
        this.realendtime = realendtime;
    }


    public Date getReceivetime() {
        return this.receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }


    public String getIsuse() {
        return this.isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }


    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getIsremind() {
        return this.isremind;
    }

    public void setIsremind(String isremind) {
        this.isremind = isremind;
    }


    public Date getUsertime() {
        return this.usertime;
    }

    public void setUsertime(Date usertime) {
        this.usertime = usertime;
    }



}
