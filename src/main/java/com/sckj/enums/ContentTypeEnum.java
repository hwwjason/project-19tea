package com.sckj.enums;

public enum ContentTypeEnum {
    CONTENT_BANNER("0","Banner配置"),
    CONTENT_PRODUCT_SLIDE("1","商品滑动栏"),
    CONTENT_LINE("2","分割线"),
    CONTENT_ADVER("3","广告长图"),
    CONTENT_PRODUCT_CLOUMN("4","商品通栏");
    private  final String code;
    private final String name;

    ContentTypeEnum(String code, String name) {
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
