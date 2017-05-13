package com.my.project.dao.impl;

import com.my.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository(value="historicTypeDao")
public class HistoricTypeDaoImpl extends BaseDaoImpl<com.my.project.entity.HistoricType> implements com.my.project.dao.HistoricTypeDao {

}
