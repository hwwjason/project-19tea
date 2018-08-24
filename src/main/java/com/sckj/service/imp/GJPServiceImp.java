package com.sckj.service.imp;

import com.sckj.pojo.ProductList;
import com.sckj.service.IGJPService;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import org.apache.http.protocol.HttpContext;

public class GJPServiceImp implements IGJPService {

    //
    // GET: /gjpAPI/

//    private static final String appKey = "Sa6WFjAwRqZdAc4vGbAG";
//    private static final String signKey = "10651245836115605605707684031430";
//   // private static final String apiUrl = System.Configuration.ConfigurationSettings.AppSettings["tokenUrl"] + "api/";
//    private static final String shopKey = "2ACKNJTXmQ6aGKFztrMatfGQrg7XCMwi";

    //测试环境
    private static final String appKey = "68943923115886070418838901844741";
    private static final String signKey = "lezitiancheng";
   // private static final String apiUrl = System.Configuration.ConfigurationSettings.AppSettings["tokenUrl"] + "api/";
    private static final String shopKey = "e7c5289b-07ee-492f-b84f-97ee9659c8b7";

    //获取商品信息列表
//    public void Productinfo()
//    {
//        String pid = "";
//
//        //获取token
//        String token = GetGJPToken();
//        if (token != "")
//        {
//            String[] pids = null;
//
//            if (!StringUtils.isEmpty(pid))
//            {
//                pids = new String[1];
//                pids[0] = pid;
//            }
//            //业务参数获取
//            ProductInfoRequest productinfoRequest = GetProductinfoRequest(50, 1, 0, pids);
//
//            //接口调用参数组装
//            IDictionary<String, String> param = GetProductinfoParams(productinfoRequest, token, appKey, signKey);
//
//            //post报文
//            String body = Dopost.DoPost(apiUrl, param, null);
//            if (body.Contains("错误"))
//            {
//                return new XCXST.Model.MyResult() { code = -99, message = "意外错误" };
//            }
//            //获取结果体，根据返回值判断
//            ProductResultBase ResponseBase = JsonHelper.JsonToModel<ProductResultBase>(body);
//            if (ResponseBase.iserror)
//            {
//                return new XCXST.Model.MyResult() { code = -99, message = "意外错误" };
//            }
//
//
//            List<ProductInfo> rezultProductInfo = ResponseBase.response.ptypes;
//
//            if (body != "")
//            {
//                return new Model.MyResult() { code = 10000, message = "获取成功", data = rezultProductInfo };
//            }
//            return new XCXST.Model.MyResult() { code = -99, message = "意外错误" };
//        }
//        else
//        {
//            return new XCXST.Model.MyResult() { code = -99, message = "意外错误，请尝试先手动授权" };
//        }
//
//    }
//
//
//    public String GetGJPToken()
//    {
//        String Token = "";
//        Model.gjp_accesstoken modelAccesstoken = DBFactory.F_MaxSystem.iThirdSystemCommonOperate.GetModel<Model.gjp_accesstoken>(String.Format("appkey='{0}'",appKey), ConnectionKey);
//
//        if (modelAccesstoken != null)
//        {
//            if (!String.IsNullOrEmpty(modelAccesstoken.auth_token))
//            {
//                //超过有效时间，重新刷新
//                if (Convert.ToDateTime(modelAccesstoken.Timestamp).AddSeconds(Convert.ToDouble(modelAccesstoken.expires_in)) < DateTime.Now)
//                {
//                    String re_codejson = re_Code(modelAccesstoken.refresh_token);
//                    AuthTokenResponse model =
//                            JsonHelper.JsonToModel<AuthTokenResponse>(re_codejson);
//                    return model.Auth_token;
//                }
//                Token = modelAccesstoken.auth_token;
//            }
//            else
//            {
//
//                return "";
//            }
//        }
//        else
//        {
//            return "";
//        }
//
//        return Token;
//    }



}
