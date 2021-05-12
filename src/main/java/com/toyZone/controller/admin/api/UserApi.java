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

import com.toyZone.dto.UserDto;
import com.toyZone.service.UserService;

@Controller
@RequestMapping(path = "/admin/api/v1/user")
public class UserApi {
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public UserDto insert(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
		UserDto newDto = new UserDto();
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			newDto.setValidated(false);
			newDto.setErrorMessages(errors);
		}else {
			newDto = userService.saveUserService(userDto);
			newDto.setValidated(true);
		}
		return newDto;
	}
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public UserDto update(@RequestBody @Valid UserDto userDto,BindingResult bindingResult) {
		UserDto newDto = new UserDto();
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			newDto.setValidated(false);
			newDto.setErrorMessages(errors);
		}else {
			newDto = userService.saveUserService(userDto);
			newDto.setValidated(true);
		}
		return newDto;
	}
	@RequestMapping(path = "/{idUser}",method = RequestMethod.DELETE)
	@ResponseBody
	public String update(@PathVariable Integer idUser) {
		return userService.deleteUserService(idUser);
	}
}
