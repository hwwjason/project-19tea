package com.sckj.service.imp;

import com.sckj.dao.UserCartMapper;
import com.sckj.dto.UserCartDTO;
import com.sckj.dto.UserCartList;
import com.sckj.enums.ProductShelvesEnum;
import com.sckj.jpa.UserCartJpa;
import com.sckj.pojo.UserCart;
import com.sckj.service.IUserCartService;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserCartServiceImp implements IUserCartService {

    @Autowired
    private UserCartMapper userCartMapper;

    @Autowired
    private UserCartJpa userCartJpa;

    @Override
    public UserCartList changeGoods(String productId, String userId, String cartType,int count) {
        //查询用户是否已经收藏这个商品了
        UserCart userCart =  userCartJpa.findByUseridAndProductid(userId,productId);
        if(userCart!=null){
            userCart.setNum(userCart.getNum()+count);
        }else {
            userCart = new UserCart();
            userCart.setNum(1);
            userCart.setProductid(productId);
            userCart.setUserid(userId);
            userCart.setCartType(cartType);
            userCart.setId(UUIDUtils.generate());
        }
        userCartJpa.saveAndFlush(userCart);

        //获取该用户购物车所有商品
       return getUserList(userId,cartType);
    }

    @Override
    public UserCartList removeGoods(String productId, String userId, String cartType ) {
        //查询用户是否已经收藏这个商品了
        UserCart userCart =  userCartJpa.findByUseridAndProductidAndCartType(userId,productId,cartType);
        if (userCart!=null){
            userCartJpa.delete(userCart);
        }
        return getUserList(userId,cartType);
    }

    @Override
    public UserCartList emptyOutGoods(String userId, String cartType ) {
        List<UserCartDTO> userCartDTO = userCartMapper.getUserCartList(userId,cartType);
        List<UserCart> outGoods = new ArrayList<>();
        for (UserCartDTO cartDTO : userCartDTO) {
            if(ProductShelvesEnum.RemoveShelf.toString().equals(cartDTO.getIsShelves())){
                outGoods.add(cartDTO);
            }
        }
        userCartJpa.deleteInBatch(outGoods);
        return getUserList(userId,cartType);
    }

    @Override
    public UserCartList getUserCart( String userId, String cartType) {
        UserCartList userCartList = getUserList(userId,cartType);
        return userCartList;
    }

    @Override
    public UserCartList updateProduct( List<UserCartDTO> userCartDTOs){
        List<String> ids = new ArrayList<>();
        Map<String,String> idStatusMap = new HashMap<>();
        for (UserCartDTO cartDTO : userCartDTOs) {
            ids.add(cartDTO.getId());
            idStatusMap.put(cartDTO.getId(),cartDTO.getStatus());
        }
        List<UserCart> sourceUserCart =userCartJpa.findAllById(ids);
        List<UserCart> changedUserCart = new ArrayList<>();

        for (UserCart userCart : sourceUserCart) {
            String status = idStatusMap.get(userCart.getId());
            if(StringUtils.isNotEmpty(status)){
                userCart.setStatus(status);
                changedUserCart.add(userCart);
            }
        }

        UserCart userCart = sourceUserCart.get(0);
        userCartJpa.saveAll(changedUserCart);
        return getUserList(userCart.getUserid(),userCart.getCartType());
    }




    private  UserCartList getUserList(String userId, String cartType){
        //获取该用户购物车所有商品
        List<UserCartDTO> UserCartDTO = userCartMapper.getUserCartList(userId,cartType);
        List<UserCartDTO> UserCart = new ArrayList<>();
        List<UserCartDTO> UserCartOut = new ArrayList<>();
        UserCartList userCartList = new UserCartList();
        BigDecimal totalSum =new BigDecimal(0) ;
        for (UserCartDTO cartDTO : UserCartDTO) {
            if(ProductShelvesEnum.RemoveShelf.toString().equals(cartDTO.getIsShelves())){
                UserCartOut.add(cartDTO);
            }else {
                UserCart.add(cartDTO);
                if(cartDTO.getPrice()!=null&&cartDTO.getStatus().equals("1")){
                    int num = cartDTO.getNum();
                    BigDecimal bnum = new BigDecimal(num);
                    totalSum =  totalSum.add(cartDTO.getPrice().multiply(bnum));
                }
            }
        }
        userCartList.setUserCartOuts(UserCartOut);
        userCartList.setUserCarts(UserCart);
        userCartList.setTotalSum(totalSum);
        return userCartList;
    }
}
