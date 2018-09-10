package com.sckj.service;
import com.sckj.model.UserAddress;
import com.sckj.model.dto.UserAddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* 描述：用户地址 服务实现层接口
* @author hww
* @date 2018/09/10
*/
public interface IUserAddressService  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    UserAddressDTO findDTOById(String id)throws Exception;

    void deleteById(String id)throws Exception;

    UserAddressDTO createUserAddress(UserAddressDTO userAddressDTO) throws Exception;

    UserAddressDTO updateUserAddress(UserAddressDTO userAddressDTO) throws Exception;

    Page<UserAddressDTO> findUserAddressPage(UserAddressDTO userAddressDTO, Pageable page) throws Exception;

}
