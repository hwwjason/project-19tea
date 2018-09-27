package com.sckj.constant;

import com.sckj.enums.OrderEnums.OrderStatusEnums;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusConstants {
    /**
     * 待支付
     */
    public  static final List<String> WATING_TO_PAY = getWatingToPay();
    /**
     * 待收货
     */
    public  static final List<String> WAIT_FOR_RECEIVING  =getWaitForReceiving();
    /**
     * 售后中
     */
    public  static final List<String> AFTER_SALING  =getWaitForReceiving();

    private static List<String> getWatingToPay(){
        List<String> list = new ArrayList<>();
        list.add(OrderStatusEnums.NO_PAY.toString());
        return list;
    }
    private static List<String> getWaitForReceiving(){
        List<String> list = new ArrayList<>();
        list.add(OrderStatusEnums.WAITE_DELIVER.toString());
        list.add(OrderStatusEnums.DELIVERED.toString());
        return list;
    }

    private static List<String> afterSaling(){
        List<String> list = new ArrayList<>();
        list.add(OrderStatusEnums.APPLY_REFUND.toString());
        list.add(OrderStatusEnums.RETURNING.toString());
        list.add(OrderStatusEnums.RETURNED.toString());
        return list;
    }
}
