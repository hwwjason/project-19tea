package com.sckj.model.GJP.GJPRequest;

import com.sckj.constant.GJPTESTConstants;
import com.sckj.model.GJP.GJPBaseModel;

import java.util.List;

public class GJPBaseRequest<T> {

    public String ShopKey = GJPTESTConstants.SHOP_KEY;

    private String apiName;

    public List<? extends T> baseModels ;

    public String getShopKey() {
        return ShopKey;
    }

    public void setShopKey(String shopKey) {
        ShopKey = shopKey;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public List<? extends T> getBaseModels() {
        return baseModels;
    }

    public void setBaseModels(List<? extends T> baseModels) {
        this.baseModels = baseModels;
    }
}
