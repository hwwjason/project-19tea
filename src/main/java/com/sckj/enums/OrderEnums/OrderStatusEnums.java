package com.sckj.enums.OrderEnums;

public enum OrderStatusEnums {
//    未支付 = 0,
//    待发货 = 1,
//    已发货 = 2,//商户
//    已收货 = 3,
//    已评价 = 4,
//    申请退款 = 5,
//    退货中 = 6,//商户
//    已退货 = 7,//商户
//    已退款 = 8,//商户
//    订单取消 = 9,
    NO_PAY("0","未支付"),
    WAITE_DELIVER("1","待发货"),
    DELIVERED("2","已发货"),
    TAKED("3","已收货"),
    EVALUATED("4","已评价"),
    APPLY_REFUND("5","申请退款"),
    RETURNING("6","退货中"),
    RETURNED("7","已退货"),
    REFUNDED("8","已退款"),
    DISABLED_ORDER("9","订单取消");
    private  final String code;
    private final String name;

    OrderStatusEnums(String code, String name) {
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
