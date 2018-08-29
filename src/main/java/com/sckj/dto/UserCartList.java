package com.sckj.dto;

import java.util.List;

public class UserCartList {
    private List<UserCartDTO> userCartDTOs;
    private int totalNum;
    private float totalSum;

    public List<UserCartDTO> getUserCartDTOs() {
        return userCartDTOs;
    }

    public void setUserCartDTOs(List<UserCartDTO> userCartDTOs) {
        this.userCartDTOs = userCartDTOs;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public float getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(float totalSum) {
        this.totalSum = totalSum;
    }
}
