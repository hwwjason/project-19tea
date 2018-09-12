package com.sckj.repository.mybatis;

import com.sckj.model.Coupon;
import com.sckj.model.dto.CouponDTO;
import com.sckj.model.dto.ProductListDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 描述：订单列表DAO 层
 * @author hww
 * @date 2018/09/07
 */
public interface CouponDAO {

    CouponDTO findDTOById(@Param("id")String id);

    Coupon findById(@Param("id")String id);


    /**
    * 描述：查询订单列表列表以及高级搜索(分页)
    * @param page  分页参数
    * @param couponDTO  订单列表DTO
    */
    Page<CouponDTO> findCouponPage(CouponDTO couponDTO, Pageable page);

    List<CouponDTO> getCouponList(Map<String,Object> map);

    int deleteLogicByIds(String[] ids);
}
