package com.my.project.dao.impl;

import com.my.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository(value="authUserDao")
public class AuthUserDaoImpl extends BaseDaoImpl<com.my.project.entity.AuthUser> implements com.my.project.dao.AuthUserDao {

}
