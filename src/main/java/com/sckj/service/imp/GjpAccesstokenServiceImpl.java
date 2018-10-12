package com.sckj.service.imp;

import com.sckj.GJP.example.AESCoder;
import com.sckj.GJP.example.Config;
import com.sckj.GJP.example.HttpRequest;
import com.sckj.model.GjpAccesstoken;
import com.sckj.model.dto.GjpAccesstokenDTO;
import com.sckj.repository.GjpAccesstokenRepository;
import com.sckj.repository.mybatis.GjpAccesstokenDAO;
import com.sckj.service.IGjpAccesstokenService;
import com.sckj.utils.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 描述：管家婆token 服务实现层
* @author hww
* @date 2018/09/28
*/
@Service
public class GjpAccesstokenServiceImpl implements IGjpAccesstokenService {

    private static final Logger logger = LoggerFactory.getLogger(GjpAccesstokenServiceImpl.class);

    @Autowired
    private GjpAccesstokenDAO gjpAccesstokenDAO;

    @Autowired
    private GjpAccesstokenRepository gjpAccesstokenRepository;


    @Override
    public GjpAccesstokenDTO findDTOById(String id) throws Exception {
        GjpAccesstokenDTO gjpAccesstokenDTO = gjpAccesstokenDAO.findDTOById(id);
        return gjpAccesstokenDTO;
    }

    @Override
    public GjpAccesstoken findById(String id) throws Exception {
        GjpAccesstoken gjpAccesstoken = gjpAccesstokenDAO.findById(id);
        return gjpAccesstoken;
    }

    @Override
    public GjpAccesstoken saveAndFlush(GjpAccesstoken gjpAccesstoken)throws Exception{
        return gjpAccesstokenRepository.saveAndFlush(gjpAccesstoken);
    }

    @Override
    public void deleteById(String id) throws Exception {
        gjpAccesstokenRepository.deleteById(id);
    }

    @Override
    public GjpAccesstokenDTO createOrUpdateGjpAccesstoken(GjpAccesstokenDTO gjpAccesstokenDTO) throws Exception {
        GjpAccesstoken gjpAccesstoken = new GjpAccesstoken();
        String id = gjpAccesstokenDTO.getId();
        if(StringUtils.isEmpty(id)){
            BeanUtils.copyProperties(gjpAccesstoken,gjpAccesstokenDTO);
            gjpAccesstoken.setId(UUIDUtils.generate());
        }else{
            gjpAccesstoken = gjpAccesstokenDAO.findById(id);
            BeanUtils.copyPropertiesWithoutNull(gjpAccesstoken,gjpAccesstokenDTO);
        }

        gjpAccesstoken = gjpAccesstokenRepository.saveAndFlush(gjpAccesstoken);
        return this.findDTOById(gjpAccesstoken.getId());
    }


    /**
    * 描述：查询列表(分页)
    * @param gjpAccesstokenDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<GjpAccesstokenDTO> findGjpAccesstokenPage(GjpAccesstokenDTO gjpAccesstokenDTO, Pageable page) throws Exception{
        return gjpAccesstokenDAO.findGjpAccesstokenPage(gjpAccesstokenDTO,page);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) throws Exception {
        gjpAccesstokenRepository.deleteByIdIn(ids);
    }

    @Override
    public List<GjpAccesstokenDTO> getGjpAccesstokenList(Map<String,Object> map) {
        List<GjpAccesstokenDTO> lists = gjpAccesstokenDAO.getGjpAccesstokenList(map);
        return lists;
    }

    private String DoGetToken(String param) throws Exception
    {
        String code = GetAuthCode(param);
        //获取p参数
        JSONObject obj = new JSONObject();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        Date now = new Date();
        String time = dateFormat.format(now);
        obj.put("TimeStamp", time);
        obj.put("GrantType", "auth_token");
        obj.put("AuthParam", code);
        String jsonParam = obj.toString();
        AESCoder coder = new AESCoder();
        String p = coder.encrypt(jsonParam, Config.app_secret);

        //获取sign
        JSONObject signObj = new JSONObject();
        signObj.put("appkey", Config.appkey);
        signObj.put("p", p);
        signObj.put("signkey", Config.sign_key);
        String sign = coder.SHA256(signObj.toString());

        //授权获取
        Map<String, String> map = new HashMap<String, String>();
        map.put("appkey",Config.appkey);
        map.put("p",  URLEncoder.encode(p, "utf-8"));
        map.put("sign",sign);
        String postString = "";
        for (String in : map.keySet()) {
            postString += in + "=" +map.get(in) +"&";
        }
        postString = postString.substring(0, postString.length() - 1);
        String ret =  HttpRequest.sendPost(Config.get_token_url, postString);

        //解析返回结果
        JSONObject jsonObject=JSONObject.fromObject(ret);
        String resp = jsonObject.getJSONObject("response").get("response").toString();
        String token = coder.decrypt(resp,  Config.app_secret);
        Map<String,Object> tokenMap = JsonUtils.parseJSON2Map(token);
        GjpAccesstoken gjpAccesstoken = new GjpAccesstoken();
        gjpAccesstoken.setId(UUIDUtils.generate());
        gjpAccesstoken.setAppkey((String) tokenMap.get("auth_token"));
        gjpAccesstoken.setAuthToken((String) tokenMap.get("auth_token"));
        gjpAccesstoken.setExpiresIn(tokenMap.get("expires_in").toString());
        gjpAccesstoken.setRefreshToken((String) tokenMap.get("refresh_token"));
        gjpAccesstoken.setReExpiresIn(tokenMap.get("re_expires_in").toString());
        gjpAccesstoken.setTimestamp(DateTimeUtils.getCurrentDate());
        gjpAccesstokenRepository.save(gjpAccesstoken);
        return token;
    }

    private String GetAuthCode(String param) throws Exception
    {
        String AuthCode = "";
        String[] params = param.trim().split("&");
        for(int i = 0; i < params.length; i++){
            if(params[i].toLowerCase().indexOf("auth_code") >= 0 && params[i].toLowerCase().indexOf("=") >= 0)
            {
                AuthCode = params[i].substring(params[i].toLowerCase().indexOf("="));
                break;
            }
        }
        if(AuthCode != "" && AuthCode.length() > 1)
        {
            AuthCode = AuthCode.substring(1);
        }
        else
        {
            throw new Exception("获取返回code失败");
        }
        return AuthCode;
    }



}



