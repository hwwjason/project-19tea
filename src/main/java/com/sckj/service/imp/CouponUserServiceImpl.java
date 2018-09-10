package com.sckj.service.imp;
import com.sckj.model.CouponUser;
import com.sckj.repository.CouponUserRepository;
import com.sckj.service.ICouponUserService;
import com.sckj.repository.mybatis.CouponUserDAO;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.UUIDUtils;
import com.sckj.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sckj.model.dto.CouponUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* 描述：优惠券用户表 服务实现层
* @author hww
* @date 2018/09/10
*/
@Service
public class CouponUserServiceImpl implements ICouponUserService {

    @Autowired
    private CouponUserDAO couponUserDAO;

    @Autowired
    private CouponUserRepository couponUserRepository;

    @Override
    public CouponUserDTO findDTOById(String id) throws Exception {
        CouponUserDTO couponUserDTO = couponUserDAO.findDTOById(id);
        return couponUserDTO;
    }

    @Override
    public void deleteById(String id) throws Exception {
        couponUserRepository.deleteById(id);
    }

    @Override
    public CouponUserDTO createCouponUser(CouponUserDTO couponUserDTO) throws Exception {
        CouponUser couponUser = new CouponUser();
        BeanUtils.copyProperties(couponUser,couponUserDTO);
        //couponUser.setStatus(StatusEnum.ENABLE.toString());
        couponUser.setReceivetime(DateTimeUtils.getCurrentDate());
        couponUser.setId(UUIDUtils.generate());
        couponUser = couponUserRepository.saveAndFlush(couponUser);
        return this.findDTOById(couponUser.getId());
    }

    @Override
    public CouponUserDTO updateCouponUser(CouponUserDTO couponUserDTO)throws Exception {
        CouponUser couponUser = new CouponUser();
        BeanUtils.copyPropertiesWithoutNull(couponUser,couponUserDTO);
        couponUser = couponUserRepository.saveAndFlush(couponUser);
        return this.findDTOById(couponUser.getId());
    }

    /**
    * 描述：查询列表(分页)
    * @param couponUserDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<CouponUserDTO> findCouponUserPage(CouponUserDTO couponUserDTO, Pageable page) throws Exception{
        return couponUserDAO.findCouponUserPage(couponUserDTO,page);
    }



}



