package com.my.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.base.dao.impl.BaseDaoImpl;
import com.my.project.dao.RoleMenuRelationDao;
import com.my.project.entity.relation.RoleMenuRelation;

@Repository(value="roleMenuRelationDao")
public class RoleMenuRelationDaoImpl extends BaseDaoImpl<RoleMenuRelation> implements RoleMenuRelationDao {

}
