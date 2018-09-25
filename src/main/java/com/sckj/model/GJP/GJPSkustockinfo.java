package com.sckj.model.GJP;

public class GJPSkustockinfo {
    private String skuid;//sku的数字id
    private String skuqty;//Sku的实际库存数量
    private String skusaleqty;//可销售库存数量

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public String getSkuqty() {
        return skuqty;
    }

    public void setSkuqty(String skuqty) {
        this.skuqty = skuqty;
    }

    public String getSkusaleqty() {
        return skusaleqty;
    }

    public void setSkusaleqty(String skusaleqty) {
        this.skusaleqty = skusaleqty;
    }
}
