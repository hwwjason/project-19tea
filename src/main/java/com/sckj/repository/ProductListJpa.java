package com.sckj.repository;

import com.sckj.model.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@NoRepositoryBean
@Repository
public interface ProductListJpa extends JpaRepository<ProductList,String> {
    List<ProductList> findByIdIn(List<String> ids);
}
