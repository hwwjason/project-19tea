package com.sckj.jpa;

import com.sckj.pojo.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@NoRepositoryBean
@Repository
public interface ProductListJpa extends JpaRepository<ProductList,String> {

}
