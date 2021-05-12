package com.toyZone.repository;

import java.util.List;

import com.toyZone.model.Order;

public interface OrderRepo extends AbstractRepo<Integer, Order> {
    List<String[]> thongKeBanHang(String tuNgay, String denNgay);
}
