package com.my.project.service.impl;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.my.base.service.impl.BaseServiceImpl;
import com.my.project.entity.Document;
import com.sun.org.apache.bcel.internal.generic.NEW;

import antlr.build.Tool;


@Service("documentService")
public class DocumentServiceImpl extends BaseServiceImpl<com.my.project.entity.Document> implements com.my.project.service.DocumentService {

    @Override
    public void save(Document document, MultipartFile file,String rootUrl,String subUrl) throws Exception {
	InputStream in = this.getClass().getResourceAsStream("/sysConfig.properties");
	Properties prop = new Properties();
	prop.load(in);
	
	//把路径改为webapps的路径
	rootUrl = rootUrl.replace("\\"+prop.getProperty("projectRootPath"), "\\");
	rootUrl = rootUrl.replace("/"+prop.getProperty("projectRootPath"), "/");
	String webappscontextPath = rootUrl;//webapps的路径
	rootUrl=rootUrl+"/"+prop.getProperty("fileUploadRootPath");
	rootUrl = rootUrl+subUrl;
	//设置文件名，为了避免重复，用uuid命名
	String fileName = file.getOriginalFilename();
	fileName=UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
	//保存上传文件到硬盘
	String path = rootUrl;
	path=path+"/"+new SimpleDateFormat("yyyyMMdd").format(new Date());
	File targetFile = new File(path, fileName);
	if (!targetFile.exists()) {
		targetFile.mkdirs();
	}
	file.transferTo(targetFile);
	//设置相对路径
	String fileRelativePath = path.replace(webappscontextPath, "").replace("\\", "/");
	fileRelativePath = fileRelativePath + "/"+fileName;
	document.setFileRelativePath(fileRelativePath);
	//设置文件标题
	document.setDocumentTitle(file.getOriginalFilename());
	//设置文件绝对路径
	document.setFilePath(targetFile.getPath());
	super.save(document);

    }
    
    @Override
    public void deleteById(Serializable id) throws Exception {
	//查询
	try {
	    Document d = super.findById(id);
	    if(d==null) {
		return;
	    }
	    String filePath = d.getFilePath();
	    File file = new File(filePath);
	    if(file.exists()) {
	        file.delete();
	    }
	    super.deleteById(id);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new Exception(e);
	}
    }

}
