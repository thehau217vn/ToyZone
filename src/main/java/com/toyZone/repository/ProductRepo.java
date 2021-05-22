package com.toyZone.repository;

import java.util.List;

import com.toyZone.model.Product;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public interface ProductRepo extends AbstractRepo<Integer, Product> {
    List<Product> getListSpKhuyenMai(int offset, int limit);

    int getCountSpKhuyenMai();
}
