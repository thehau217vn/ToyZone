package com.toyZone.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Auth {
<<<<<<< HEAD
	public enum Role {
        LOGIN,
        ADMIN,
        USER,
    };
    public Role role() default Role.LOGIN; 
=======
    public enum Role {
        LOGIN,
        ADMIN,
        USER,
    }

    ;

    public Role role() default Role.LOGIN;
>>>>>>> develop
}
