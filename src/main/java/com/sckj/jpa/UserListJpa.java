package com.sckj.jpa;

import com.sckj.pojo.SckjUserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@NoRepositoryBean
@Repository
public interface UserListJpa  extends JpaRepository<SckjUserList,String> {

    List<SckjUserList> findByUnionid(String unionid);

    List<SckjUserList> findByUserId(String userId);

    List<SckjUserList> findByOpenid(String openid);


    @Modifying
    @Query("update SckjUserList set tel=:tel where user_id = :userId")
    int updateByUserId(@Param(value = "tel") String tel,@Param("userId") String userId);
}
