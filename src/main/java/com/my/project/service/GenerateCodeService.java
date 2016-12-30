package com.my.project.service;


import com.my.base.service.BaseService;

public interface GenerateCodeService extends BaseService<com.my.project.entity.GenerateCode> {

	public void generateCode(String id) throws Exception;

}
