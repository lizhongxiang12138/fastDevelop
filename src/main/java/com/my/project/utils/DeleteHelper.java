package com.my.project.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据条件生成删除记录的sql
 * @author 李忠翔
 * @date : 2016年4月22日 下午5:20:24
 *
 */
public class DeleteHelper {
	/**
	 * 返回的sql
	 */
	private String deleteSql="";
	/**
	 * 条件语句
	 */
	private String conditionSql="";
	/**
	 * 别名
	 */
	private String alias="";
	/**
	 * 条件参数
	 */
	private List<Object> parameters;
	
	
	/**
	 * 
	 * @param clazz 实体类类型
	 * @param alias	别名
	 */
	public DeleteHelper(Class clazz) {
		parameters = new ArrayList<Object>();
		deleteSql="delete "+clazz.getSimpleName()+" ";
	}
	
	/**
	 * 增加条件
	 * @param condition 条件语句 列如 "c.companyId=?"
	 * @param parameter	参数
	 */
	public void addCondition(String condition,Object parameter) {
		if(conditionSql.length()>1){
			conditionSql+=" and "+condition;
		}else {
			conditionSql+=" where "+condition;
		}
		if(parameters!=null){
			parameters.add(parameter);
		}
	}
	/**
	 * 返回删除语句
	 * @return
	 */
	public String getDeleteSql() {
		return deleteSql+conditionSql;
	}
	/**
	 * 返回参数
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}
}
