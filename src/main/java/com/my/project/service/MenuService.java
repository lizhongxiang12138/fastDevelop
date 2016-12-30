package com.my.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.my.base.service.BaseService;
import com.my.project.entity.TbMenu;

public interface MenuService extends BaseService<TbMenu> {
    
    public void setChildren(List<TbMenu> menus) throws Exception;

}
