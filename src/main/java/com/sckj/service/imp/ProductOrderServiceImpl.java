package com.sckj.service.imp;

import com.sckj.enums.OrderEnums.CouponTypeEnums;
import com.sckj.enums.OrderEnums.OrderStatusEnums;
import com.sckj.exception.BusinessException;
import com.sckj.model.ProductList;
import com.sckj.model.ProductOrder;
import com.sckj.model.ProductSonOrder;
import com.sckj.model.UserAddress;
import com.sckj.model.dto.CouponUserDTO;
import com.sckj.model.dto.ProductOrderDTO;
import com.sckj.model.dto.ProductSonOrderDTO;
import com.sckj.model.dto.UserCartDTO;
import com.sckj.model.model.UserCartList;
import com.sckj.repository.*;
import com.sckj.repository.mybatis.CouponUserDAO;
import com.sckj.repository.mybatis.ProductListMapper;
import com.sckj.repository.mybatis.ProductOrderDAO;
import com.sckj.repository.mybatis.ProductSonOrderDAO;
import com.sckj.service.IProductOrderService;
import com.sckj.service.IProductSonOrderService;
import com.sckj.service.IUserCartService;
import com.sckj.service.IUserListService;
import com.sckj.utils.BeanUtils;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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

    private static final Logger logger = LoggerFactory.getLogger(ProductOrderServiceImpl.class);

    @Autowired
    private ProductOrderDAO productOrderDAO;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductSonOrderRepository productSonOrderRepository;

    @Autowired
    private IProductSonOrderService productSonOrderService;

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

    @Autowired
    private IUserListService userListService;

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

    @Override
    public List<ProductOrderDTO> getProductOrder(Map<String,Object> map) {
        List<ProductOrderDTO> productOrders = productOrderDAO.getProductOrder(map);
        for (ProductOrderDTO order : productOrders) {
            if (null == order.getExpressPrice()){
                order.setExpressPrice(new BigDecimal(0));
            }
            if(null == order.getOrderStatus()){
                order.setOrderStatus(OrderStatusEnums.NO_PAY.toString());
            }
            if(null == order.getExpressCode()){
                order.setExpressCode("");
            }
            if(null == order.getExpressPrice()){
                order.setExpressPrice(new BigDecimal(0));
            }
            if(null == order.getBuyuserTel()){
                order.setBuyuserTel("");
            }
            if(null == order.getOrderType()){
                order.setOrderType("0");
            }
        }
        return productOrders;
    }

    @Override
    public void packageSonOrders(List<ProductOrderDTO> productOrder) throws Exception {
        //获取子订单信息
        List<String> orderIds = new ArrayList<>();
        Map<String,List<ProductSonOrder>> productSonOrdersMap = new HashMap<>();
        for (ProductOrderDTO order : productOrder) {
            orderIds.add(order.getId());
        }
        List<ProductSonOrder> productSonOrders = productSonOrderService.findByProductOrderids(orderIds);
        for (ProductSonOrder sonOrder : productSonOrders) {
            List<ProductSonOrder> productSonOrder =  productSonOrdersMap.get(sonOrder.getProductOrderid());
            if(productSonOrder == null){
                productSonOrder = new ArrayList<>();
            }
            productSonOrder.add(sonOrder);
            productSonOrdersMap.put(sonOrder.getProductOrderid(),productSonOrder);
        }
        for (ProductOrderDTO orderDTO : productOrder) {
            List<ProductSonOrder> productSonOrders1  =productSonOrdersMap.get(orderDTO.getId());
            BigDecimal totalPrice = new BigDecimal(0);
            if(productSonOrders1 != null){
                for (ProductSonOrder sonOrder : productSonOrders1) {
                     totalPrice = totalPrice.add(sonOrder.getProductPrice().multiply(new BigDecimal(sonOrder.getBuynum())));
                }
            }
            orderDTO.setProductSonOrder(productSonOrdersMap.get(orderDTO.getId()));
            orderDTO.setTotalPrice(totalPrice);
        }
    }

    @Override
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
    public ProductOrderDTO createTempProductOrder(ProductOrderDTO productOrderDTO) throws Exception {
        productOrderDTO.setId(UUIDUtils.generate());
        productOrderDTO.setCreatetime(DateTimeUtils.getCurrentDate());

        //子订单
        //获取购物车列表
        UserCartList userCartList = userCartService.getUserCart(productOrderDTO.getBuyuserId(),productOrderDTO.getCartType());
        List<UserCartDTO> userCarts = userCartList.getUserCarts();//购物车未下线的商品
        productOrderDTO.setUserCarts(userCarts);

        //对应的商品
        List<String> productIds = userCarts.stream().map(UserCartDTO::getProductid).collect(Collectors.toList());
        List<ProductList> productLists = productListJpa.findByIdIn(productIds);

        //创建子订单，并返回商品总额
        BigDecimal productTotalPrice = packageProductSonOrdersByUserCarAndProductList(productOrderDTO,userCarts,productLists);

        //包装订单所需的信息，地址，优惠券
        return packageProductOrder(productOrderDTO,productTotalPrice,productLists);
    }

    /**
     * 去结算
     * @param productOrderDTO
     * @return
     * @throws Exception
     */
    @Override
    public ProductOrderDTO toAccount(ProductOrderDTO productOrderDTO) throws Exception {
        return createTempProductOrder(productOrderDTO);
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
            realReduceMoney = packageCouponUser(productOrderDTO,productTotalPrice,userId,productLists,productOrderDTO.getCouponUserid());
        }else{
            realReduceMoney = packageCouponUser(productOrderDTO,productTotalPrice,userId,productLists);
        }

        List<UserAddress> userAddresss = userAddressRepository.findByUserid(userId);

        //设置地址
        productOrderDTO.setUserAddresss(userAddresss);
        if(userAddresss!=null){
            UserAddress userAddress = userAddresss.get(0);
            productOrderDTO.setProvince(userAddress.getProvince());
            productOrderDTO.setArea(userAddress.getArea());
            productOrderDTO.setCity(userAddress.getCity());
            productOrderDTO.setAcceptName(userAddress.getName());
            productOrderDTO.setAcceptTel(userAddress.getTel());
            productOrderDTO.setAddress(userAddress.getAddress());
        }
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
    private BigDecimal packageProductSonOrdersByUserCarAndProductList(ProductOrderDTO productOrderDTO, List<UserCartDTO> userCarts, List<ProductList> productLists){
        Map<String,UserCartDTO> userCartDTOMap = userCarts.stream().filter(e->"1".equals(e.getStatus())).collect(Collectors.toMap(UserCartDTO::getProductid,UserCartDTO->UserCartDTO));
        Map<String,ProductList> productListMap = productLists.stream().collect(Collectors.toMap(ProductList::getId,ProductList->ProductList));
        productOrderDTO.setProductListMap(productListMap);
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

        //if(isSave) productSonOrderRepository.saveAll(productSonOrders);

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
        Map<String,ProductList>  productListCodeMap = new HashMap<>();
        try{
            productListCodeMap = productLists.stream().collect(Collectors.toMap(ProductList::getCode,ProductList->ProductList));
        }catch (IllegalStateException e){
            logger.error("error","商品编码重复");
            throw new BusinessException("商品编码重复请! 联系管理员");
        }
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
                if(coupon.getReduceMoney()!=null){
                    reduceMoney = coupon.getReduceMoney();
                    if(reduceMoney.compareTo(productPrice)>0){
                        reduceMoney = productPrice;
                    }
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
     * @param couponUserId 指定优惠券,值为0指定不适用优惠券
     * @return 优惠金额
     */
    private BigDecimal packageCouponUser(ProductOrderDTO pDto , BigDecimal productPrice, String userId, List<ProductList> productLists, String couponUserId){
        this.packageCouponUser(pDto,productPrice,userId,productLists);
        List<CouponUserDTO>  couponUserDTOS = pDto.getCouponUserDTO();
        if("0".equals(couponUserId)){
            for (CouponUserDTO couponUser : couponUserDTOS) {
                couponUser.setIsOptimal("0");
            }
            return  new BigDecimal(0);
        }else{
            CouponUserDTO couponUs = couponUserDAO.findDTOById(couponUserId);

            for (CouponUserDTO couponUser : couponUserDTOS) {
                if(couponUserId.equals(couponUser.getId())){
                    couponUser.setIsOptimal("1");
                }else{
                    couponUser.setIsOptimal("0");
                }
            }
            return  getOneCouponUserReduceMoney(couponUs,productPrice);
        }

    }

    /**
     * 计算优惠金额，前提是优惠券可被使用
     * @param coupon
     * @param productPrice
     * @return
     */
    private BigDecimal getOneCouponUserReduceMoney(CouponUserDTO coupon,BigDecimal productPrice){
        if(coupon == null){
            return new BigDecimal(0);
        }
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

    public void updateOrderStatus(String orderId, String userId,String orderStatus) throws Exception{
        if(!userListService.isLogin(userId)){
            logger.warn("请登录后退款,用户id为空或不存在");
            throw new BusinessException("请登录后退款");
        }
        ProductOrder productOrder = productOrderRepository.getOne(orderId);
        productOrder.setOrderStatus(orderStatus);
        productOrderRepository.saveAndFlush(productOrder);
    }

    public void deliverProduct(String orderId) throws Exception{
        ProductOrder productOrder = productOrderRepository.getOne(orderId);
        if(OrderStatusEnums.WAITE_DELIVER.toString().equals(productOrder.getOrderStatus())){

        }else {
            throw new BusinessException(OrderStatusEnums.WAITE_DELIVER.getName()+"状态，不允许发货");
        }
        productOrder.setOrderStatus(OrderStatusEnums.DELIVERED.toString());
        productOrderRepository.saveAndFlush(productOrder);
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



