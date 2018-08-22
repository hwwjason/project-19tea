package com.sckj.service.imp;

import com.sckj.pojo.ProductList;
import com.sckj.service.IERPService;
import com.sckj.utils.UUIDUtils;

public class ERPServiceImp implements IERPService {
    @Override
    public ProductList getProductById(String id) {
        //模拟下
        ProductList productList = new ProductList();
        productList.setTitle("111");
        productList.setId(UUIDUtils.generate());
        return productList;
    }
}
