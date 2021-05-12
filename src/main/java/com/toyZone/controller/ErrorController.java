package com.toyZone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
<<<<<<< HEAD
	@RequestMapping("/403")
	public String error403() {
		return "/error/403";
	}
=======
    @RequestMapping("/403")
    public String error403() {
        return "/error/403";
    }
>>>>>>> develop
}
