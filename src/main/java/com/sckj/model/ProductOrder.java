package com.sckj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

/**
* 描述：订单列表模型
* @author hww
* @date 2018/09/06
*/
@Entity
@Table(name="sckj_product_order")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ProductOrder {

    /**
    *
    */
    @Id
    private String id;



    /**
    *购买的用户id
    */
    @Column(name = "buyuser_id",columnDefinition = "VARCHAR")
    private String buyuserId;



    /**
    *购买用户的电话
    */
    @Column(name = "buyuser_tel",columnDefinition = "VARCHAR")
    private String buyuserTel;



    /**
    *支付用户
    */
    @Column(name = "payuser",columnDefinition = "VARCHAR")
    private String payuser;



    /**
    *邮费
    */


    @Column(name = "express_price",columnDefinition = "DECIMAL")
    private BigDecimal expressPrice;

    /**
    *商品价格(真正的销售价格)
    */


    @Column(name = "product_price",columnDefinition = "DECIMAL")
    private BigDecimal productPrice;

    /**
    *会员价格
    */


    @Column(name = "vip_price",columnDefinition = "DECIMAL")
    private BigDecimal vipPrice;

    /**
    *优惠金额(使用优惠券等)
    */


    @Column(name = "coupon_price",columnDefinition = "DECIMAL")
    private BigDecimal couponPrice;

    /**
    *总价
    */


    @Column(name = "total_price",columnDefinition = "DECIMAL")
    private BigDecimal totalPrice;

    /**
    *支付方式
    */
    @Column(name = "pay_method",columnDefinition = "VARCHAR")
    private String payMethod;



    /**
    *是否通过app
    */
    @Column(name = "isapp",columnDefinition = "VARCHAR")
    private String isapp;



    /**
    *订单状态
    */
    @Column(name = "order_status",columnDefinition = "VARCHAR")
    private String orderStatus;



    /**
    *创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "createtime",columnDefinition = "TIMESTAMP")
    private Date createtime;



    /**
    *是否支付提醒
    */
    @Column(name = "payremind",columnDefinition = "VARCHAR")
    private String payremind;



    /**
    *支付时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "paytime",columnDefinition = "TIMESTAMP")
    private Date paytime;



    /**
    *备注
    */
    @Column(name = "remark",columnDefinition = "VARCHAR")
    private String remark;



    /**
    *签收人
    */
    @Column(name = "signer",columnDefinition = "VARCHAR")
    private String signer;



    /**
    *签收人电话
    */
    @Column(name = "signer_tel",columnDefinition = "VARCHAR")
    private String signerTel;



    /**
    *省
    */
    @Column(name = "province",columnDefinition = "VARCHAR")
    private String province;



    /**
    *市
    */
    @Column(name = "city",columnDefinition = "VARCHAR")
    private String city;



    /**
    *区域
    */
    @Column(name = "area",columnDefinition = "VARCHAR")
    private String area;



    /**
    *详细地址
    */
    @Column(name = "address",columnDefinition = "VARCHAR")
    private String address;



    /**
    *用户备注
    */
    @Column(name = "user_remark",columnDefinition = "VARCHAR")
    private String userRemark;



    /**
    *分享用户ID
    */
    @Column(name = "shareuid",columnDefinition = "VARCHAR")
    private String shareuid;



    /**
    *退款状态（售后状态）
    */
    @Column(name = "refundstatus",columnDefinition = "VARCHAR")
    private String refundstatus;



    /**
    *退款原因
    */
    @Column(name = "refundreason",columnDefinition = "VARCHAR")
    private String refundreason;



    /**
    *退款id
    */
    @Column(name = "refund_id",columnDefinition = "VARCHAR")
    private String refundId;



    /**
    *退款快递
    */
    @Column(name = "refund_express",columnDefinition = "VARCHAR")
    private String refundExpress;



    /**
    *退款备注
    */
    @Column(name = "refund_remark",columnDefinition = "VARCHAR")
    private String refundRemark;



    /**
    *退款时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "refund_time",columnDefinition = "TIMESTAMP")
    private Date refundTime;



    /**
    *确认收货时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "confirm_time",columnDefinition = "TIMESTAMP")
    private Date confirmTime;



    /**
    *预支付（未知）
    */
    @Column(name = "prepay_id",columnDefinition = "VARCHAR")
    private String prepayId;



    /**
    *订单状态
    */
    @Column(name = "Status",columnDefinition = "VARCHAR")
    private String status;



    /**
    *快递名称
    */
    @Column(name = "express_name",columnDefinition = "VARCHAR")
    private String expressName;



    /**
    *快递编码
    */
    @Column(name = "express_code",columnDefinition = "VARCHAR")
    private String expressCode;



    /**
    *快递订单id
    */
    @Column(name = "express_orderid",columnDefinition = "VARCHAR")
    private String expressOrderid;



    /**
    *
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "m_refundtime",columnDefinition = "TIMESTAMP")
    private Date mRefundtime;



    /**
    *是否同步管家婆
    */
    @Column(name = "is_syn_gjp",columnDefinition = "VARCHAR")
    private String isSynGjp;



    /**
    *同步管家婆的时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "syn_gjp_time",columnDefinition = "TIMESTAMP")
    private Date synGjpTime;

    /**
     *订单类型（订单渠道）扫码购0，线上购物1
     */
    @Column(name = "order_type",columnDefinition = "VARCHAR")
    private String orderType;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyuserId() {
        return buyuserId;
    }

    public void setBuyuserId(String buyuserId) {
        this.buyuserId = buyuserId;
    }

    public String getBuyuserTel() {
        return buyuserTel;
    }

    public void setBuyuserTel(String buyuserTel) {
        this.buyuserTel = buyuserTel;
    }

    public String getPayuser() {
        return payuser;
    }

    public void setPayuser(String payuser) {
        this.payuser = payuser;
    }

    public BigDecimal getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(BigDecimal expressPrice) {
        this.expressPrice = expressPrice;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getIsapp() {
        return isapp;
    }

    public void setIsapp(String isapp) {
        this.isapp = isapp;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getPayremind() {
        return payremind;
    }

    public void setPayremind(String payremind) {
        this.payremind = payremind;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getSignerTel() {
        return signerTel;
    }

    public void setSignerTel(String signerTel) {
        this.signerTel = signerTel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getShareuid() {
        return shareuid;
    }

    public void setShareuid(String shareuid) {
        this.shareuid = shareuid;
    }

    public String getRefundstatus() {
        return refundstatus;
    }

    public void setRefundstatus(String refundstatus) {
        this.refundstatus = refundstatus;
    }

    public String getRefundreason() {
        return refundreason;
    }

    public void setRefundreason(String refundreason) {
        this.refundreason = refundreason;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getRefundExpress() {
        return refundExpress;
    }

    public void setRefundExpress(String refundExpress) {
        this.refundExpress = refundExpress;
    }

    public String getRefundRemark() {
        return refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getExpressOrderid() {
        return expressOrderid;
    }

    public void setExpressOrderid(String expressOrderid) {
        this.expressOrderid = expressOrderid;
    }

    public Date getmRefundtime() {
        return mRefundtime;
    }

    public void setmRefundtime(Date mRefundtime) {
        this.mRefundtime = mRefundtime;
    }

    public String getIsSynGjp() {
        return isSynGjp;
    }

    public void setIsSynGjp(String isSynGjp) {
        this.isSynGjp = isSynGjp;
    }

    public Date getSynGjpTime() {
        return synGjpTime;
    }

    public void setSynGjpTime(Date synGjpTime) {
        this.synGjpTime = synGjpTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
