package com.my.project.service.impl;

import org.springframework.stereotype.Service;
import com.my.base.service.impl.BaseServiceImpl;
import com.my.project.entity.AuthUser;


@Service("authUserService")
public class AuthUserServiceImpl extends BaseServiceImpl<com.my.project.entity.AuthUser> implements com.my.project.service.AuthUserService {

    @Override
    public AuthUser getByUserName(String userName) {
	// TODO Auto-generated method stub
	return null;
    }

}
