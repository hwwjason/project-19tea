package com.sckj.enums.OrderEnums;

/**
 * 优惠券类型
 */
public enum CouponTimeTypeEnums {
    DSYS("0","估计天数"),
    SCOPE("1","固定时间范围");
    private  final String code;
    private final String name;

    CouponTimeTypeEnums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return this.code;
    }

}
