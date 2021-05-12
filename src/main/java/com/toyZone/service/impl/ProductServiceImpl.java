package com.toyZone.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyZone.dto.ProductDto;
import com.toyZone.model.Product;
import com.toyZone.repository.ProductRepo;
import com.toyZone.service.ProductOrderService;
import com.toyZone.service.ProductService;
import com.toyZone.utils.Constant;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepo productRepo;
	@Autowired
	ProductOrderService productOrderService;
	@Autowired
	ServletContext context;
	@Override
	public ProductDto saveProductService(ProductDto productDto) {
		// TODO Auto-generated method stub
		Product product = productDto.convertToEntity();
		if(product.getId() == null) {
			product.setCreatedAt(new Date());
			product.setUpdatedAt(new Date());
			product= productRepo.insert(product);
			productDto = product.convertToDto();
		}else {
			product = productRepo.findById(product.getId());
			product = product.convertToEntity(productDto);
			product.setUpdatedAt(new Date());
			product = productRepo.update(product);
			productDto = product.convertToDto();
		}
		
		// TODO Auto-generated method stub
		return productDto;
	}

	@Override
	public String deleteProductService(Integer id) {
		// TODO Auto-generated method stub
		ProductDto dto = findProductByIdService(id);
		File file = new File(context.getRealPath(dto.getImage()));
		file.delete();
		if (productRepo.delete(id)){
			return "success";
		}else {
			return "fail";
		}
	}

	@Override
	public Object[] viewPageProductService(int offset, int limit,int id_cate) {
		// TODO Auto-generated method stub
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		
		Object[] objects = null;
		
		if(id_cate!=0) {
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("category_id", id_cate);
			objects = productRepo.findByProperty(properties, "updated_at",Constant.DESC, offset, limit,null);
		}else {
			objects = productRepo.findByProperty(null, "created_at",Constant.DESC, offset, limit,null);
		}
		
		Long total = (Long) objects[0];
		List<Product> listEntity;
		listEntity = (List<Product>) objects[1];

		for(Product product : listEntity) {
			productDtos.add(product.convertToDto());
		}
		return new Object[]{total,productDtos};

	}

	@Override
	public ProductDto findProductByIdService(Integer id) {
		// TODO Auto-generated method stub
		Product product = productRepo.findById(id);
		ProductDto productdto = product.convertToDto();
		return productdto;
	}
	@Override
	public Object[] findFilterProductService(String[] filter) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		Object[] objects = productRepo.findByProperty(null, "updated_at",Constant.DESC, null, null,filter);
		Long total = (Long) objects[0];
		List<Product> listEntity = (List<Product>) objects[1];
		for(Product product  : listEntity) {
			productDtos.add(product.convertToDto());
		}
		return new Object[]{total,productDtos};
	}

	@Override
	public List<ProductDto> getListSpKhuyenMaiService(int offset,int limit) {
		// TODO Auto-generated method stub
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		List<Product> products = productRepo.getListSpKhuyenMai(offset,limit);
		for(Product product : products) {
			productDtos.add(product.convertToDto());
		}
		return productDtos;
	}

	@Override
	public int getCountSpKmService() {
		// TODO Auto-generated method stub
		return productRepo.getCountSpKhuyenMai();
	}
	@Override
	public Object[] viewPageProductHotService(int offset, int limit) {
		// TODO Auto-generated method stub
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		
		Object[] objects = productRepo.findByProperty(null, "updated_at",Constant.DESC, offset, limit,null);
		
		Long total = (Long) objects[0];
		List<Product> listEntity = (List<Product>) objects[1];
		
		for(Product product : listEntity) {
			productDtos.add(product.convertToDto());
		}
		return new Object[]{total,productDtos};
	}
}
