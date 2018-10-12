package com.sckj.service.imp;
import com.sckj.enums.OrderEnums.CouponInvalidTypeEnums;
import com.sckj.enums.OrderEnums.CouponTimeTypeEnums;
import com.sckj.exception.BusinessException;
import com.sckj.model.Coupon;
import com.sckj.model.CouponUser;
import com.sckj.model.UserList;
import com.sckj.repository.CouponRepository;
import com.sckj.repository.CouponUserRepository;
import com.sckj.repository.UserListJpa;
import com.sckj.repository.mybatis.CouponDAO;
import com.sckj.service.ICouponUserService;
import com.sckj.repository.mybatis.CouponUserDAO;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.UUIDUtils;
import com.sckj.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sckj.model.dto.CouponUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private CouponDAO couponDAO;

    @Override
    public CouponUserDTO findDTOById(String id) throws Exception {
        CouponUserDTO couponUserDTO = couponUserDAO.findDTOById(id);
        return couponUserDTO;
    }

    @Override
    public CouponUser saveAndFlush(CouponUser couponUser)throws Exception{
       return couponUserRepository.saveAndFlush(couponUser);
    }

//    @Override
//    public CouponUser findById(String id) throws Exception {
//        CouponUser couponUser = couponUserDAO.findById(id);
//        return couponUser;
//    }

    @Override
    public void deleteById(String id) throws Exception {
        couponUserRepository.deleteById(id);
    }

    @Override
    public CouponUserDTO createCouponUser(CouponUserDTO couponUserDTO) throws Exception {
        Coupon coupon = couponDAO.findById(couponUserDTO.getCouponid());
        List<UserList> allUserLists = userListJpa.findAll();
        if("1".equals(couponUserDTO.getIsAllUser())){//发放给所有用户
            addCouponUser(allUserLists,coupon);
        }else{//发放给指定用户
            String userPhoneStr = couponUserDTO.getUserPhoneStr();
            List<String> userPhoneStrList = new ArrayList<>();
            if(userPhoneStr!=null){ //文本不为空，通过文本读取，否者读取文件
                String[] userPhoneStrArr = userPhoneStr.split(",");
                userPhoneStrList =Arrays.asList(userPhoneStrArr);
            }else{//通过读取文件
                //todo
            }
            List<String> userPhoneNotFindStrList = new ArrayList<>();
            List<String> userTels = allUserLists.stream().map(e->e.getTel()).collect(Collectors.toList());
            for (String s : userPhoneStrList) {
                if(!userTels.contains(s)){
                    userPhoneNotFindStrList.add(s);
                }
            }
            if(userPhoneNotFindStrList!=null && userPhoneNotFindStrList.size()>0){
                throw new BusinessException("发放失败，请校验"+ userPhoneNotFindStrList.toString() +"电话号码");
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
        if( couponNum != 0 && couponNum < userLists.size()){
            throw new BusinessException("优惠券数量不足! 当前人数"+userLists.size()+"(人)，优惠券数量"+couponNum+"(张)");
        }
        List<CouponUser> couponUsers = new ArrayList<>();
        for (UserList list : userLists) {
            CouponUser couponUser = new CouponUser();
            couponUser.setReceivetime(DateTimeUtils.getCurrentDate());
            couponUser.setId(UUIDUtils.generate());
            couponUser.setCouponid(coupon.getId());
            couponUser.setIsuse("0");
            couponUser.setTel(list.getTel());
            //根据时间类型设置开始和结束时间
            if(CouponTimeTypeEnums.DSYS.toString().equals(coupon.getTimeType())){
                couponUser.setRealstarttime(DateTimeUtils.getCurrentDate());
                couponUser.setRealendtime(DateTimeUtils.getDate(coupon.getDays()));
            }else{
                couponUser.setRealstarttime(coupon.getStarttime());
                couponUser.setRealendtime(coupon.getEndtime());
            }
            couponUser.setUserid(list.getUserId());
            couponUsers.add(couponUser);
        }
        coupon.setGive(coupon.getGive()+userLists.size());
        couponRepository.saveAndFlush(coupon);
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

    @Override
    public List<CouponUserDTO> getCouponUserIsInvalid(Map<String,Object> map, String isInvalid) {
        List<CouponUserDTO> couponUserDTOS = getCouponUserList(map);
        List<CouponUserDTO> couponUser = new ArrayList<>();
        if("1".equals(isInvalid)){//已经失效(已经过期，已经使用的)
            for (CouponUserDTO coupon : couponUserDTOS) {
                if("1".equals(coupon.getIsuse())){//已经使用
                    couponUser.add(coupon);
                    coupon.setInvalidType(CouponInvalidTypeEnums.USED.toString());
                }else{//未使用
                    if((coupon.getRealendtime()==null || coupon.getRealstarttime()==null)||(coupon.getRealendtime().getTime()>DateTimeUtils.getCurrentDate().getTime() ||
                            coupon.getRealstarttime().getTime()<DateTimeUtils.getCurrentDate().getTime())){//已经过期 未使用
                        couponUser.add(coupon);
                        coupon.setInvalidType(CouponInvalidTypeEnums.TIME_OUT.toString());
                    }
                }
            }
        }else {//0 未失效
            for (CouponUserDTO coupon : couponUserDTOS) {
                if("0".equals(coupon.getIsuse())){//未使用
                    if(coupon.getRealendtime()!=null && (coupon.getRealendtime().getTime()>= DateTimeUtils.getCurrentDate().getTime())){//未过期,不用管开始时间
                        couponUser.add(coupon);
                    }
                }
            }
        }
        return couponUser;
    }
}



