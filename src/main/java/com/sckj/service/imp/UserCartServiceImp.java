package com.sckj.service.imp;

import com.sckj.dao.UserCartMapper;
import com.sckj.dto.UserCartDTO;
import com.sckj.dto.UserCartList;
import com.sckj.jpa.UserCartJpa;
import com.sckj.pojo.UserCart;
import com.sckj.service.IUserCartService;
import com.sckj.utils.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCartServiceImp implements IUserCartService {

    @Autowired
    private UserCartMapper userCartMapper;

    @Autowired
    private UserCartJpa userCartJpa;

    @Override
    public UserCartList addGoods(String productId, String userId, String cartType) {
        //查询用户是否已经收藏这个商品了
        UserCart userCart =  userCartJpa.findByUseridAndProductid(userId,productId);
        if(userCart!=null){
            userCart.setNum(userCart.getNum()+1);
        }else {
            userCart = new UserCart();
            userCart.setNum(1);
            userCart.setProductid(productId);
            userCart.setUserid(userId);
            userCart.setCartType(cartType);
            userCart.setId(UUIDUtils.generate());
            userCartMapper.insert(userCart);
        }
        userCartJpa.saveAndFlush(userCart);


        List<UserCart>  userCarts = userCartJpa.findByUserid(userId);
        List<UserCartDTO>  userCartDTOS= new ArrayList<>();
        BeanUtils.copyProperties(userCart,userCartDTOS);
        UserCartList userCartList = new UserCartList();
        userCartList.setUserCartDTOs(userCartDTOS);
        userCartList.setTotalNum(100);
        userCartList.setTotalSum(9999);

        //todo

        return userCartList;
    }
}
