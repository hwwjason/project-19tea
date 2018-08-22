package com.sckj.service;

import com.sckj.pojo.ProductList;

import java.util.List;

public interface IProductService {
   /**
    * 商品入库
    * @param productList
    */
   void putProductToStorage(ProductList productList);

   /**
    * 更新商品状态
    * @param productList
    */
   void updateProduct(ProductList productList);

   /**
    * 获取商品列表
    * @return
    */
   List<ProductList> getProductList();


}
