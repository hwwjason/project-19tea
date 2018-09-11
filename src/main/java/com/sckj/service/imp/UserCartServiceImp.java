package com.sckj.service.imp;

import com.sckj.repository.mybatis.ProductListMapper;
import com.sckj.repository.mybatis.UserCartMapper;
import com.sckj.repository.mybatis.UserListDAO;
import com.sckj.model.dto.UserCartDTO;
import com.sckj.model.dto.UserCartList;
import com.sckj.enums.ProductShelvesEnum;
import com.sckj.exception.BusinessException;
import com.sckj.repository.ProductListJpa;
import com.sckj.repository.UserCartJpa;
import com.sckj.repository.UserListJpa;
import com.sckj.model.ProductList;
import com.sckj.model.UserList;
import com.sckj.model.UserCart;
import com.sckj.service.IUserCartService;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
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

    @Autowired
    private ProductListJpa productListJpa;

    @Autowired
    private ProductListMapper productListMapper;

    @Autowired
    private UserListDAO userListDAO;

    @Autowired
    private UserListJpa userListJpa;

    @Override
    public UserCartList changeGoods(String productId, String userId, String cartType,int count) {
        //查询商品是否已经存在
        List<String> ids = new ArrayList<>();
        ids.add(productId);
        ProductList productList = productListMapper.getOne(productId);
        if(productList==null){
            throw new BusinessException("商品已被删除，无法加入购物车");
        }

        //查询用户是否已经收藏这个商品了
        List<UserList> userList =  userListJpa.findByUserId(userId);
        if(userList ==null || userList.size()==0){
            throw new BusinessException("用户信息出错，请联系管理员");
        }

        //查询用户是否已经收藏这个商品了
        UserCart userCart =  userCartJpa.findByUseridAndProductid(userId,productId);
        if(count<0 && userCart.getNum()<=1){
            throw new BusinessException("无法继续减少数量");
        }
        if(userCart!=null){
            userCart.setNum(userCart.getNum()+count);
        }else {
            userCart = new UserCart();
            userCart.setNum(1);
            userCart.setStatus("1");
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
    public UserCartList removeGoods( List<String> ids, String userId, String cartType ) {
        //查询用户是否已经收藏这个商品了
//        userCartJpa.deleteByIdIn(ids);
        userCartJpa.deleteInBatch(userCartJpa.findAllById(ids));
        return getUserList(userId,cartType);
    }

    @Override
    public void deleteByIds( List<String> ids) {
        //查询用户是否已经收藏这个商品了
//        userCartJpa.deleteByIdIn(ids);
        userCartJpa.deleteInBatch(userCartJpa.findAllById(ids));
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
        if(sourceUserCart==null || sourceUserCart.size()<=0){
            throw new BusinessException("出现异常请联系管理员");
        }
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
                if(cartDTO.getPrice()!=null && cartDTO.getStatus().equals("1")){
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
