package com.sckj.enums;

public enum ResultStatusEnum {
    SUCESS("1","成功"),
    FAIL("0","失败");
    private  final String code;
    private final String name;

    ResultStatusEnum(String code, String name) {
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
