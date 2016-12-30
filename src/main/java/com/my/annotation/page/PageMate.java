package com.my.annotation.page;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类型对应的表格标题名称
 * @author lzx
 *
 * 2016年8月13日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PageMate {
	String fieldName();//标题名称
	String dataTypeId() default "data_baseType";//数据类型
}
