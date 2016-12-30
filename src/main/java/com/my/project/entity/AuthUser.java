package com.my.project.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.my.annotation.excel.ExcelColumn;
import com.my.annotation.excel.ExcelTable;
import com.my.annotation.page.PageMate;

/**
 * TbMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "auth_user")
@ExcelTable(title="用户数据")
public class AuthUser implements java.io.Serializable {

	/**
	 * 角色id
	 */
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;//角色id
	
	/**
	 * 用户名称
	 */
	@Column(name = "user_name", nullable = false, length = 200)
	@ExcelColumn(title="用户名称",column=0)
	@PageMate(fieldName="用户名称")
	private String userName;
	
	/**
	 * 用户账号
	 */
	@Column(name = "user_account", nullable = false, length = 200)
	@ExcelColumn(title="用户账号",column=1)
	@PageMate(fieldName="用户账号")
	private String userAccount;

	/**
	 * 用户密码
	 */
	@Column(name = "user_password", nullable = false, length = 200)
	@ExcelColumn(title="用户密码",column=2)
	@PageMate(fieldName="用户密码")
	private String userPassword;

	//getter setter 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}