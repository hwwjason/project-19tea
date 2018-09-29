package com.sckj.repository.mybatis;

import com.sckj.model.GjpAccesstoken;
import com.sckj.model.dto.GjpAccesstokenDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
/**
 * 描述：管家婆tokenDAO 层
 * @author hww
 * @date 2018/09/28
 */
public interface GjpAccesstokenDAO {

    GjpAccesstokenDTO findDTOById(@Param("id")String id);

    GjpAccesstoken findById(@Param("id")String id);

    /**
    * 描述：查询管家婆token列表以及高级搜索(分页)
    * @param page  分页参数
    * @param gjpAccesstokenDTO  管家婆tokenDTO
    */
    Page<GjpAccesstokenDTO> findGjpAccesstokenPage(GjpAccesstokenDTO gjpAccesstokenDTO, Pageable page);

    List<GjpAccesstokenDTO> getGjpAccesstokenList(Map<String,Object> map);

}
