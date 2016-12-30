package com.my.base.action;


import com.my.project.utils.PageUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author 李忠翔
 * 描述：基础action类，封装action的通用方法
 *
 */
public class BaseAction extends ActionSupport {
	protected int pgNo;
	protected int pgSize;
	protected PageUtils pgResult;
	protected String[] selectedRow;
	
	public int getPgNo() {
		if(pgNo<1){
			pgNo=1;
		}
		return pgNo;
	}
	public void setPgNo(int pgNo) {
		this.pgNo = pgNo;
	}
	public int getPgSize() {
		if(pgSize<1){
			pgSize=3;
		}
		return pgSize;
	}
	public void setPgSize(int pgSize) {
		this.pgSize = pgSize;
	}
	public PageUtils getPgResult() {
		return pgResult;
	}
	public void setPgResult(PageUtils pgResult) {
		this.pgResult = pgResult;
	}
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
}
