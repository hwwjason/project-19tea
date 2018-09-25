package com.sckj.model.GJP;

import java.math.BigDecimal;

public class GJPOrderdetail {
    /**
     *商品数字ID
     */
    private String ptypeid;//ulong 商品数字ID
    /**
     *商品商家编码 （客户填写的商家编码。如果网点启用强制按商家编码对应，此参数必须传值）
     */
    private String outid;//
    /**
     *商品名称
     */
    private String productname;//
    /**
     *子订单编号
     */
    private String oid;//
    /**
     *图片地址
     */
    private String picurl;//
    /**
     *Sku数字id ulong
     */
    private String skuid;//
    /**
     *Sku属性名称
     */
    private String platformpropertiesname;//
    /**
     *商品条形码
     */
    private String barcode;//
    /**
     *ulong 套餐ID
     */
    private String comboid;//
    /**
     * 网店商品原单价
     */
    private BigDecimal tradeoriginalprice;//
    /**
     * 金额 (接口不会直接取这个明细金额，金额计算方式为 price = tradeoriginalprice - preferentialtotal / qty）
     */
    private BigDecimal price;//金额 (接口不会直接取这个明细金额，金额计算方式为 price = tradeoriginalprice - preferentialtotal / qty）
    /**
     *优惠金额 （如果商品明细有多个数量，此金额为所有数量优惠金额的总和）
     */
    private BigDecimal preferentialtotal;//
    /**
     *数量
     */
    private BigDecimal qty;//
    /**
     *退货数量
     */
    private BigDecimal refundqty;//
    /**
     *退货金额总和
     */
    private BigDecimal refundtotal;//
    /**
     *是否赠品 （如果商品金额为零，系统会将此商品标记为赠品）
     */
    private boolean Isgift;//
    /**
     *售后状态（0=正常,1=等待卖家同意,2=卖家同意,3=退款关闭,4=退款成功）
     */
    private int refundstatus;//
    /**
     *明细是否被删除
     */
    private boolean isclosed;//
    /**
     *税金
     */
    private BigDecimal taxfee;//

    public String getPtypeid() {
        return ptypeid;
    }

    public void setPtypeid(String ptypeid) {
        this.ptypeid = ptypeid;
    }

    public String getOutid() {
        return outid;
    }

    public void setOutid(String outid) {
        this.outid = outid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public String getPlatformpropertiesname() {
        return platformpropertiesname;
    }

    public void setPlatformpropertiesname(String platformpropertiesname) {
        this.platformpropertiesname = platformpropertiesname;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getComboid() {
        return comboid;
    }

    public void setComboid(String comboid) {
        this.comboid = comboid;
    }

    public BigDecimal getTradeoriginalprice() {
        return tradeoriginalprice;
    }

    public void setTradeoriginalprice(BigDecimal tradeoriginalprice) {
        this.tradeoriginalprice = tradeoriginalprice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPreferentialtotal() {
        return preferentialtotal;
    }

    public void setPreferentialtotal(BigDecimal preferentialtotal) {
        this.preferentialtotal = preferentialtotal;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getRefundqty() {
        return refundqty;
    }

    public void setRefundqty(BigDecimal refundqty) {
        this.refundqty = refundqty;
    }

    public BigDecimal getRefundtotal() {
        return refundtotal;
    }

    public void setRefundtotal(BigDecimal refundtotal) {
        this.refundtotal = refundtotal;
    }

    public boolean isIsgift() {
        return Isgift;
    }

    public void setIsgift(boolean isgift) {
        Isgift = isgift;
    }

    public int getRefundstatus() {
        return refundstatus;
    }

    public void setRefundstatus(int refundstatus) {
        this.refundstatus = refundstatus;
    }

    public boolean isIsclosed() {
        return isclosed;
    }

    public void setIsclosed(boolean isclosed) {
        this.isclosed = isclosed;
    }

    public BigDecimal getTaxfee() {
        return taxfee;
    }

    public void setTaxfee(BigDecimal taxfee) {
        this.taxfee = taxfee;
    }
}
