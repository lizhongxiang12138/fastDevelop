package com.my.project.service;


import com.my.base.service.BaseService;
import com.my.project.entity.Article;

public interface ArticleService extends BaseService<com.my.project.entity.Article> {
    /**
     * 
     * @param article 文章实体
     * @param imgGroup	文章图片组对应的id（document实体）
     * @throws Exception 
     */
    public void update(Article article, String[] imgGroup) throws Exception;
    
    /**
     * 保存方法
     * @param article
     * @param imgGroup
     * @throws Exception 
     */
    public void save(Article article, String[] imgGroup) throws Exception;

}
