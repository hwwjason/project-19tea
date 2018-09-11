package com.sckj.model.dto;

import com.sckj.model.Coupon;
import com.sckj.utils.DateTimeUtils;

import java.util.List;

/**
* 描述：订单列表DTO
* @author hww
* @date 2018/09/07
*/
public class CouponDTO extends Coupon{
    private String starttimeStr;

    private String endtimeStr;

    private String createtimeStr;

    private List<String> ids;

    public String getStarttimeStr() {
        return DateTimeUtils.getString(getStarttime(),starttimeStr);
    }

    public void setStarttimeStr(String starttimeStr) {
        this.starttimeStr = starttimeStr;
    }

    public String getEndtimeStr() {
        return DateTimeUtils.getString(getEndtime(),endtimeStr);
    }

    public void setEndtimeStr(String endtimeStr) {
        this.endtimeStr = endtimeStr;
    }

    public String getCreatetimeStr() {
        return DateTimeUtils.getString(getCreatetime(),createtimeStr);
    }

    public void setCreatetimeStr(String createtimeStr) {
        this.createtimeStr = createtimeStr;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
