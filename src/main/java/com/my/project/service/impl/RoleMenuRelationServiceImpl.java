package com.my.project.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.base.service.impl.BaseServiceImpl;
import com.my.project.dao.RoleMenuRelationDao;
import com.my.project.entity.relation.RoleMenuRelation;
import com.my.project.service.RoleMenuRelationService;
import com.my.project.utils.DeleteHelper;

@Service("roleMenuRelationService")
public class RoleMenuRelationServiceImpl extends
	BaseServiceImpl<RoleMenuRelation> implements RoleMenuRelationService {

    @Autowired
    private RoleMenuRelationDao roleMenuRelationDao;
    
    @Override
    public void updatePermission(String[] mIds, String roleId) throws Exception {
	RoleMenuRelation rm;
	DeleteHelper dh = new DeleteHelper(RoleMenuRelation.class);
	dh.addCondition("roleId=?", roleId);
	roleMenuRelationDao.deleteByCondition(dh);
	for (String s : mIds) {
	    if (StringUtils.isNotBlank(s)) {
		rm = new RoleMenuRelation();
		rm.setRoleId(roleId);
		rm.setMenuId(s);
		roleMenuRelationDao.save(rm);
	    }
	}
    }

}
