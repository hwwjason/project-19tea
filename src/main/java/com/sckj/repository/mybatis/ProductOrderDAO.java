package com.sckj.repository.mybatis;

import com.sckj.model.dto.ProductOrderDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * 描述：订单列表DAO 层
 * @author hww
 * @date 2018/09/06
 */
public interface ProductOrderDAO {

    ProductOrderDTO findDTOById(@Param("id")String id);


    /**
    * 描述：查询订单列表列表以及高级搜索(分页)
    * @param page  分页参数
    * @param productOrderDTO  订单列表DTO
    */
    Page<ProductOrderDTO> findProductOrderPage(ProductOrderDTO productOrderDTO, Pageable page);

}