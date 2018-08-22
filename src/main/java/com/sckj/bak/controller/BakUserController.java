package com.sckj.bak.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.ResultData;
import com.sckj.dao.UserListDAO;
import com.sckj.dto.SckjUserListDTO;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.jpa.UserListJpa;
import com.sckj.pojo.SckjUserList;
import com.sckj.service.IUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 后台用户接口
 */
@RestController
@RequestMapping("/bak/user")
public class BakUserController {

   @Autowired
   private UserListJpa userListJpa;

   @Autowired
   private UserListDAO userListDAO;

   @Autowired
   private IUserListService IUserListService;

    @RequestMapping("/getUserInfo")
    public ResultData getUserInfo(){
        ResultData resultData = new ResultData();
        try{
            List<SckjUserList> sckjUserList = userListJpa.findAll();
            for (SckjUserList user : sckjUserList) {
                user.setSessionKey(null);
                user.setSignature(null);
                user.setRawData(null);
            }
            resultData.setData(sckjUserList);
            resultData.setStatus(ResultStatusEnum.SUCESS.toString());
            resultData.setMessage("查询成功");
        }catch (Exception e){
            resultData.setStatus(ResultStatusEnum.FAIL.toString());
            resultData.setMessage("查询失败：" + e);
        }
        return resultData;

    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getUserList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "condition",required = false) Map<String,String> condition){
        ResultData resultData = new ResultData();
        PageHelper.startPage(pageNum,pageSize);
        List<SckjUserListDTO> sckjUserList = userListDAO.selectUser();
        PageInfo<SckjUserListDTO> pageInfo = new PageInfo<SckjUserListDTO>(sckjUserList);
        resultData.setData(pageInfo);
        return resultData;
    }

    @RequestMapping(value = "/iiii", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData model(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "condition",required = false) Map<String,String> condition){
        ResultData resultData = new ResultData();
        return resultData;
    }

    @RequestMapping(value = "/aaaa", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData addGoods(@RequestParam(value = "goodId") String goodId, @RequestParam(value = "userId") int userId){
        ResultData resultData = new ResultData();
        return resultData;
    }

}
