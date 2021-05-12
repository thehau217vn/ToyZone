package com.toyZone.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.toyZone.service.CategoryService;

public class CategoryInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	CategoryService categoryService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("menuCate", categoryService.viewAllService());
		return true;
	}
}
