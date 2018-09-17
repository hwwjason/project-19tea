package com.sckj.repository.mybatis;

import com.sckj.model.CouponUser;
import com.sckj.model.dto.CouponUserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 描述：优惠券用户表DAO 层
 * @author hww
 * @date 2018/09/10
 */
public interface CouponUserDAO {

    CouponUserDTO findDTOById(@Param("id")String id);

    CouponUser findById(@Param("id")String id);

    /**
    * 描述：查询优惠券用户表列表以及高级搜索(分页)
    * @param page  分页参数
    * @param couponUserDTO  优惠券用户表DTO
    */
    Page<CouponUserDTO> findCouponUserPage(CouponUserDTO couponUserDTO, Pageable page);

    List<CouponUserDTO> getCouponUserByUserId(@Param("userid") String userid);

    List<CouponUserDTO> getCouponUserList(Map<String,Object> map);

}
