package com.longshihan.commonlibrary.utils.route;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author longshihan
 * @time 2017/5/8 16:08
 * @des
 */

@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface RouterParam {
    String value() default "";
}
