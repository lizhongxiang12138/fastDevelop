package com.my.project.service.impl;

import org.springframework.stereotype.Service;
import com.my.base.service.impl.BaseServiceImpl;


@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl<com.my.project.entity.Article> implements com.my.project.service.ArticleService {

}
