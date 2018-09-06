package com.sckj.model.dto;

import com.sckj.model.ProductOrder;

import java.util.List;

/**
* 描述：订单列表DTO
* @author hww
* @date 2018/09/06
*/
public class ProductOrderDTO extends ProductOrder{
    private List<ProductSonOrderDTO> productSonOrderDTO;

    public List<ProductSonOrderDTO> getProductSonOrderDTO() {
        return productSonOrderDTO;
    }

    public void setProductSonOrderDTO(List<ProductSonOrderDTO> productSonOrderDTO) {
        this.productSonOrderDTO = productSonOrderDTO;
    }
}
