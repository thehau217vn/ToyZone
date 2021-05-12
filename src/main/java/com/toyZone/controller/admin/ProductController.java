package com.toyZone.controller.admin;

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

import com.toyZone.dto.CategoryDto;
import com.toyZone.dto.ProductDto;
import com.toyZone.service.CategoryService;
import com.toyZone.service.ProductService;
import com.toyZone.utils.MessageRespone;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
<<<<<<< HEAD
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	MessageRespone messageRespone;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewAllCategory(@RequestParam int page, 
								@RequestParam int maxPageItem,
								ModelMap map) {
		Object[] objects = categoryService.viewPageCategoryService((page-1)*maxPageItem,maxPageItem);
		CategoryDto categoryDto = new CategoryDto();
		ProductDto productDto = new ProductDto();
		Long l = (Long) objects[0];
		int total = l.intValue();
		categoryDto.customPage(page, maxPageItem,total,(List<CategoryDto>) objects[1]);
		
		map.addAttribute("category",categoryDto);
		map.addAttribute("product",productDto);
		return "admin/product/listcategory";
	}
	@RequestMapping(method = RequestMethod.GET,path = "/{idCategory}")
	public String viewProduct(@PathVariable int idCategory,
								@RequestParam int page ,
								@RequestParam int maxPageItem,
								@RequestParam(value = "message",required = false) String message,
								ModelMap map) {
		Object[] objects = productService.viewPageProductService((page-1)*maxPageItem,maxPageItem,idCategory);
		ProductDto productDto = new ProductDto();
		
		Long l = (Long) objects[0];
		int total = l.intValue();
		productDto.customPage(page, maxPageItem,total,(List<ProductDto>) objects[1]);
		if(message!=null) {
			Map<String, String> mesMap= messageRespone.getMessage(message);
			map.addAttribute("message",mesMap.get("message"));
			map.addAttribute("alert",mesMap.get("alert"));
		}
		map.addAttribute("product",productDto);
		map.addAttribute("idCategory", idCategory);
		return "admin/product/list";
	}
	@RequestMapping(path = "/{idCategory}/edit/",method = RequestMethod.GET)
	public String viewAddProduct(@PathVariable int idCategory,
									@RequestParam(value = "message",required = false) String message
									,ModelMap map) {
		
		CategoryDto cate = new CategoryDto();
		cate.setListResult(categoryService.viewAllService());
		map.addAttribute("category",cate);
		
		ProductDto productDto = new ProductDto();
		if(message!=null) {
			Map<String, String> mesMap= messageRespone.getMessage(message);
			map.addAttribute("message",mesMap.get("message"));
			map.addAttribute("alert",mesMap.get("alert"));
		}
		map.addAttribute("product", productDto);
		map.addAttribute("idCategory", idCategory);
		
		return "admin/product/edit";
		
	}
	@RequestMapping(path = "/{idCategory}/edit/{idproduct}",method = RequestMethod.GET)
	public String viewUpdateProduct(@PathVariable int idCategory,@PathVariable int idproduct,
									@RequestParam(value = "message",required = false) String message
									,ModelMap map) {
		
		CategoryDto cate = new CategoryDto();
		cate.setListResult(categoryService.viewAllService());
		map.addAttribute("category",cate);
		
		ProductDto productDto = productService.findProductByIdService(idproduct);
		if(message!=null) {
			Map<String, String> mesMap= messageRespone.getMessage(message);
			map.addAttribute("message",mesMap.get("message"));
			map.addAttribute("alert",mesMap.get("alert"));
		}
		map.addAttribute("product", productDto);
		map.addAttribute("idCategory", idCategory);
		return "admin/product/edit";
		
	}
	@RequestMapping(path = "/find",method = RequestMethod.POST)
	public String viewFindCategory(@ModelAttribute ProductDto productDto,
									
									ModelMap map) {
		String[] filter = {"name",productDto.getName()};
		Object[] objects = productService.findFilterProductService(filter);
		Long l = (Long) objects[0];
		int total = l.intValue();
		productDto.setListResult((List<ProductDto>) objects[1]);
		map.addAttribute("product", productDto);
		//map.addAttribute("idCaterogy", productDto.getIdCategory());
		return "admin/product/find";
	}
