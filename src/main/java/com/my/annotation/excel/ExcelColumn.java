package com.my.annotation.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import org.apache.commons.lang.ObjectUtils.Null;

/**
 * 数据所所在的列
 * @author lzx
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelColumn {
	/**
	 * 标题
	 * @return
	 */
	String title();//标题
	/**
	 * 对应的列（ps：从0开始）
	 * @return
	 */
	int column();//第几列 
	/**
	 * 下拉框
	 */
	String selectList() default "";
	
}
