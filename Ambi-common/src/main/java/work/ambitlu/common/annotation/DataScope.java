package work.ambitlu.common.annotation;

import java.lang.annotation.*;

/**
 * 数据权限过滤注解
 *
 * @author Ambi
 * @Description:
 * @ate: 2020/5/5 15:19
 * @since JDK 1.11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 部门表的别名
     */
    public String deptAlias() default "";

    /**
     * 用户表的别名
     */
    public String userAlias() default "";
}
