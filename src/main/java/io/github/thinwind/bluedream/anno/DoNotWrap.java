package io.github.thinwind.bluedream.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
*
* 用于标注Controller方法不要包装成CommonResult
*
* @author ShangYh <niceshang@outlook.com>
* @since 2020-09-01 15:10
*
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoNotWrap {
    
}