=======
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    MessageRespone messageRespone;

    @RequestMapping(method = RequestMethod.GET)
    public String viewAllCategory(@RequestParam int page,
                                  @RequestParam int maxPageItem,
                                  ModelMap map) {
        Object[] objects = categoryService.viewPageCategoryService((page - 1) * maxPageItem, maxPageItem);
        CategoryDto categoryDto = new CategoryDto();
        ProductDto productDto = new ProductDto();
        Long l = (Long) objects[0];
        int total = l.intValue();
        categoryDto.customPage(page, maxPageItem, total, (List<CategoryDto>) objects[1]);

        map.addAttribute("category", categoryDto);
        map.addAttribute("product", productDto);
        return "admin/product/listcategory";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{idCategory}")
    public String viewProduct(@PathVariable int idCategory,
                              @RequestParam int page,
                              @RequestParam int maxPageItem,
                              @RequestParam(value = "message", required = false) String message,
                              ModelMap map) {
        Object[] objects = productService.viewPageProductService((page - 1) * maxPageItem, maxPageItem, idCategory);
        ProductDto productDto = new ProductDto();

        Long l = (Long) objects[0];
        int total = l.intValue();
        productDto.customPage(page, maxPageItem, total, (List<ProductDto>) objects[1]);
        if (message != null) {
            Map<String, String> mesMap = messageRespone.getMessage(message);
            map.addAttribute("message", mesMap.get("message"));
            map.addAttribute("alert", mesMap.get("alert"));
        }
        map.addAttribute("product", productDto);
        map.addAttribute("idCategory", idCategory);
        return "admin/product/list";
    }

    @RequestMapping(path = "/{idCategory}/edit/", method = RequestMethod.GET)
    public String viewAddProduct(@PathVariable int idCategory,
                                 @RequestParam(value = "message", required = false) String message
            , ModelMap map) {

        CategoryDto cate = new CategoryDto();
        cate.setListResult(categoryService.viewAllService());
        map.addAttribute("category", cate);

        ProductDto productDto = new ProductDto();
        if (message != null) {
            Map<String, String> mesMap = messageRespone.getMessage(message);
            map.addAttribute("message", mesMap.get("message"));
            map.addAttribute("alert", mesMap.get("alert"));
        }
        map.addAttribute("product", productDto);
        map.addAttribute("idCategory", idCategory);

        return "admin/product/edit";

    }

    @RequestMapping(path = "/{idCategory}/edit/{idproduct}", method = RequestMethod.GET)
    public String viewUpdateProduct(@PathVariable int idCategory, @PathVariable int idproduct,
                                    @RequestParam(value = "message", required = false) String message
            , ModelMap map) {

        CategoryDto cate = new CategoryDto();
        cate.setListResult(categoryService.viewAllService());
        map.addAttribute("category", cate);

        ProductDto productDto = productService.findProductByIdService(idproduct);
        if (message != null) {
            Map<String, String> mesMap = messageRespone.getMessage(message);
            map.addAttribute("message", mesMap.get("message"));
            map.addAttribute("alert", mesMap.get("alert"));
        }
        map.addAttribute("product", productDto);
        map.addAttribute("idCategory", idCategory);
        return "admin/product/edit";

    }

    @RequestMapping(path = "/find", method = RequestMethod.POST)
    public String viewFindCategory(@ModelAttribute ProductDto productDto,

                                   ModelMap map) {
        String[] filter = {"name", productDto.getName()};
        Object[] objects = productService.findFilterProductService(filter);
        Long l = (Long) objects[0];
        int total = l.intValue();
        productDto.setListResult((List<ProductDto>) objects[1]);
        map.addAttribute("product", productDto);
        //map.addAttribute("idCaterogy", productDto.getIdCategory());
        return "admin/product/find";
    }
>>>>>>> develop
}
