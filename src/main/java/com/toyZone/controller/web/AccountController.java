package com.toyZone.controller.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.toyZone.utils.GenerateOTP;
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

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

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

    public static String userAccount = null;

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
        if (loginUser != null && !(loginUser.getRoleId() == 3)) {
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

    @RequestMapping(path = "/verify", method = RequestMethod.GET)
    public String verify(ModelMap map, @RequestParam(required = false) String message) {
        map.addAttribute("messaege", message);
        map.addAttribute("userVerify", new UserDto());
        if (message != null) {
            Map<String, String> mesMap = messageRespone.getMessage(message);
            map.addAttribute("message", message);
            map.addAttribute("alert", mesMap.get("alert"));
        }
        return "/web/account/verify";
    }

    @RequestMapping(path = "/verify", method = RequestMethod.POST)
    public String verify(ModelMap map, HttpSession session, @Validated @ModelAttribute("userVerify") UserDto userVer, BindingResult result) {
        String[] filter = {"account", userAccount};
        List<UserDto> users = (List<UserDto>) userService.findFilterUserService(filter)[1];

        System.out.println("Code: " + userVer.getOtpCode() + " userAccount: " + users.get(0).getAccount());
        String userCode = userService.findByIdUserService(users.get(0).getId()).getOtpCode();
        if (userVer.getOtpCode().equals(userCode)) {
            userService.updateUserVerifyStatus(users.get(0).getId());
            System.out.println("UserOTP: " + userVer.getOtpCode() + " userCode: " + userCode);
            map.addAttribute("message", "Kích hoạt tài khoản thành công");
        } else {
            map.addAttribute("message", "Kích hoạt tài khoản thất bại");
        }
        return "redirect:/home";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(ModelMap map, HttpSession session, HttpServletRequest request, @Validated @ModelAttribute("userDk") UserDto userDto, BindingResult bindingResult, @RequestParam(required = false) String message) {
        if (bindingResult.hasErrors()) {
            return "/web/account/register";
        }
        String[] filter = {"account", userDto.getAccount()};
        List<UserDto> users = (List<UserDto>) userService.findFilterUserService(filter)[1];
        if (users != null && users.size() > 0) {
            map.addAttribute("message", "Đăng ký thành công, vui lòng kiểm tra lại");
            return "redirect:/register";
        }
        userDto.setRoleId(Constant.USER);
        userDto.setOtpCode(GenerateOTP.generateOTP());
        userService.saveUserService(userDto);
        userAccount = userDto.getAccount();
        map.addAttribute("message", "Đăng ký thành công, vui lòng kiểm tra hộp thư");

        try {
            //config mail here
            StringBuilder sendText = new StringBuilder();
            String from = "toychildshop@gmail.com";
            sendText.append("<!DOCTYPE html\n" +
                    "   PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                    "   <head>\n" +
                    "      <meta name=\"viewport\" content=\"width=device-width\" />\n" +
                    "      <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                    "      <style>\n" +
                    "         * {\n" +
                    "         margin: 0;\n" +
                    "         font-family: \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n" +
                    "         box-sizing: border-box;\n" +
                    "         font-size: 14px;\n" +
                    "         }\n" +
                    "         img {\n" +
                    "         max-width: 100%;\n" +
                    "         }\n" +
                    "         body {\n" +
                    "         -webkit-font-smoothing: antialiased;\n" +
                    "         -webkit-text-size-adjust: none;\n" +
                    "         width: 100% !important;\n" +
                    "         height: 100%;\n" +
                    "         line-height: 1.6em;\n" +
                    "         }\n" +
                    "         table td {\n" +
                    "         vertical-align: top;\n" +
                    "         }\n" +
                    "         body {\n" +
                    "         background-color: #f6f6f6;\n" +
                    "         }\n" +
                    "         .body-wrap {\n" +
                    "         background-color: #f6f6f6;\n" +
                    "         width: 100%;\n" +
                    "         }\n" +
                    "         .container {\n" +
                    "         display: block !important;\n" +
                    "         max-width: 600px !important;\n" +
                    "         margin: 0 auto !important;\n" +
                    "         /* makes it centered */\n" +
                    "         clear: both !important;\n" +
                    "         }\n" +
                    "         .content {\n" +
                    "         max-width: 600px;\n" +
                    "         margin: 0 auto;\n" +
                    "         display: block;\n" +
                    "         padding: 20px;\n" +
                    "         }\n" +
                    "         .main {\n" +
                    "         background-color: #fff;\n" +
                    "         border: 1px solid #e9e9e9;\n" +
                    "         border-radius: 3px;\n" +
                    "         }\n" +
                    "         .content-wrap {\n" +
                    "         padding: 20px;\n" +
                    "         }\n" +
                    "         .content-block {\n" +
                    "         padding: 0 0 20px;\n" +
                    "         }\n" +
                    "         .last {\n" +
                    "         margin-bottom: 0;\n" +
                    "         }\n" +
                    "         .first {\n" +
                    "         margin-top: 0;\n" +
                    "         }\n" +
                    "         .aligncenter {\n" +
                    "         text-align: center;\n" +
                    "         }\n" +
                    "         .alignright {\n" +
                    "         text-align: right;\n" +
                    "         }\n" +
                    "         .alignleft {\n" +
                    "         text-align: left;\n" +
                    "         }\n" +
                    "         .clear {\n" +
                    "         clear: both;\n" +
                    "         }\n" +
                    "         .invoice {\n" +
                    "         width: 100%;\n" +
                    "         margin: 0;\n" +
                    "         text-align: left;\n" +
                    "         }\n" +
                    "         .invoice td {\n" +
                    "         padding: 5px 0;\n" +
                    "         }\n" +
                    "         .invoice .invoice-items {\n" +
                    "         width: 100%;\n" +
                    "         }\n" +
                    "         .invoice .invoice-items td {\n" +
                    "         border-top: #eee 1px solid;\n" +
                    "         }\n" +
                    "         .invoice .invoice-items .total td {\n" +
                    "         border-top: 2px solid #333;\n" +
                    "         border-bottom: 2px solid #333;\n" +
                    "         font-weight: 700;\n" +
                    "         }\n" +
                    "      </style>\n" +
                    "   </head>\n" +
                    "   <body itemscope itemtype=\"http://schema.org/EmailMessage\">\n" +
                    "      <table class=\"body-wrap\">\n" +
                    "         <tr>\n" +
                    "            <td></td>\n" +
                    "            <td class=\"container\" width=\"600\">\n" +
                    "               <div class=\"content\">\n" +
                    "                  <table class=\"main\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                     <tr>\n" +
                    "                        <td class=\"content-wrap aligncenter\">\n" +
                    "                           <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                              <tr>\n" +
                    "                                 <td class=\"content-block\">\n" +
                    "                                    <h1 class=\"aligncenter\"\n" +
                    "                                       style=\"text-align: center;text-transform: uppercase;margin:0;font-size:14px;height: 260px;\">\n" +
                    "                                       <img src=\"https://drive.google.com/uc?export=view&id=1_yeYUrkUsdrq9g6yx0MLntJjmDHqiszE\" alt=\"ToyZone\"\n" +
                    "                                          style=\"width:250px\" />\n" +
                    "                                    </h1>\n" +
                    "                                 </td>\n" +
                    "                              </tr>\n" +
                    "                              <tr>\n" +
                    "                                 <td style=\"display:block\">\n" +
                    "                                    <h2 class=\"aligncenter\"\n" +
                    "                                       style=\"text-align: center;font-weight: 500;text-transform: uppercase;font-size: 18px;color: #0a9ae8;margin: 0\">\n" +
                    "                                       Xác nhận đăng ký tài khoản\n" +
                    "                                    </h2>\n" +
                    "                                 </td>\n" +
                    "                                 <td style=\"display:block;margin-top:20px;text-align: left\">\n" +
                    "                                    Xin chào <b>" + userDto.getFullName() + "</b>,\n" +
                    "                                 </td>\n" +
                    "                                 <td style=\"display:block;margin-top:10px;text-align: left\">\n" +
                    "                                    Cảm ơn bạn đã đăng ký tài khoản tại <b>ToyZoneShop</b> mã OTP của bạn là: <b>" + userDto.getOtpCode() + "</b> mã này có hiệu lực trong ngày hôm nay\n" +
                    "                                 </td>\n" +
                    "                              </tr>\n" +
                    "                              <tr>\n" +
                    "                                 <td class=\"content-block aligncenter\">\n" +
                    "                                    <table class=\"invoice\">\n" +
                    "                                       <tr>\n" +
                    "                                          <td style=\"display: block\"><span\n" +
                    "                                             style=\"display: inline-block;width: 120px\">Tên tài khoản\n" +
                    "                                             </span><b>" + userDto.getAccount() + "</b>\n" +
                    "                                          </td>\n" +
                    "                                          <td style=\"display: block\"><span\n" +
                    "                                             style=\"display: inline-block;width: 120px\">Họ tên\n" +
                    "                                             </span><b>" + userDto.getFullName() + "</b>\n" +
                    "                                          </td>\n" +
                    "                                          <td style=\"display: block\"><span\n" +
                    "                                             style=\"display: inline-block;width: 120px\">Số điện thoại\n" +
                    "                                             </span><b>" + userDto.getPhone() + "</b>\n" +
                    "                                          </td>\n" +
                    "                                          <td style=\"display: block\"><span\n" +
                    "                                             style=\"display: inline-block;width: 120px\">Thời gian</span>\n" +
                    "                                             <b>" + new Date() + "</b> \n" +
                    "                                          </td>\n" +
                    "                                          <td style=\"display:block\">\n" +
                    "                                             <table class=\"invoice-items\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                                                <tr>\n" +
                    "                                                   <td style=\"text-align: center;padding-top:25px\"><a\n" +
                    "                                                      rel=\"noopener noreferrer\" target=\"_blank\"\n" +
                    "                                                      href=\"http://localhost:8080/toyZone/home\"\n" +
                    "                                                      style=\"display: inline-block;font-weight: 400;text-align: center;white-space: nowrap;vertical-align: middle;padding: .75rem 70px;font-size: 1rem;line-height: 1.5;border-radius: .25rem;background: #0070b6;color: #fff;text-decoration: none;\">Truy cập nhanh</a></td>\n" +
                    "                                                </tr>\n" +
                    "                                             </table>\n" +
                    "                                          </td>\n" +
                    "                                       </tr>\n" +
                    "                                    </table>\n" +
                    "                                 </td>\n" +
                    "                              </tr>\n" +
                    "                              <tr>\n" +
                    "                                 <td\n" +
                    "                                    style=\"font-style: italic;font-size: 12px;padding: 0 0 20px 0;text-align: center\">\n" +
                    "                                    Cảm ơn quý khách đã đăng ký tài khoản tại shop\n" +
                    "                                    chúng tôi. Mọi\n" +
                    "                                    thắc mắc vui lòng liên hệ tổng đài <b>0999999999</b> để được hỗ trợ.\n" +
                    "                                    <br />Xin chân thành cảm ơn\n" +
                    "                                 </td>\n" +
                    "                              </tr>\n" +
                    "                           </table>\n" +
                    "                        </td>\n" +
                    "                     </tr>\n" +
                    "                  </table>\n" +
                    "               </div>\n" +
                    "            </td>\n" +
                    "            <td></td>\n" +
                    "         </tr>\n" +
                    "      </table>\n" +
                    "   </body>\n" +
                    "</html>");
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail);
//            SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
//            UserDto userDtoMail = userService.findByIdUserService(sessionUser.getUserId());
            helper.setFrom(from, from);
            System.out.println(userDto.getEmail());
            helper.setTo(userDto.getEmail());
            helper.setReplyTo(from, from);
            helper.setSubject("[ToyZoneShop] Xác nhận đăng ký tài khoản");
            helper.setText(sendText.toString(), true);
            //send req
            mailSender.send(mail);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session = request.getSession(false);
        return "redirect:/verify";
    }

    @Auth(role = Role.LOGIN)
    @RequestMapping(path = "/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session = request.getSession(false);
        session.removeAttribute("sessionUser");
        return "redirect:/home";
    }
}
