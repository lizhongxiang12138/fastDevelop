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
@Table(name = "generate_code")
@ExcelTable(title="自动生成代码模块")
public class GenerateCode implements java.io.Serializable {

	/**
	 * id
	 */
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;
	
	/**
	 * 类全名
	 */
	@Column(name = "class_name",  unique=true,nullable = false, length = 200)
	@ExcelColumn(title="类全名",column=0)
	@PageMate(fieldName="类全名")
	private String className;
	
	/**
	 * 模块名称
	 */
	@Column(name = "module_name", nullable = true, length = 200)
	@ExcelColumn(title="模块名称",column=1)
	@PageMate(fieldName="模块名称")
	private String moduleName;
	
	/**
	 * 模块子controller包名称
	 */
	@Column(name = "package_hildName", nullable = true, length = 500)
	@ExcelColumn(title="模块子controller包名称",column=2)
	@PageMate(fieldName="模块子controller包名称")
	private String packageChildName ;


	//getter setter 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getPackageChildName() {
		return packageChildName;
	}

	public void setPackageChildName(String packageChildName) {
		this.packageChildName = packageChildName;
	}
	
}