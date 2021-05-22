package com.toyZone.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyZone.dto.CategoryDto;
import com.toyZone.model.Category;
import com.toyZone.model.Product;
import com.toyZone.repository.CategoryRepo;
import com.toyZone.repository.ProductRepo;
import com.toyZone.service.CategoryService;
import com.toyZone.service.ProductService;
import com.toyZone.utils.Constant;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepo productRepo;

    @Override
    public List<CategoryDto> viewAllService() {
        // TODO Auto-generated method stub
        List<Category> listEntity = categoryRepo.findAll();

        List<CategoryDto> listDto = new ArrayList<CategoryDto>();
        for (Category c : listEntity) {
            listDto.add(c.convertToDto());
        }
        return listDto;
    }

    @Override
    public CategoryDto saveCategoryService(CategoryDto categoryDto) {
        Category category = categoryDto.convertToEntities();
        CategoryDto newDto = null;
        if (category.getId() == null) {
            category.setCreatedAt(new Date());
            category.setUpdatedAt(new Date());
            category = categoryRepo.insert(category);
            newDto = category.convertToDto();
            newDto.setMessage("insert_success");
        } else {
            category = categoryRepo.findById(category.getId());
            category = category.convertToEntitiy(categoryDto);
            category.setUpdatedAt(new Date());
            category = categoryRepo.update(category);
            newDto = category.convertToDto();
            newDto.setMessage("update_success");
        }
        if (newDto != null) {
            return newDto;
        }
        return null;
    }

    @Override
    public String deleteCategoryService(Integer id) {
        Map<String, Object> property = new HashMap<String, Object>();
        property.put("category_id", id);
        Object[] objects = productRepo.findByProperty(property, null, null, null, null, null);
        List<Product> products = (List<Product>) objects[1];
        for (Product p : products) {
            productService.deleteProductService(p.getId());
        }

        // TODO Auto-generated method stub
        if (categoryRepo.delete(id)) {
            return "success";
        } else {
            return "fail";
        }

    }

    @Override
    public Object[] viewPageCategoryService(int offset, int limit) {
        // TODO Auto-generated method stub
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();
        Object[] objects = categoryRepo.findByProperty(null, "updated_at", Constant.DESC, offset, limit, null);
        Long total = (Long) objects[0];
        List<Category> listEntity = (List<Category>) objects[1];
        for (Category category : listEntity) {
            categoryDtos.add(category.convertToDto());
        }
        return new Object[]{total, categoryDtos};
    }

    @Override
    public CategoryDto findByIdCategoryService(Integer id) {
        // TODO Auto-generated method stub
        Category category = categoryRepo.findById(id);
        CategoryDto categoryDto = category.convertToDto();
        return categoryDto;
    }

    @Override
    public Object[] findFilterCategoryService(String[] filter) {
        // TODO Auto-generated method stub
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();
        Object[] objects = categoryRepo.findByProperty(null, "updated_at", Constant.DESC, null, null, filter);
        Long total = (Long) objects[0];
        List<Category> listEntity = (List<Category>) objects[1];
        for (Category category : listEntity) {
            categoryDtos.add(category.convertToDto());
        }
        return new Object[]{total, categoryDtos};
    }


}
