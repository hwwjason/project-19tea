package com.sckj.enums.OrderEnums;

/**
 * 优惠券类型
 */
public enum CouponInvalidTypeEnums {
    USED("0","已经使用"),
    TIME_OUT("1","已经过期");
    private  final String code;
    private final String name;

    CouponInvalidTypeEnums(String code, String name) {
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
