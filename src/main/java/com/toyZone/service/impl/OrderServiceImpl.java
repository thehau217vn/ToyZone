package com.toyZone.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.toyZone.dto.OrderDto;
import com.toyZone.model.Order;
import com.toyZone.model.ProductOrder;
import com.toyZone.repository.OrderRepo;
import com.toyZone.repository.ProductOrderRepo;
import com.toyZone.service.OrderService;
import com.toyZone.utils.Constant;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProductOrderRepo productOrderRepo;

    @Override
    public OrderDto saveOderService(OrderDto orderDto) {
        Order order = orderDto.convertToEntities();
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());
        order = orderRepo.insert(order);
        orderDto = order.convertToDto();
        orderDto.setMessage("insert_success");
        return orderDto;
    }

    @Override
    public Object[] viewPageOrderService(int offset, int limit) {
        List<OrderDto> orderDtos = new ArrayList<OrderDto>();
        Object[] objects = orderRepo.findByProperty(null, "updated_at", Constant.DESC, offset, limit, null);
        Long total = (Long) objects[0];
        List<Order> listEntity = (List<Order>) objects[1];
        for (Order order : listEntity) {
            orderDtos.add(order.convertToDto());
        }
        return new Object[]{total, orderDtos};
    }

    @Override
    public OrderDto findByIdOrderService(Integer idOrder) {
        OrderDto orderDto = null;
        try {
            Order order = orderRepo.findById(idOrder);
            orderDto = order.convertToDto();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderDto;
    }

    @Override
    public Object[] viewPageOrderServiceByStatus(int offset, int limit, Boolean status) {
        List<OrderDto> orderDtos = new ArrayList<OrderDto>();
        Map<String, Object> property = new HashMap<String, Object>();
        property.put("status", status);
        Object[] objects = orderRepo.findByProperty(property, "updated_at", Constant.DESC, offset, limit, null);
        Long total = (Long) objects[0];
        List<Order> listEntity = (List<Order>) objects[1];
        for (Order order : listEntity) {
            orderDtos.add(order.convertToDto());
        }
        return new Object[]{total, orderDtos};
    }

    @Override
    public Boolean updateOrderByStatus(Boolean status, int idOrder) {
        Order order = orderRepo.findById(idOrder);
        order.setStatus(status);
        if (order != null) {
            orderRepo.update(order);
            return true;
        }
        return false;

    }

    @Override
    public Boolean deleteOrderService(int idOrder) {
        // TODO Auto-generated method stub
        Map<String, Object> property = new HashMap<String, Object>();
        property.put("odder_id", idOrder);
        Object[] objects = productOrderRepo.findByProperty(property, null, null, null, null, null);
        for (ProductOrder productOrder : (List<ProductOrder>) objects[1]) {
            productOrderRepo.deleteProductOrder(productOrder);
        }
        return orderRepo.delete(idOrder);
    }

    @Override
    public List<String[]> thongKeBanHang(String tungay, String denngay) {
        // TODO Auto-generated method stub
        return orderRepo.thongKeBanHang(tungay, denngay);
    }


}
