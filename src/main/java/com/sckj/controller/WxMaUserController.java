package com.sckj.controller;

import com.sckj.dto.ResultData;
import com.sckj.dto.WechatLoginInfo;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.service.UserListService;
import com.sckj.service.imp.UserListServiceImp;
import com.sckj.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序登录控制器
 */
@RestController
@RequestMapping("/wechat/user")
public class WxMaUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserListService userListService = new UserListServiceImp();


    //获取凭证校检接口
    @RequestMapping("/login")
    public ResultData login(@RequestParam(value = "code") String code,@RequestParam(value = "encryptedData") String encryptedData,
            @RequestParam(value = "iv") String iv,@RequestParam(value = "rawData") String rawData,
            @RequestParam(value = "signature") String signature)
    {
        ResultData resultData = new ResultData();

        try{
            WechatLoginInfo wechatLoginInfo = userListService.login(code,encryptedData,iv,rawData,signature);
            resultData.setData(wechatLoginInfo);//.replaceAll("\n",""));
            resultData.setStatus(ResultStatusEnum.SUCESS.toString());
            resultData.setMessage("登录成功");
        }catch (Exception e){
            resultData.setStatus(ResultStatusEnum.FAIL.toString());
            resultData.setMessage("登录失败：" + e);

        }
        return resultData;

    }

    @RequestMapping("/speedLogin")
    public ResultData speedLogin(@RequestParam(value = "userId") String userId,@RequestParam(value = "encryptedData") String encryptedData, @RequestParam(value = "iv") String iv){
        ResultData resultData = new ResultData();

        try{
            WechatLoginInfo wechatLoginInfo = userListService.speedLogin(userId,encryptedData,iv);
            resultData.setData(wechatLoginInfo);
            resultData.setStatus(ResultStatusEnum.SUCESS.toString());
            resultData.setMessage("极速登录成功");
        }catch (Exception e){
            resultData.setStatus(ResultStatusEnum.FAIL.toString());
            resultData.setMessage("极速登录失败：" + e);

        }
        return resultData;
    }






}
