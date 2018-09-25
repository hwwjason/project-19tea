package com.sckj.model.GJP;

public class GJPErpptypesku extends GJPBaseModel{
    private long  ptypeid;//商品数字id
    private long  skuid;//Sku数字id
    private String  skucode;//Sku商家编码
    private String  fullbarcode;//条形码
    private String  prop1;//商品属性1
    private String  prop2;//商品属性2
    private String  unitname;//单位名称
    private long  unitid;//单位id

    public long getPtypeid() {
        return ptypeid;
    }

    public void setPtypeid(long ptypeid) {
        this.ptypeid = ptypeid;
    }

    public long getSkuid() {
        return skuid;
    }

    public void setSkuid(long skuid) {
        this.skuid = skuid;
    }

    public String getSkucode() {
        return skucode;
    }

    public void setSkucode(String skucode) {
        this.skucode = skucode;
    }

    public String getFullbarcode() {
        return fullbarcode;
    }

    public void setFullbarcode(String fullbarcode) {
        this.fullbarcode = fullbarcode;
    }

    public String getProp1() {
        return prop1;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public long getUnitid() {
        return unitid;
    }

    public void setUnitid(long unitid) {
        this.unitid = unitid;
    }
}
