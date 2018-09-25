package com.sckj.model.GJP;

import java.math.BigDecimal;

public class GJPSelfbuiltmallproductsku {
    private String numid;//unlong
    private String skuid;//unlong
    private String outerskuid;
    private String properties;
    private String propertiesname;
    private String qty;//unlong
    private BigDecimal price;
    private String barcode;

    public String getNumid() {
        return numid;
    }

    public void setNumid(String numid) {
        this.numid = numid;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public String getOuterskuid() {
        return outerskuid;
    }

    public void setOuterskuid(String outerskuid) {
        this.outerskuid = outerskuid;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getPropertiesname() {
        return propertiesname;
    }

    public void setPropertiesname(String propertiesname) {
        this.propertiesname = propertiesname;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
