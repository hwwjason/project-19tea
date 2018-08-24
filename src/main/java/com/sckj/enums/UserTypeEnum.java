package com.sckj.enums;

/**
 * 用户类型
 */
public enum UserTypeEnum {
    NORMAL("0","普通用户"),
    VIP1("1","VIP1"),
    VIP2("2","VIP2");

    private  final String code;
    private final String name;

    UserTypeEnum(String code, String name) {
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
