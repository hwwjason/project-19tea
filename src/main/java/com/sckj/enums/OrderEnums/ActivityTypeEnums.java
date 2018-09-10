package com.sckj.enums.OrderEnums;

public enum ActivityTypeEnums {
//    满减 = 0,
//    打折 = 1,
//    秒杀 = 2,
//    满包邮 = 3,
    FULL_REDUCE("0","满减"),
    WAITE_DELIVER("1","打折"),
    DELIVERED("2","秒杀"),
    TAKED("3","满包邮");
    private  final String code;
    private final String name;

    ActivityTypeEnums(String code, String name) {
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
