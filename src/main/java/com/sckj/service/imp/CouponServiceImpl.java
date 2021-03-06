package com.sckj.service.imp;

import com.sckj.model.Coupon;
import com.sckj.model.dto.CouponDTO;
import com.sckj.repository.CouponRepository;
import com.sckj.repository.mybatis.CouponDAO;
import com.sckj.service.ICouponService;
import com.sckj.utils.BeanUtils;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
* 描述：订单列表 服务实现层
* @author hww
* @date 2018/09/07
*/
@Service
public class CouponServiceImpl implements ICouponService {

    @Autowired
    private CouponDAO couponDAO;

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public CouponDTO findDTOById(String id) throws Exception {
        CouponDTO couponDTO = couponDAO.findDTOById(id);
        return couponDTO;
    }

    @Override
    public Coupon findById(String id) throws Exception {
        Coupon coupon = couponDAO.findById(id);
        return coupon;
    }

    @Override
    public void deleteById(String id) throws Exception {
        couponRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) throws Exception {
        //couponRepository.deleteByIdIn(ids);
        couponDAO.deleteLogicByIds(ids.toArray(new String[0]));
    }

    @Override
    public CouponDTO createCoupon(CouponDTO couponDTO) throws Exception {
        Coupon coupon = new Coupon();
        String id = couponDTO.getId();
        if(StringUtils.isEmpty(id)){
            BeanUtils.copyProperties(coupon,couponDTO);
            coupon.setCreatetime(DateTimeUtils.getCurrentDate());
            coupon.setStatus("1");
            coupon.setId(UUIDUtils.generate());
        }else{
            coupon = couponDAO.findById(id);
            BeanUtils.copyPropertiesWithoutNull(coupon,couponDTO);
        }
        coupon = couponRepository.saveAndFlush(coupon);
        return this.findDTOById(coupon.getId());
    }

    @Override
    public CouponDTO updateCoupon(CouponDTO couponDTO)throws Exception {
        Coupon coupon = new Coupon();
        BeanUtils.copyPropertiesWithoutNull(coupon,couponDTO);
        coupon = couponRepository.saveAndFlush(coupon);
        return this.findDTOById(coupon.getId());
    }

    /**
    * 描述：查询列表(分页)
    * @param couponDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<CouponDTO> findCouponPage(CouponDTO couponDTO, Pageable page) throws Exception{
        return couponDAO.findCouponPage(couponDTO,page);
    }

    @Override
    public List<CouponDTO> getCouponList(Map<String,Object> map) {
        List<CouponDTO> productLists = couponDAO.getCouponList(map);
        return productLists;
    }

}



