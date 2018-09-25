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
import com.sckj.service.IGJPService;
import com.sckj.utils.JsonUtils;
import net.sf.json.util.JSONUtils;
import org.apache.commons.collections.MapUtils;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.springframework.stereotype.Service;

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
        GJPProductinfoRequest productinfo = new GJPProductinfoRequest();
        Map<String, String> param = new HashMap<>();
        String ret = GJPHelp.sendPost(productinfo,param);
        return ret;
    }

    @Override
    public  String getProductinfoByCode(String code)  throws Exception
    {
        GJPProductinfoRequest productinfo = new GJPProductinfoRequest();
        Map<String, String> param = new HashMap<>();
        List<String> ids = new ArrayList<>();
        ids.add(code);
        param.put("ptypeids",ids.toString());
        String ret = GJPHelp.sendPost(productinfo,param);
        Map<String,Object> retMap = JsonUtils.parseJSON2Map(ret);
        Map<String,Object> responseMap = (Map<String, Object>) retMap.get("response");
        List<Map<String,Object>> ptypesListMap = (List<Map<String, Object>>) responseMap.get("ptypes");
        Map<String,Object> ptypesMap = ptypesListMap.get(0);
        GJPProductinfoModel productinfoModel = (GJPProductinfoModel) JsonUtils.mapToObject(ptypesMap,GJPProductinfoModel.class);

        return ret;
    }

    @Override
    public  String querysaleqty(String code)  throws Exception
    {
        GJPBaseRequest baseRequest = new GJPBaseRequest();
        baseRequest.setApiName(GJPApiConstants.QUERY_SALE_QTY);
        Map<String, String> param = new HashMap<>();
//        List<String> ids = new ArrayList<>();
//
//        ids.add(code);
        //param.put("ktypeids",ids.toString());
        param.put("numid",code);
        List<Object> jsonObjects = this.queryktypelist();//((JSONObject) ktypeids.get(0)).get("usercode")
        List<Object> ktypeids =jsonObjects.stream().map(e->((JSONObject)e).get("ktypeid")).collect(Collectors.toList());
        param.put("ktypeids",(JsonUtils.List2JSON(ktypeids)).replace("\"",""));
        param.put("iscalcsaleqty","true");
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
        String numid = "9529";
        selfbuiltmallproduct.setProductname("测试套餐");
        selfbuiltmallproduct.setNumid(numid);
        selfbuiltmallproduct.setOuterid(numid);
        selfbuiltmallproduct.setPrice(decimal);//
        selfbuiltmallproduct.setStockstatus(1);

        List<GJPSelfbuiltmallproductsku> selfbuiltmallproductskus = new ArrayList<>();

        GJPSelfbuiltmallproductsku selfbuiltmallproductsku = new GJPSelfbuiltmallproductsku();
        selfbuiltmallproductsku.setNumid(numid);
        selfbuiltmallproductsku.setSkuid("123124");
        selfbuiltmallproductsku.setOuterskuid(numid);
        selfbuiltmallproductsku.setPropertiesname("123");
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
    public List<Object> uploadsaleorders() throws Exception {
        GJPBaseRequest baseRequest = new GJPBaseRequest();
        baseRequest.setApiName(GJPApiConstants.UPLOAD_SALE_ORDERS);
        List<GJPSelfbuiltmalleshoporder> selfbuiltmalleshoporder = new ArrayList<>();
        baseRequest.setBaseModels(selfbuiltmalleshoporder);

        Map<String, String> param = new HashMap<>();
        param.put("begintime","2018-09-20 11:11:11");
        param.put("endtime","2018-10-18 11:11:11");
        param.put("tradeid","");
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

}
