package com.sckj.service.imp;

import com.sckj.jpa.ProductListJpa;
import com.sckj.pojo.ProductList;
import com.sckj.service.IProductService;
import com.sckj.service.IShoppingService;
import com.sckj.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements IProductService{

    @Autowired
    private ProductListJpa productListJpa;

    @Override
    public void putProductToStorage(ProductList productList) {
        productList.setId(UUIDUtils.generate());
        productListJpa.saveAndFlush(productList);
    }

    @Override
    public void updateProduct(ProductList productList) {

    }

    @Override
    public List<ProductList> getProductList() {
        return null;
    }
}
