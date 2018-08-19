package com.sckj.bak.service.imp;

import com.sckj.dto.ResultData;
import com.sckj.enums.ResultStatusEnum;
import org.springframework.web.bind.annotation.RequestMapping;

public class BakUserServiceImp {

    @RequestMapping("/login")
    public ResultData getUserInfo()
    {
        ResultData resultData = new ResultData();

        try{

            resultData.setStatus(ResultStatusEnum.SUCESS.toString());
            resultData.setMessage("获取用户成功");
        }catch (Exception e){
            resultData.setStatus(ResultStatusEnum.FAIL.toString());
            resultData.setMessage("获取用户失败：" + e);

        }
        return resultData;

    }
}
