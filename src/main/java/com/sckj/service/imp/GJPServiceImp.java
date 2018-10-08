package com.sckj.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.sckj.constant.GJPApiConstants;
import com.sckj.helper.GJPHelp;
import com.sckj.model.GJP.GJPProductinfoModel;
import com.sckj.model.GJP.GJPRequest.GJPBaseRequest;
import com.sckj.model.GJP.GJPRequest.GJPProductinfoRequest;
import com.sckj.model.GJP.GJPSelfbuiltmalleshoporder;
import com.sckj.model.GJP.GJPSelfbuiltmallproduct;
import com.sckj.model.GJP.GJPSelfbuiltmallproductsku;
import com.sckj.model.ProductList;
import com.sckj.model.ProductOrder;
import com.sckj.model.ProductSonOrder;
import com.sckj.model.UserList;
import com.sckj.model.dto.ProductListDTO;
import com.sckj.service.IGJPService;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.JsonUtils;
import net.sf.json.util.JSONUtils;
import org.apache.commons.collections.MapUtils;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.springframework.stereotype.Service;

import javax.json.Json;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GJPServiceImp implements IGJPService {

    @Override
    public  String getProductinfo()  throws Exception
    {
        GJPBaseRequest baseRequest = new GJPBaseRequest();
        baseRequest.setApiName(GJPApiConstants.QUERY_PRODUCT_INFO);
        Map<String, String> param = new HashMap<>();
        String ret = GJPHelp.sendPost(baseRequest,param);
        return ret;
    }

    @Override
    public  String getProductinfoByCode(String code)  throws Exception
    {
        GJPBaseRequest baseRequest = new GJPBaseRequest();
        baseRequest.setApiName(GJPApiConstants.QUERY_PRODUCT_INFO);
        Map<String, String> param = new HashMap<>();
        List<String> ids = new ArrayList<>();
        ids.add(code);
        param.put("ptypeids",ids.toString());
        String ret = GJPHelp.sendPost(baseRequest,param);
        return ret;
    }

    /**
     * 获取ERP商品基本资料的库存信息
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public  String querysaleqty(String code)  throws Exception
    {
        GJPBaseRequest baseRequest = new GJPBaseRequest();
        baseRequest.setApiName(GJPApiConstants.QUERY_SALE_QTY);
        Map<String, String> param = new HashMap<>();
        List<Long> ids = getKtypeids();
        param.put("ktypeids",ids.toString());
        param.put("numid",code);
        param.put("iscalcsaleqty","true");
        param.put("pagesize","50");
        param.put("pageno","1");
        String ret = GJPHelp.sendPost(baseRequest,param);

        return ret;
    }

    /**
     * 批量 获取ERP商品基本资料的库存信息
     * @param codes
     * @return
     * @throws Exception
     */
    @Override
    public  String batchquerysaleqty(List<String> codes)  throws Exception
    {
        GJPBaseRequest baseRequest = new GJPBaseRequest();
        baseRequest.setApiName(GJPApiConstants.BATCH_QUERY_SALE_QTY);
        Map<String, String> param = new HashMap<>();
        List<Long> ids = getKtypeids();
        param.put("ktypeids",ids.toString());
        param.put("numids",codes.toString());
        param.put("iscalcsaleqty","false");
        param.put("eshopid","0");
        param.put("pagesize","50");
        param.put("pageno","1");
        String ret = GJPHelp.sendPost(baseRequest,param);

        return ret;
    }

    /**
     * 上载商品
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public  String uploadproducts(String code)  throws Exception
    {
        GJPBaseRequest baseRequest = new GJPBaseRequest();
        baseRequest.setApiName(GJPApiConstants.UPLOAD_PRODUCTS);
        Map<String, String> param = new HashMap<>();
        GJPSelfbuiltmallproduct selfbuiltmallproduct = new GJPSelfbuiltmallproduct();
        BigDecimal decimal = new BigDecimal(0);
        String numid = code;
        selfbuiltmallproduct.setProductname("测试套餐");
        selfbuiltmallproduct.setNumid(numid);
        selfbuiltmallproduct.setOuterid("OuterId"+numid);
        selfbuiltmallproduct.setPrice(decimal);//
        selfbuiltmallproduct.setStockstatus(1);

        List<GJPSelfbuiltmallproductsku> selfbuiltmallproductskus = new ArrayList<>();

        GJPSelfbuiltmallproductsku selfbuiltmallproductsku = new GJPSelfbuiltmallproductsku();
        selfbuiltmallproductsku.setNumid(numid);
        selfbuiltmallproductsku.setSkuid("123124");
        selfbuiltmallproductsku.setOuterskuid("OuterSkuid"+numid);
        selfbuiltmallproductsku.setPropertiesname(numid);
        selfbuiltmallproductsku.setQty("100");//
        selfbuiltmallproductsku.setPrice(decimal);//
        selfbuiltmallproductskus.add(selfbuiltmallproductsku);

        selfbuiltmallproduct.setSkus(selfbuiltmallproductskus);

        Map<String,Object> map = JsonUtils.objectToMap(selfbuiltmallproduct);
        param.put("products","["+JsonUtils.Map2JSON(map)+"]");
        String ret = GJPHelp.sendPost(baseRequest,param);
        return ret;
    }

    @Override
    public String uploadproducts(ProductList product) throws Exception{
        GJPBaseRequest baseRequest = new GJPBaseRequest();
        baseRequest.setApiName(GJPApiConstants.UPLOAD_PRODUCTS);
        Map<String, String> param = new HashMap<>();
        GJPSelfbuiltmallproduct selfbuiltmallproduct = new GJPSelfbuiltmallproduct();
        String numid = product.getCode();
        selfbuiltmallproduct.setProductname(product.getTitle());
        selfbuiltmallproduct.setNumid(numid);
        selfbuiltmallproduct.setOuterid(numid);
        selfbuiltmallproduct.setPrice(product.getOriginalPrice());//
        selfbuiltmallproduct.setStockstatus(1);

        List<GJPSelfbuiltmallproductsku> selfbuiltmallproductskus = new ArrayList<>();

        GJPSelfbuiltmallproductsku selfbuiltmallproductsku = new GJPSelfbuiltmallproductsku();
        selfbuiltmallproductsku.setNumid(numid);
        selfbuiltmallproductsku.setSkuid("123124");//Sku数字id（请与订单明细中skuid保持一致）??订单还没生成怎么办
        selfbuiltmallproductsku.setOuterskuid(numid);
        selfbuiltmallproductsku.setPropertiesname(product.getTitle());
        selfbuiltmallproductsku.setQty(product.getStock()+"");
        selfbuiltmallproductsku.setPrice(product.getOriginalPrice());
        selfbuiltmallproductskus.add(selfbuiltmallproductsku);

        selfbuiltmallproduct.setSkus(selfbuiltmallproductskus);

        Map<String,Object> map = JsonUtils.objectToMap(selfbuiltmallproduct);
        param.put("products","["+JsonUtils.Map2JSON(map)+"]");
        String ret = GJPHelp.sendPost(baseRequest,param);
        return ret;
    }

    /**
     * 系统仓库
     * @return
     * @throws Exception
     */
    @Override
    public   List<Object> queryktypelist() throws Exception{
        GJPBaseRequest baseRequest = new GJPBaseRequest();
        baseRequest.setApiName(GJPApiConstants.QUERY_KTYPE_LIST);
        Map<String, String> param = new HashMap<>();
        param.put("pageno","1");
        param.put("pagesize","20");
        String ret = GJPHelp.sendPost(baseRequest,param);
        Map<String,Object> map =  JsonUtils.parseJSON2Map(ret);//response
        Map<String,Object> responseMap = (Map<String, Object>) map.get("response");
        List<Object> jsonObjects= (List<Object>) responseMap.get("ktypelist");
        return jsonObjects;
    }

    public List<Long> getKtypeids() throws Exception {
        List<Object> objects = this.queryktypelist();
        List<Long> ktypeids = new ArrayList<>();
        for (Object object : objects) {
            ktypeids.add((Long) ((JSONObject) object).get("ktypeid"));
        }
        return ktypeids;
    }

    @Override
    public List<Object> querysaleorder() throws Exception {
        GJPBaseRequest baseRequest = new GJPBaseRequest();
        baseRequest.setApiName(GJPApiConstants.QUERY_SALE_ORDER);
        Map<String, String> param = new HashMap<>();
        param.put("begintime","2018-09-20 11:11:11");
        param.put("endtime","2018-10-18 11:11:11");
//        param.put("tradeid","");
        param.put("datetype","0");
        param.put("tradestatus","1");
        param.put("pagesize","99");
        param.put("pageno","1");
        String ret = GJPHelp.sendPost(baseRequest,param);
        Map<String,Object> map =  JsonUtils.parseJSON2Map(ret);//response
        Map<String,Object> responseMap = (Map<String, Object>) map.get("response");
        List<Object> jsonObjects= (List<Object>) responseMap.get("orders");
        return jsonObjects;
    }

    @Override
    public String uploadsaleorders(ProductOrder productOrder, List<ProductSonOrder> productSonOrders,UserList user) throws Exception {
        GJPBaseRequest baseRequest = new GJPBaseRequest();
        baseRequest.setApiName(GJPApiConstants.UPLOAD_SALE_ORDERS);

        Map<String, String> param = new HashMap<>();

        List<Map<String, Object>> orders = new ArrayList();
        Map<String, Object> order = new HashMap<>();
        order.put("tradeid",productOrder.getId());
        order.put("tradestatus","1");
        order.put("tradecreatetime", DateTimeUtils.getCurTime());
        order.put("tradetype","0");
        order.put("refundstatus","0");
        order.put("tradetotal",productOrder.getProductPrice().toString());       ////
        order.put("total",productOrder.getTotalPrice().toString());
        order.put("preferentialtotal",productOrder.getCouponPrice().toString());

        //orderdetails   begin
        List<Map<String, String>>  orderdetails  = new ArrayList<>();
        for (ProductSonOrder sonOrder : productSonOrders) {
            Map<String, String> orderdetail = new HashMap<>();
            orderdetail.put("ptypeid",sonOrder.getProductcode());
            orderdetail.put("productname",sonOrder.getTitle());
            orderdetail.put("platformpropertiesname",sonOrder.getProductcode());    //Sku属性名称
            orderdetail.put("tradeoriginalprice",sonOrder.getProductPrice().toString());
            orderdetail.put("preferentialtotal","0");        //  优惠金额 （如果商品明细有多个数量，此金额为所有数量优惠金额的总和）
            orderdetail.put("qty",sonOrder.getBuynum()+"");
            orderdetail.put("refundstatus","1");
            orderdetails.add( orderdetail);
        }

        //order.put("orderdetails",JsonUtils.List2JSON(orderdetails));
        order.put("orderdetails",orderdetails);
        // orderdetails  end

        Map<String,String> eshopbuyer = new HashMap<>();
        eshopbuyer.put("customerreceiver",productOrder.getAcceptName());
        eshopbuyer.put("customerreceivermobile",productOrder.getAcceptTel());
        eshopbuyer.put("customerreceiverprovince",productOrder.getProvince());//
        eshopbuyer.put("customerreceivercity",productOrder.getCity());
        eshopbuyer.put("customerreceiverdistrict",productOrder.getArea());
        eshopbuyer.put("customerreceiveraddress",productOrder.getAddress());
        //order.put("eshopbuyer", JsonUtils.Map2JSON(eshopbuyer));
        order.put("eshopbuyer", eshopbuyer);

        orders.add(order);

        param.put("orders",JsonUtils.List2JSON(orders));
        String ret = GJPHelp.sendPost(baseRequest,param);
        return ret;
    }

    public List<Object> addsaleorder() throws Exception{
        GJPBaseRequest baseRequest = new GJPBaseRequest();
        baseRequest.setApiName(GJPApiConstants.ADD_SALE_ORDER);

        Map<String, String> param = new HashMap<>();
        param.put("tradeid","");//*
        param.put("tradestatus","");
        param.put("tradecreatetime","");
        param.put("tradepaiedtime","");
        param.put("tradetype","");
        param.put("refundstatus","");
        param.put("tradetotal","");
        param.put("total","");
        param.put("paiedtotal","");
        param.put("preferentialtotal","");

        List<Map<String,Object>>  orderdetails  = new ArrayList<>();
        Map<String,Object> orderdetail = new HashMap<>();
        orderdetail.put("ptypeid","");
        orderdetail.put("productname","");
        orderdetail.put("platformpropertiesname","");
        orderdetail.put("tradeoriginalprice","");
        orderdetail.put("qty","");
        orderdetail.put("refundstatus","0");
        orderdetails.add(orderdetail);
        param.put("orderdetails",orderdetails.toString());//

        Map<String,Object> eshopbuyer = new HashMap<>();
        eshopbuyer.put("customerreceiver","");//
        param.put("eshopbuyer",eshopbuyer.toString());

        String ret = GJPHelp.sendPost(baseRequest,param);
        Map<String,Object> map =  JsonUtils.parseJSON2Map(ret);//response
        Map<String,Object> responseMap = (Map<String, Object>) map.get("response");
        return null;
    }

}
