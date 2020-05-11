package work.ambitlu.common.annotation;

import work.ambitlu.common.enums.BusinessType;
import work.ambitlu.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * Excel注解集
 *
 * @author Ambi
 * @Description: 自定义操作日志记录注解
 * @ate: 2020/5/5 15:21
 * @since JDK 1.11
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    /**
     * 模块 
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}
