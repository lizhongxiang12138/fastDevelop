package com.my.project.service;

import java.io.OutputStream;
import java.util.List;

import com.my.base.service.BaseService;
import com.my.controller.sys.project.role.model.RoleModel;
import com.my.project.entity.TbMenu;
import com.my.project.entity.TbRole;

public interface RoleService extends BaseService<TbRole> {
	/**
	 * 
	 * 描述:同时保存权限
	 * @author: 李忠翔
	 * @date： 2016年7月14日
	 * @param r
	 */
	public void save(RoleModel r)throws Exception;
	
	/**
	 * 
	 * 描述:同时更新权限
	 * @author: 李忠翔
	 * @date： 2016年7月14日
	 * @param roleModel
	 */
	public void update(RoleModel r)throws Exception;
	
	/**
	 * 设置菜单的权限
	 * @param menus
	 */
	public void setChecked(List<TbMenu> menus,String roleId);
}
