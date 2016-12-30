package com.my.project.service;


import org.springframework.web.multipart.MultipartFile;

import com.my.base.service.BaseService;
import com.my.project.entity.Document;

public interface DocumentService extends BaseService<com.my.project.entity.Document> {
    
    public void save(Document document, MultipartFile file,
	    String rootUrl) throws Exception;

}
