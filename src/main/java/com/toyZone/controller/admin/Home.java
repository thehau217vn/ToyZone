package com.toyZone.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toyZone.interceptor.Auth;
import com.toyZone.interceptor.Auth.Role;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Controller
public class Home {
    @Auth(role = Role.ADMIN)
    @RequestMapping(path = "/admin/trang-chu", method = RequestMethod.GET)
    public String viewHomePage() {
        return "admin/home";
    }
}
