package com.my.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.base.service.impl.BaseServiceImpl;
import com.my.controller.sys.project.role.model.RoleModel;
import com.my.project.dao.MenuDao;
import com.my.project.dao.RoleDao;
import com.my.project.dao.RoleMenuRelationDao;
import com.my.project.entity.TbMenu;
import com.my.project.entity.TbRole;
import com.my.project.entity.relation.RoleMenuRelation;
import com.my.project.service.RoleService;
import com.my.project.utils.DeleteHelper;
import com.my.project.utils.QueryHelper;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<TbRole> implements RoleService {
	@Resource
	private RoleMenuRelationDao roleMenuRelationDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private MenuDao menuDao;
	
	
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


	@Override
	public void setChecked(List<TbMenu> menus,String roleId) {
	    for(int i=0;i<menus.size();i++) {
		TbMenu tbMenu = menus.get(i);
		QueryHelper qh = new QueryHelper(RoleMenuRelation.class, "rm");
		qh.addCondition("roleId=?", roleId);
		qh.addCondition("menuId=?", tbMenu.getId());
		List<RoleMenuRelation> list = roleMenuRelationDao.list(qh);
		if(tbMenu.getChildren()==null||tbMenu.getChildren().size()==0) {
		    if(list!=null&&list.size()>0) {
			tbMenu.setChecked(true);
		    }else {
			tbMenu.setChecked(false);
		    }
		}else {
		    setChecked(tbMenu.getChildren(),roleId);
		}
	    }
	}
}
