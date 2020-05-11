package work.ambitlu.common.annotation;

import java.lang.annotation.*;

/**
 * @author Ambi
 * @Description: 自定义注解防止表单重复提交
 * @ate: 2020/5/5 15:21
 * @since JDK 1.11
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit
{

}