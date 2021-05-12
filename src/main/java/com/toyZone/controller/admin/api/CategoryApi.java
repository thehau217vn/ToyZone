package com.toyZone.controller.admin.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toyZone.dto.CategoryDto;
import com.toyZone.service.CategoryService;

@Controller
@RequestMapping(path = "/admin/api/v1/category/")
public class CategoryApi {
<<<<<<< HEAD
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public CategoryDto create(@RequestBody @Valid CategoryDto categoryDto, BindingResult bindingResult) {

		CategoryDto newDto = new CategoryDto();

		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			newDto.setValidated(false);
			newDto.setErrorMessages(errors);
		} else {
			newDto = categoryService.saveCategoryService(categoryDto);
			newDto.setValidated(true);
		}

		return newDto;
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public CategoryDto update(@RequestBody @Valid CategoryDto categoryDto, BindingResult bindingResult) {
		CategoryDto newDto = new CategoryDto();

		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			newDto.setValidated(false);
			newDto.setErrorMessages(errors);
		} else {
			newDto = categoryService.saveCategoryService(categoryDto);
			newDto.setValidated(true);
		}

		return newDto;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable int id) {
		return categoryService.deleteCategoryService(id);
	}
=======
    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public CategoryDto create(@RequestBody @Valid CategoryDto categoryDto, BindingResult bindingResult) {

        CategoryDto newDto = new CategoryDto();

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            newDto.setValidated(false);
            newDto.setErrorMessages(errors);
        } else {
            newDto = categoryService.saveCategoryService(categoryDto);
            newDto.setValidated(true);
        }

        return newDto;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public CategoryDto update(@RequestBody @Valid CategoryDto categoryDto, BindingResult bindingResult) {
        CategoryDto newDto = new CategoryDto();

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            newDto.setValidated(false);
            newDto.setErrorMessages(errors);
        } else {
            newDto = categoryService.saveCategoryService(categoryDto);
            newDto.setValidated(true);
        }

        return newDto;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable int id) {
        return categoryService.deleteCategoryService(id);
    }
>>>>>>> develop

}
