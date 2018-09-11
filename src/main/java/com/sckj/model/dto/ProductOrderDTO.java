package com.sckj.model.dto;

import com.sckj.model.CouponUser;
import com.sckj.model.ProductOrder;
import com.sckj.model.ProductSonOrder;
import com.sckj.model.UserAddress;

import java.math.BigDecimal;
import java.util.List;

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

    private String cartType;

    private String couponUserid;//couponUserid

    private BigDecimal reduceMoney;

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
}
