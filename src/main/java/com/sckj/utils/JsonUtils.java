package com.sckj.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class JsonUtils {
    private static final ObjectMapper JSON = new ObjectMapper();
    public static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        JSON.setSerializationInclusion(Include.NON_NULL);
        JSON.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
    }

    public static String toJson(Object obj) {
        try {
            return JSON.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }



    public JsonUtils() {
    }


    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null){
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }

    public static String object2Json(Object o) {
        String json = "";

        try {
            json = objectMapper.writeValueAsString(o);
            return json;
        } catch (IOException var3) {
            logger.error(var3.getMessage());
            throw new RuntimeException(var3.getMessage());
        }
    }

    public static Map parseJSON2Map(String jsonStr) {
        Map map = new HashMap();
        JSONObject json = (JSONObject)JSONObject.parse(jsonStr);
        Iterator var3 = json.keySet().iterator();

        while(true) {
            while(var3.hasNext()) {
                Object k = var3.next();
                Object v = json.get(k);
                if (v instanceof JSONArray) {
                    List list = new ArrayList();
                    Iterator it = ((JSONArray)v).iterator();

                    while(it.hasNext()) {
                        JSONObject json2 = (JSONObject)it.next();
                        list.add(parseJSON2Map(json2.toString()));
                    }

                    map.put(k.toString(), list);
                } else {
                    map.put(k.toString(), v);
                }
            }

            return map;
        }
    }

    public static <T> T json2Entity(String json, Class<T> entityClass) {
        try {
            return objectMapper.readValue(json, entityClass);
        } catch (IOException var3) {
            logger.error(var3.getMessage());
            throw new RuntimeException(var3.getMessage());
        }
    }

    public static <T> List<T> JSON2List(String json, Class<T[]> obj) throws Exception {
        List<T> list = null;
        ObjectMapper mapper = new ObjectMapper();
        T[] result = mapper.readValue(json, obj);
        list = Arrays.asList(result);
        return list;
    }

    public static final <T> String List2JSON(List<T> list) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        return json;
    }

    public static final <K, V> String Map2JSON(Map<K, V> map) {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            json = mapper.writeValueAsString(map);
            return json;
        } catch (IOException var4) {
            logger.error(var4.getMessage());
            throw new RuntimeException(var4.getMessage());
        }
    }

    public static final String Object2JSON(Object value) {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            json = mapper.writeValueAsString(value);
            return json;
        } catch (IOException var4) {
            logger.error(var4.getMessage());
            throw new RuntimeException(var4.getMessage());
        }
    }
}
