package com.sckj.repository;
import com.sckj.model.ProductSonOrder;
import com.sckj.model.dto.ProductSonOrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
* 描述：订单子表 Repository接口
* @author hww
* @date 2018/09/05
*/
public interface ProductSonOrderRepository extends JpaRepository<ProductSonOrder, String> {

     List<ProductSonOrder> findByProductOrderidIn(List<String> productOrderids);

     List<ProductSonOrder> findByProductOrderid(String productOrderid);

     List<ProductSonOrder> findByIdIn(List<String> ids);
}
