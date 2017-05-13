package com.my.controller.sys.project.article.model;

import java.util.Map;

import com.my.project.entity.ArticleType;

public class ArticleModel {
	/**
	 * 文章
	 */
	private com.my.project.entity.Article article;
	/**
	 * 文章类型
	 * 
	 */
	private Map<String, String> articleTypeMap;
	
	
	public com.my.project.entity.Article getArticle() {
		return article;
	}

	public void setArticle(com.my.project.entity.Article article) {
		this.article = article;
	}

	public Map<String, String> getArticleTypeMap() {
	    return articleTypeMap;
	}

	public void setArticleTypeMap(Map<String, String> articleTypeMap) {
	    this.articleTypeMap = articleTypeMap;
	}
	
}
