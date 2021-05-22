package com.toyZone.service;

import java.util.List;

import com.toyZone.dto.CategoryDto;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public interface CategoryService {
    List<CategoryDto> viewAllService();

    CategoryDto saveCategoryService(CategoryDto categoryDto);

    String deleteCategoryService(Integer id);

    Object[] viewPageCategoryService(int offset, int limit);

    CategoryDto findByIdCategoryService(Integer id);

    Object[] findFilterCategoryService(String[] filter);
}
