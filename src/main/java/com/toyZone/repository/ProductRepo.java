package com.toyZone.repository;

import java.util.List;

import com.toyZone.model.Product;

<<<<<<< HEAD
public interface ProductRepo extends AbstractRepo<Integer,Product>{
	List<Product> getListSpKhuyenMai(int offset,int limit);
	int getCountSpKhuyenMai();
=======
public interface ProductRepo extends AbstractRepo<Integer, Product> {
    List<Product> getListSpKhuyenMai(int offset, int limit);

    int getCountSpKhuyenMai();
>>>>>>> develop
}
