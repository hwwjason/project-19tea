package com.sckj.service;
import com.sckj.model.CouponUser;
import com.sckj.model.dto.CouponUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* 描述：优惠券用户表 服务实现层接口
* @author hww
* @date 2018/09/10
*/
public interface ICouponUserService  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    CouponUserDTO findDTOById(String id)throws Exception;

    void deleteById(String id)throws Exception;

    CouponUserDTO createCouponUser(CouponUserDTO couponUserDTO) throws Exception;

    CouponUserDTO updateCouponUser(CouponUserDTO couponUserDTO) throws Exception;

    Page<CouponUserDTO> findCouponUserPage(CouponUserDTO couponUserDTO, Pageable page) throws Exception;

    void deleteByIds(List<String> ids)throws Exception;

    List<CouponUserDTO> getCouponUserList(Map<String,Object> couponUserListMap);
}
