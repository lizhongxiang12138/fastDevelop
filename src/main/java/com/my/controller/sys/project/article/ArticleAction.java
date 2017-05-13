package com.my.controller.sys.project.article;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.lob.OracleLobHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.my.controller.sys.project.document.model.DocumentModel;
import com.my.exception.IdNumOUtOFException;
import com.my.exception.NoDataException;
import com.my.project.entity.Article;
import com.my.project.entity.ArticleType;
import com.my.project.entity.Document;
import com.my.project.service.ArticleTypeService;
import com.my.project.service.DocumentService;
import com.my.project.utils.OverridUtils;
import com.my.project.utils.PageUtils;
import com.my.project.utils.QueryHelper;

/**
 * 
 * 描述: 角色模块请求控制器
 * 
 * @author 李忠翔 时间 : 2016年6月26日
 */
@Controller
@RequestMapping(value = "/sys/project/article")
public class ArticleAction {
    @Autowired
    private com.my.project.service.ArticleService articleService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private ArticleTypeService articleTypeService;

    /**
     * 
     * 描述:保存修改角色
     * 
     * @throws Exception
     *
     */
    @RequestMapping("/save")
    public @ResponseBody Map save(String[] imgGroup,
	    com.my.controller.sys.project.article.model.ArticleModel articleModel)
	    throws Exception {
	Map resultMap = new HashMap();
	String resultMess = "false";
	if (articleModel.getArticle() != null) {
	    if (StringUtils.isNotBlank(articleModel.getArticle().getId())) {// 更新
		// 根据id查询角色
		com.my.project.entity.Article tempArticle = articleService
			.findById(articleModel.getArticle().getId());
		OverridUtils.overridNotNull(tempArticle,
			articleModel.getArticle(),
			com.my.project.entity.Article.class);
		articleModel.setArticle(tempArticle);
		articleService.update(articleModel.getArticle(), imgGroup);
		resultMess = "success";
	    } else {
		articleModel.getArticle().setCreateTime(new Timestamp(new Date().getTime()));// 添加文章添加时间
		try {
		    articleService.save(articleModel.getArticle(), imgGroup);
		} catch (NoDataException e) {
		    e.printStackTrace();
		    resultMess=e.getMessage();
		    resultMap.put("mess", resultMess);
		    return resultMap;
		}catch(IdNumOUtOFException e){
		    e.printStackTrace();
		    resultMess=e.getMessage();
		    resultMap.put("mess", resultMess);
		    return resultMap;
		}
		resultMess = "success";
	    }
	}

	resultMap.put("mess", resultMess);
	return resultMap;
    }

    /**
     * 
     * 描述:初始化编辑页面
     * 
     * @author: 李忠翔 @date： 2016年7月7日
     * @param articleModel
     * @param model
     * @return
     */
    @RequestMapping("/form")
    public String form(
	    com.my.controller.sys.project.article.model.ArticleModel articleModel,
	    Model model) {

	Document titleImg = null;
	List<Document> imgGroup = null;
	if (articleModel.getArticle() != null) {
	    if (StringUtils.isNotBlank(articleModel.getArticle().getId())) {
		Article article = articleService
			.findById(articleModel.getArticle().getId());
		articleModel.setArticle(article);
		// 如果标题图片不为空、则获取标题图片
		if (StringUtils.isNotBlank(article.getTitleImageID())) {
		    titleImg = documentService
			    .findById(article.getTitleImageID());
		}
		// 图片组
		QueryHelper qh = new QueryHelper(Document.class, "f");
		qh.addCondition("f.serviceID=?", article.getId());
		imgGroup = documentService.list(qh);
	    }
	}
	model.addAttribute("titleImg", titleImg);
	model.addAttribute("articleModel", articleModel);
	model.addAttribute("imgGroup", imgGroup);
	return "/webPage/sys/project/article/form.jsp";
    }

