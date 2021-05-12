package com.toyZone.service;

import java.util.List;

import com.toyZone.dto.ProductDto;

public interface ProductService {
<<<<<<< HEAD
	ProductDto saveProductService(ProductDto productDto);
	String deleteProductService(Integer id);
	Object[] viewPageProductService(int offset, int limit,int id_cate);
	Object[] viewPageProductHotService(int offset, int limit);
	ProductDto findProductByIdService(Integer id);
	Object[] findFilterProductService(String[] filter);
	List<ProductDto> getListSpKhuyenMaiService(int offset,int limit);
	int getCountSpKmService();
=======
    ProductDto saveProductService(ProductDto productDto);

    String deleteProductService(Integer id);

    Object[] viewPageProductService(int offset, int limit, int id_cate);

    Object[] viewPageProductHotService(int offset, int limit);

    ProductDto findProductByIdService(Integer id);

    Object[] findFilterProductService(String[] filter);

    List<ProductDto> getListSpKhuyenMaiService(int offset, int limit);

    int getCountSpKmService();
>>>>>>> develop
}
