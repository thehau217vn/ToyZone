package com.toyZone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Controller
@RequestMapping("/error")
public class ErrorController {
    @RequestMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
