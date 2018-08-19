package com.sckj.enums;

public enum  LoginStateEnum {

    SUCESS_SPEED("2","成功极速登录"),
    SUCESS("1","成功"),
    FAIL("0","失败");
    private  final String code;
    private final String name;

    LoginStateEnum(String code, String name) {
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
