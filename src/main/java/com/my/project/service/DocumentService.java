package com.my.project.service;


import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.my.base.service.BaseService;
import com.my.project.entity.Document;

public interface DocumentService extends BaseService<com.my.project.entity.Document> {
    
    /**
     * <p>文件保存方法</p>
     * <a href="http://fex.baidu.com/webuploader/">web前台使用插件</a>
     * @param document 文档对象
     * @param file 文件对象
     * @param rootUrl 上传文件的的跟节点
     * @param subUrl 子路径
     * @throws Exception
     */
    public void save(Document document, MultipartFile file,
	    String rootUrl,String subUrl) throws Exception;
    
}
