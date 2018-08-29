package com.sckj.jpa;

import com.sckj.pojo.ProductListWithBLOBs;
import com.sckj.pojo.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@NoRepositoryBean
@Repository
public interface UserCartJpa extends JpaRepository<UserCart,String> {
    UserCart findByUseridAndProductid(String userid,String productid);

    List<UserCart> findByUserid(String userid);
}
