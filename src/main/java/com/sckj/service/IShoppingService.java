package com.sckj.service;

import com.sckj.pojo.ProductList;

import java.util.List;

public interface IShoppingService {
   int addProductToCart( String produceId, String userId,String cartType);

   int removeProductFromCart( String produceId, String userId,String cartType);
}
