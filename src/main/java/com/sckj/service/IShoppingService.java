package com.sckj.service;

public interface IShoppingService {
   int addProductToCart( String produceId, String userId,String cartType);

   int removeProductFromCart( String produceId, String userId,String cartType);
}
