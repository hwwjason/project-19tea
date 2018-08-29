package com.sckj.dto;

import com.sckj.pojo.UserCart;

import java.math.BigDecimal;

public class UserCartDTO extends UserCart {
    private BigDecimal sum;//总额
    private String name;
    private String imgUrl;
    private BigDecimal price;

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
