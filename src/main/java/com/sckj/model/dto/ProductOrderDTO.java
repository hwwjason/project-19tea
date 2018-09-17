package com.sckj.model.dto;

import com.sckj.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
* 描述：订单列表DTO
* @author hww
* @date 2018/09/06
*/
public class ProductOrderDTO extends ProductOrder{
    private List<ProductSonOrderDTO> productSonOrderDTO;

    private List<ProductSonOrder> productSonOrder;

    private List<CouponUser> couponUsers;

    private List<CouponUserDTO> couponUserDTO;


    private List<UserAddress> userAddresss;

    Map<String,ProductList> productListMap;//与订单列表对应的商品<id,ProductList>

    private String cartType;

    private String couponUserid;//couponUserid  ,去结算的时候传空，不用的时候传 0

    private BigDecimal reduceMoney;

    private String orderidSignerTelBuyuserTel;//订单号，或收货人手机，或用户手机

    private String createStarttime;

    private String createEndtime;

    public List<ProductSonOrderDTO> getProductSonOrderDTO() {
        return productSonOrderDTO;
    }

    public void setProductSonOrderDTO(List<ProductSonOrderDTO> productSonOrderDTO) {
        this.productSonOrderDTO = productSonOrderDTO;
    }

    public List<ProductSonOrder> getProductSonOrder() {
        return productSonOrder;
    }

    public void setProductSonOrder(List<ProductSonOrder> productSonOrder) {
        this.productSonOrder = productSonOrder;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public String getCouponUserid() {
        return couponUserid;
    }

    public void setCouponUserid(String couponUserid) {
        this.couponUserid = couponUserid;
    }

    public List<CouponUser> getCouponUsers() {
        return couponUsers;
    }

    public void setCouponUsers(List<CouponUser> couponUsers) {
        this.couponUsers = couponUsers;
    }

    public List<UserAddress> getUserAddresss() {
        return userAddresss;
    }

    public void setUserAddresss(List<UserAddress> userAddresss) {
        this.userAddresss = userAddresss;
    }

    public List<CouponUserDTO> getCouponUserDTO() {
        return couponUserDTO;
    }

    public void setCouponUserDTO(List<CouponUserDTO> couponUserDTO) {
        this.couponUserDTO = couponUserDTO;
    }

    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public Map<String, ProductList> getProductListMap() {
        return productListMap;
    }

    public void setProductListMap(Map<String, ProductList> productListMap) {
        this.productListMap = productListMap;
    }

    public String getOrderidSignerTelBuyuserTel() {
        return orderidSignerTelBuyuserTel;
    }

    public void setOrderidSignerTelBuyuserTel(String orderidSignerTelBuyuserTel) {
        this.orderidSignerTelBuyuserTel = orderidSignerTelBuyuserTel;
    }

    public String getCreateStarttime() {
        return createStarttime;
    }

    public void setCreateStarttime(String createStarttime) {
        this.createStarttime = createStarttime;
    }

    public String getCreateEndtime() {
        return createEndtime;
    }

    public void setCreateEndtime(String createEndtime) {
        this.createEndtime = createEndtime;
    }
}
