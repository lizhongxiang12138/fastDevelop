package com.my.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.base.dao.impl.BaseDaoImpl;
import com.my.project.dao.MenuDao;
import com.my.project.entity.TbMenu;

@Repository(value="menuDao")
public class MenuDaoImpl extends BaseDaoImpl<TbMenu> implements MenuDao {

}
