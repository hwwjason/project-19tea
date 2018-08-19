package com.sckj.service.imp;

import com.google.gson.Gson;
import com.sckj.jpa.UserListJpa;
import com.sckj.constant.MiniAppConstants;
import com.sckj.dao.sckjUserListMapper;
import com.sckj.dto.OpenIdAndSessionKey;
import com.sckj.dto.WechatLoginInfo;
import com.sckj.enums.LoginStateEnum;
import com.sckj.pojo.SckjUserList;
import com.sckj.service.UserListService;
import com.sckj.utils.AesEncodeUtil;
import com.sckj.utils.UUIDUtils;
import com.weixin.miniapp.api.WxMaService;
import com.weixin.miniapp.bean.WxMaPhoneNumberInfo;
import com.weixin.miniapp.bean.WxMaUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserListServiceImp  implements UserListService{

    @Autowired
    private WxMaService wxService;

    @Autowired
    private sckjUserListMapper userListMapper;

    @Autowired
    private UserListJpa userListJpa;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @Override
    public WxMaUserInfo getUserInfo(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        // 用户信息校验
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return null;
        }
        // 解密用户信息
        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        return userInfo;
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @Override
    public WxMaPhoneNumberInfo getUserPhone(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        // 用户信息校验
//        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
//            return "user check failed";
//        }
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = this.wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return phoneNoInfo;
    }

    //获取凭证校检接口
    @Override
    public WechatLoginInfo login( String code, String encryptedData, String iv, String rawData, String signature)
    {
        WechatLoginInfo wechatLoginInfo = new WechatLoginInfo();
        OpenIdAndSessionKey weChatSession = getSession(code);
        if(null!=weChatSession)
        {
            //获取用户的唯一标识
            String openid = weChatSession.getOpenid();
            //获取会话秘钥
            String session_key = weChatSession.getSession_key();
            WxMaUserInfo userInfo = getUserInfo( session_key,  signature,  rawData,  encryptedData,  iv) ;
            if(openid!=null){
                String encryptOpenid = AesEncodeUtil.encrypt(openid);
                wechatLoginInfo.setUserId(encryptOpenid);
                //判断是否已经登录过
                if(!isLogin(wechatLoginInfo)){
                    SckjUserList userList = new SckjUserList();
                    userList.setId(UUIDUtils.generate());
                    userList.setUserId(encryptOpenid);
                    userList.setOpenid(openid);
                    userList.setSessionKey(session_key);
                    userList.setRawData(rawData);
                    userList.setSignature(signature);
                    userList.setHeadimg(userInfo.getAvatarUrl());
                    userList.setNickname(userInfo.getNickName());

//                    userList.setAv(userInfo.getAvatarUrl());
                    userList.setCity(userInfo.getCity());
//                    userList.(userInfo.getCountry());
//                    userList.setB(userInfo.getGender());
//                    userList.setL(userInfo.getLanguage());
                    userList.setOpenid(userInfo.getOpenId());
                    userList.setProvince(userInfo.getProvince());
                    userList.setUnionid(userInfo.getUnionId());
//                    userList.setW(userInfo.getWatermark());

                    userListMapper.insert(userList);
                }else{
                    //更新 头像 昵称
                    List<SckjUserList> userList = userListJpa.findByUserId(encryptOpenid);
                    if(userList.size()>0){
                        SckjUserList user = userList.get(0);
                        user.setSessionKey(session_key);//Ao+XJdoi5YvJrZvqEI1zmQ== Ao+XJdoi5YvJrZvqEI1zmQ== 8q9rxhum2iOLzTSdGTuJhA==
                        user.setNickname(userInfo.getNickName());
                        user.setOpenid(openid);
                        userListJpa.saveAndFlush(user);
//                        user.setHeadimg(userInfo.get);
                    }
                    wechatLoginInfo.setState(LoginStateEnum.SUCESS.toString());
                }
            }else{
                wechatLoginInfo.setState(LoginStateEnum.FAIL.toString());
            }

        }else{
            wechatLoginInfo.setState(LoginStateEnum.FAIL.toString());
        }
        return wechatLoginInfo;

    }

    /**
     * 极速登录，主要是存电话号码
     * @param userId
     * @param encryptedData
     * @param iv
     * @return
     */
    @Override
    public WechatLoginInfo speedLogin(String userId,String encryptedData, String iv) {
        //判断登录情况
        WechatLoginInfo wechatLoginInfo = new WechatLoginInfo();
        List<SckjUserList>  userList = userListJpa.findByUserId(userId);
        if(userList.size()==0){
            wechatLoginInfo.setState(LoginStateEnum.FAIL.toString());
        }else {
            SckjUserList user = userList.get(0);
            String session_key = user.getSessionKey();
            String signature = user.getSessionKey();
            String rawData = user.getRawData();
            WxMaPhoneNumberInfo tel = getUserPhone(session_key,signature,rawData,encryptedData,iv);
            user.setTel(tel.getPhoneNumber());
            userListJpa.saveAndFlush(user);
            //int result = userListJpa.updateByUserId(tel.getPhoneNumber(),user.getUserId());
//            if(result==0){
//                logger.error("手机号码写入失败");
//                wechatLoginInfo.setState(LoginStateEnum.SUCESS.toString());
//            }
            wechatLoginInfo.setState(LoginStateEnum.SUCESS_SPEED.toString());
        }
        return wechatLoginInfo;
    }

    private OpenIdAndSessionKey getSession(String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+ MiniAppConstants.AppID+
                "&secret="+MiniAppConstants.AppSecret+"&js_code="+ code +"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        //进行网络请求,访问url接口
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //根据返回值进行后续操作
        if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK){
            String sessionData = responseEntity.getBody();
            Gson gson = new Gson();
            //解析从微信服务器获得的openid和session_key;
            OpenIdAndSessionKey weChatSession = gson.fromJson(sessionData,OpenIdAndSessionKey.class);
            return weChatSession;
        }
        return null;
    }

    /**
     * 判断code，unionId是否已经存在，
     * @return
     */
    public boolean isLogin(WechatLoginInfo wechatLoginInfo){
        if(StringUtils.isEmpty(wechatLoginInfo.getUserId())){
            return false;
        }
        List<SckjUserList> UserLists = userListJpa.findByUserId(wechatLoginInfo.getUserId());
        if(UserLists.size()>0){
            return true;
        }
        return false;
    }

    /**
     * 判断电话号码是否已经保存
     * @return
     */
    public boolean isSpeedLogin(WechatLoginInfo wechatLoginInfo){
        if(wechatLoginInfo.getUserId()==null){
            return false;
        }
        List<SckjUserList> UserLists = userListJpa.findByUserId(wechatLoginInfo.getUserId());
        if(UserLists.size()==0){
            return false;
        }
        if(StringUtils.isNotEmpty(UserLists.get(0).getTel())){
            return true;
        }
        return false;
    }




}
