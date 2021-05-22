package com.toyZone.controller.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toyZone.dto.ProductDto;
import com.toyZone.dto.SessionGioHang;
import com.toyZone.service.CategoryService;
import com.toyZone.service.ProductService;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Controller
public class HomeController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String viewHome(ModelMap map, HttpSession session) {
        List<ProductDto> list = (List<ProductDto>) productService.viewPageProductService(0, 4, 0)[1];
        List<ProductDto> listKm = productService.getListSpKhuyenMaiService(0, 4);
        List<ProductDto> listhot = (List<ProductDto>) productService.viewPageProductHotService(0, 4)[1];
        if (session.getAttribute("gioHang") == null) {
            session.setAttribute("gioHang", new SessionGioHang());
        }
        map.addAttribute("products", list);
        map.addAttribute("listKm", listKm);
        map.addAttribute("listhot", listhot);
        return "/web/home";
    }

    @RequestMapping(path = "/khuyenmai", method = RequestMethod.GET)
    public String viewKhuyenMaiPage(ModelMap map, HttpSession session, @RequestParam int page,
                                    @RequestParam int maxPageItem) {
        List<ProductDto> listKm = productService.getListSpKhuyenMaiService((page - 1) * maxPageItem, maxPageItem);
        ProductDto productDto = new ProductDto();
        int total = productService.getCountSpKmService();
        productDto.customPage(page, maxPageItem, total, listKm);
        map.addAttribute("product", productDto);
        return "/web/khuyenmai";
    }
}
