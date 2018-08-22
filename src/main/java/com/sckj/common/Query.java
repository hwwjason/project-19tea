//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sckj.common;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

public class Query {
    private Pageable pageable;
    private List<SearchFilter> filters;

    public Query() {
    }

    public Pageable getPageable() {
        return this.pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public List<SearchFilter> getFilters() {
        return this.filters;
    }

    public void setFilters(List<SearchFilter> filters) {
        this.filters = filters;
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();
        Iterator var2 = this.filters.iterator();

        while(var2.hasNext()) {
            SearchFilter searchFilter = (SearchFilter)var2.next();
            map.put(searchFilter.getFieldName(), searchFilter.getValue() != null ? searchFilter.getValue() : searchFilter.getValues());
        }

        return map;
    }

    public Object getBean(Class clazz) {
        try {
            Map<String, Object> map = new HashMap();
            Map<String, Object> tmp = this.getMap();
            Iterator var4 = tmp.keySet().iterator();

            while(true) {
                while(var4.hasNext()) {
                    String key = (String)var4.next();
                    if (key.contains(".")) {
                        String[] names = key.split("\\.");
                        Object object = null;
                        Object tmpObject1 = null;

                        for(int i = 0; i < names.length; ++i) {
                            if (i == 0) {
                                object = map.get(names[i]);
                                if (null == object) {
                                    object = clazz.getDeclaredField(names[i]).getType().newInstance();
                                }

                                tmpObject1 = object;
                            } else if (i == names.length - 1) {
                                ReflectUtils.setFieldValue(tmpObject1, names[i], tmp.get(key));
                            } else {
                                Object tmpObject2 = tmpObject1.getClass().getDeclaredField(names[i]).getType().newInstance();
                                ReflectUtils.setFieldValue(tmpObject1, names[i], tmpObject2);
                                tmpObject1 = tmpObject2;
                            }
                        }

                        map.put(names[0], object);
                    } else {
                        map.put(key, tmp.get(key));
                    }
                }

                return BeanUtils.convertMap(clazz, map);
            }
        } catch (Exception var11) {
            throw new RuntimeException(var11.getMessage());
        }
    }
}
