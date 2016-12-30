package com.my.project.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;
import com.my.base.service.impl.BaseServiceImpl;
import com.my.project.entity.DataType;
import com.my.project.utils.QueryHelper;

import autoGenerateModel.AutoGenerateUtil;

@Service("dataTypeService")
public class DataTypeServiceImpl extends BaseServiceImpl<com.my.project.entity.DataType> implements com.my.project.service.DataTypeService {
    
    @Override
    public void save(DataType t) throws Exception {
        generateValidate(t);
        super.save(t);
    }
    
    @Override
    public void update(DataType t) throws Exception {
	generateValidate(t);
        super.update(t);
    }
    
    /**
     * 生成validate.js
     * @param dt
     * @throws Exception
     */
    private void generateValidate(DataType dt) throws Exception{
	QueryHelper qh = new QueryHelper(DataType.class, "dt");
	qh.addCondition("id <> ?", dt.getId());
	List<DataType> datas = list(qh);
	datas.add(dt);
	AutoGenerateUtil autoGenerateUtil = new AutoGenerateUtil(this.getClass());
	autoGenerateUtil.generateValidateJs(datas);
    }
}