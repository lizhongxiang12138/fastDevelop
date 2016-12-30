package com.my.base.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import com.my.base.dao.impl.BaseDaoImpl;
import com.my.base.service.BaseService;
import com.my.project.entity.TbMenu;
import com.my.project.utils.DeleteHelper;
import com.my.project.utils.ExcelHelper;
import com.my.project.utils.PageUtils;
import com.my.project.utils.QueryHelper;
import com.sun.org.apache.xerces.internal.xni.QName;

import sun.security.ec.ECDHKeyAgreement;

/**
 * 
 * @author 李忠翔
 *
 * 描述，基本的Service类，所有的Service继承这个类
 */
public class BaseServiceImpl<T> extends BaseDaoImpl<T> implements BaseService<T> {
	@Override
	public void save(T t) throws Exception {
		super.save(t);
		
	}

	@Override
	public void update(T t)throws Exception {
		super.update(t);
		
	}

	@Override
	public T findById(Serializable id) {
		
		return super.findById(id);
	}

	@Override
	public void deleteById(Serializable id)throws Exception {
		super.deleteById(id);
		
	}

	@Override
	public List<T> list() {
		
		return super.list();
	}

	@Override
	public T findByCondition(HashMap<String, Object> conditions) {
		
		return super.findByCondition(conditions);
	}

	@Override
	public List<T> list(QueryHelper queryHelper) {
		// TODO Auto-generated method stub
		return super.list(queryHelper);
	}

	@Override
	public PageUtils list(QueryHelper queryHelper, int pgNo, int pgSize) {
		return super.list(queryHelper, pgNo, pgSize);
	}
	
	@Override
	public void deleteByCondition(DeleteHelper dh)throws Exception {
		super.deleteByCondition(dh);
	}

	@Override
	public void ExportModel(OutputStream o,Class clazz)throws Exception {
		ExcelHelper<T> eh = new ExcelHelper<T>(clazz);
		eh.ExportModel(o);
	}

	@Override
	public void ExportDatas(OutputStream os, Class clazz, List datas)throws Exception {
		ExcelHelper<T> eh = new ExcelHelper<T>(clazz);
		eh.ExportDatasToExcel(os, datas);
	}

	@Override
	public void importDatas(InputStream in, String fileName,Class clazz,String[] uniqueField) throws Exception {
		ExcelHelper<T> eh = new ExcelHelper<T>(clazz,//
				in,fileName);
		List<T> datas = eh.importDatasToMemory();
		QueryHelper qh = null;
		//获取字段
		//TODO 目前为直接导入数据，不检测数据的唯一性
		for (T m : datas) {
			qh = new QueryHelper(clazz, "q");
			//查询是否有该条数据，没有
			if (uniqueField != null && uniqueField.length > 0) {
				for (int i = 0; i < uniqueField.length; i++) {
					// 通过get方法
					Method getMethod = clazz.getMethod("get" + uniqueField[i].substring(0, 1).//
							toUpperCase() + uniqueField[i].substring(1, uniqueField[i].length()));
					if (getMethod != null) {
						Object value = getMethod.invoke(m);
						System.out.println(value);
						qh.addCondition(uniqueField[i] + "=?", value);
					}
				}
			}
			List<T> datasTemp = super.list(qh);
			if(datasTemp.size()==0||datasTemp!=null){
				super.save(m);
			}
		}
	}
}
