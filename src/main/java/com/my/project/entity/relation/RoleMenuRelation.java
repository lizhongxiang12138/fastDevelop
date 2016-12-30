package com.my.project.entity.relation;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TbMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role_menu_relation")
public class RoleMenuRelation implements java.io.Serializable {

	/**
	 * 角色菜单关系id
	 */
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;//角色菜单关系id
	
	/**
	 * 角色id 对应类tbRole 的 id
	 */
	@Column(name = "role_id", nullable = false, length = 32)
	private String roleId;
	
	/**
	 * 菜单id 对应类tbMenu 的 id
	 */
	@Column(name = "menu_id", nullable = false, length = 32)
	private String menuId;
	
	/**
	 * 菜单id 对应类tbMenu 的 id
	 */
	@Column(name = "PERMISSIONS_DETAIL", nullable = true, length = 500)
	private String permissionsDetail;

	// Constructors
	
	public RoleMenuRelation(String roleId, String menuId) {
		super();
		this.roleId = roleId;
		this.menuId = menuId;
	}
	
	public RoleMenuRelation() {
		super();
	}

	//get set----------------------------------------------------------------------
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getPermissionsDetail() {
	    return permissionsDetail;
	}

	public void setPermissionsDetail(String permissionsDetail) {
	    this.permissionsDetail = permissionsDetail;
	}
}