package com.sckj.model.dto;

public class PersonalInformationDTO {
    /**
     * 可用的优惠券数量
     */
    private int couponUserNum;

    private int watingToPayNum;//待支付=未支付
    private int waitForReceivingNum;//待收货=已发货
    private int afterSalingNum;//退换货=退货中+已退货+已退款 6 7 8
    /**
     * 购物车数量
     */
    private int usetCartNum;
    /**
     * 积分
     */
    private int integral=0;

    public int getCouponUserNum() {
        return couponUserNum;
    }

    public void setCouponUserNum(int couponUserNum) {
        this.couponUserNum = couponUserNum;
    }

    public int getWatingToPayNum() {
        return watingToPayNum;
    }

    public void setWatingToPayNum(int watingToPayNum) {
        this.watingToPayNum = watingToPayNum;
    }

    public int getWaitForReceivingNum() {
        return waitForReceivingNum;
    }

    public void setWaitForReceivingNum(int waitForReceivingNum) {
        this.waitForReceivingNum = waitForReceivingNum;
    }

    public int getAfterSalingNum() {
        return afterSalingNum;
    }

    public void setAfterSalingNum(int afterSalingNum) {
        this.afterSalingNum = afterSalingNum;
    }

    public int getUsetCartNum() {
        return usetCartNum;
    }

    public void setUsetCartNum(int usetCartNum) {
        this.usetCartNum = usetCartNum;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
