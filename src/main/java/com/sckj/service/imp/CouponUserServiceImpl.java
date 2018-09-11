package com.sckj.service.imp;
import com.sckj.enums.OrderEnums.CouponTimeTypeEnums;
import com.sckj.exception.BusinessException;
import com.sckj.model.Coupon;
import com.sckj.model.CouponUser;
import com.sckj.model.UserList;
import com.sckj.repository.CouponRepository;
import com.sckj.repository.CouponUserRepository;
import com.sckj.repository.UserListJpa;
import com.sckj.service.ICouponUserService;
import com.sckj.repository.mybatis.CouponUserDAO;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.UUIDUtils;
import com.sckj.utils.BeanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sckj.model.dto.CouponUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
* 描述：优惠券用户表 服务实现层
* @author hww
* @date 2018/09/10
*/
@Service
public class CouponUserServiceImpl implements ICouponUserService {

    @Autowired
    private CouponUserDAO couponUserDAO;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponUserRepository couponUserRepository;

    @Autowired
    private UserListJpa userListJpa;

    @Override
    public CouponUserDTO findDTOById(String id) throws Exception {
        CouponUserDTO couponUserDTO = couponUserDAO.findDTOById(id);
        return couponUserDTO;
    }

    @Override
    public void deleteById(String id) throws Exception {
        couponUserRepository.deleteById(id);
    }

    @Override
    public CouponUserDTO createCouponUser(CouponUserDTO couponUserDTO) throws Exception {
        Coupon coupon = couponRepository.getOne(couponUserDTO.getCouponid());
        if("1".equals(couponUserDTO.getIsAllUser())){//发放给所有用户
            List<UserList> userLists = userListJpa.findAll();
            addCouponUser(userLists,coupon);
        }else{//发放给指定用户
            String userPhoneStr = couponUserDTO.getUserPhoneStr();
            List<String> userPhoneStrList = new ArrayList<>();
            if(userPhoneStr!=null){ //文本不为空，通过文本读取，否者读取文件
                String[] userPhoneStrArr = userPhoneStr.split(",");
                userPhoneStrList =Arrays.asList(userPhoneStrArr);
            }else{//通过读取文件
                //todo
            }
            //todo 优化提示
            List<UserList> userLists = userListJpa.findByTelIn(userPhoneStrList);
            addCouponUser(userLists,coupon);
        }
        return null;
    }

    /**
     * 批量添加优惠券
     * @param userLists
     * @param coupon
     */
    private void addCouponUser(List<UserList> userLists, Coupon coupon ){
        int couponNum = coupon.getStock()-coupon.getGive();
        if(couponNum < userLists.size()){
            throw new BusinessException("优惠券数量不足! 当前人数"+userLists.size()+"(人)，优惠券数量"+couponNum+"(张)");
        }
        List<CouponUser> couponUsers = new ArrayList<>();
        for (UserList list : userLists) {
            CouponUser couponUser = new CouponUser();
            couponUser.setReceivetime(DateTimeUtils.getCurrentDate());
            couponUser.setId(UUIDUtils.generate());
            couponUser.setCouponid(coupon.getId());
            couponUser.setIsuse("0");
            //根据时间类型设置开始和结束时间
            if(CouponTimeTypeEnums.DSYS.toString().equals(coupon.getTimeType())){
                couponUser.setRealendtime(DateTimeUtils.getCurrentDate());
                couponUser.setRealstarttime(DateTimeUtils.getDate(coupon.getDays()));
            }else{
                couponUser.setRealendtime(coupon.getStarttime());
                couponUser.setRealstarttime(coupon.getEndtime());
            }
            couponUser.setUserid(list.getId());
            couponUsers.add(couponUser);
        }
        couponUserRepository.saveAll(couponUsers);
    }

    @Override
    public CouponUserDTO updateCouponUser(CouponUserDTO couponUserDTO)throws Exception {
        CouponUser couponUser = new CouponUser();
        BeanUtils.copyPropertiesWithoutNull(couponUser,couponUserDTO);
        couponUser = couponUserRepository.saveAndFlush(couponUser);
        return this.findDTOById(couponUser.getId());
    }

    /**
    * 描述：查询列表(分页)
    * @param couponUserDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<CouponUserDTO> findCouponUserPage(CouponUserDTO couponUserDTO, Pageable page) throws Exception{
        return couponUserDAO.findCouponUserPage(couponUserDTO,page);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) throws Exception {
        couponUserRepository.deleteByIdIn(ids);
    }

    @Override
    public List<CouponUserDTO> getCouponUserList(Map<String,Object> map) {
        List<CouponUserDTO> lists = couponUserDAO.getCouponUserList(map);
        return lists;
    }
}



