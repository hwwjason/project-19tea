package com.sckj.model.dto;

import com.sckj.model.ProductOrder;
import com.sckj.model.ProductSonOrder;

import java.util.List;

/**
* 描述：订单列表DTO
* @author hww
* @date 2018/09/06
*/
public class ProductOrderDTO extends ProductOrder{
    private List<ProductSonOrderDTO> productSonOrderDTO;

    private List<ProductSonOrder> productSonOrder;

    private String cartType;

    private String couponId;

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

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }


}
