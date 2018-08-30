package com.sckj.dto;

import com.sckj.pojo.UserCart;

import java.math.BigDecimal;

public class UserCartDTO extends UserCart {
    private BigDecimal sum;//总额
    private String title;
    private String IsShelves;//上架，下架
    private BigDecimal price;
    private String img = "";

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsShelves() {
        return IsShelves;
    }

    public void setIsShelves(String isShelves) {
        IsShelves = isShelves;
    }
}
