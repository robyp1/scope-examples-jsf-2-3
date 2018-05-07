package com.bluelotussoftware.example.cdi;

import com.sun.javafx.css.SizeUnits;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME )
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface LoggingMethod {

    @Nonbinding
    LogLevel value() default LogLevel.SEVERE;
}
