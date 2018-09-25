package com.sckj.model.GJP;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单信息
 */
public class GJPSelfbuiltmalleshoporder extends GJPBaseModel{
    /**
     *订单号
     */
    private String tradeid;
    /**
     *支付单号
     */
    private String payno;
    /**
     *交易状态（交易状态 -1=全部,1= 未付款订单，2=已付款订单，3=已发货订单，4=交易成功订单，5=已关闭订单，6=部分发货）
     */
    private int tradestatus;
    /**
     *买家备注
     */
    private String buyermessage;
    /**
     *卖家备注
     */
    private String sellermemo;
    /**
     *交易创建时间
     */
    private String tradecreatetime;
    /**
     *支付成功时间
     */
    private String tradepaiedtime;
    /**
     *交易完成时间
     */
    private String tradefinishtime;
    /**
     *交易修改时间
     */
    private String trademodifiedtime;
    /**
     *货到付款交易发货时间
     */
    private String steptradedeliverytime;
    /**
     *交易类型 （0=普通，1=预售，2=征集，3=货到付款）
     */
    private int tradetype;
    /**
     *物流方式  未定义：0 ems：1 快递： 2 邮政： 3 包邮：4  虚拟：5 亚马逊配送 :6 卖家自行配送 :7 京东配送
     */
    private int shippingtype;
    /**
     *预售订单交易状态
     */
    private int steptradestatus;
    /**
     *售后状态（0=正常，1=退款中，2=退款成功）
     */
    private int refundstatus;
    /**
     *发票抬头
     */
    private String invoicetitle;
    /**
     *物流公司编码
     */
    private String freightcode;
    /**
     *物流单号
     */
    private String freightbillno;
    /**
     *卖家旗帜
     */
    private int sellerflag;
    /**
     *订单总金额
     */
    private BigDecimal tradetotal;
    /**
     *订单实付金额
     */
    private BigDecimal total;
    /**
     *订单已付金额
     */
    private BigDecimal paiedtotal;
    /**
     *订单优惠金额
     */
    private BigDecimal preferentialtotal;
    /**
     *商城扣费
     */
    private BigDecimal mallfee;
    /**
     *邮费
     */
    private BigDecimal customerfreightfee;
    /**
     *货到付款卖家应付
     */
    private BigDecimal buyerpayment;
    /**
     *快递代收费用
     */
    private BigDecimal expressagencyfee;
    /**
     *税号
     */
    private String invoicecode;
    /**
     *订单明细
     */
    private List<GJPOrderdetail> orderdetails;
    /**
     *买家信息
     */
    private GJPEshopbuyer eshopbuyer;
    /**
     *整单优惠（此优惠金额会分配到明细商品优惠中）
     */
    private BigDecimal orderpreferential;
    /**
     *门店/仓库编码（如果业务以门店方式处理订单，mallsendtype请选择7:自提或者8:配送.发货类型，并且对接门店信息添加接口）
     */
    private String storecode;
    /**
     *发货类型（0=自发,5=扫码购,7=自提,8=配送）
     */
    private int mallsendtype;
    /**
     *预计发货时间
     */
    private String osrange;
    /**
     *预计送达时间
     */
    private String esrange;
    /**
     *自提时间
     */
    private String fetchtime;
    /**
     *自提人
     */
    private String fetchcustomer;
    /**
     *操作员编码（传入操作员编码和erp系统操作员编码一致可以指定订单的操作员）
     */
    private String employeecode;

    public String getTradeid() {
        return tradeid;
    }

    public void setTradeid(String tradeid) {
        this.tradeid = tradeid;
    }

    public String getPayno() {
        return payno;
    }

    public void setPayno(String payno) {
        this.payno = payno;
    }

    public int getTradestatus() {
        return tradestatus;
    }

    public void setTradestatus(int tradestatus) {
        this.tradestatus = tradestatus;
    }

    public String getBuyermessage() {
        return buyermessage;
    }

    public void setBuyermessage(String buyermessage) {
        this.buyermessage = buyermessage;
    }

    public String getSellermemo() {
        return sellermemo;
    }

    public void setSellermemo(String sellermemo) {
        this.sellermemo = sellermemo;
    }

    public String getTradecreatetime() {
        return tradecreatetime;
    }

    public void setTradecreatetime(String tradecreatetime) {
        this.tradecreatetime = tradecreatetime;
    }

    public String getTradepaiedtime() {
        return tradepaiedtime;
    }

    public void setTradepaiedtime(String tradepaiedtime) {
        this.tradepaiedtime = tradepaiedtime;
    }

    public String getTradefinishtime() {
        return tradefinishtime;
    }

    public void setTradefinishtime(String tradefinishtime) {
        this.tradefinishtime = tradefinishtime;
    }

    public String getTrademodifiedtime() {
        return trademodifiedtime;
    }

