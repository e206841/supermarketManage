package cn.stylefeng.guns.sys.modular.system.annotation;

import java.lang.annotation.*;

/**
 * 对外开放的接口;暨不仅manger可以访问的接口;
 * 基于改注解标记的接口,在获取user时,不能使用authToken获取
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PublicApi {

    String[] apiNames() default {};
}
