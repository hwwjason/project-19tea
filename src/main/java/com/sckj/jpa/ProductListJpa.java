package com.sckj.jpa;

import com.sckj.pojo.ProductList;
import com.sckj.pojo.SckjUserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@NoRepositoryBean
@Repository
public interface ProductListJpa extends JpaRepository<ProductList,String> {

}
