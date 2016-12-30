package com.my.project.dao.impl;

import com.my.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository(value="documentDao")
public class DocumentDaoImpl extends BaseDaoImpl<com.my.project.entity.Document> implements com.my.project.dao.DocumentDao {

}
