package com.my.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.base.dao.impl.BaseDaoImpl;
import com.my.project.dao.RoleDao;
import com.my.project.entity.TbRole;

@Repository(value="roleDao")
public class RoleDaoImpl extends BaseDaoImpl<TbRole> implements RoleDao {

}
