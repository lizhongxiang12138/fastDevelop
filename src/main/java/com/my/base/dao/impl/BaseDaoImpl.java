package com.my.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.my.base.dao.BaseDao;
import com.my.project.utils.DeleteHelper;
import com.my.project.utils.PageUtils;
import com.my.project.utils.QueryHelper;

/**
 * @author 李忠翔
 * 描述：基础Dao类，封装操作数据库的通用方法
 */ 
public abstract class BaseDaoImpl<T>  implements BaseDao<T> {
	
	@Resource
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}



	private Class<T> clazz;
	
	public BaseDaoImpl(){
	    ParameterizedType pt=(ParameterizedType)this.getClass().getGenericSuperclass();
	    Type[] type = pt.getActualTypeArguments();
	    clazz = (Class<T>) type[0];
	}

	/*
	 * 保存数据到数据库
	 * (non-Javadoc)
	 * @see com.lzx.dao.BaseDao#save(java.lang.Object)
	 */
	public void save(T t)throws Exception  {
		getSession().save(t);
		
	}

	/*
	 * 修改数据
	 * (non-Javadoc)
	 * @see com.lzx.dao.BaseDao#update(java.lang.Object)
	 */
	public void update(T t)throws Exception {
		getSession().update(t);
	}


	/*
	 * 根据id查找数据
	 * (non-Javadoc)
	 * @see com.lzx.dao.BaseDao#findById(java.io.Serializable)
	 */
	public T findById(Serializable id) {
		return (T)getSession()//
				.get(clazz, id);
	}


	/*
	 * 根据id删除数据
	 * (non-Javadoc)
	 * @see com.lzx.dao.BaseDao#deleteById(java.io.Serializable)
	 */
	public void deleteById(Serializable id)throws Exception {
		getSession().delete(findById(id));
	}
	

	/*
	 * 获取所有数据
	 * (non-Javadoc)
	 * @see com.lzx.dao.BaseDao#list()
	 */
	public List<T> list() {
		
		return getSession()//
				.createQuery("FROM "+clazz.getSimpleName())//
				.list();
	}
	
	@Override
	public T findByCondition(HashMap<String, Object> conditions) {
		return null;
	}
	
	@Override
	public List<T> list(QueryHelper queryHelper) {
		Query q = getSession().createQuery(queryHelper.getHqlStr());
		if(queryHelper.getParamaters()!=null&&queryHelper.getParamaters().size()>0){
			for(int i=0;i<queryHelper.getParamaters().size();i++){
				q.setParameter(i,queryHelper.getParamaters().get(i));
			}	
		}
		return q.list();
	}
	
	@Override
	public PageUtils list(QueryHelper queryHelper, int pgNo,int pgSize) {
		Query q = getSession().createQuery(queryHelper.getHqlStr());
		if(queryHelper.getParamaters()!=null&&queryHelper.getParamaters().size()>0){
			for(int i=0;i<queryHelper.getParamaters().size();i++){
				q.setParameter(i,queryHelper.getParamaters().get(i));
			}	
		}
		//总记录数
		Query qCount = getSession().createQuery(queryHelper.getQueryCountHql());
		if(queryHelper.getParamaters()!=null&&queryHelper.getParamaters().size()>0){
			for(int i=0;i<queryHelper.getParamaters().size();i++){
				qCount.setParameter(i,queryHelper.getParamaters().get(i));
			}	
		}   
		
		long count = (Long)qCount.uniqueResult();
		//分页设置
		PageUtils pg = new PageUtils(pgNo,pgSize,count);
		q.setFirstResult((pg.getPgNo()-1)*pg.getPgSize());
		q.setMaxResults(pg.getPgSize());
		List<T> list = new ArrayList<T>();
		list=q.list();
		pg.setItem(list);
		return pg;
	}
	
	@Override
	public void deleteByCondition(DeleteHelper dh)throws Exception {
		Query q = getSession().createQuery(dh.getDeleteSql());
		if(dh.getParameters()!=null&&dh.getParameters().size()>0){
			for (int i = 0; i < dh.getParameters().size(); i++) {
				q.setParameter(i, dh.getParameters().get(i));
			}
		}
		q.executeUpdate();
	}
}