    public void setTrademodifiedtime(String trademodifiedtime) {
        this.trademodifiedtime = trademodifiedtime;
    }

    public String getSteptradedeliverytime() {
        return steptradedeliverytime;
    }

    public void setSteptradedeliverytime(String steptradedeliverytime) {
        this.steptradedeliverytime = steptradedeliverytime;
    }

    public int getTradetype() {
        return tradetype;
    }

    public void setTradetype(int tradetype) {
        this.tradetype = tradetype;
    }

    public int getShippingtype() {
        return shippingtype;
    }

    public void setShippingtype(int shippingtype) {
        this.shippingtype = shippingtype;
    }

    public int getSteptradestatus() {
        return steptradestatus;
    }

    public void setSteptradestatus(int steptradestatus) {
        this.steptradestatus = steptradestatus;
    }

    public int getRefundstatus() {
        return refundstatus;
    }

    public void setRefundstatus(int refundstatus) {
        this.refundstatus = refundstatus;
    }

    public String getInvoicetitle() {
        return invoicetitle;
    }

    public void setInvoicetitle(String invoicetitle) {
        this.invoicetitle = invoicetitle;
    }

    public String getFreightcode() {
        return freightcode;
    }

    public void setFreightcode(String freightcode) {
        this.freightcode = freightcode;
    }

    public String getFreightbillno() {
        return freightbillno;
    }

    public void setFreightbillno(String freightbillno) {
        this.freightbillno = freightbillno;
    }

    public int getSellerflag() {
        return sellerflag;
    }

    public void setSellerflag(int sellerflag) {
        this.sellerflag = sellerflag;
    }

    public BigDecimal getTradetotal() {
        return tradetotal;
    }

    public void setTradetotal(BigDecimal tradetotal) {
        this.tradetotal = tradetotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPaiedtotal() {
        return paiedtotal;
    }

    public void setPaiedtotal(BigDecimal paiedtotal) {
        this.paiedtotal = paiedtotal;
    }

    public BigDecimal getPreferentialtotal() {
        return preferentialtotal;
    }

    public void setPreferentialtotal(BigDecimal preferentialtotal) {
        this.preferentialtotal = preferentialtotal;
    }

    public BigDecimal getMallfee() {
        return mallfee;
    }

    public void setMallfee(BigDecimal mallfee) {
        this.mallfee = mallfee;
    }

    public BigDecimal getCustomerfreightfee() {
        return customerfreightfee;
    }

    public void setCustomerfreightfee(BigDecimal customerfreightfee) {
        this.customerfreightfee = customerfreightfee;
    }

    public BigDecimal getBuyerpayment() {
        return buyerpayment;
    }

    public void setBuyerpayment(BigDecimal buyerpayment) {
        this.buyerpayment = buyerpayment;
    }

    public BigDecimal getExpressagencyfee() {
        return expressagencyfee;
    }

    public void setExpressagencyfee(BigDecimal expressagencyfee) {
        this.expressagencyfee = expressagencyfee;
    }

    public String getInvoicecode() {
        return invoicecode;
    }

    public void setInvoicecode(String invoicecode) {
        this.invoicecode = invoicecode;
    }

    public List<GJPOrderdetail> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<GJPOrderdetail> orderdetails) {
        this.orderdetails = orderdetails;
    }

    public GJPEshopbuyer getEshopbuyer() {
        return eshopbuyer;
    }

    public void setEshopbuyer(GJPEshopbuyer eshopbuyer) {
        this.eshopbuyer = eshopbuyer;
    }

    public BigDecimal getOrderpreferential() {
        return orderpreferential;
    }

    public void setOrderpreferential(BigDecimal orderpreferential) {
        this.orderpreferential = orderpreferential;
    }

    public String getStorecode() {
        return storecode;
    }

    public void setStorecode(String storecode) {
        this.storecode = storecode;
    }

    public int getMallsendtype() {
        return mallsendtype;
    }

    public void setMallsendtype(int mallsendtype) {
        this.mallsendtype = mallsendtype;
    }

    public String getOsrange() {
        return osrange;
    }

    public void setOsrange(String osrange) {
        this.osrange = osrange;
    }

    public String getEsrange() {
        return esrange;
    }

    public void setEsrange(String esrange) {
        this.esrange = esrange;
    }

    public String getFetchtime() {
        return fetchtime;
    }

    public void setFetchtime(String fetchtime) {
        this.fetchtime = fetchtime;
    }

    public String getFetchcustomer() {
        return fetchcustomer;
    }

    public void setFetchcustomer(String fetchcustomer) {
        this.fetchcustomer = fetchcustomer;
    }

    public String getEmployeecode() {
        return employeecode;
    }

    public void setEmployeecode(String employeecode) {
        this.employeecode = employeecode;
    }
}
