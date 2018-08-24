package com.sckj.enums;

/**
 * 商品上架下架状态
 */
public enum ProductShelvesEnum {
    PutShelf("1","上架"),
    RemoveShelf("0","下架");
    private  final String code;
    private final String name;

    ProductShelvesEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
