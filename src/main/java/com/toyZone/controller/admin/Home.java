package com.toyZone.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toyZone.interceptor.Auth;
import com.toyZone.interceptor.Auth.Role;

@Controller
public class Home {
<<<<<<< HEAD
	@Auth(role = Role.ADMIN)
	@RequestMapping(path = "/admin/trang-chu",method = RequestMethod.GET)
	public String viewHomePage() {
		return "admin/home";
	}
=======
    @Auth(role = Role.ADMIN)
    @RequestMapping(path = "/admin/trang-chu", method = RequestMethod.GET)
    public String viewHomePage() {
        return "admin/home";
    }
>>>>>>> develop
}
