package com.sckj.service;

import com.sckj.model.dto.ProductOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* 描述：订单列表 服务实现层接口
* @author hww
* @date 2018/09/06
*/
public interface IProductOrderService  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    ProductOrderDTO findDTOById(String id)throws Exception;

    List<ProductOrderDTO> findProductOrder(ProductOrderDTO productOrderDTO)throws Exception;

    /**
     * 分页查询
     * @param productListMap
     * @return
     * @throws Exception
     */
    List<ProductOrderDTO> getProductOrder(Map<String,Object> productListMap)throws Exception;

    void packageSonOrders(List<ProductOrderDTO> productOrder) throws Exception ;

    List<ProductOrderDTO> findProductOrderByUserID(String userId) throws Exception;

    void deleteById(String id)throws Exception;

    /**
     * 创建临时订单信息（生成数据但是不保存）
     * @param productOrderDTO
     * @return
     * @throws Exception
     */
    ProductOrderDTO createTempProductOrder(ProductOrderDTO productOrderDTO) throws Exception;

    /**
     * 去结算
     * @param productOrderDTO
     * @return
     * @throws Exception
     */
    ProductOrderDTO toAccount(ProductOrderDTO productOrderDTO) throws Exception;

    ProductOrderDTO updateProductOrder(ProductOrderDTO productOrderDTO) throws Exception;

    /**
     * 更新单号状态
     * @param orderId
     * @param userId
     * @param orderStatus
     * @throws Exception
     */
    void updateOrderStatus(String orderId, String userId,String orderStatus) throws Exception;

    /**
     * 发货
     * @param orderId
     * @throws Exception
     */
    void deliverProduct(String orderId) throws Exception;



    Page<ProductOrderDTO> findProductOrderPage(ProductOrderDTO productOrderDTO, Pageable page) throws Exception;

}
