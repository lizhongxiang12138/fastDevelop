package com.my.project.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.base.service.impl.BaseServiceImpl;
import com.my.project.dao.RoleMenuRelationDao;
import com.my.project.entity.TbMenu;
import com.my.project.entity.relation.RoleMenuRelation;
import com.my.project.service.MenuService;
import com.my.project.utils.DeleteHelper;
import com.my.project.utils.QueryHelper;

@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<TbMenu>
	implements MenuService {

    @Resource
    private RoleMenuRelationDao roleMenuRelationDao;
    
    @Override
    public void setChildren(List<TbMenu> menus) throws Exception {
	QueryHelper qh;
	for (int i = 0; i < menus.size(); i++) {
	    qh = new QueryHelper(TbMenu.class, "m");
	    qh.addCondition("m.menuParent=?", menus.get(i).getId());
	    qh.addOrder("text", QueryHelper.ORDER_BY_DESC);
	    List<TbMenu> children = list(qh);
	    if(children.size()>0) {
		//menus.get(i).setIconCls("glyphicon glyphicon-floder-open");
		menus.get(i).setChildren(children);
		setChildren(children);
	    }
	}
    }
    
    @Override
    public void deleteById(Serializable id) throws Exception {
        super.deleteById(id);
        //删除关系
        DeleteHelper dh = new DeleteHelper(RoleMenuRelation.class);
        dh.addCondition("menuId=?", id);
        roleMenuRelationDao.deleteByCondition(dh);
    }

}
