package com.sckj.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class RefelectUtils {

    public static Object setValue(Object obj, String methodName, String value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] field = obj.getClass().getDeclaredFields(); //获取实体类的所有属性，返回Field数组
        for (int j = 0; j < field.length; j++) { //遍历所有属性
            String type = field[j].getGenericType().toString(); //获取属性的类型
            if(type.equals("class java.lang.String")){ //如果type是类类型，则前面包含"class "，后面跟类名
                String name = StringUtils.toUpperCaseFirstOne(field[j].getName()); //获取属性的名字
                if (field[j].getName().equals(methodName) && value != null) {//等于所给属性的值
                    Method setMethod = obj.getClass().getMethod("set" + name, new Class[]{String.class});
                    setMethod.invoke(obj, new Object[]{value});
                }
            }else if(type.equals("class java.math.BigDecimal")){
                String name = StringUtils.toUpperCaseFirstOne(field[j].getName()); //获取属性的名字
                if (field[j].getName().equals(methodName) && value != null) {//等于所给属性的值
                    Method setMethod = obj.getClass().getMethod("set" + name, new Class[]{BigDecimal.class});
                    setMethod.invoke(obj, new Object[]{value});
                }
            }else if(type.equals("class java.lang.Integer")){
                String name = StringUtils.toUpperCaseFirstOne(field[j].getName()); //获取属性的名字
                if (field[j].getName().equals(methodName) && value != null) {//等于所给属性的值
                    Method setMethod = obj.getClass().getMethod("set" + name, new Class[]{Integer.class});
                    setMethod.invoke(obj, new Object[]{value});
                }
            }else if(type.equals("class java.util.Date")){
                String name = StringUtils.toUpperCaseFirstOne(field[j].getName()); //获取属性的名字
                if (field[j].getName().equals(methodName) && value != null) {//等于所给属性的值
                    Method setMethod = obj.getClass().getMethod("set" + name, new Class[]{Date.class});
                    setMethod.invoke(obj, new Object[]{value});
                }
            }
            else{
                System.out.println(type+"类型未处理");
            }
        }

        return obj;
    }

    public Object setValues(Object obj, Map<String,Object> nameValues) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
       return null;
    }
}
