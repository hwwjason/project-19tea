package com.sckj.dto;

import com.sckj.pojo.ProductList;
import com.sckj.pojo.ProductListWithBLOBs;
import com.sckj.utils.DateTimeUtils;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

public class ProductListDTO extends ProductListWithBLOBs {
    /**
     * 排序
     */
    private List<Sort.Order> orders;

    private String updateTimeStr;

    private String addTimeStr;

    public List<Sort.Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Sort.Order> orders) {
        this.orders = orders;
    }

    public String getUpdateTimeStr() {
        return DateTimeUtils.getString(getUpdatetime(),updateTimeStr);
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = DateTimeUtils.getString(getUpdatetime(),updateTimeStr);;
    }

    public String getAddTimeStr() {
        return DateTimeUtils.getString(getAddtime(),addTimeStr);
    }

    public void setAddTimeStr(String addTimeStr) {
        this.addTimeStr = DateTimeUtils.getString(getAddtime(),addTimeStr);
    }
}
