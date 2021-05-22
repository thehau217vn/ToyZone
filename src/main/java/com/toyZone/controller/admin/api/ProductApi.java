package com.toyZone.controller.admin.api;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.toyZone.dto.CategoryDto;
import com.toyZone.dto.ProductDto;
import com.toyZone.service.CategoryService;
import com.toyZone.service.ProductService;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Controller
@RequestMapping(path = "/admin/api/v1/product")
public class ProductApi {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    ServletContext context;

    @Value("${product.url}")
    String productUrl;

    @Value("${page.page}")
    String page;

    @Value("${page.maxPageItem}")
    String maxPageItem;


    @RequestMapping(method = RequestMethod.POST)
    public String saveProduct(@RequestParam MultipartFile file,
                              @RequestParam int idCategory,
                              HttpServletRequest request,
                              ModelMap map,
                              @Validated @ModelAttribute("product") ProductDto productDto,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            CategoryDto cate = new CategoryDto();
            cate.setListResult(categoryService.viewAllService());
            map.addAttribute("category", cate);
            map.addAttribute("idCategory", idCategory);
            return "admin/product/edit";
        }
        String message = "update_success";
        if (productDto.getId() == null) {
            if (file.isEmpty()) {
                message = "error_upload";
                return "redirect:" + productUrl + "/" + idCategory + "/edit/?message=" + message;
            } else {
                try {
                    String path = context.getRealPath("/images/" + file.getOriginalFilename());
                    File newfile = new File(path);
                    System.out.println(path);
                    if (newfile.exists()) {
                        message = "error_upload_exist";
                        return "redirect:" + productUrl + "/" + idCategory + "/edit/?message=" + message;
                    } else {
                        file.transferTo(newfile);
                        productDto.setImage("/images/" + file.getOriginalFilename());
                        message = "insert_success";
                    }


                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            if (!file.isEmpty()) {
                String path = context.getRealPath("/images/" + file.getOriginalFilename());
                File newfile = new File(path);
                try {
                    if (newfile.exists()) {
                        message = "error_upload_exist";
                        return "redirect:" + productUrl + "/" + idCategory + "/edit/" + productDto.getId() + "?message=" + message;
                    } else {
                        File oldfile = new File(context.getRealPath(productDto.getImage()));
                        oldfile.delete();

                        file.transferTo(newfile);
                        productDto.setImage("/images/" + file.getOriginalFilename());
                        message = "update_success";
                    }
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        productDto.setIdCategory(idCategory);
        ProductDto dto = productService.saveProductService(productDto);
        map.addAttribute("product", dto);

        return "redirect:" + productUrl + "/" + idCategory + "?page=" + page + "&maxPageItem=" + maxPageItem + "&message=" + message;

    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable int id) {

        return productService.deleteProductService(id);
    }
}
