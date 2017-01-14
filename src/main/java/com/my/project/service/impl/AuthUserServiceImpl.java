package com.my.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.my.base.service.impl.BaseServiceImpl;
import com.my.project.dao.AuthUserDao;
import com.my.project.dao.RoleDao;
import com.my.project.entity.AuthUser;
import com.my.project.entity.TbMenu;
import com.my.project.entity.TbRole;
import com.my.project.utils.QueryHelper;


@Service("authUserService")
public class AuthUserServiceImpl extends BaseServiceImpl<com.my.project.entity.AuthUser> implements com.my.project.service.AuthUserService {
    
    @Resource
    private AuthUserDao authUserDao;
    @Resource
    private RoleDao roleDao;
    
    @Override
    public AuthUser getByUserName(String userName) {
	AuthUser user =null;
	QueryHelper qh = new QueryHelper(AuthUser.class, "u");
	qh.addCondition("u.userName=?", userName);
	List<AuthUser> list = authUserDao.list(qh);
	if(list!=null&&list.size()>0) {
	    user=list.get(0);
	}
	return user;
    }
    
    /**
     * 更新用户角色名称
     * @param t
     */
    private void updateRoleName(AuthUser t) {
	QueryHelper qh = new QueryHelper(TbRole.class, "r");
	qh.addCondition("r.id=?", t.getRoleId());
	List<TbRole> list = roleDao.list(qh );
	if(list!=null&&list.size()>0) {
	    t.setRoleName(list.get(0).getRoleName());
	}
    }

    @Override
    public void update(AuthUser t) throws Exception {
	updateRoleName(t);
        super.update(t);
    }
    
    @Override
    public void save(AuthUser t) throws Exception {
	updateRoleName(t);
        super.save(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TbMenu> getMenus(String roleId) throws Exception {
	String sqlStr = "SELECT m FROM TbMenu m,RoleMenuRelation rm"
		    +"		where m.id=rm.menuId"
		    + "		and  rm.roleId=? "
		    + "		and  m.menuGrade=?";
	Query q = getSession().createQuery(sqlStr);
	q.setParameter(0, roleId);
	q.setParameter(1, "0");
	List<TbMenu> menus= q.list();
	setChildren(menus,roleId);
	return menus;
    }
    
    private void setChildren(List<TbMenu> menus,String roleId) throws Exception {
	for (int i = 0; i < menus.size(); i++) {
	    String sqlStr = "SELECT m FROM TbMenu m,RoleMenuRelation rm"
		    +"		where m.id=rm.menuId"
		    + "		and  rm.roleId=? "
		    + "		and  m.menuParent=?";
	    Query q = getSession().createQuery(sqlStr);
	    q.setParameter(0, roleId);
	    q.setParameter(1, menus.get(i).getId());
	    @SuppressWarnings("unchecked")
	    List<TbMenu> children = q.list();
	    if (children.size() > 0) {
		menus.get(i).setChildren(children);
		setChildren(children,roleId);
	    }
	}
    }
}
