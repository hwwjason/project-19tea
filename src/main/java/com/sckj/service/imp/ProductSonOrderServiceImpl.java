package com.sckj.service.imp;
import com.sckj.model.ProductSonOrder;
import com.sckj.repository.ProductSonOrderRepository;
import com.sckj.service.IProductSonOrderService;
import com.sckj.repository.mybatis.ProductSonOrderDAO;
import com.sckj.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sckj.model.dto.ProductSonOrderDTO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* 描述：订单子表 服务实现层
* @author hww
* @date 2018/09/05
*/
@Service
public class ProductSonOrderServiceImpl  implements IProductSonOrderService {

    @Autowired
    private ProductSonOrderDAO productSonOrderDAO;

    @Autowired
    private ProductSonOrderRepository productSonOrderRepository;

    @Override
    public ProductSonOrderDTO findDTOById(String id) throws Exception {
        ProductSonOrderDTO productSonOrderDTO = productSonOrderDAO.findDTOById(id);
        return productSonOrderDTO;
    }

    @Override
    public  List<ProductSonOrder> findByIds(List<String> ids)throws Exception{
        return productSonOrderRepository.findByIdIn(ids);
    }

    @Override
    public  List<ProductSonOrder> findByProductOrderids(List<String> ids)throws Exception{
        return productSonOrderRepository.findByProductOrderidIn(ids);
    }

    @Override
    public void deleteById(String id) throws Exception {
        productSonOrderRepository.deleteById(id);
    }



    @Override
    public ProductSonOrderDTO createProductSonOrder(ProductSonOrderDTO productSonOrderDTO) throws Exception {
        ProductSonOrder productSonOrder = new ProductSonOrder();
        BeanUtils.copyProperties(productSonOrder,productSonOrderDTO);
        //productSonOrder.setStatus(StatusEnum.ENABLE.toString());
        productSonOrder.setCreateTime(DateTimeUtils.getCurrentDate());
        productSonOrder = productSonOrderRepository.saveAndFlush(productSonOrder);
        return this.findDTOById(productSonOrder.getId());
    }

    @Override
    public ProductSonOrderDTO updateProductSonOrder(ProductSonOrderDTO productSonOrderDTO)throws Exception {
        ProductSonOrder productSonOrder = new ProductSonOrder();
        BeanUtils.copyProperties(productSonOrder,productSonOrderDTO);
        productSonOrder = productSonOrderRepository.saveAndFlush(productSonOrder);
        return this.findDTOById(productSonOrder.getId());
    }

    /**
    * 描述：查询列表(分页)
    * @param productSonOrderDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<ProductSonOrderDTO> findProductSonOrderPage(ProductSonOrderDTO productSonOrderDTO, Pageable page) throws Exception{
        return productSonOrderDAO.findProductSonOrderPage(productSonOrderDTO,page);
    }

}



