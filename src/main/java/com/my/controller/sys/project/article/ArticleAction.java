package com.my.controller.sys.project.article;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping(value="/sys/project/article")
public class ArticleAction {
	@Autowired 
	private com.my.project.service.ArticleService articleService;
	
	/**
	 * 
	 * 描述:保存修改角色
	 * @throws Exception 
	 *
	 */
	@RequestMapping("/save")
	public @ResponseBody Map save(com.my.controller.sys.project.article.model.ArticleModel articleModel) throws Exception{
		Map resultMap = new HashMap();
		String resultMess = "false";
		if(articleModel.getArticle()!=null){
			if(StringUtils.isNotBlank(articleModel.getArticle().getId())){//更新
				//根据id查询角色
				com.my.project.entity.Article tempArticle = articleService.findById(articleModel.getArticle().getId());
				OverridUtils.overridNotNull(tempArticle,articleModel.getArticle(),com.my.project.entity.Article.class);
				articleModel.setArticle(tempArticle);
				articleService.update(articleModel.getArticle());
				resultMess="success";
			}else{
				articleService.save(articleModel.getArticle());
				resultMess="success";
			}
		}
		
		resultMap.put("mess",resultMess);
		return resultMap;
	}
	
	/**
	 * 
	 * 描述:初始化编辑页面
	 * @author: 李忠翔
	 * @date： 2016年7月7日
	 * @param articleModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/form")
	public String form(com.my.controller.sys.project.article.model.ArticleModel articleModel,Model model) {		
		if(articleModel.getArticle()!=null){
			if(StringUtils.isNotBlank(articleModel.getArticle().getId())){
				articleModel.setArticle(articleService.findById(articleModel.getArticle().getId()));								
			}
		}
		model.addAttribute("articleModel", articleModel);
		return "/webPage/sys/project/article/form.jsp";
	}
	
	/**
	 * 
	 * 描述:查询数据，分页查询
	 * @author: 李忠翔
	 * @date： 2016年7月11日
	 * @param articleModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/dataForPage")
	public String dataForPage(com.my.controller.sys.project.article.model.ArticleModel articleModel,PageUtils page,Model model) {
		QueryHelper queryHelper = new QueryHelper(com.my.project.entity.Article.class, "r");
		//queryHelper.addOrder("roleName", QueryHelper.ORDER_BY_ASC);//默认字段升序排序
		/*
		 * 添加条件
		 */
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getFullTitle())) {
					queryHelper.addCondition("fullTitle = ?", articleModel.getArticle().getFullTitle());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getAuthor())) {
					queryHelper.addCondition("author = ?", articleModel.getArticle().getAuthor());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getSubhead())) {
					queryHelper.addCondition("subhead = ?", articleModel.getArticle().getSubhead());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getTitleImageID())) {
					queryHelper.addCondition("titleImageID = ?", articleModel.getArticle().getTitleImageID());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getContent())) {
					queryHelper.addCondition("content = ?", articleModel.getArticle().getContent());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getType())) {
					queryHelper.addCondition("type = ?", articleModel.getArticle().getType());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getTitle())) {
					queryHelper.addCondition("title = ?", articleModel.getArticle().getTitle());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getArticleIntro())) {
					queryHelper.addCondition("articleIntro = ?", articleModel.getArticle().getArticleIntro());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getRecord())) {
					queryHelper.addCondition("record = ?", articleModel.getArticle().getRecord());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getTop())) {
					queryHelper.addCondition("top = ?", articleModel.getArticle().getTop());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getId())) {
					queryHelper.addCondition("id = ?", articleModel.getArticle().getId());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getTypeID())) {
					queryHelper.addCondition("typeID = ?", articleModel.getArticle().getTypeID());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getComment())) {
					queryHelper.addCondition("comment = ?", articleModel.getArticle().getComment());
				}
			}
		}
		if (articleModel != null) {
			if (articleModel.getArticle() != null) {
				if (StringUtils.isNotBlank(articleModel.getArticle().getKeyword())) {
					queryHelper.addCondition("keyword = ?", articleModel.getArticle().getKeyword());
				}
			}
		}
		page = articleService.list(queryHelper, page.getPgNo(), page.getPgSize());
		model.addAttribute("page", page);
		model.addAttribute("articleModel", articleModel);
		
		return "/webPage/sys/project/article/article.jsp";
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
	        String fileName=new String("文章.xlsx".getBytes("UTF-8"), "iso8859-1");
	        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
			OutputStream os = response.getOutputStream();
			articleService.ExportModel(os,com.my.project.entity.Article.class);
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
			List<com.my.project.entity.Article> list = articleService.list();
			articleService.ExportDatas(os,com.my.project.entity.Article.class,list);
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
			articleService.importDatas(in, fileName,com.my.project.entity.Article.class,null);
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
	public String delete(String[] ids,com.my.controller.sys.project.article.model.ArticleModel articleModel,PageUtils page,Model model) {
		try {
			if(ids!=null){
				for (String id : ids) {
					System.out.println("删除数据：【"+id+"】");
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
	
}
