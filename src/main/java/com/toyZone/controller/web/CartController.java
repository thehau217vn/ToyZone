package com.toyZone.controller.web;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toyZone.dto.OrderDto;
import com.toyZone.dto.ProductDto;
import com.toyZone.dto.ProductOrderDto;
import com.toyZone.dto.SessionGioHang;
import com.toyZone.dto.SessionUser;
import com.toyZone.dto.UserDto;
import com.toyZone.interceptor.Auth;
import com.toyZone.interceptor.Auth.Role;
import com.toyZone.service.OrderService;
import com.toyZone.service.ProductOrderService;
import com.toyZone.service.ProductService;
import com.toyZone.service.UserService;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductOrderService productOrderService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    JavaMailSender mailSender;

    @RequestMapping(path = "/cart", method = RequestMethod.GET)
    public String viewCartProduct(ModelMap map, HttpSession session, @RequestParam(required = false) String message) {
        List<ProductDto> listSpDto = new ArrayList<ProductDto>();
        SessionGioHang gioHang = (SessionGioHang) session.getAttribute("gioHang");
        if (gioHang != null) {
            for (ProductOrderDto productOrderDto : gioHang.getGioHangs()) {
                ProductDto dto = productService.findProductByIdService(productOrderDto.getIdProduct());
                dto.setTotalItem(productOrderDto.getCount());
                dto.setTotalPrices(productOrderDto.getPrice());
                listSpDto.add(dto);
            }
        }
        map.addAttribute("message", message);
        map.addAttribute("orderdto", new OrderDto());
        map.addAttribute("productsCart", listSpDto);
        return "web/giohang";
    }

    @RequestMapping(path = "/api/v1/cart", method = RequestMethod.POST)
    @ResponseBody
    public String themSanPhamVaoGioHang(@RequestBody ProductOrderDto productOrderDto, HttpSession session) {
        boolean checkTonTai = false;
        int existProduct = -1;
        int vtExits = -1;
        long total = 0;
        int totalItem = 0;
        SessionGioHang gioHang = (SessionGioHang) session.getAttribute("gioHang");
        ProductDto productDto = productService.findProductByIdService(productOrderDto.getIdProduct());

        if (productDto.getCount() < productOrderDto.getCount()) {
            return "fail";
        }
        if (productOrderDto.getCount() < 1) {
            return "fail";
        }
        for (int i = 0; i < gioHang.getGioHangs().size(); i++) {
            if (productOrderDto.getIdProduct() == gioHang.getGioHangs().get(i).getIdProduct()) {
                checkTonTai = true;
                existProduct = gioHang.getGioHangs().get(i).getCount();
                vtExits = i;
                break;
            }
        }

        if (checkTonTai) {
            productOrderDto.setCount(productOrderDto.getCount() + existProduct);
            productOrderDto.setPrice(
                    (long) ((productOrderDto.getPrice() - productOrderDto.getPrice() * (productDto.getDiscount() / 100))
                            * productOrderDto.getCount()));
            gioHang.getGioHangs().set(vtExits, productOrderDto);
            gioHang.setGioHangs(gioHang.getGioHangs());
            for (ProductOrderDto dto : gioHang.getGioHangs()) {
                total += dto.getPrice();
                totalItem += dto.getCount();
            }
            NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
            gioHang.setTotalPrice(nf.format(total));
            gioHang.setTotalItem(totalItem);
        } else {
            productOrderDto.setPrice(
                    (long) ((productOrderDto.getPrice() - productOrderDto.getPrice() * (productDto.getDiscount() / 100))
                            * productOrderDto.getCount()));
            gioHang.getGioHangs().add(productOrderDto);
            gioHang.setGioHangs(gioHang.getGioHangs());

            for (ProductOrderDto dto : gioHang.getGioHangs()) {
                total += dto.getPrice();
                totalItem += dto.getCount();
            }
            NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
            gioHang.setTotalPrice(nf.format(total));
            gioHang.setTotalItem(totalItem);
        }

        return "success";
    }

    @RequestMapping(path = "/api/v1/cart/{idProduct}", method = RequestMethod.DELETE)
    @ResponseBody
    public String xoaSanPhamRaGioHang(@PathVariable int idProduct, HttpSession session) {
        SessionGioHang gioHang = (SessionGioHang) session.getAttribute("gioHang");
        ProductOrderDto spXoa = null;
        for (ProductOrderDto dto : gioHang.getGioHangs()) {
            if (dto.getIdProduct() == idProduct) {
                spXoa = dto;
                break;
            }
        }
        gioHang.getGioHangs().remove(spXoa);
        gioHang.setGioHangs(gioHang.getGioHangs());
        long total = 0;
        int totalItem = 0;
        for (ProductOrderDto dto : gioHang.getGioHangs()) {
            total += dto.getPrice();
            totalItem += dto.getCount();
        }
        NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
        gioHang.setTotalPrice(nf.format(total));
        gioHang.setTotalItem(totalItem);
        return "success";
    }

    @Auth(role = Role.LOGIN)
    @RequestMapping(path = "/order", method = RequestMethod.POST)
    public String insertOrder(ModelMap map, HttpSession session, HttpServletRequest request, @Validated @ModelAttribute("orderdto") OrderDto orderdto, BindingResult bindingResult) {
        SessionGioHang gioHang = (SessionGioHang) session.getAttribute("gioHang");
        if (bindingResult.hasErrors()) {
            List<ProductDto> listSpDto = new ArrayList<ProductDto>();
            if (gioHang != null) {
                for (ProductOrderDto productOrderDto : gioHang.getGioHangs()) {
                    ProductDto dto = productService.findProductByIdService(productOrderDto.getIdProduct());
                    dto.setTotalItem(productOrderDto.getCount());
                    dto.setTotalPrices(productOrderDto.getPrice());
                    listSpDto.add(dto);
                }
            }
            map.addAttribute("message", "");
            map.addAttribute("order", new OrderDto());
            map.addAttribute("productsCart", listSpDto);
            return "web/giohang";
        }
        if (gioHang.getGioHangs().size() <= 0) {
            return "redirect:/home";
        }
        try {
            StringBuilder sendText = new StringBuilder();
            String from = "toychildshop@gmail.com";
            //format mail
            sendText.append("<style type=\"text/css\">\n" + "table {\n" + "	border-collapse: collapse;\n"
                    + "	width: 100%;\n" + "	}\n" + "	th,td{\n" + "		line-height: 25px;\n"
                    + "		border: 1px solid black;\n" + "		padding:5px;\n" + "	}\n" + "	th{\n"
                    + "		background-color: gray;\n" + "	}\n" + "	</style>");
            sendText.append(
                    "<p>Cảm ơn bạn đã mua hàng tại ShopToyHVN ! Mặt hàng của bạn sẽ được giao trong 2-3 ngày ! Cảm ơn bạn : </p>");
            sendText.append("<h1 style=" + "color:blue;" + ">ToyHVN</h1>");

            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail);

            SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
            UserDto userDtoMail = userService.findByIdUserService(sessionUser.getUserId());

            sendText.append("<p> Ngày mua: " + orderdto.getCreatedDate() + "</p>");
            sendText.append("<p> Người gửi : " + userDtoMail.getFullName() + "</p>");
            sendText.append("<p> Người mua: " + orderdto.getReceiver() + "</p>");
            sendText.append("<p> Địa chỉ giao hàng: " + orderdto.getDeliveryAddress() + "</p>");
            sendText.append("<p> Số điện thoại người nhận : " + orderdto.getPhone() + "</p>");
            sendText.append("<p> Nội dung : " + orderdto.getContent() + "</p>");
            sendText.append("<table><tr>");
            sendText.append("<th>Mã</th>").append("<th>Tên Sản Phẩm</th>").append("<th>Giá</th>")
                    .append("<th>Giảm giá</th>").append("<th>Số Lượng</th>").append("<th>Tổng Tiền</th>");
            sendText.append("</tr>");

            helper.setFrom(from, from);
            helper.setTo(userDtoMail.getEmail());
            helper.setReplyTo(from, from);
            helper.setSubject("Hóa đơn điện tử");
            helper.setText(sendText.toString(), true);
            mailSender.send(mail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session = request.getSession(false);
        session.removeAttribute("gioHang");
        return "redirect:/home";
    }
}
