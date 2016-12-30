package com.my.base.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.my.project.utils.DeleteHelper;
import com.my.project.utils.PageUtils;
import com.my.project.utils.QueryHelper;

/**
 * @author 李忠翔
 * @date : 2016年1月15日 下午1:12:45
 */ 
public interface BaseDao<T> {
	
	/**
	 * 保存
	 * @param t
	 */
	public void save(T t)throws Exception;
	/**
	 * 修改
	 * @param t
	 */
	public void update(T t)throws Exception;
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteById(Serializable id)throws Exception;
	/**
	 * 列表查询
	 * @return
	 */
	public List<T> list();
	/**
	 * 列表查询带条件
	 * @return
	 */
	public List<T> list(QueryHelper queryHelper);
	/**
	 * 列表查询带条件分页
	 * @return
	 */
	public PageUtils list(QueryHelper queryHelper, int pgNo,int pgSize);
	
	/**
	 * 根据条件查找
	 * @return
	 */
	public T findByCondition(HashMap<String, Object> conditions);
	
	/**
	 * 根据添加删除
	 * @param dh删除助手
	 */
	public void deleteByCondition(DeleteHelper dh)throws Exception;
}
