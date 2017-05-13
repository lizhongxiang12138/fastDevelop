package com.my.controller.sys.project.document;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.my.project.entity.Document;
import com.my.project.utils.OverridUtils;
import com.my.project.utils.PageUtils;
import com.my.project.utils.QueryHelper;

/**
 * 
 * 描述: 角色模块请求控制器
 * @author 李忠翔
 * 时间 : 2016年6月26日
 */
@Controller
@RequestMapping(value="/sys/project/document")
public class DocumentAction {
	@Autowired 
	private com.my.project.service.DocumentService documentService;
	
	/**
	 * 
	 * 描述:保存修改文件
	 * @throws Exception 
	 *
	 */
	@RequestMapping("/save")
	public @ResponseBody Map save(@RequestParam(value="documentFile")MultipartFile file,HttpServletRequest request,
		HttpServletResponse response) throws Exception{
		
	    	Map resultMap = new HashMap();
		String resultMess = "false";
		Document document = new Document();
		document.setDocumentTitle(file.getOriginalFilename());
		String realPath = request.getServletContext().getRealPath("/");
		String subUrl="/root";
		try {
		    documentService.save(document,file,realPath,subUrl);
		    resultMess="success";
		    resultMap.put("mess",resultMess);
		    return resultMap;
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    resultMap.put("mess","系统出现了异常，我们正在加紧抢修！");
		    e.printStackTrace();
		    return resultMap;	   
		}
		
		
	}
	
	/**
	 * 
	 * 描述:初始化编辑页面
	 * @author: 李忠翔
	 * @date： 2016年7月7日
	 * @param documentModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/form")
	public String form(com.my.controller.sys.project.document.model.DocumentModel documentModel,Model model) {		
		if(documentModel.getDocument()!=null){
			if(StringUtils.isNotBlank(documentModel.getDocument().getId())){
				documentModel.setDocument(documentService.findById(documentModel.getDocument().getId()));								
			}
		}
		model.addAttribute("documentModel", documentModel);
		return "/webPage/sys/project/document/form.jsp";
	}
	
	/**
	 * 
	 * 描述:查询数据，分页查询
	 * @author: 李忠翔
	 * @date： 2016年7月11日
	 * @param documentModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/dataForPage")
	public String dataForPage(com.my.controller.sys.project.document.model.DocumentModel documentModel,PageUtils page,Model model) {
		QueryHelper queryHelper = new QueryHelper(com.my.project.entity.Document.class, "r");
		//queryHelper.addOrder("roleName", QueryHelper.ORDER_BY_ASC);//默认字段升序排序
		/*
		 * 添加条件
		 */
		if (documentModel != null) {
			if (documentModel.getDocument() != null) {
				if (StringUtils.isNotBlank(documentModel.getDocument().getDocumentDeclare())) {
					queryHelper.addCondition("documentDeclare = ?", documentModel.getDocument().getDocumentDeclare());
				}
			}
		}
		if (documentModel != null) {
			if (documentModel.getDocument() != null) {
				if (StringUtils.isNotBlank(documentModel.getDocument().getLastBrowseUserID())) {
					queryHelper.addCondition("lastBrowseUserID = ?", documentModel.getDocument().getLastBrowseUserID());
				}
			}
		}
		if (documentModel != null) {
			if (documentModel.getDocument() != null) {
				if (StringUtils.isNotBlank(documentModel.getDocument().getCreateUserID())) {
					queryHelper.addCondition("createUserID = ?", documentModel.getDocument().getCreateUserID());
				}
			}
		}
		if (documentModel != null) {
			if (documentModel.getDocument() != null) {
				if (StringUtils.isNotBlank(documentModel.getDocument().getLastBrowseUser())) {
					queryHelper.addCondition("lastBrowseUser = ?", documentModel.getDocument().getLastBrowseUser());
				}
			}
		}
		if (documentModel != null) {
			if (documentModel.getDocument() != null) {
				if (StringUtils.isNotBlank(documentModel.getDocument().getFilePath())) {
					queryHelper.addCondition("filePath = ?", documentModel.getDocument().getFilePath());
				}
			}
		}
		if (documentModel != null) {
			if (documentModel.getDocument() != null) {
				if (StringUtils.isNotBlank(documentModel.getDocument().getServiceName())) {
					queryHelper.addCondition("serviceName = ?", documentModel.getDocument().getServiceName());
				}
			}
		}
		if (documentModel != null) {
			if (documentModel.getDocument() != null) {
				if (StringUtils.isNotBlank(documentModel.getDocument().getId())) {
					queryHelper.addCondition("id = ?", documentModel.getDocument().getId());
				}
			}
		}
		if (documentModel != null) {
			if (documentModel.getDocument() != null) {
				if (StringUtils.isNotBlank(documentModel.getDocument().getCreateUser())) {
					queryHelper.addCondition("createUser = ?", documentModel.getDocument().getCreateUser());
				}
			}
		}
		if (documentModel != null) {
			if (documentModel.getDocument() != null) {
				if (StringUtils.isNotBlank(documentModel.getDocument().getDocumentTitle())) {
					queryHelper.addCondition("documentTitle = ?", documentModel.getDocument().getDocumentTitle());
				}
			}
		}
		if (documentModel != null) {
			if (documentModel.getDocument() != null) {
				if (StringUtils.isNotBlank(documentModel.getDocument().getFileDescribe())) {
					queryHelper.addCondition("fileDescribe = ?", documentModel.getDocument().getFileDescribe());
				}
			}
		}
		if (documentModel != null) {
			if (documentModel.getDocument() != null) {
				if (StringUtils.isNotBlank(documentModel.getDocument().getServiceID())) {
					queryHelper.addCondition("serviceID = ?", documentModel.getDocument().getServiceID());
				}
			}
		}
		page = documentService.list(queryHelper, page.getPgNo(), page.getPgSize());
		model.addAttribute("page", page);
		model.addAttribute("documentModel", documentModel);
		
