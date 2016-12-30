package com.my.base.service;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.el.ELContext;

import org.springframework.web.multipart.MultipartFile;

import com.my.base.dao.BaseDao;
import com.my.project.entity.TbRole;

public interface BaseService<T> extends BaseDao<T> {
	/**
	 * 输出Excel模板
	 * @param os
	 * @param clazz
	 * @throws Exception
	 */
	public void ExportModel(OutputStream os,Class clazz)throws Exception;
	
	/**
	 * 输出Excel模板并且输出数据
	 * @param os
	 * @param class1
	 * @throws Exception 
	 */
	public void ExportDatas(OutputStream os, Class clazz,List datas) throws Exception;

	/**
	 * 导入数据
	 * @param in 文件输入流
	 * @param fileName 文件名
	 * @param clazz 存储的类型
	 * @param uniqueFile 确定数据唯一性的字段名称
	 * @throws Exception
	 */
	public void importDatas(InputStream in,String fileName,Class clazz,String[] uniqueField)throws Exception;
}
