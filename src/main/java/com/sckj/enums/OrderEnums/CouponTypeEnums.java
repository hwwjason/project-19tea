package com.sckj.enums.OrderEnums;

/**
 * 优惠券类型
 */
public enum CouponTypeEnums {
    FULL_REDUCE("0","满减"),
    CASH("1","现金"),
    DISCOUNT("2","折扣"),
    PRODUCT("3","商品");
    private  final String code;
    private final String name;

    CouponTypeEnums(String code, String name) {
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
