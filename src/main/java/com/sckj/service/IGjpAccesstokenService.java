package com.sckj.service;
import com.sckj.model.GjpAccesstoken;
import com.sckj.model.dto.GjpAccesstokenDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

/**
* 描述：管家婆token 服务实现层接口
* @author hww
* @date 2018/09/28
*/
public interface IGjpAccesstokenService  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    GjpAccesstokenDTO findDTOById(String id)throws Exception;

    GjpAccesstoken findById(String id)throws Exception;

    GjpAccesstoken saveAndFlush(GjpAccesstoken gjpAccesstoken)throws Exception;

    void deleteById(String id)throws Exception;

    GjpAccesstokenDTO createOrUpdateGjpAccesstoken(GjpAccesstokenDTO gjpAccesstokenDTO) throws Exception;


    Page<GjpAccesstokenDTO> findGjpAccesstokenPage(GjpAccesstokenDTO gjpAccesstokenDTO, Pageable page) throws Exception;

    void deleteByIds(List<String> ids)throws Exception;

    List<GjpAccesstokenDTO> getGjpAccesstokenList(Map<String,Object> gjpAccesstokenListMap);


}
