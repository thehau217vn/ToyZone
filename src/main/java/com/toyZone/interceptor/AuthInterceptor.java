package com.toyZone.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.toyZone.dto.SessionUser;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public class AuthInterceptor implements HandlerInterceptor {
    @Value("${user.login}")
    String loginUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Auth roleAnnotation = AnnotationUtils.findAnnotation(method, Auth.class);
        Auth.Role role = roleAnnotation != null ? roleAnnotation.role() : null;
        HttpSession session = request.getSession();

        if (session.getAttribute("sessionUser") != null) {
            SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
            boolean isLogined = sessionUser.getCheckLogin();

            Auth.Role loginRole = sessionUser.getRole() != null ? Auth.Role.valueOf(sessionUser.getRole().toUpperCase()) : null;

            if (role != null) {
                if (!isLogined) {
                    response.sendRedirect(request.getContextPath() + loginUrl);
                    return false;
                } else {
                    if (role != Auth.Role.LOGIN && role != loginRole) {
                        response.sendRedirect(request.getContextPath() + "/error/403");
                        return false;
                    }
                }
            }
        } else {
            response.sendRedirect(request.getContextPath() + loginUrl);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
