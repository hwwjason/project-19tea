package com.sckj.bak.controller;


import com.sckj.dto.ResultData;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.jpa.UserListJpa;
import com.sckj.pojo.SckjUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台用户接口
 */
@RestController
@RequestMapping("/bak/user")
public class BakUserController {

   @Autowired
   private UserListJpa userListJpa;

    @RequestMapping("/getUserInfo")
    public ResultData getUserInfo(){
        ResultData resultData = new ResultData();
        try{
            List<SckjUserList> sckjUserList = new ArrayList<>();
            for (SckjUserList user : sckjUserList) {
                user.setSessionKey(null);
                user.setSignature(null);
                user.setRawData(null);
            }
            resultData.setData(userListJpa.findAll());
            resultData.setStatus(ResultStatusEnum.SUCESS.toString());
            resultData.setMessage("查询成功");
        }catch (Exception e){
            resultData.setStatus(ResultStatusEnum.FAIL.toString());
            resultData.setMessage("查询失败：" + e);
        }
        return resultData;

    }
}
