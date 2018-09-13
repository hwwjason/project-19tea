package com.sckj.model.dto;

import java.math.BigDecimal;
import java.util.List;

public class UserCartList {
    private List<UserCartDTO> userCarts;
    private List<UserCartDTO> userCartOuts;//下架的商品

    private int totalNum;
    private BigDecimal totalSum;

    public List<UserCartDTO> getUserCarts() {
        return userCarts;
    }

    public void setUserCarts(List<UserCartDTO> userCarts) {
        this.userCarts = userCarts;
    }

    public List<UserCartDTO> getUserCartOuts() {
        return userCartOuts;
    }

    public void setUserCartOuts(List<UserCartDTO> userCartOuts) {
        this.userCartOuts = userCartOuts;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }
}
