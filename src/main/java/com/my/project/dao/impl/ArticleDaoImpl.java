package com.my.project.dao.impl;

import com.my.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository(value="articleDao")
public class ArticleDaoImpl extends BaseDaoImpl<com.my.project.entity.Article> implements com.my.project.dao.ArticleDao {

}
