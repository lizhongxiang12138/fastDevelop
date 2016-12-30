package com.my.project.service.impl;

import java.io.OutputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.base.service.impl.BaseServiceImpl;
import com.my.controller.sys.project.role.model.RoleModel;
import com.my.project.dao.RoleDao;
import com.my.project.dao.RoleMenuRelationDao;
import com.my.project.entity.TbRole;
import com.my.project.entity.relation.RoleMenuRelation;
import com.my.project.service.RoleService;
import com.my.project.utils.DeleteHelper;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<TbRole> implements RoleService {
	@Resource
	private RoleMenuRelationDao roleMenuRelationDao;
	@Resource
	private RoleDao roleDao;
	
	
	public void save(RoleModel r)throws Exception {
		//新增
		roleDao.save(r.getRole());
		String[] menuList = r.getMenuList();
		if(menuList!=null&&menuList.length>0){
			for (int i = 0; i < menuList.length; i++) {
				roleMenuRelationDao.save(new RoleMenuRelation(r.getRole().getId(),menuList[i]));
			}
		}
		
	}


	@Override
	public void update(RoleModel r)throws Exception {
		roleDao.update(r.getRole());
		//先删除权限
		DeleteHelper dh = new DeleteHelper(RoleMenuRelation.class);
		dh.addCondition("roleId=?", r.getRole().getId());
		deleteByCondition(dh);
		
		//再保存更新后的权限
		String[] menuList = r.getMenuList();
		if(menuList!=null&&menuList.length>0){
			for (int i = 0; i < menuList.length; i++) {
				roleMenuRelationDao.save(new RoleMenuRelation(r.getRole().getId(),menuList[i]));
			}
		}
	}
}
