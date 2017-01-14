package com.my.project.service;

import com.my.base.service.BaseService;
import com.my.project.entity.relation.RoleMenuRelation;

public interface RoleMenuRelationService extends BaseService<RoleMenuRelation> {
    /**
     * 更新角色权限
     * @param mIds
     * @param roleId
     * @throws Exception 
     */
    void updatePermission(String[] mIds, String roleId) throws Exception;

}
