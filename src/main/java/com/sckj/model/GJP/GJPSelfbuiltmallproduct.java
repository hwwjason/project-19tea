package com.sckj.model.GJP;

import java.math.BigDecimal;
import java.util.List;

public class GJPSelfbuiltmallproduct {
    private String productname;//
    private String numid;//商品数字ID（请与订单明细中ptypeid保持一致）
    private String outerid;//
    private String picurl;//
    private BigDecimal price;
    private int stockstatus;//
    private List<GJPSelfbuiltmallproductsku> skus;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getNumid() {
        return numid;
    }

    public void setNumid(String numid) {
        this.numid = numid;
    }

    public String getOuterid() {
        return outerid;
    }

    public void setOuterid(String outerid) {
        this.outerid = outerid;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStockstatus() {
        return stockstatus;
    }

    public void setStockstatus(int stockstatus) {
        this.stockstatus = stockstatus;
    }

    public List<GJPSelfbuiltmallproductsku> getSkus() {
        return skus;
    }

    public void setSkus(List<GJPSelfbuiltmallproductsku> skus) {
        this.skus = skus;
    }
}
