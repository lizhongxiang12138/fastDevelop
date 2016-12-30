package com.my.project.utils;

import java.util.ArrayList;
import java.util.List;
/**
 * @author 李忠翔
 * 描述:分页查询工具类
 * @date : 2016年1月20日 下午7:56:21
 */ 
public class PageUtils {

	private int pgNo = 1;//当前页码默认第一页
	private int pgSize = 3;//每页记录数默认每页10条记录
	private long totalCount;//记录总数
	private int totalPg;//总页数
	private List item;//数据
	
	
	
	public PageUtils() {
		
	}


	public PageUtils(int pgNo, int pgSize, long totalCount) {
		if(item==null){
			item=new ArrayList();
		}
		//计算总页数
		if(totalCount!=0){
			int tem = (int) (totalCount/pgSize);
			this.totalPg=(int) ((totalCount%pgSize==0)? tem:(tem+1));
		}else {
			this.totalPg=0;
		}
		this.pgNo=pgNo;
		this.pgSize=pgSize;
		this.totalCount=totalCount;
	}
	
	
	public int getPgNo() {
		return pgNo;
	}
	public void setPgNo(int pgNo) {
		this.pgNo = pgNo;
	}
	
	public int getPgSize() {
		return pgSize;
	}


	public void setPgSize(int pgSize) {
		this.pgSize = pgSize;
	}
	
	public long getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}


	public int getTotalPg() {
		return totalPg;
	}
	public void setTotalPg(int totalPg) {
		this.totalPg = totalPg;
	}
	public List getItem() {
		return item;
	}
	public void setItem(List item) {
		this.item = item;
	}
	
	
}
