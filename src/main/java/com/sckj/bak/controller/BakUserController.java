package com.sckj.bak.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.Query;
import com.sckj.common.ResultData;
import com.sckj.repository.mybatis.UserListDAO;
import com.sckj.model.dto.SckjUserListDTO;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.repository.UserListJpa;
import com.sckj.model.UserList;
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
            List<UserList> userList = userListJpa.findAll();
            for (UserList user : userList) {
                user.setSessionKey(null);
                user.setSignature(null);
                user.setRawData(null);
            }
            resultData.setData(userList);
            resultData.setStatus(ResultStatusEnum.SUCESS.toString());
            resultData.setMessage("查询成功");
        }catch (Exception e){
            resultData.setStatus(ResultStatusEnum.FAIL.toString());
            resultData.setMessage("查询失败：" + e);
        }
        return resultData;

    }

    @RequestMapping(value = "/getUserilst", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getUserList(@RequestBody Query query){
        ResultData resultData = new ResultData();
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Map<String,Object> map = (Map<String, Object>) query.getCondition();
        List<SckjUserListDTO> sckjUserList = userListDAO.getUserList(map);
        for (SckjUserListDTO listDTO : sckjUserList) {
            listDTO.setRawData(null);
        }
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
