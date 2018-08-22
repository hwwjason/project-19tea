//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sckj.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.StringUtils;

public class SearchFilter {
    private String fieldName;
    private Object value;
    private MatchType matchType;
    private Object[] values;
    private LogicType logicType;
    private LogicType roundLogicType;
    private List<SearchFilter> joinFilters;

    public SearchFilter(String fieldName, MatchType matchType, LogicType logicType, Object value) {
        this(fieldName, matchType, value);
        this.logicType = logicType;
    }

    public SearchFilter(String fieldName, MatchType matchType, LogicType logicType, Object... values) {
        this(fieldName, matchType, values);
        this.logicType = logicType;
    }

    public SearchFilter(String fieldName, MatchType matchType, Object value) {
        this.logicType = LogicType.AND;
        this.joinFilters = new ArrayList();
        this.fieldName = fieldName;
        this.value = value;
        this.matchType = matchType;
    }

    public SearchFilter(String fieldName, MatchType matchType, Object... values) {
        this.logicType = LogicType.AND;
        this.joinFilters = new ArrayList();
        this.fieldName = fieldName;
        this.values = values;
        this.matchType = matchType;
    }

    public static List<SearchFilter> convertBean(Object bean) {
        Map map = BeanMap.create(bean);
        List<SearchFilter> searchFilters = new ArrayList();
        Set<String> keys = map.keySet();
        Iterator var4 = keys.iterator();

        while(var4.hasNext()) {
            String key = (String)var4.next();
            Object value = map.get(key);
            if (!StringUtils.isEmpty(value)) {
                searchFilters.add(new SearchFilter(key, MatchType.EQ, value));
            }
        }

        return searchFilters;
    }

    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = new HashMap();
        Iterator var2 = searchParams.entrySet().iterator();

        while(var2.hasNext()) {
            Entry<String, Object> entry = (Entry)var2.next();
            String key = (String)entry.getKey();
            Object value = entry.getValue();
            if (!org.apache.commons.lang3.StringUtils.isBlank(String.valueOf(value))) {
                String[] names = org.apache.commons.lang3.StringUtils.split(key, "_");
                if (names.length != 2) {
                    throw new IllegalArgumentException(key + " is not a valid search filter name");
                }

                String filedName = names[1];
                MatchType operator = MatchType.valueOf(names[0].toUpperCase());
                SearchFilter filter = new SearchFilter(filedName, operator, value);
                filters.put(key, filter);
            }
        }

        return filters;
    }

    public SearchFilter addRoundFilter(List<SearchFilter> filters, LogicType logicType) {
        switch(logicType) {
        case AND:
            return this.addRoundAndFilter(filters);
        case OR:
            return this.addRoundOrFilter(filters);
        default:
            return this;
        }
    }

    public SearchFilter addRoundAndFilter(List<SearchFilter> filters) {
        this.setRoundLogicType(LogicType.AND);
        this.joinFilters.addAll(filters);
        return this;
    }

    public SearchFilter addRoundOrFilter(List<SearchFilter> filters) {
        this.setRoundLogicType(LogicType.OR);
        this.joinFilters.addAll(filters);
        return this;
    }

    public MatchType getMatchType() {
        return this.matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object[] getValues() {
        return this.values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public LogicType getLogicType() {
        return this.logicType;
    }

    public void setLogicType(LogicType logicType) {
        this.logicType = logicType;
    }

    public LogicType getRoundLogicType() {
        return this.roundLogicType;
    }

    public void setRoundLogicType(LogicType roundLogicType) {
        this.roundLogicType = roundLogicType;
    }

    public List<SearchFilter> getJoinFilters() {
        return this.joinFilters;
    }

    public void setJoinFilters(List<SearchFilter> joinFilters) {
        this.joinFilters = joinFilters;
    }
}
