package com.my.controller.sys.project.role.model;

import java.util.List;

import com.my.project.entity.TbMenu;
import com.my.project.entity.TbRole;

public class RoleModel {
	/**
	 * 角色
	 */
	private TbRole role;
	
	/**
	 * 角色拥有的权限
	 */
	private String[] menuList;
	
	/**
	 * 资源
	 */
	private TbMenu menu;
	

	public TbRole getRole() {
		return role;
	}

	public void setRole(TbRole role) {
		this.role = role;
	}

	public String[] getMenuList() {
		return menuList;
	}

	public void setMenuList(String[] menuList) {
		this.menuList = menuList;
	}

	public TbMenu getMenu() {
	    return menu;
	}

	public void setMenu(TbMenu menu) {
	    this.menu = menu;
	}
	
}
