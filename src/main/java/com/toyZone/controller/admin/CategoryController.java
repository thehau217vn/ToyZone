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
import com.toyZone.service.CategoryService;
import com.toyZone.utils.MessageRespone;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Controller
@RequestMapping(path = "/admin/category")
public class CategoryController {
    @Autowired
    MessageRespone messageRespone;
    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewListCategory(@RequestParam int page,
                                   @RequestParam int maxPageItem,
                                   @RequestParam(required = false) String message,
                                   ModelMap map) {
        Object[] objects = categoryService.viewPageCategoryService((page - 1) * maxPageItem, maxPageItem);

        CategoryDto categoryDto = new CategoryDto();
        Long l = (Long) objects[0];
        int total = l.intValue();
        categoryDto.customPage(page, maxPageItem, total, (List<CategoryDto>) objects[1]);

        if (message != null) {
            Map<String, String> mesMap = messageRespone.getMessage(message);
            map.addAttribute("message", mesMap.get("message"));
            map.addAttribute("alert", mesMap.get("alert"));
        }

        map.addAttribute("category", categoryDto);

        return "admin/category/list";
    }

    @RequestMapping(path = "/edit/", method = RequestMethod.GET)
    public String viewAddCategory(@RequestParam(value = "message", required = false) String message, ModelMap map) {
        CategoryDto categoryDto = new CategoryDto();

        if (message != null) {
            Map<String, String> mesMap = messageRespone.getMessage(message);
            map.addAttribute("message", mesMap.get("message"));
            map.addAttribute("alert", mesMap.get("alert"));
        }
        map.addAttribute("category", categoryDto);
        return "admin/category/edit";

    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String viewEditCategory(@PathVariable(required = false) Integer id,
                                   @RequestParam(value = "message", required = false) String message, ModelMap map) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto = categoryService.findByIdCategoryService(id);
        if (message != null) {
            Map<String, String> mesMap = messageRespone.getMessage(message);
            map.addAttribute("message", mesMap.get("message"));
            map.addAttribute("alert", mesMap.get("alert"));
        }
        map.addAttribute("category", categoryDto);
        return "admin/category/edit";

    }

    @RequestMapping(path = "/find", method = RequestMethod.POST)
    public String viewFindCategory(@ModelAttribute CategoryDto category,
                                   ModelMap map) {
        String[] filter = {"name", category.getName()};
        Object[] objects = categoryService.findFilterCategoryService(filter);
        Long l = (Long) objects[0];
        int total = l.intValue();
        category.setListResult((List<CategoryDto>) objects[1]);
        map.addAttribute("category", category);
        return "admin/category/find";
    }


}
