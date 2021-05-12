package com.toyZone.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toyZone.dto.OrderDto;
import com.toyZone.dto.ProductDto;
import com.toyZone.dto.ProductOrderDto;
import com.toyZone.service.OrderService;
import com.toyZone.service.ProductOrderService;
import com.toyZone.service.ProductService;
import com.toyZone.utils.MessageRespone;

@Controller
@RequestMapping("/admin")
public class OrderController {
	@Autowired
	MessageRespone messageRespone;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductOrderService productOrderService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/order/da-giao",method = RequestMethod.GET)
	public String viewOrderStatusTrue(@RequestParam int page, @RequestParam int maxPageItem,
			@RequestParam(required = false) String message, ModelMap map) {
		Object[] objects = orderService.viewPageOrderServiceByStatus((page-1)*maxPageItem,maxPageItem,true);
		
		OrderDto orderDto = new OrderDto();
		Long l = (Long) objects[0];
		int total = l.intValue();
		orderDto.customPage(page, maxPageItem,total,(List<OrderDto>) objects[1]);
		
		if(message!=null) {
			Map<String, String> mesMap= messageRespone.getMessage(message);
			map.addAttribute("message",mesMap.get("message"));
			map.addAttribute("alert",mesMap.get("alert"));
		}
		
		map.addAttribute("orderDto",orderDto);
		map.addAttribute("order", new OrderDto());
		
		return "admin/order/list";
		
	}
	@RequestMapping(value = "/order/chua-giao",method = RequestMethod.GET)
	public String viewOrderStatusFalse(@RequestParam int page, @RequestParam int maxPageItem,
			@RequestParam(required = false) String message, ModelMap map) {
		Object[] objects = orderService.viewPageOrderServiceByStatus((page-1)*maxPageItem,maxPageItem,false);
		
		OrderDto orderDto = new OrderDto();
		Long l = (Long) objects[0];
		int total = l.intValue();
		orderDto.customPage(page, maxPageItem,total,(List<OrderDto>) objects[1]);
		
		if(message!=null) {
			Map<String, String> mesMap= messageRespone.getMessage(message);
			map.addAttribute("message",mesMap.get("message"));
			map.addAttribute("alert",mesMap.get("alert"));
		}
		
		map.addAttribute("orderDto",orderDto);
		map.addAttribute("order", new OrderDto());
		
		return "admin/order/list";
		
	}
	@RequestMapping(path = "/order/edit/{idOrder}",method = RequestMethod.GET)
	public String viewEditUser(@PathVariable int idOrder,@RequestParam(value = "message",required = false) String message,ModelMap map) {
		long totalMoney = 0;
		OrderDto orderDto = new OrderDto();
		orderDto = orderService.findByIdOrderService(idOrder);
		List<ProductOrderDto> productOrderDtos = productOrderService.getListProductOrderDtoByOrder(idOrder);
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		for(ProductOrderDto po : productOrderDtos) {
			ProductDto dto = productService.findProductByIdService(po.getIdProduct());
			productDtos.add(dto);
			totalMoney += po.getPrice();
		}
		if(message!=null) {
			Map<String, String> mesMap= messageRespone.getMessage(message);
			map.addAttribute("message",mesMap.get("message"));
			map.addAttribute("alert",mesMap.get("alert"));
		}
		map.addAttribute("orderDto", orderDto);
		map.addAttribute("productOrderDtos", productOrderDtos);
		map.addAttribute("productDtos", productDtos);
		map.addAttribute("totalMoney", totalMoney);
		return "admin/order/edit";
	}
	@RequestMapping(path = "/order/find",method = RequestMethod.POST)
	public String findOrder(ModelMap map,@ModelAttribute OrderDto order) {
		OrderDto orderDto = orderService.findByIdOrderService(order.getId());
		if(orderDto!=null) {
			map.addAttribute("order", new OrderDto());
			map.addAttribute("orderFind", orderDto);
		}else {
			Map<String, String> mesMap= messageRespone.getMessage("error_find");
			map.addAttribute("message", mesMap.get("message"));
			map.addAttribute("alert",mesMap.get("alert"));map.addAttribute("order", new OrderDto());
			map.addAttribute("order", new OrderDto());
		}
		
		return "admin/order/find";
	}
	@RequestMapping(path = "/thong-ke",method = RequestMethod.GET)
	public String viewFormThongKe(ModelMap map) {
		map.addAttribute("message","");
		
		return "/admin/thongke/form";
	}
	@RequestMapping(path = "/thong-ke",method = RequestMethod.POST)
	public String thongKe(ModelMap map,@RequestParam("tungay") String tungay,@RequestParam("denngay") String denngay) throws Exception {
		long total = 0;
		List<String[]> list = orderService.thongKeBanHang(tungay,denngay);
		for(String[] item : list) {
			total += Long.parseLong(item[4]) ;
		}
		map.addAttribute("tuNgay", tungay);
		map.addAttribute("denNgay", denngay);
		map.addAttribute("totalMoney", total);
		map.addAttribute("list", list);
		return "/admin/thongke/baocao";
	}
}
