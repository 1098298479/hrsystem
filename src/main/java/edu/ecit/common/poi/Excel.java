package edu.ecit.common.poi;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {

    /**
     *
     * @Title: head
     * @Description: excel导出数据表表头
     * @Author sff
     */
    String head() default "";

    /**
     *
     * @Title: isDefault
     * @Description: 是否为默认导出字段
     * @Author sff
     */
    boolean isDefault() default false;

}
