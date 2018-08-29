package com.sckj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    private static SimpleDateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat fmtDate2 = new SimpleDateFormat("yyyyMMdd");

    private static SimpleDateFormat fmtDate3 = new SimpleDateFormat("yyyy/MM/dd");

    private static SimpleDateFormat fmtDT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat fmtDT2 = new SimpleDateFormat("yyyyMMddHHmmss");

    public static Date getCurrentDate(){
        Date date = new Date();
        return date;
    }

    public static String getCurTime2(){
        return fmtDT2.format(new Date());
    }

    /** 返回一个日期时间戳, 精确到秒yyyy-MM-dd HH:mm:ss */
    public static String getCurTime() {
        return fmtDT.format(new Date());
    }

    public static String getCurDate2(){
        return fmtDate2.format(new Date());
    }

    public static String toString(Date date) {
        return fmtDT.format(date);
    }



    public static String getString(Date time,String timeStr){
        return StringUtils.isNotEmpty(timeStr)?timeStr:(time!=null?DateTimeUtils.toString(time):"");
    }




}
