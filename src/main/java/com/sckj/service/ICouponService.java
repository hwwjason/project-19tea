package com.sckj.service;
import com.sckj.model.Coupon;
import com.sckj.model.dto.CouponDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* 描述：订单列表 服务实现层接口
* @author hww
* @date 2018/09/07
*/
public interface ICouponService  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    CouponDTO findDTOById(String id)throws Exception;

    void deleteById(String id)throws Exception;

    void deleteByIds(List<String> ids)throws Exception;

    CouponDTO createCoupon(CouponDTO couponDTO) throws Exception;

    CouponDTO updateCoupon(CouponDTO couponDTO) throws Exception;

    Page<CouponDTO> findCouponPage(CouponDTO couponDTO, Pageable page) throws Exception;

    List<CouponDTO> getProductList(Map<String,Object> productListMap);

}
