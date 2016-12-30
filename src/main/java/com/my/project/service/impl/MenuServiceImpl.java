package com.my.project.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.my.base.service.impl.BaseServiceImpl;
import com.my.project.entity.TbMenu;
import com.my.project.service.MenuService;
import com.my.project.utils.QueryHelper;

@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<TbMenu>
	implements MenuService {

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

}
