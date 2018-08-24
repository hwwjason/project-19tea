package com.sckj.dto;

import com.sckj.pojo.SckjUserList;
import com.sckj.utils.DateTimeUtils;
import org.springframework.data.domain.Sort;

import java.util.List;

public class SckjUserListDTO extends SckjUserList {

    private String regtimeStr ;

    private String lastlogintimeStr ;
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

    public String getRegtimeStr() {
        return DateTimeUtils.getString(getRegtime(),regtimeStr);
    }

    public void setRegtimeStr(String regtimeStr) {
        this.regtimeStr = regtimeStr;
    }

    public String getLastlogintimeStr() {
        return DateTimeUtils.getString(getLastlogintime(),lastlogintimeStr);
    }

    public void setLastlogintimeStr(String lastlogintimeStr) {
        this.lastlogintimeStr = lastlogintimeStr;
    }
}

