package com.toyZone.repository;

import java.util.List;

import com.toyZone.model.Order;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public interface OrderRepo extends AbstractRepo<Integer, Order> {
    List<String[]> thongKeBanHang(String tuNgay, String denNgay);
}
