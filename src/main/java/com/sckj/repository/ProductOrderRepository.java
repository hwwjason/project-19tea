package com.sckj.repository;
import com.sckj.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
* 描述：订单列表 Repository接口
* @author hww
* @date 2018/09/06
*/
public interface ProductOrderRepository extends JpaRepository<ProductOrder, String> {

    List<ProductOrder> findByBuyuserId(String buyUserId);
}
