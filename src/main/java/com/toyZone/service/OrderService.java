package com.toyZone.service;

import java.util.List;

import com.toyZone.dto.OrderDto;


public interface OrderService {
	OrderDto saveOderService(OrderDto orderDto);
	Object[] viewPageOrderService(int offset, int limit);
	Object[] viewPageOrderServiceByStatus(int offset, int limit,Boolean status);
	OrderDto findByIdOrderService(Integer order);
	Boolean updateOrderByStatus(Boolean status,int idOrder);
	Boolean deleteOrderService(int idOrder);
	List<String[]> thongKeBanHang(String tungay,String denngay);
}
