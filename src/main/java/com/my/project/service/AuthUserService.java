package com.my.project.service;


import java.util.List;

import com.my.base.service.BaseService;
import com.my.project.entity.AuthUser;
import com.my.project.entity.TbMenu;

public interface AuthUserService extends BaseService<com.my.project.entity.AuthUser> {

    /**
     * 根据用户名称获取用户
     * @param userName
     * @return
     */
    public AuthUser getByUserName(String userName);

    /**
     * 获取权限内的菜单
     * @param string
     * @return
     * @throws Exception 
     */
    public List<TbMenu> getMenus(String roleId) throws Exception;
    

}
