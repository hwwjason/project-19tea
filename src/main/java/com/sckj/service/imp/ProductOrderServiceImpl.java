package com.sckj.service.imp;

import com.sckj.model.ProductList;
import com.sckj.model.ProductOrder;
import com.sckj.model.ProductSonOrder;
import com.sckj.model.dto.ProductSonOrderDTO;
import com.sckj.repository.ProductListJpa;
import com.sckj.repository.ProductOrderRepository;
import com.sckj.repository.ProductSonOrderRepository;
import com.sckj.repository.mybatis.ProductListMapper;
import com.sckj.service.IProductOrderService;
import com.sckj.repository.mybatis.ProductOrderDAO;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.JsonUtils;
import com.sckj.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sckj.model.dto.ProductOrderDTO;
import com.sckj.utils.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
* 描述：订单列表 服务实现层
* @author hww
* @date 2018/09/06
*/
@Service
public class ProductOrderServiceImpl implements IProductOrderService {

    @Autowired
    private ProductOrderDAO productOrderDAO;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductSonOrderRepository productSonOrderRepository;

    @Autowired
    private ProductListJpa productListJpa;

    @Autowired
    private ProductListMapper productListMapper;

    @Override
    public ProductOrderDTO findDTOById(String id) throws Exception {
        ProductOrderDTO productOrderDTO = productOrderDAO.findDTOById(id);
        List<ProductSonOrder> productSonOrder = productSonOrderRepository.findByProductOrderid(id);
        List<ProductSonOrderDTO> productSonOrderDTO = new ArrayList<>();
        for (ProductSonOrder sonOrder : productSonOrder) {
            ProductSonOrderDTO sonOrderDTO = new ProductSonOrderDTO();
            BeanUtils.copyProperties(sonOrderDTO,sonOrder);
            productSonOrderDTO.add(sonOrderDTO);
        }
        BeanUtils.copyProperties(productSonOrderDTO,productSonOrder);
        productOrderDTO.setProductSonOrderDTO(productSonOrderDTO);
        return productOrderDTO;
    }

    @Override
    public void deleteById(String id) throws Exception {
        productOrderRepository.deleteById(id);
    }

    @Override
    public ProductOrderDTO createProductOrder(ProductOrderDTO productOrderDTO) throws Exception {
        ProductOrder productOrder = new ProductOrder();
        BeanUtils.copyProperties(productOrder,productOrderDTO);
        productOrder.setId(UUIDUtils.generate());
        productOrder.setCreatetime(DateTimeUtils.getCurrentDate());
        productOrder = productOrderRepository.saveAndFlush(productOrder);

        //保存子订单
        List<ProductSonOrderDTO> productSonOrderDTOs =productOrderDTO.getProductSonOrderDTO();
        List<ProductSonOrder> productSonOrders = new ArrayList<>();
        for (ProductSonOrderDTO order: productSonOrderDTOs) {
            ProductSonOrder sonOrder = new ProductSonOrder();
            BeanUtils.copyProperties(sonOrder,order);

            sonOrder.setProductOrderid(productOrder.getId());
            String productId = sonOrder.getProductid();
            ProductList productList = productListMapper.getOne(productId);
            String json =  JsonUtils.object2Json(productList);
            sonOrder.setSnapshot(json);
            sonOrder.setId(UUIDUtils.generate());
            sonOrder.setCreateTime(DateTimeUtils.getCurrentDate());

            productSonOrders.add(sonOrder);
        }
        productSonOrderRepository.saveAll(productSonOrders);

        return this.findDTOById(productOrder.getId());
    }

    @Override
    public ProductOrderDTO updateProductOrder(ProductOrderDTO productOrderDTO)throws Exception {
        ProductOrder productOrder = new ProductOrder();
        BeanUtils.copyPropertiesWithoutNull(productOrder,productOrderDTO);
        productOrder = productOrderRepository.saveAndFlush(productOrder);
        return this.findDTOById(productOrder.getId());
    }

    /**
    * 描述：查询列表(分页)
    * @param productOrderDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<ProductOrderDTO> findProductOrderPage(ProductOrderDTO productOrderDTO, Pageable page) throws Exception{
        return productOrderDAO.findProductOrderPage(productOrderDTO,page);
    }

}



