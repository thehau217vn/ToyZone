package com.toyZone.controller.web;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toyZone.dto.AccountDto;
import com.toyZone.dto.SessionUser;
import com.toyZone.dto.UserDto;
import com.toyZone.interceptor.Auth;
import com.toyZone.interceptor.Auth.Role;
import com.toyZone.service.RoleService;
import com.toyZone.service.UserService;
import com.toyZone.utils.Constant;
import com.toyZone.utils.MessageRespone;


@Controller
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    MessageRespone messageRespone;
    @Autowired
    JavaMailSender mailSender;

    @Auth(role = Role.LOGIN)
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(ModelMap map) {
        map.addAttribute("user", new UserDto());
        return "/web/account/login";
    }

    @Auth(role = Role.LOGIN)
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(ModelMap map, HttpSession session, @Validated @ModelAttribute("user") AccountDto user, BindingResult result) {
        UserDto loginUser = null;
        if (result.hasErrors()) {
            map.addAttribute("user", user);
            return "/web/account/login";
        }
        loginUser = userService.getUserByUserNameAndPassWordService(user.getAccount(), user.getPassword());
        if (loginUser != null) {
            SessionUser sessionUser = new SessionUser();
            sessionUser.setUserId(loginUser.getId());
            sessionUser.setFullName(loginUser.getFullName());
            sessionUser.setRole(roleService.getRoleById(loginUser.getRoleId()).getName());
            sessionUser.setCheckLogin(true);
            session.setAttribute("sessionUser", sessionUser);
            if (loginUser.getRoleId() == Constant.ADMIN) {
                return "redirect:/admin/trang-chu";
            } else {
                return "redirect:/home";
            }
        } else {
            map.addAttribute("user", user);
            map.addAttribute("message", "Tài khoản hoặc mật khẩu không đúng");
            return "/web/account/login";
        }
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(ModelMap map, @RequestParam(required = false) String message) {
        map.addAttribute("message", message);
        map.addAttribute("userDk", new UserDto());
        if (message != null) {
            Map<String, String> mesMap = messageRespone.getMessage(message);
            map.addAttribute("message", mesMap.get("message"));
            map.addAttribute("alert", mesMap.get("alert"));
        }
        return "/web/account/register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(ModelMap map, HttpSession session, HttpServletRequest request, @Validated @ModelAttribute("userDk") UserDto userDto, BindingResult bindingResult, @RequestParam(required = false) String message) {
        if (bindingResult.hasErrors()) {
            return "/web/account/register";
        }
        String[] filter = {"account", userDto.getAccount()};
        List<UserDto> users = (List<UserDto>) userService.findFilterUserService(filter)[1];
        if (users != null && users.size() > 0) {
            map.addAttribute("message", "error_register");
            return "redirect:/register";
        }
        userDto.setRoleId(Constant.USER);
        userService.saveUserService(userDto);
        map.addAttribute("message", "success_register");

        //mail here

        try {
            //config mail here
            StringBuilder sendText = new StringBuilder();
            String from = "toychildshop@gmail.com";
            sendText.append(
                    "<p>Bạn đã đăng ký thành công"
            );
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail);

//            SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
//            UserDto userDtoMail = userService.findByIdUserService(sessionUser.getUserId());
            helper.setFrom(from, from);
            System.out.println(userDto.getEmail());
            helper.setTo(userDto.getEmail());
            helper.setReplyTo(from, from);
            helper.setSubject("Thank You !");
            helper.setText(sendText.toString(), true);
            //send req
            mailSender.send(mail);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session = request.getSession(false);
        return "redirect:/home";

    }

    @Auth(role = Role.LOGIN)
    @RequestMapping(path = "/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session = request.getSession(false);
        session.removeAttribute("sessionUser");
        return "redirect:/home";
    }
}
