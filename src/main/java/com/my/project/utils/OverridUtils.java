package com.my.project.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.my.project.entity.TbMenu;

/**
 * 
 * 功能描述:
 *
 * @author: 李忠翔
 * @date： 2016年7月6日	
 * @param <T>
 */
public class OverridUtils {

	/**
	 * 
	 * 描述:用于更新操作
	 * @author: 李忠翔
	 * @date： 2016年7月6日
	 * @param obj1 被覆盖对象
	 * @param obj2
	 * @param clazz 类型
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void overridNotNull(Object obj1, Object obj2,Class clazz) throws Exception {
		//获取所有属性
		Field[] fields = clazz.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			//获取属性名称
			String fieldName = fields[i].getName();
			System.out.println(fieldName);
			//通过get方法
			Method getMethod = clazz.getMethod("get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1, fieldName.length()));
			//执行get方法获取值
			Object value = getMethod.invoke(obj2);
			
			if(value!=null){
				Method setMethod = clazz.getMethod("set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1, fieldName.length()),
						fields[i].getType());
				setMethod.invoke(obj1, value);
			}
			
		}
		
	}

}
