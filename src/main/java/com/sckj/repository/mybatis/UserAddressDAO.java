package com.sckj.repository.mybatis;

import com.sckj.model.dto.UserAddressDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * 描述：用户地址DAO 层
 * @author hww
 * @date 2018/09/10
 */
public interface UserAddressDAO {

    UserAddressDTO findDTOById(@Param("id")String id);


    /**
    * 描述：查询用户地址列表以及高级搜索(分页)
    * @param page  分页参数
    * @param userAddressDTO  用户地址DTO
    */
    Page<UserAddressDTO> findUserAddressPage(UserAddressDTO userAddressDTO, Pageable page);

}
