//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sckj.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class ReflectUtils {
    private static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);
    private static final Map<Class<?>, Method[]> METHODS_CACHEMAP = new HashMap();

    public ReflectUtils() {
    }

    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);

            try {
                result = field.get(obj);
            } catch (Exception var5) {
                logger.error(var5.getMessage(), var5);
                throw new RuntimeException(var5);
            }
        }

        return result;
    }

    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        Class clazz = obj.getClass();

        while(clazz != Object.class) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException var5) {
                clazz = clazz.getSuperclass();
            }
        }

        return field;
    }

    public static void setFieldValue(Object obj, String fieldName, String fieldValue) {
        Field field = getField(obj, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (Exception var5) {
                logger.error(var5.getMessage(), var5);
                throw new RuntimeException(var5);
            }
        }

    }

    public static <T> void copyProperties(T fromobj, T toobj, String... fieldspec) {
        String[] var3 = fieldspec;
        int var4 = fieldspec.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String filename = var3[var5];
            Object val = invokeGetterMethod(fromobj, filename);
            invokeSetterMethod(toobj, filename, val);
        }

    }

    public static Object invokeGetterMethod(Object obj, String propertyName) {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(obj, getterMethodName, (Class[])null, (Object[])null);
    }

    public static void invokeSetterMethod(Object obj, String propertyName, Object value) {
        invokeSetterMethod(obj, propertyName, value, (Class)null);
    }

    public static void invokeSetterMethod(Object obj, String propertyName, Object value, Class<?> propertyType) {
        value = handleValueType(obj, propertyName, value, propertyType);
        propertyType = propertyType != null ? propertyType : value.getClass();
        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
        invokeMethod(obj, setterMethodName, new Class[]{propertyType}, new Object[]{value});
    }

    private static Object handleValueType(Object obj, String propertyName, Object value, Class<?> propertyType) {
        String getterMethodName = propertyType == Boolean.TYPE ? "is" + StringUtils.capitalize(propertyName) : "get" + StringUtils.capitalize(propertyName);
        Class<?> argsType = value.getClass();
        Class<?> returnType = obtainAccessibleMethod(obj, getterMethodName).getReturnType();
        if (argsType == returnType) {
            return value;
        } else {
            if (returnType != Boolean.TYPE && returnType != Boolean.class) {
                if (returnType != Long.TYPE && returnType != Long.class) {
                    if (returnType != Double.TYPE && returnType != Double.class) {
                        if (returnType != Float.TYPE && returnType != Float.class) {
                            if (returnType == Date.class) {
                                if (argsType == java.sql.Date.class) {
                                    value = new Date(((java.sql.Date)value).getTime());
                                } else if (argsType == Timestamp.class) {
                                    value = new Date(((Timestamp)value).getTime());
                                }
                            } else if (returnType != Short.TYPE && returnType != Short.class) {
                                if (returnType == BigDecimal.class) {
                                    value = BigDecimal.valueOf(Long.valueOf(value.toString()).longValue());
                                } else if (returnType == BigInteger.class) {
                                    value = BigInteger.valueOf(Long.valueOf(value.toString()).longValue());
                                } else if (returnType == String.class) {
                                    value = String.valueOf(value);
                                } else if (returnType == Integer.TYPE || returnType == Integer.class) {
                                    value = Integer.valueOf(value.toString());
                                }
                            } else {
                                value = Short.valueOf(value.toString());
                            }
                        } else {
                            value = Float.valueOf(value.toString());
                        }
                    } else {
                        value = Double.valueOf(value.toString());
                    }
                } else {
                    value = Long.valueOf(value.toString());
                }
            } else {
                value = Boolean.valueOf(value.toString());
            }

            return value;
        }
    }

    public static Object invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object[] args) {
        Method method = obtainAccessibleMethod(obj, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("Devkit: Could not find method [" + methodName + "] on target [" + obj + "].");
        } else {
            try {
                return method.invoke(obj, args);
            } catch (IllegalAccessException var6) {
                var6.printStackTrace();
            } catch (IllegalArgumentException var7) {
                var7.printStackTrace();
            } catch (InvocationTargetException var8) {
                var8.printStackTrace();
            }

            return null;
        }
    }

    public static Method obtainAccessibleMethod(Object obj, String methodName, Class... parameterTypes) {
        Class<?> superClass = obj.getClass();

        for(Class objClass = Object.class; superClass != objClass; superClass = superClass.getSuperclass()) {
            Method method = null;

            try {
                method = superClass.getDeclaredMethod(methodName, parameterTypes);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException var7) {
                ;
            } catch (SecurityException var8) {
                ;
            }
        }

        return null;
    }

    public static Method obtainMethod(Object obj, String methodName) {
        Class<?> clazz = obj.getClass();
        Method[] methods = (Method[])METHODS_CACHEMAP.get(clazz);
        if (methods == null) {
            methods = clazz.getDeclaredMethods();
            METHODS_CACHEMAP.put(clazz, methods);
        }

        Method[] var4 = methods;
        int var5 = methods.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Method method = var4[var6];
            if (method.getName().equals(methodName)) {
                return method;
            }
        }

        return null;
    }

    public static Object obtainFieldValue(Object obj, String fieldName) {
        Field field = obtainAccessibleField(obj, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Devkit: could not find field [" + fieldName + "] on target [" + obj + "]");
        } else {
            Object retval = null;

            try {
                retval = field.get(obj);
            } catch (IllegalArgumentException var5) {
                var5.printStackTrace();
            } catch (IllegalAccessException var6) {
                var6.printStackTrace();
            }

            return retval;
        }
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) {
        Field field = obtainAccessibleField(obj, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Devkit: could not find field [" + fieldName + "] on target [" + obj + "]");
        } else {
            try {
                field.set(obj, value);
            } catch (IllegalArgumentException var5) {
                var5.printStackTrace();
            } catch (IllegalAccessException var6) {
                var6.printStackTrace();
            }

        }
    }

    public static Field obtainAccessibleField(Object obj, String fieldName) {
        Class<?> superClass = obj.getClass();

        for(Class objClass = Object.class; superClass != objClass; superClass = superClass.getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException var5) {
                ;
            } catch (SecurityException var6) {
                ;
            }
        }

        return null;
    }

    public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
        if (!(e instanceof IllegalAccessException) && !(e instanceof IllegalArgumentException) && !(e instanceof NoSuchMethodException)) {
            if (e instanceof InvocationTargetException) {
                return new RuntimeException(((InvocationTargetException)e).getTargetException());
            } else {
                return e instanceof RuntimeException ? (RuntimeException)e : new RuntimeException("Unexpected Checked Exception.", e);
            }
        } else {
            return new IllegalArgumentException(e);
        }
    }

    public static <T> Class<T> getClassGenricType(Class clazz) {
        return getClassGenricType(clazz, 0);
    }

    public static Class getClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        } else {
            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
            if (index < params.length && index >= 0) {
                return !(params[index] instanceof Class) ? Object.class : (Class)params[index];
            } else {
                return Object.class;
            }
        }
    }
}