		return "/webPage/sys/project/document/document.jsp";
	}
	
	/**
	 * 数据Excel模板下载
	 */
	@RequestMapping("/ExportModel")
	public @ResponseBody String ExportModel(HttpServletRequest request,HttpServletResponse response) {
		try {
			//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
	        response.setContentType("multipart/form-data");  
	        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
	        String fileName=new String("文档.xlsx".getBytes("UTF-8"), "iso8859-1");
	        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
			OutputStream os = response.getOutputStream();
			documentService.ExportModel(os,com.my.project.entity.Document.class);
			os.flush();
			os.close();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "系统出现了错误";
		}
	}
	
	/**
	 * 数据导出到Excel
	 */
	@RequestMapping("/ExportDatas")
	public @ResponseBody String ExportDatas(HttpServletRequest request,HttpServletResponse response) {
		try {
			//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
			response.setContentType("multipart/form-data");  
			//2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
			String fileName=new String("角色数据.xlsx".getBytes("UTF-8"), "iso8859-1");
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
			OutputStream os = response.getOutputStream();
			List<com.my.project.entity.Document> list = documentService.list();
			documentService.ExportDatas(os,com.my.project.entity.Document.class,list);
			os.flush();
			os.close();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "系统出现了错误";
		}
	}
	
	/**
	 * 数据导入
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("importDatas")
	public @ResponseBody Map<String, Object> importDatas(@RequestParam("file")MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String fileName = file.getOriginalFilename();
			InputStream in = file.getInputStream();
			//String[] uniqueField = {"roleName"};
			documentService.importDatas(in, fileName,com.my.project.entity.Document.class,null);
			//提示导入成功
			resultMap.put("mess","success");
			return resultMap;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//提示导入成功
			resultMap.put("mess","数据导入失败");
			return resultMap;
		}
		
	}
	
	
	@RequestMapping("delete")
	public String delete(String[] ids,com.my.controller.sys.project.document.model.DocumentModel documentModel,PageUtils page,Model model) {
		try {
			if(ids!=null){
				for (String id : ids) {
					System.out.println("删除数据：【"+id+"】");
					documentService.deleteById(id);
				}
			}
			return "redirect:/sys/project/document/dataForPage.action";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
}
