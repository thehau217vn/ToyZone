package com.toyZone.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Auth {
    public enum Role {
        LOGIN,
        ADMIN,
        USER,
    }

    ;

    public Role role() default Role.LOGIN;
}
