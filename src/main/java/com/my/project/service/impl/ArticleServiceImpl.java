package com.my.project.service.impl;


import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.my.base.service.impl.BaseServiceImpl;
import com.my.exception.IdNumOUtOFException;
import com.my.exception.NoDataException;
import com.my.project.dao.DocumentDao;
import com.my.project.entity.Article;
import com.my.project.entity.Document;

@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl<com.my.project.entity.Article> implements com.my.project.service.ArticleService {

    @Resource
    private DocumentDao documentDao;

    @Override
    public void update(Article article, String[] imgGroup) throws Exception {
	//更新文章
	super.update(article);
	updateImgGroup(article, imgGroup);
    }

    @Override
    public void save(Article article, String[] imgGroup) throws NoDataException, IdNumOUtOFException,Exception{
	if (article.getId()!=null) {
	    article.setId(super.getGenerateId("ARTICLE"));
	}
	if(article.getCreateTime()==null) article.setCreateTime(new Timestamp(new Date().getTime()));//文章添加时间
	super.save(article);
	updateImgGroup(article, imgGroup);
	
    }

    
    /**
     * 更新图片组
     * @param a
     * @param imgGroup
     * @throws Exception
     */
    private void updateImgGroup(Article a,String[] imgGroup) throws Exception {
	if (imgGroup != null&&imgGroup.length>0) {
		for (String s : imgGroup) {
		    Document d = documentDao.findById(s);
		    if(d==null) {
			continue;
		    }
		    d.setServiceID(a.getId());
		    d.setServiceName(a.getTitle()+"的图片组");
		    documentDao.update(d);
		}
	}
    }

}
