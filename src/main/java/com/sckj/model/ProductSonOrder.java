package com.sckj.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述：订单子表模型
 * @author hww
 * @date 2018/09/05
 */
@Entity
@Table(name="sckj_product_son_order")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ProductSonOrder {

    /**
     *
     */
    @Id
    private String id;



    /**
     *订单id
     */
    @Column(name = "product_orderid",columnDefinition = "VARCHAR")
    private String productOrderid;



    /**
     *订单状态
     */
    @Column(name = "express_status",columnDefinition = "VARCHAR")
    private String expressStatus;



    /**
     *商品快照
     */
    @Column(name = "snapshot",columnDefinition = "VARCHAR")
    private String snapshot;



    /**
     *投递时间
     */
    @Column(name = "deliver_time",columnDefinition = "TIMESTAMP")
    private Date deliverTime;



    /**
     *收货时间
     */
    @Column(name = "receipt_time",columnDefinition = "TIMESTAMP")
    private Date receiptTime;



    /**
     *商品id
     */
    @Column(name = "productid",columnDefinition = "VARCHAR")
    private String productid;



    /**
     *商品类型
     */
    @Column(name = "product_type",columnDefinition = "VARCHAR")
    private String productType;



    /**
     *商品销售价格
     */


    @Column(name = "product_price",columnDefinition = "DECIMAL")
    private BigDecimal productPrice;

    /**
     *优惠券价格
     */


    @Column(name = "coupon_price",columnDefinition = "DECIMAL")
    private BigDecimal couponPrice;

    /**
     *成本价
     */


    @Column(name = "price",columnDefinition = "DECIMAL")
    private BigDecimal price;

    /**
     *购买数量
     */

    @Column(name = "buynum",columnDefinition = "INTEGER")
    private int buynum;


    /**
     *
     */
    @Column(name = "status",columnDefinition = "VARCHAR")
    private String status;



    /**
     *
     */
    @Column(name = "remark",columnDefinition = "VARCHAR")
    private String remark;



    /**
     *
     */
    @Column(name = "create_time",columnDefinition = "TIMESTAMP")
    private Date createTime;




    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getProductOrderid() {
        return this.productOrderid;
    }

    public void setProductOrderid(String productOrderid) {
        this.productOrderid = productOrderid;
    }



    public String getExpressStatus() {
        return this.expressStatus;
    }

    public void setExpressStatus(String expressStatus) {
        this.expressStatus = expressStatus;
    }





    public Date getDeliverTime() {
        return this.deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }



    public Date getReceiptTime() {
        return this.receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }



    public String getProductid() {
        return this.productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }



    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }




    public BigDecimal getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }



    public BigDecimal getCouponPrice() {
        return this.couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }



    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }




    public int getBuynum() {
        return this.buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }
}
