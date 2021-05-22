package com.toyZone.service;

import java.util.List;

import com.toyZone.dto.ProductOrderDto;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public interface ProductOrderService {
    void saveProductOderService(ProductOrderDto productOrderDto);

    List<ProductOrderDto> getListProductOrderDtoByOrder(Integer idOrder);
}
