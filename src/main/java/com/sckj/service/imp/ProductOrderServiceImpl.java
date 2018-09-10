package com.sckj.service.imp;

import com.sckj.enums.OrderEnums.CouponTypeEnums;
import com.sckj.enums.OrderEnums.OrderStatusEnums;
import com.sckj.model.*;
import com.sckj.model.dto.*;
import com.sckj.repository.*;
import com.sckj.repository.mybatis.CouponUserDAO;
import com.sckj.repository.mybatis.ProductListMapper;
import com.sckj.repository.mybatis.ProductOrderDAO;
import com.sckj.repository.mybatis.ProductSonOrderDAO;
import com.sckj.service.IProductOrderService;
import com.sckj.service.IUserCartService;
import com.sckj.utils.BeanUtils;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.JsonUtils;
import com.sckj.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private ProductSonOrderDAO productSonOrderDAO;

    @Autowired
    private ProductListJpa productListJpa;

    @Autowired
    private ProductListMapper productListMapper;

    @Autowired
    private IUserCartService userCartService;

    @Autowired
    private CouponUserRepository couponUserRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponUserDAO couponUserDAO;

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
        //List<ProductSonOrderDTO> productSonOrderDTO = productSonOrderDAO.findDTOByProductOrderid(id);
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

        //获取购物车列表
        UserCartList userCartList = userCartService.getUserCart(productOrderDTO.getBuyuserId(),productOrderDTO.getCartType());
        List<UserCartDTO> userCarts = userCartList.getUserCarts();

        Map<String,UserCartDTO> productMap = userCarts.stream().filter(e->"1".equals(e.getStatus())).collect(Collectors.toMap(UserCartDTO::getProductid,UserCartDTO->UserCartDTO));

        List<ProductSonOrder> productSonOrders = new ArrayList<>();

        BigDecimal productPrice = new BigDecimal(0);
        List<ProductList> productLists = new ArrayList<>();
        for(Map.Entry<String,UserCartDTO> entry :productMap.entrySet()){
            String productId = entry.getKey();
            UserCartDTO userCartDTO = entry.getValue();
            ProductList productList = productListMapper.getOne(productId);
            productLists.add(productList);//可优化
            ProductSonOrder sonOrder = new ProductSonOrder();
            sonOrder.setId(UUIDUtils.generate());
            sonOrder.setProductOrderid(productOrder.getId());
            sonOrder.setProductid(productId);
            sonOrder.setProductPrice(productList.getPrice());
            productPrice = productPrice.add(productList.getPrice().multiply(new BigDecimal(userCartDTO.getNum())));
            sonOrder.setExpressStatus(OrderStatusEnums.NO_PAY.toString());//!!!
            sonOrder.setPrice(productList.getOriginalPrice());
            sonOrder.setBuynum(userCartDTO.getNum());
            sonOrder.setCreateTime(DateTimeUtils.getCurrentDate());
            sonOrder.setCode(productList.getCode());
            sonOrder.setImg(productList.getImg());
            sonOrder.setTitle(productList.getTitle());
            sonOrder.setSpecification(productList.getSpecification());
            productSonOrders.add(sonOrder);
        }



        productSonOrderRepository.saveAll(productSonOrders);
        ProductOrderDTO pDto =this.findDTOById(productOrder.getId());


        String userId =productOrderDTO.getBuyuserId();
        BigDecimal discountMoney = calculateCoupon(pDto,productPrice,userId,productLists);

        List<UserAddress> userAddresss = userAddressRepository.findByUserid(productOrderDTO.getBuyuserId());
        pDto.setUserAddresss(userAddresss);
        pDto.setProductPrice(productPrice);
        //邮费怎么算！！
        BigDecimal expressPrice = new BigDecimal(10);
        pDto.setExpressPrice(expressPrice);

        //优惠券优惠价格
        pDto.setTotalPrice(productPrice.add(expressPrice).subtract(discountMoney));

        return pDto;
    }

    /**
     * 设置最优优惠券，以及优惠金额
     * @param pDto
     * @param productPrice
     * @param userId
     * @param productLists
     * @return 优惠金额
     */
    private BigDecimal calculateCoupon( ProductOrderDTO pDto ,BigDecimal productPrice,String userId,List<ProductList> productLists){
        List<CouponUserDTO> couponUsers = couponUserDAO.getCouponUserByUserId(userId);
        Map<String,ProductList>  productListMap = productLists.stream().collect(Collectors.toMap(ProductList::getId,ProductList->ProductList));
        List<CouponUserDTO> couponUsersCanUser = new ArrayList<>();
        BigDecimal discountMoney = new BigDecimal(0);//优惠金额
        String bigReduceMoneyId = "";
        for (CouponUserDTO coupon : couponUsers) {
            String couponType = coupon.getCouponType();
            BigDecimal reduceMoney = new BigDecimal(0);
            if(CouponTypeEnums.FULL_REDUCE.toString().equals(couponType)){
                reduceMoney = coupon.getFullMoney();
                if(productPrice.compareTo(reduceMoney)>0){
                    couponUsersCanUser.add(coupon);
                }else{
                    reduceMoney = new BigDecimal(0);
                }
            }else if(CouponTypeEnums.CASH.toString().equals(couponType)){
                couponUsersCanUser.add(coupon);
                reduceMoney = coupon.getReduceMoney();
                if(reduceMoney.compareTo(productPrice)<0){
                    reduceMoney = productPrice;
                }
            }else if(CouponTypeEnums.DISCOUNT.toString().equals(couponType)){
                couponUsersCanUser.add(coupon);
                reduceMoney = productPrice.multiply(coupon.getDiscount());
            }else if(CouponTypeEnums.PRODUCT.toString().equals(couponType)){
                if(productListMap.containsKey(coupon.getProductid())){
                    couponUsersCanUser.add(coupon);
                    ProductList productList = productListMap.get(coupon.getProductid());
                    if(coupon.getReduceMoney().compareTo(productList.getPrice())>0){
                        reduceMoney = productList.getPrice();
                    }else {
                        reduceMoney = coupon.getReduceMoney();
                    }
                }else{
                    reduceMoney = new BigDecimal(0);
                }
            }
            if(discountMoney.compareTo(reduceMoney)<0){
                discountMoney = reduceMoney;
                bigReduceMoneyId=coupon.getId();
            }
        }
        for (CouponUserDTO couponUser : couponUsersCanUser) {
            if(couponUser.getId().equals(bigReduceMoneyId)){
                couponUser.setIsOptimal("1");
            }else{
                couponUser.setIsOptimal("0");
            }
        }
        pDto.setCouponUserDTO(couponUsersCanUser);
        return  discountMoney;
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



