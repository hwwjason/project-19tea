package com.sckj.model.GJP;


import java.math.BigDecimal;
import java.util.List;

public class GJPProductinfoModel extends GJPBaseModel {
    private String usercode;//商家编码
    private String barcode;//条形码
    private String fullname;//商品全名
    private String name;//商品简名
    private String type;//商品型号
    private String area;//商品产地
    private String standard;//商品规格
    private String comment;//商品备注
    private long id;//商品ID
    private boolean isstop;//商品是否停用(1,停用，0正常)
    private String brandname;//商品品牌
    private BigDecimal weight;//重量(kg)
    private BigDecimal length;//长度
    private BigDecimal width;//宽度
    private BigDecimal height;//高度
    private BigDecimal price;//商品价格
    private List<GJPErpptypesku> skus;//sku列表

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isIsstop() {
        return isstop;
    }

    public void setIsstop(boolean isstop) {
        this.isstop = isstop;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<GJPErpptypesku> getSkus() {
        return skus;
    }

    public void setSkus(List<GJPErpptypesku> skus) {
        this.skus = skus;
    }
}
