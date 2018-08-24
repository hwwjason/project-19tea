package com.sckj.service;

import com.sckj.dto.ProductListDTO;
import com.sckj.pojo.ProductList;
import com.sckj.pojo.ProductListWithBLOBs;

import java.util.List;
import java.util.Map;

public interface IProductService {
   /**
    * 商品入库
    * @param productList
    */
   void putProductToStorage(ProductListWithBLOBs productList);

   void deleteProduct(String[] ids);

   /**
    * 更新商品状态
    * @param productList
    */
   void updateProduct(ProductListWithBLOBs productList) throws Exception;

   /**
    * 获取商品列表
    * @return
    */
   List<ProductListDTO> getProductList(Map<String,Object> productListMap);


}
