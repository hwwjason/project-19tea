package com.sckj.enums;

public enum CartTypeEnum {
    UNDERLINE_CART("0","门店购物车"),
    ONLINE_CART("1","线上购物车");
    private  final String code;
    private final String name;

    CartTypeEnum(String code, String name) {
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
