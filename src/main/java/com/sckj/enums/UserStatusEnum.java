package com.sckj.enums;

/**
 * 用户状态
 * 0, 未激活，1，正常，2，冻结，9，已删除
 */
public enum UserStatusEnum {
    NOTACTIVE("0","未激活"),
    NORMAL("1","正常"),
    FREEZE("2","冻结"),
    DELETED("9","已删除");

    private  final String code;
    private final String name;

    UserStatusEnum(String code, String name) {
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
