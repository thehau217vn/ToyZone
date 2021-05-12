package com.toyZone.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toyZone.dto.ProductDto;
import com.toyZone.service.ProductService;

@Controller
public class ProductWebController {
	@Autowired
	ProductService productService;
	
	@RequestMapping(path = "/category/{idCategory}")
	public String viewProductsCategory(ModelMap map,@PathVariable int idCategory,
									@RequestParam int page ,
									@RequestParam int maxPageItem) {
		Object[] objects = productService.viewPageProductService((page-1)*maxPageItem,maxPageItem,idCategory);
		ProductDto productDto = new ProductDto();
		
		Long l = (Long) objects[0];
		int total = l.intValue();
		productDto.customPage(page, maxPageItem,total,(List<ProductDto>) objects[1]);
		
		map.addAttribute("product",productDto);
		map.addAttribute("idCategory", idCategory);
		return "web/products";
	}
	@RequestMapping(path = "/category/{idCategory}/product/{idProduct}")
	public String viewDetailProduct(ModelMap map,@PathVariable int idProduct,@PathVariable int idCategory) {
		Object[] objects = productService.viewPageProductService(0,4,idCategory);
		ProductDto productDto = productService.findProductByIdService(idProduct);
		
		Long l = (Long) objects[0];
		int total = l.intValue();
		productDto.customPage(0, 4,total,(List<ProductDto>) objects[1]);
		map.addAttribute("product",productDto);
		map.addAttribute("idCategory", idCategory);
		return "web/detail";
	}
	@RequestMapping(path = "/product/find",method = RequestMethod.POST)
	public String viewFindProduct(@RequestParam String name,
									
									ModelMap map) {
		String[] filter = {"name",name};
		Object[] objects = productService.findFilterProductService(filter);
		map.addAttribute("products", (List<ProductDto>) objects[1]);
		return "/web/find";
	}
	
}
