package com.sckj.repository;

import com.sckj.model.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@NoRepositoryBean
@Repository
public interface ProductListJpa extends JpaRepository<ProductList,String> {

}
