package com.sckj.repository;
import com.sckj.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
* 描述：用户地址 Repository接口
* @author hww
* @date 2018/09/10
*/
public interface UserAddressRepository extends JpaRepository<UserAddress, String> {

    List<UserAddress> findByUserid(String userid);

}
