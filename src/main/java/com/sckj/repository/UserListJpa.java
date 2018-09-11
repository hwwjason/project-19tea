package com.sckj.repository;

import com.sckj.model.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@NoRepositoryBean
@Repository
public interface UserListJpa  extends JpaRepository<UserList,String> {

    List<UserList> findByUnionid(String unionid);

    List<UserList> findByUserId(String userId);

    List<UserList> findByOpenid(String openid);

    List<UserList> findByTelIn(List<String> tels);

    @Modifying
    @Query("update UserList set tel=:tel where user_id = :userId")
    int updateByUserId(@Param(value = "tel") String tel,@Param("userId") String userId);
}
