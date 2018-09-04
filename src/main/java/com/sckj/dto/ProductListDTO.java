package com.sckj.dto;

import com.sckj.constant.MiniAppConstants;
import com.sckj.pojo.ProductList;
import com.sckj.utils.DateTimeUtils;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProductListDTO extends ProductList {
    /**
     * 排序
     */
    private List<Sort.Order> orders;

    private String updateTimeStr;

    private String addTimeStr;

    private MultipartFile imgFile;

    private String miniProUrl;//小程序连接

    private List<String> slideImgs;

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

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public String getMiniProUrl() {
        return miniProUrl==null? MiniAppConstants.MiniAppUrlPre+getId():miniProUrl;
    }

    public void setMiniProUrl(String miniProUrl) {
        this.miniProUrl = miniProUrl;
    }

    public List<String> getSlideImgs() {
        return slideImgs;
    }

    public void setSlideImgs(List<String> slideImgs) {
        this.slideImgs = slideImgs;
    }
}
