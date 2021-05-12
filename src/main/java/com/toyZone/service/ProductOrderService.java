package com.toyZone.service;

import java.util.List;

import com.toyZone.dto.ProductOrderDto;

public interface ProductOrderService {
	void saveProductOderService(ProductOrderDto productOrderDto);
	List<ProductOrderDto> getListProductOrderDtoByOrder(Integer idOrder);
}
