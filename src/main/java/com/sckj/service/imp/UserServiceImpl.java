package com.sckj.service.imp;

import com.sckj.constant.OrderStatusConstants;
import com.sckj.model.dto.CouponUserDTO;
import com.sckj.model.dto.PersonalInformationDTO;
import com.sckj.model.dto.ProductOrderDTO;
import com.sckj.model.dto.UserCartDTO;
import com.sckj.model.model.UserCartList;
import com.sckj.repository.mybatis.UserInfoDAO;
import com.sckj.service.ICouponUserService;
import com.sckj.service.IProductOrderService;
import com.sckj.service.IUserCartService;
import com.sckj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

 
    @Autowired
    private UserInfoDAO userDao;//这里可能会报错，但是并不会影响

    @Autowired
    private IProductOrderService productOrderService;

    @Autowired
    private IUserCartService userCartService;

    @Autowired
    private ICouponUserService couponUserService;

    @Override
    public List findAllUser(){
       return  userDao.findAllUser();
    }

    @Override
    public PersonalInformationDTO getPersonalInformation(String userId,String cartType) throws Exception {
        PersonalInformationDTO personalInformationDTO = new PersonalInformationDTO();
        int waitingToPayNum = getOrderNumByUserIDAndOrderStatus(userId,OrderStatusConstants.WATING_TO_PAY);
        int waitForReceivingNum = getOrderNumByUserIDAndOrderStatus(userId,OrderStatusConstants.WAIT_FOR_RECEIVING);
        int afterSalingNum = getOrderNumByUserIDAndOrderStatus(userId,OrderStatusConstants.AFTER_SALING);
        personalInformationDTO.setWatingToPayNum(waitingToPayNum);
        personalInformationDTO.setWaitForReceivingNum(waitForReceivingNum);
        personalInformationDTO.setAfterSalingNum(afterSalingNum);

        //购物车数量
        UserCartList userCartList = userCartService.getUserCart(userId,cartType);
        List<UserCartDTO> userCarts = userCartList.getUserCarts();
        int userCartNum = 0 ;
        if(userCarts!=null){
            for (UserCartDTO cart : userCarts) {
                userCartNum +=  cart.getNum();
            }
        }
        personalInformationDTO.setUsetCartNum(userCartNum);

        //优惠券
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userId);
        List<CouponUserDTO> couponUserDTOS = couponUserService.getCouponUserIsInvalid(map,"0");
        int couponUserNum = couponUserDTOS==null ? 0 : couponUserDTOS.size();
        personalInformationDTO.setCouponUserNum(couponUserNum);

        return personalInformationDTO;
    }

    private int getOrderNumByUserIDAndOrderStatus(String userId,List<String> orderStatus) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("orderStatusList", orderStatus);
        map.put("buyuserId",userId);
        List<ProductOrderDTO> productOrderWaitingToPay = productOrderService.getProductOrder(map);
        return productOrderWaitingToPay==null?0:productOrderWaitingToPay.size();
    }
}
