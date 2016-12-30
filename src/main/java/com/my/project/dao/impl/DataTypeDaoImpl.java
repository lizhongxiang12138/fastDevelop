package com.my.project.dao.impl;

import com.my.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository(value="dataTypeDao")
public class DataTypeDaoImpl extends BaseDaoImpl<com.my.project.entity.DataType> implements com.my.project.dao.DataTypeDao {

}
