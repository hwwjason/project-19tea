package com.sckj.dto;

import com.sckj.pojo.SckjUserList;
import org.springframework.data.domain.Sort;

import java.util.List;

public class SckjUserListDTO extends SckjUserList {
    /**
     * 排序
     */
    private List<Sort.Order> orders;

    public List<Sort.Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Sort.Order> orders) {
        this.orders = orders;
    }
}

