package com.sckj.service.imp;

import com.sckj.model.ProductList;
import com.sckj.model.ProductOrder;
import com.sckj.model.ProductSonOrder;
import com.sckj.model.dto.ProductOrderDTO;
import com.sckj.model.dto.ProductSonOrderDTO;
import com.sckj.model.dto.UserCartDTO;
import com.sckj.model.dto.UserCartList;
import com.sckj.repository.ProductListJpa;
import com.sckj.repository.ProductOrderRepository;
import com.sckj.repository.ProductSonOrderRepository;
import com.sckj.repository.mybatis.ProductListMapper;
import com.sckj.repository.mybatis.ProductOrderDAO;
import com.sckj.service.IProductOrderService;
import com.sckj.service.IUserCartService;
import com.sckj.utils.BeanUtils;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private IUserCartService userCartService;



    @Override
    public ProductOrderDTO findDTOById(String id) throws Exception {
        ProductOrderDTO productOrderDTO = productOrderDAO.findDTOById(id);
        if(productOrderDTO==null){
           return null;
        }
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
    public List<ProductOrderDTO> findProductOrder(ProductOrderDTO productOrderDTO) throws Exception {
        List<ProductOrder> productOrder = productOrderRepository.findByBuyuserId(productOrderDTO.getBuyuserId());
        List<ProductOrderDTO> productOrderDTOS = new ArrayList<>();
        for (ProductOrder order : productOrder) {
            ProductOrderDTO p = new ProductOrderDTO();
            BeanUtils.copyProperties(p,order);
            productOrderDTOS.add(p);
        }

        for (ProductOrderDTO order : productOrderDTOS) {
            List<ProductSonOrder>  productSonOrders = productSonOrderRepository.findByProductOrderid(order.getId());
            order.setProductSonOrder(productSonOrders);
        }
        return productOrderDTOS;
    }


    public List<ProductOrderDTO> findProductOrderByUserID(String id) throws Exception{
        List<ProductOrder> productOrder = productOrderRepository.findByBuyuserId(id);
        List<ProductOrderDTO> productOrderDTOS = new ArrayList<>();
        for (ProductOrder order : productOrder) {
            ProductOrderDTO p = new ProductOrderDTO();
            BeanUtils.copyProperties(p,order);
            productOrderDTOS.add(p);
        }

        for (ProductOrderDTO order : productOrderDTOS) {
            List<ProductSonOrder>  productSonOrders = productSonOrderRepository.findByProductOrderid(order.getId());
            order.setProductSonOrder(productSonOrders);
        }
        return productOrderDTOS;
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
        UserCartList userCartList = userCartService.getUserCart(productOrderDTO.getBuyuserId(),productOrderDTO.getCartType());
        List<UserCartDTO> userCarts = userCartList.getUserCarts();
        List<String> productIds = userCarts.stream().filter(e->"1".equals(e.getStatus())).map(e->e.getProductid()).collect(Collectors.toList());
        List<ProductSonOrder> productSonOrders = new ArrayList<>();

        for (String productId: productIds) {
            ProductSonOrder sonOrder = new ProductSonOrder();
            sonOrder.setProductOrderid(productOrder.getId());
            ProductList productList = productListMapper.getOne(productId);
            //String json =  JsonUtils.object2Json(productList);
            //sonOrder.setSnapshot(json);
            sonOrder.setId(UUIDUtils.generate());
            sonOrder.setCreateTime(DateTimeUtils.getCurrentDate());
            productSonOrders.add(sonOrder);
        }
        productSonOrderRepository.saveAll(productSonOrders);
        userCartService.deleteByIds(productIds);
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


