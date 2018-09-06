package com.sckj.service.imp;

import com.sckj.service.IShoppingService;
import org.springframework.stereotype.Service;

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
