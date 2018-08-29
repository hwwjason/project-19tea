package com.sckj.service.imp;

import com.sckj.pojo.ProductList;
import com.sckj.service.IShoppingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingServiceImp implements IShoppingService{

    @Override
    public int addProductToCart(String productId, String userId, String cartType) {
        return 0;
    }

    @Override
    public int removeProductFromCart(String productId, String userId, String cartType) {
        return 0;
    }
}
