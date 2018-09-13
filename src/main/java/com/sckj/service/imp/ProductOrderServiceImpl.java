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
import com.sckj.utils.*;
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
        productOrder.setOrderStatus(OrderStatusEnums.TEMPORARY.toString());
        productOrder = productOrderRepository.saveAndFlush(productOrder);

        //productOrderDTO = productOrderDAO.findDTOById(productOrder.getId());
        //保存子订单
        //获取购物车列表
        UserCartList userCartList = userCartService.getUserCart(productOrderDTO.getBuyuserId(),productOrderDTO.getCartType());
        List<UserCartDTO> userCarts = userCartList.getUserCarts();//购物车未下线的商品

        //对应的商品
        List<String> productIds = userCarts.stream().map(UserCartDTO::getProductid).collect(Collectors.toList());
        List<ProductList> productLists = productListJpa.findByIdIn(productIds);

        //保存子订单，并返回商品总额
        BigDecimal productTotalPrice = saveProductSonOrders(productOrderDTO,userCarts,productLists,true);

        //包装订单所需的信息，地址，优惠券
        return packageProductOrder(productOrderDTO,productTotalPrice,productLists);
    }

    @Override
    public ProductOrderDTO toAccount(ProductOrderDTO productOrderDTO) throws Exception {
        //生成子订单
        //获取购物车列表
        UserCartList userCartList = userCartService.getUserCart(productOrderDTO.getBuyuserId(),productOrderDTO.getCartType());
        List<UserCartDTO> userCarts = userCartList.getUserCarts();//购物车未下线的商品

        //对应的商品
        List<String> productIds = userCarts.stream().map(UserCartDTO::getProductid).collect(Collectors.toList());
        List<ProductList> productLists = productListJpa.findByIdIn(productIds);

        BigDecimal productTotalPrice = saveProductSonOrders(productOrderDTO,userCarts,productLists,false);

        //包装订单所需的信息，地址，优惠券
        return packageProductOrder(productOrderDTO,productTotalPrice,productLists);

    }


    /**
     * 包装订单所需的信息，地址，优惠券
     * @param productOrderDTO
     * @param productTotalPrice
     * @param productLists
     * @return
     */
    private ProductOrderDTO  packageProductOrder(ProductOrderDTO productOrderDTO,BigDecimal productTotalPrice,List<ProductList> productLists){
        String userId =productOrderDTO.getBuyuserId();

        BigDecimal realReduceMoney = new BigDecimal(0);//真正优惠的价格

        if(StringUtils.isNotEmpty(productOrderDTO.getCouponUserid())){
            realReduceMoney = packageCouponUser(productOrderDTO,productTotalPrice,userId,productLists,userId);
        }else{
            realReduceMoney = packageCouponUser(productOrderDTO,productTotalPrice,userId,productLists);
        }

        List<UserAddress> userAddresss = userAddressRepository.findByUserid(userId);

        //设置地址
        productOrderDTO.setUserAddresss(userAddresss);
        productOrderDTO.setProductPrice(productTotalPrice);
        //邮费怎么算！！
        BigDecimal expressPrice = new BigDecimal(10);
        productOrderDTO.setExpressPrice(expressPrice);

        //优惠券优惠价格
        productOrderDTO.setTotalPrice(productTotalPrice.add(expressPrice).subtract(realReduceMoney));
        productOrderDTO.setReduceMoney(realReduceMoney);

        return productOrderDTO;
    }

    /**
     * 保存子订单，并返回商品总额
     * @param productOrderDTO
     * @param userCarts
     * @param productLists
     * @return
     */
    private BigDecimal saveProductSonOrders(ProductOrderDTO productOrderDTO,List<UserCartDTO> userCarts,List<ProductList> productLists,Boolean isSave){
        Map<String,UserCartDTO> userCartDTOMap = userCarts.stream().filter(e->"1".equals(e.getStatus())).collect(Collectors.toMap(UserCartDTO::getProductid,UserCartDTO->UserCartDTO));
        Map<String,ProductList> productListMap = productLists.stream().collect(Collectors.toMap(ProductList::getId,ProductList->ProductList));
        List<ProductSonOrder> productSonOrders = new ArrayList<>();

        BigDecimal productTotalPrice = new BigDecimal(0);//商品总金额
        for(Map.Entry<String,UserCartDTO> entry :userCartDTOMap.entrySet()){
            UserCartDTO userCartDTO = entry.getValue();

            ProductSonOrder sonOrder = new ProductSonOrder();

            ProductList productList = productListMap.get(entry.getKey());

            sonOrder.setProductid(productList.getId());
            sonOrder.setProductcode(productList.getCode());
            sonOrder.setProductPrice(productList.getPrice());
            sonOrder.setPrice(productList.getOriginalPrice());
            sonOrder.setCode(productList.getCode());
            sonOrder.setImg(productList.getImg());
            sonOrder.setTitle(productList.getTitle());
            sonOrder.setSpecification(productList.getSpecification());

            sonOrder.setId(UUIDUtils.generate());
            sonOrder.setProductOrderid(productOrderDTO.getId());
            sonOrder.setExpressStatus(OrderStatusEnums.NO_PAY.toString());//!!!
            sonOrder.setBuynum(userCartDTO.getNum());
            sonOrder.setCreateTime(DateTimeUtils.getCurrentDate());

            productSonOrders.add(sonOrder);

            productTotalPrice = productTotalPrice.add(productList.getPrice().multiply(new BigDecimal(userCartDTO.getNum())));//计算商品总金额

        }
        productOrderDTO.setProductSonOrder(productSonOrders);

        if(isSave) productSonOrderRepository.saveAll(productSonOrders);

        return productTotalPrice;
    }

    /**
     * 设置最优优惠券，以及获取优惠金额
     * @param pDto
     * @param productPrice
     * @param userId
     * @param productLists
     * @return 优惠金额
     */
    private BigDecimal packageCouponUser(ProductOrderDTO pDto , BigDecimal productPrice, String userId, List<ProductList> productLists){
        List<CouponUserDTO> couponUsers = couponUserDAO.getCouponUserByUserId(userId);
        Map<String,ProductList>  productListCodeMap = productLists.stream().collect(Collectors.toMap(ProductList::getCode,ProductList->ProductList));
        List<CouponUserDTO> couponUsersCanUser = new ArrayList<>();
        BigDecimal realReduceMoney = new BigDecimal(0);//优惠金额
        String bigReduceMoneyId = "";
        for (CouponUserDTO coupon : couponUsers) {
            String couponType = coupon.getCouponType();
            BigDecimal reduceMoney = new BigDecimal(0);
            if(CouponTypeEnums.FULL_REDUCE.toString().equals(couponType)){
                if(coupon.getFullMoney().compareTo(productPrice)<0){
                    reduceMoney = coupon.getReduceMoney();
                    if(productPrice.compareTo(reduceMoney)>0){
                        couponUsersCanUser.add(coupon);
                    }else{
                        reduceMoney = new BigDecimal(0);
                    }
                }
            }else if(CouponTypeEnums.CASH.toString().equals(couponType)){
                couponUsersCanUser.add(coupon);
                reduceMoney = coupon.getReduceMoney();
                if(reduceMoney.compareTo(productPrice)>0){
                    reduceMoney = productPrice;
                }
            }else if(CouponTypeEnums.DISCOUNT.toString().equals(couponType)){
                couponUsersCanUser.add(coupon);
                reduceMoney = productPrice.subtract(productPrice.multiply(coupon.getDiscount()).divide(new BigDecimal(10)));
            }else if(CouponTypeEnums.PRODUCT.toString().equals(couponType)){
                if(productListCodeMap.containsKey(coupon.getProductcode())){
                    couponUsersCanUser.add(coupon);
                    ProductList productList = productListCodeMap.get(coupon.getProductid());
                    if(coupon.getReduceMoney().compareTo(productList.getPrice())>0){
                        reduceMoney = productList.getPrice();
                    }else {
                        reduceMoney = coupon.getReduceMoney();
                    }
                }else{
                    reduceMoney = new BigDecimal(0);
                }
            }
            if(realReduceMoney.compareTo(reduceMoney)<0){
                realReduceMoney = reduceMoney;
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
        return  realReduceMoney;
    }

    /**
     * 设置最优优惠券，以及获取优惠金额
     * @param pDto
     * @param productPrice
     * @param userId
     * @param productLists
     * @param couponUserId 指定优惠券
     * @return 优惠金额
     */
    private BigDecimal packageCouponUser(ProductOrderDTO pDto , BigDecimal productPrice, String userId, List<ProductList> productLists, String couponUserId){
        this.packageCouponUser(pDto,productPrice,userId,productLists);
        CouponUserDTO couponUs = couponUserDAO.findDTOById(couponUserId);
        List<CouponUserDTO>  couponUserDTOS = pDto.getCouponUserDTO();
        for (CouponUserDTO couponUser : couponUserDTOS) {
            if(couponUserId.equals(couponUser.getId())){
                couponUser.setIsOptimal("1");
            }else{
                couponUser.setIsOptimal("0");
            }
        }
        return  getOneCouponUserReduceMoney(couponUs,productPrice);
    }

    /**
     * 计算优惠金额，前提是优惠券可被使用
     * @param coupon
     * @param productPrice
     * @return
     */
    private BigDecimal getOneCouponUserReduceMoney(CouponUserDTO coupon,BigDecimal productPrice){
        String couponType = coupon.getCouponType();
        BigDecimal reduceMoney = new BigDecimal(0);
        if(CouponTypeEnums.FULL_REDUCE.toString().equals(couponType)){
            if(coupon.getFullMoney().compareTo(productPrice)<0){
                reduceMoney = coupon.getReduceMoney();
            }
        }else if(CouponTypeEnums.CASH.toString().equals(couponType)){
            reduceMoney = coupon.getReduceMoney();
            if(reduceMoney.compareTo(productPrice)>0){
                reduceMoney = productPrice;
            }
        }else if(CouponTypeEnums.DISCOUNT.toString().equals(couponType)){
            reduceMoney = productPrice.subtract(productPrice.multiply(coupon.getDiscount()).divide(new BigDecimal(10)));
        }else if(CouponTypeEnums.PRODUCT.toString().equals(couponType)){
            reduceMoney = coupon.getReduceMoney();
        }
        return reduceMoney;
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



