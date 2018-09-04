//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sckj.utils;

import com.alibaba.fastjson.JSONArray;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import org.springframework.util.MultiValueMap;

public class BeanUtils {
    public BeanUtils() {
    }

    public static Object map2Bean(Class object, MultiValueMap<String, String> map) throws Exception {
        Field[] fields = object.getDeclaredFields();
        Object classInstance = object.newInstance();
        Field[] var4 = fields;
        int var5 = fields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field f = var4[var6];
            String fieldName = f.getName();
            if (map.get(fieldName) != null) {
                String fieldValue = (String)((List)map.get(fieldName)).get(0);
                if (!"".equals(fieldValue)) {
                    Method method = object.getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), String.class);
                    method.invoke(classInstance, fieldValue);
                }
            }
        }

        return classInstance;
    }

    public static Object convertMap(Class type, Map map) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException, SecurityException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        Object obj = type.newInstance();
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for(int i = 0; i < propertyDescriptors.length; ++i) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (map.containsKey(propertyName)) {
                Object value = map.get(propertyName);
                Class clz = descriptor.getPropertyType();
                if (clz == Date.class) {
                    if (value instanceof Long) {
                        value = new Date(((Long)value).longValue());
                    } else if (value instanceof String) {
                        value = new Date(Long.parseLong((String)value));
                    }
                } else if (List.class.isAssignableFrom(clz) && value instanceof JSONArray) {
                    value = JSONArray.parseArray(value.toString(), (Class)((ParameterizedType)type.getDeclaredField(propertyName).getGenericType()).getActualTypeArguments()[0]);
                }

                Object[] args = new Object[]{value};
                descriptor.getWriteMethod().invoke(obj, args);
            }
        }

        return obj;
    }

    public static Map convertBean(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Class type = bean.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Map<String, Object> returnMap = new HashMap();
        PropertyDescriptor[] var5 = propertyDescriptors;
        int var6 = propertyDescriptors.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            PropertyDescriptor descriptor = var5[var7];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null) {
                    returnMap.put(propertyName, result);
                }
            }
        }

        return returnMap;
    }

    public static Map convertPersistence(Object bean) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Class type = bean.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        Map<String, Field> fieldMap = new HashMap();

        int var8;
        for(Class superClass = type; superClass != null; superClass = superClass.getSuperclass()) {
            Field[] fields = superClass.getDeclaredFields();
            Field[] var6 = fields;
            int var7 = fields.length;

            for(var8 = 0; var8 < var7; ++var8) {
                Field field = var6[var8];
                fieldMap.put(field.getName(), field);
            }
        }

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Map<String, Object> returnMap = new HashMap();
        PropertyDescriptor[] var17 = propertyDescriptors;
        var8 = propertyDescriptors.length;

        for(int var18 = 0; var18 < var8; ++var18) {
            PropertyDescriptor descriptor = var17[var18];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null) {
                    Field field = (Field)fieldMap.get(propertyName);
                    if (field.isAnnotationPresent(Column.class)) {
                        propertyName = ((Column)field.getAnnotation(Column.class)).name();
                    }

                    returnMap.put(propertyName, result);
                }
            }
        }

        return returnMap;
    }

    public static void copyProperties(Object dest, Object orig) {
        if (orig != null && dest != null) {
            try {
                BeanInfo origInfo = Introspector.getBeanInfo(orig.getClass());
                PropertyDescriptor[] origPropertyDescriptors = origInfo.getPropertyDescriptors();
                BeanInfo destInfo = Introspector.getBeanInfo(dest.getClass());
                PropertyDescriptor[] destPropertyDescriptors = destInfo.getPropertyDescriptors();
                Map<String, Method> destPropertyMap = new HashMap();
                PropertyDescriptor[] var7 = destPropertyDescriptors;
                int var8 = destPropertyDescriptors.length;

                int var9;
                PropertyDescriptor propertyDescriptor;
                for(var9 = 0; var9 < var8; ++var9) {
                    propertyDescriptor = var7[var9];
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    if (writeMethod != null && propertyDescriptor.getReadMethod() != null) {
                        destPropertyMap.put(propertyDescriptor.getName(), writeMethod);
                    }
                }

                var7 = origPropertyDescriptors;
                var8 = origPropertyDescriptors.length;

                for(var9 = 0; var9 < var8; ++var9) {
                    propertyDescriptor = var7[var9];
                    String propertyName = propertyDescriptor.getName();
                    if (destPropertyMap.containsKey(propertyName)) {
                        Object[] args = new Object[]{propertyDescriptor.getReadMethod().invoke(orig)};
                        ((Method)destPropertyMap.get(propertyName)).invoke(dest, args);
                    }
                }
            } catch (IntrospectionException var13) {
                var13.printStackTrace();
            } catch (InvocationTargetException var14) {
                var14.printStackTrace();
            } catch (IllegalAccessException var15) {
                var15.printStackTrace();
            }

        }
    }


    public static void copyPropertiesWithoutNull(Object dest, Object orig) {
        if (orig != null && dest != null) {
            try {
                BeanInfo origInfo = Introspector.getBeanInfo(orig.getClass());
                PropertyDescriptor[] origPropertyDescriptors = origInfo.getPropertyDescriptors();
                BeanInfo destInfo = Introspector.getBeanInfo(dest.getClass());
                PropertyDescriptor[] destPropertyDescriptors = destInfo.getPropertyDescriptors();
                Map<String, Method> destPropertyMap = new HashMap();
                PropertyDescriptor[] var7 = destPropertyDescriptors;
                int var8 = destPropertyDescriptors.length;

                int var9;
                PropertyDescriptor propertyDescriptor;
                for(var9 = 0; var9 < var8; ++var9) {
                    propertyDescriptor = var7[var9];
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    if (writeMethod != null && propertyDescriptor.getReadMethod() != null) {
                        destPropertyMap.put(propertyDescriptor.getName(), writeMethod);
                    }
                }

                var7 = origPropertyDescriptors;
                var8 = origPropertyDescriptors.length;

                for(var9 = 0; var9 < var8; ++var9) {
                    propertyDescriptor = var7[var9];
                    String propertyName = propertyDescriptor.getName();
                    if (destPropertyMap.containsKey(propertyName)) {
                        Object[] args = new Object[]{propertyDescriptor.getReadMethod().invoke(orig)};
                        if(args.length<=0 || args[0]==null){
                            continue;
                        }
                        ((Method)destPropertyMap.get(propertyName)).invoke(dest, args);
                    }
                }
            } catch (IntrospectionException var13) {
                var13.printStackTrace();
            } catch (InvocationTargetException var14) {
                var14.printStackTrace();
            } catch (IllegalAccessException var15) {
                var15.printStackTrace();
            }

        }
    }
}
