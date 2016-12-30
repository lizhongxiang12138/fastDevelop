package com.my.project.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Order;

/**
 * @author 李忠翔
 * 查询助手工作类，根据条件生成查询的sql
 * @date : 2016年1月20日 下午5:20:24
 */ 
public class QueryHelper {

	private String hqlStr;//hql语句
	private String fromStr="";//from语句
	private String conditionStr="";//条件语句
	private String orderStr="";//排序语句
	private String alias;//别名
	private List<Object> parameters;
	
	//排序常量
	public static String ORDER_BY_DESC="DESC";//降序
	public static String ORDER_BY_ASC="ASC";//升序
	/**
	 * 构造函数
	 * @param clazz 查询的实体类类型
	 * @param alias 指定的别名
	 * 列如：new QueryHelper(info.getClass,"i")
	 */
	public QueryHelper(Class clazz,String alias) {
		this.parameters=new ArrayList<Object>();
		fromStr+="FROM "+clazz.getSimpleName()+" "+alias;
		this.alias=alias;
	}
	/**
	 * 增减条件
	 * @param conditionStr //条件语句
	 * @param Paramaters	//条件参数
	 * 列如:addCondition("i.title LIKE ?","%信息%")
	 * 如果没有条件参数则写为(".....",null)
	 */
	public void addCondition(String conditionStr,Object paramater) {
		if(this.conditionStr.length()>1){
			this.conditionStr+=" AND "+conditionStr;
		}else {
			this.conditionStr+=" WHERE "+conditionStr;
		}
		if(paramater!=null){
			parameters.add(paramater);
		}
	}
	/**
	 * 添加排序
	 * @param property 指定排序的属性
	 * @param order	怎么排序
	 */
	public void addOrder(String property,String order) {
		if(orderStr.length()>1){
			orderStr +=" , "+property+" "+order;
		}else {
			orderStr +=" ORDER BY "+property+" "+order;
		}
	}
	/**
	 * 获取hql语句
	 * @return
	 */
	public String getHqlStr(){
		hqlStr=fromStr+conditionStr+orderStr;
		return hqlStr;
	}
	/**
	 * 返回条件查询的参数
	 * @return
	 */
	public List<Object> getParamaters() {
		return parameters;
	}
	/**
	 * 获取查询总记录数的hql
	 * @return
	 */
	public String getQueryCountHql(){
		return "SELECT COUNT("+alias+") " + fromStr + conditionStr;
	}
}
