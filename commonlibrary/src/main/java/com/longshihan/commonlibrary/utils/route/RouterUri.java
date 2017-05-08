package com.longshihan.commonlibrary.utils.route;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author longshihan
 * @time 2017/5/8 16:07
 * @des
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface RouterUri {
    String routerUri() default "";
}
