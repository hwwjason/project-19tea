package com.sckj.repository;

import com.sckj.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
* 描述：订单列表 Repository接口
* @author hww
* @date 2018/09/07
*/
public interface CouponRepository extends JpaRepository<Coupon, String> {
    void deleteByIdIn(List<String> ids);
}
