package com.sckj.service;
import com.sckj.model.ProductSonOrder;
import com.sckj.model.dto.ProductSonOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* 描述：订单子表 服务实现层接口
* @author hww
* @date 2018/09/05
*/
public interface IProductSonOrderService {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    ProductSonOrderDTO findDTOById(String id)throws Exception;

    List<ProductSonOrder> findByIds(List<String> ids)throws Exception;

    List<ProductSonOrder> findByProductOrderids(List<String> ids)throws Exception;

    void deleteById(String id)throws Exception;

    ProductSonOrderDTO createProductSonOrder(ProductSonOrderDTO productSonOrderDTO) throws Exception;

    ProductSonOrderDTO updateProductSonOrder(ProductSonOrderDTO productSonOrderDTO) throws Exception;

    Page<ProductSonOrderDTO> findProductSonOrderPage(ProductSonOrderDTO productSonOrderDTO, Pageable page) throws Exception;

}
