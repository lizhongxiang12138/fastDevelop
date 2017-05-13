package com.my.project.dao.impl;

import com.my.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository(value="articleTypeDao")
public class ArticleTypeDaoImpl extends BaseDaoImpl<com.my.project.entity.ArticleType> implements com.my.project.dao.ArticleTypeDao {

}
