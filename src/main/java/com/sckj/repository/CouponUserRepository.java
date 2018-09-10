package com.sckj.repository;
import com.sckj.model.CouponUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
* 描述：优惠券用户表 Repository接口
* @author hww
* @date 2018/09/10
*/
public interface CouponUserRepository extends JpaRepository<CouponUser, String> {

    //根据用户id，获取优惠券

    List<CouponUser> findByUserid(String userId);

}
