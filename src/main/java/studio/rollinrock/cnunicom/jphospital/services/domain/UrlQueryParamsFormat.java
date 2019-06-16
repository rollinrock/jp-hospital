package studio.rollinrock.cnunicom.jphospital.services.domain;


import java.lang.annotation.*;

/**
 * api返回数据格式类似url参数格式：
 * a=1&b=2&c=3
 *
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface UrlQueryParamsFormat {

}
