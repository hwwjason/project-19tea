package com.sckj.service.imp;
import com.sckj.exception.BusinessException;
import com.sckj.model.UserAddress;
import com.sckj.repository.UserAddressRepository;
import com.sckj.service.IUserAddressService;
import com.sckj.repository.mybatis.UserAddressDAO;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.UUIDUtils;
import com.sckj.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sckj.model.dto.UserAddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* 描述：用户地址 服务实现层
* @author hww
* @date 2018/09/10
*/
@Service
public class UserAddressServiceImpl implements IUserAddressService {

    @Autowired
    private UserAddressDAO userAddressDAO;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Override
    public UserAddressDTO findDTOById(String id) throws Exception {
        UserAddressDTO userAddressDTO = userAddressDAO.findDTOById(id);
        return userAddressDTO;
    }

    @Override
    public void deleteById(String id) throws Exception {
        userAddressRepository.deleteById(id);
    }

    @Override
    public UserAddressDTO createUserAddress(UserAddressDTO userAddressDTO) throws Exception {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddress,userAddressDTO);
        userAddress.setId(UUIDUtils.generate());
        userAddress = userAddressRepository.saveAndFlush(userAddress);
        return this.findDTOById(userAddress.getId());
    }

    @Override
    public UserAddressDTO updateUserAddress(UserAddressDTO userAddressDTO)throws Exception {
        List<UserAddress> userAddressList = userAddressRepository.findByUserid(userAddressDTO.getUserid());
        UserAddress userAddress = null;
        if(userAddressList!=null && userAddressList.size()>0){
            userAddress = userAddressList.get(0);
            if(userAddress!=null){
                BeanUtils.copyPropertiesWithoutNull(userAddress,userAddressDTO);
                userAddress = userAddressRepository.saveAndFlush(userAddress);
                return this.findDTOById(userAddress.getId());
            }
            throw new BusinessException("更新失败");
        }else{
            userAddress = new UserAddress();
            BeanUtils.copyProperties(userAddress,userAddressDTO);
            userAddress.setId(UUIDUtils.generate());
            userAddress = userAddressRepository.saveAndFlush(userAddress);
            return this.findDTOById(userAddress.getId());
        }
    }

    /**
    * 描述：查询列表(分页)
    * @param userAddressDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<UserAddressDTO> findUserAddressPage(UserAddressDTO userAddressDTO, Pageable page) throws Exception{
        return userAddressDAO.findUserAddressPage(userAddressDTO,page);
    }

}



