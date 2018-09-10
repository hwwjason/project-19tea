package com.sckj.enums.OrderEnums;

public enum CouponTypeEnums {
    FULL_REDUCE("0","满减"),
    WAITE_DELIVER("1","现金"),
    DELIVERED("2","折扣"),
    TAKED("3","商品");
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