    /**
     * 
     * 描述:查询数据，分页查询
     * 
     * @author: 李忠翔 @date： 2016年7月11日
     * @param articleModel
     * @param model
     * @return
     */
    @RequestMapping("/dataForPage")
    public String dataForPage( com.my.controller.sys.project.article.model.ArticleModel articleModel, PageUtils page, Model model) {
	QueryHelper queryHelper = new QueryHelper(
		com.my.project.entity.Article.class, "r");
	// queryHelper.addOrder("roleName", QueryHelper.ORDER_BY_ASC);//默认字段升序排序
	/*
	 * 添加条件
	 */
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils
			.isNotBlank(articleModel.getArticle().getFullTitle())) {
		    queryHelper.addCondition("fullTitle = ?",
			    articleModel.getArticle().getFullTitle());
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils
			.isNotBlank(articleModel.getArticle().getAuthor())) {
		    queryHelper.addCondition("author = ?",
			    articleModel.getArticle().getAuthor());
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils
			.isNotBlank(articleModel.getArticle().getSubhead())) {
		    queryHelper.addCondition("subhead = ?",
			    articleModel.getArticle().getSubhead());
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils.isNotBlank(
			articleModel.getArticle().getTitleImageID())) {
		    queryHelper.addCondition("titleImageID = ?",
			    articleModel.getArticle().getTitleImageID());
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils
			.isNotBlank(articleModel.getArticle().getContent())) {
		    queryHelper.addCondition("content = ?",
			    articleModel.getArticle().getContent());
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils
			.isNotBlank(articleModel.getArticle().getType())) {
		    queryHelper.addCondition("type = ?",
			    articleModel.getArticle().getType());
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils
			.isNotBlank(articleModel.getArticle().getTitle())) {
		    queryHelper.addCondition("title like ?",
			    "%"+articleModel.getArticle().getTitle()+"%");
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils.isNotBlank(
			articleModel.getArticle().getArticleIntro())) {
		    queryHelper.addCondition("articleIntro = ?",
			    articleModel.getArticle().getArticleIntro());
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils
			.isNotBlank(articleModel.getArticle().getRecord())) {
		    queryHelper.addCondition("record = ?",
			    articleModel.getArticle().getRecord());
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils
			.isNotBlank(articleModel.getArticle().getTop())) {
		    queryHelper.addCondition("top = ?",
			    articleModel.getArticle().getTop());
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils.isNotBlank(articleModel.getArticle().getId())) {
		    queryHelper.addCondition("id = ?",
			    articleModel.getArticle().getId());
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils
			.isNotBlank(articleModel.getArticle().getTypeID())) {
		    queryHelper.addCondition("typeID = ?",
			    articleModel.getArticle().getTypeID());
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils
			.isNotBlank(articleModel.getArticle().getComment())) {
		    queryHelper.addCondition("comment = ?",
			    articleModel.getArticle().getComment());
		}
	    }
	}
	if (articleModel != null) {
	    if (articleModel.getArticle() != null) {
		if (StringUtils
			.isNotBlank(articleModel.getArticle().getKeyword())) {
		    queryHelper.addCondition("keyword = ?",
			    articleModel.getArticle().getKeyword());
		}
	    }
	}
	queryHelper.addOrder("createTime", QueryHelper.ORDER_BY_DESC);// 根据添加时间降序排序
	page = articleService.list(queryHelper, page.getPgNo(),page.getPgSize());
	Map<String, String> articleTypeMap = articleModel.getArticleTypeMap();
	articleTypeMap=new  HashMap<String, String>();
	List<ArticleType> listTypes =articleTypeService.list();
	for (ArticleType at : listTypes) {
	    articleTypeMap.put(at.getId(), at.getName());
	}
	articleModel.setArticleTypeMap(articleTypeMap);
	model.addAttribute("page", page);
	model.addAttribute("articleModel", articleModel);

	return "/webPage/sys/project/article/article.jsp";
    }

    /**
     * 数据Excel模板下载
     */
    @RequestMapping("/ExportModel")
    public @ResponseBody String ExportModel(HttpServletRequest request,
	    HttpServletResponse response) {
	try {
	    // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
	    response.setContentType("multipart/form-data");
	    // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
	    String fileName = new String("文章.xlsx".getBytes("UTF-8"),
		    "iso8859-1");
	    response.setHeader("Content-Disposition",
		    "attachment;fileName=" + fileName);
	    OutputStream os = response.getOutputStream();
	    articleService.ExportModel(os, com.my.project.entity.Article.class);
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
    public @ResponseBody String ExportDatas(HttpServletRequest request,
	    HttpServletResponse response) {
	try {
	    // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
	    response.setContentType("multipart/form-data");
	    // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
	    String fileName = new String("角色数据.xlsx".getBytes("UTF-8"),
		    "iso8859-1");
	    response.setHeader("Content-Disposition",
		    "attachment;fileName=" + fileName);
	    OutputStream os = response.getOutputStream();
	    List<com.my.project.entity.Article> list = articleService.list();
	    articleService.ExportDatas(os, com.my.project.entity.Article.class,
		    list);
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
     * 
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("importDatas")
    public @ResponseBody Map<String, Object> importDatas(
	    @RequestParam("file") MultipartFile file,
	    HttpServletRequest request, HttpServletResponse response) {
	HashMap<String, Object> resultMap = new HashMap<String, Object>();
	try {
	    String fileName = file.getOriginalFilename();
	    InputStream in = file.getInputStream();
	    // String[] uniqueField = {"roleName"};
	    articleService.importDatas(in, fileName,
		    com.my.project.entity.Article.class, null);
	    // 提示导入成功
	    resultMap.put("mess", "success");
	    return resultMap;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    // 提示导入成功
	    resultMap.put("mess", "数据导入失败");
	    return resultMap;
	}

    }

    @RequestMapping("delete")
    public String delete(String[] ids,
	    com.my.controller.sys.project.article.model.ArticleModel articleModel,
	    PageUtils page, Model model) {
	try {
	    if (ids != null) {
		for (String id : ids) {
		    System.out.println("删除数据：【" + id + "】");
		    articleService.deleteById(id);
		}
	    }
	    return "redirect:/sys/project/article/dataForPage.action";
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}

    }

    /**
     * 
     * 描述:上传文章标题图片
     * 
     * @throws Exception
     *
     */
    @RequestMapping("/uploadeImg")
    public @ResponseBody Map uploadeImg(
	    @RequestParam(value = "documentFile") MultipartFile file,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	Map resultMap = new HashMap();
	String resultMess = "false";
	Document document = new Document();
	document.setDocumentTitle(file.getOriginalFilename());
	String realPath = request.getServletContext().getRealPath("/");
	String subUrl = "/article";
	try {
	    document.setDocumentDeclare("文章标题图片");
	    documentService.save(document, file, realPath, subUrl);
	    resultMess = "success";
	    resultMap.put("mess", resultMess);
	    resultMap.put("documentId", document.getId());
	    return resultMap;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    resultMap.put("mess", "系统出现了异常，我们正在加紧抢修！");
	    e.printStackTrace();
	    return resultMap;
	}

    }

    /**
     * 删除图片
     * 
     * @param ids
     * @return
     */
    @RequestMapping("deleteImgGroup")
    public @ResponseBody Map<String, Object> deleteImgGroup(String[] ids) {
	Map<String, Object> result = new HashMap<String, Object>();
	try {
	    if (ids != null) {
		for (String id : ids) {
		    System.out.println("删除数据：【" + id + "】");
		    documentService.deleteById(id);
		}
	    }
	    result.put("mess", "success");
	    return result;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}

    }

    /**
     * 
     * 描述:初始化编辑页面
     * 
     * @author: 李忠翔 @date： 2016年7月7日
     * @param documentModel
     * @param model
     * @return
     */
    @RequestMapping("/remarkImgPage")
    public String remarkImgPage(
	    com.my.controller.sys.project.document.model.DocumentModel documentModel,
	    Model model) {
	if (documentModel.getDocument() != null) {
	    if (StringUtils.isNotBlank(documentModel.getDocument().getId())) {
		documentModel.setDocument(documentService
			.findById(documentModel.getDocument().getId()));
	    }
	}
	model.addAttribute("documentModel", documentModel);
	return "/webPage/sys/project/article/imgRemark.jsp";
    }

    /**
     * 
     * 描述:保存修改文件
     * 
     * @throws Exception
     *
     */
    @RequestMapping("/updateImgRemark")
    public @ResponseBody Map<String,Object> updateImgRemark(DocumentModel documentModel,HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	Map<String, Object> result = new HashMap<String, Object>();
	if (documentModel == null) {
	    result.put("mess", "系统出现了错误");
	    return result;
	}
	if (documentModel.getDocument() == null) {
	    result.put("mess", "系统出现了错误");
	    return result;
	}
	if (StringUtils.isBlank(documentModel.getDocument().getId())) {
	    result.put("mess", "系统出现了错误");
	    return result;
	}
	Document d = documentService.findById(documentModel.getDocument().getId());
	OverridUtils.overridNotNull(d, documentModel.getDocument(), Document.class);
	documentService.update(d);
	try {
	    result.put("mess", "success");
	    return result;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    result.put("mess", "系统出现了异常，我们正在加紧抢修！");
	    e.printStackTrace();
	    return result;
	}

    }
}
