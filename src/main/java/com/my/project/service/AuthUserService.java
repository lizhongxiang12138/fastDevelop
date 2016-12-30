package com.my.project.service;


import com.my.base.service.BaseService;
import com.my.project.entity.AuthUser;

public interface AuthUserService extends BaseService<com.my.project.entity.AuthUser> {

    public AuthUser getByUserName(String userName);
    
    

}
