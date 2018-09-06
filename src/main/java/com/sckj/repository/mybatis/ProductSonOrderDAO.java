package com.sckj.repository.mybatis;

import com.sckj.model.dto.ProductSonOrderDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * 描述：订单子表DAO 层
 * @author hww
 * @date 2018/09/05
 */

public interface ProductSonOrderDAO {

    ProductSonOrderDTO findDTOById(@Param("id")String id);


    /**
    * 描述：查询订单子表列表以及高级搜索(分页)
    * @param page  分页参数
    * @param productSonOrderDTO  订单子表DTO
    */
    Page<ProductSonOrderDTO> findProductSonOrderPage(ProductSonOrderDTO productSonOrderDTO, Pageable page);

}
