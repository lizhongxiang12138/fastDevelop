package com.my.controller.sys.project.historicalRelic;

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
@RequestMapping(value="/sys/project/historicalRelic")
public class HistoricalRelicAction {
	@Autowired 
	private com.my.project.service.HistoricalRelicService historicalRelicService;
	
	/**
	 * 
	 * 描述:保存修改角色
	 * @throws Exception 
	 *
	 */
	@RequestMapping("/save")
	public @ResponseBody Map save(com.my.controller.sys.project.historicalRelic.model.HistoricalRelicModel historicalRelicModel) throws Exception{
		Map resultMap = new HashMap();
		String resultMess = "false";
		if(historicalRelicModel.getHistoricalRelic()!=null){
			if(StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getId())){//更新
				//根据id查询角色
				com.my.project.entity.HistoricalRelic tempHistoricalRelic = historicalRelicService.findById(historicalRelicModel.getHistoricalRelic().getId());
				OverridUtils.overridNotNull(tempHistoricalRelic,historicalRelicModel.getHistoricalRelic(),com.my.project.entity.HistoricalRelic.class);
				historicalRelicModel.setHistoricalRelic(tempHistoricalRelic);
				historicalRelicService.update(historicalRelicModel.getHistoricalRelic());
				resultMess="success";
			}else{
				historicalRelicService.save(historicalRelicModel.getHistoricalRelic());
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
	 * @param historicalRelicModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/form")
	public String form(com.my.controller.sys.project.historicalRelic.model.HistoricalRelicModel historicalRelicModel,Model model) {		
		if(historicalRelicModel.getHistoricalRelic()!=null){
			if(StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getId())){
				historicalRelicModel.setHistoricalRelic(historicalRelicService.findById(historicalRelicModel.getHistoricalRelic().getId()));								
			}
		}
		model.addAttribute("historicalRelicModel", historicalRelicModel);
		return "/webPage/sys/project/historicalRelic/form.jsp";
	}
	
	/**
	 * 
	 * 描述:查询数据，分页查询
	 * @author: 李忠翔
	 * @date： 2016年7月11日
	 * @param historicalRelicModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/dataForPage")
	public String dataForPage(com.my.controller.sys.project.historicalRelic.model.HistoricalRelicModel historicalRelicModel,PageUtils page,Model model) {
		QueryHelper queryHelper = new QueryHelper(com.my.project.entity.HistoricalRelic.class, "r");
		//queryHelper.addOrder("roleName", QueryHelper.ORDER_BY_ASC);//默认字段升序排序
		/*
		 * 添加条件
		 */
		if (historicalRelicModel != null) {
			if (historicalRelicModel.getHistoricalRelic() != null) {
				if (StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getIntro())) {
					queryHelper.addCondition("intro = ?", historicalRelicModel.getHistoricalRelic().getIntro());
				}
			}
		}
		if (historicalRelicModel != null) {
			if (historicalRelicModel.getHistoricalRelic() != null) {
				if (StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getShowTime())) {
					queryHelper.addCondition("showTime = ?", historicalRelicModel.getHistoricalRelic().getShowTime());
				}
			}
		}
		if (historicalRelicModel != null) {
			if (historicalRelicModel.getHistoricalRelic() != null) {
				if (StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getComUpTime())) {
					queryHelper.addCondition("comUpTime = ?", historicalRelicModel.getHistoricalRelic().getComUpTime());
				}
			}
		}
		if (historicalRelicModel != null) {
			if (historicalRelicModel.getHistoricalRelic() != null) {
				if (StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getId())) {
					queryHelper.addCondition("id = ?", historicalRelicModel.getHistoricalRelic().getId());
				}
			}
		}
		if (historicalRelicModel != null) {
			if (historicalRelicModel.getHistoricalRelic() != null) {
				if (StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getOutlineDescribe())) {
					queryHelper.addCondition("outlineDescribe = ?", historicalRelicModel.getHistoricalRelic().getOutlineDescribe());
				}
			}
		}
		if (historicalRelicModel != null) {
			if (historicalRelicModel.getHistoricalRelic() != null) {
				if (StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getTypeID())) {
					queryHelper.addCondition("typeID = ?", historicalRelicModel.getHistoricalRelic().getTypeID());
				}
			}
		}
		if (historicalRelicModel != null) {
			if (historicalRelicModel.getHistoricalRelic() != null) {
				if (StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getTitleImageID())) {
					queryHelper.addCondition("titleImageID = ?", historicalRelicModel.getHistoricalRelic().getTitleImageID());
				}
			}
		}
		if (historicalRelicModel != null) {
			if (historicalRelicModel.getHistoricalRelic() != null) {
				if (StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getShowAddress())) {
					queryHelper.addCondition("showAddress = ?", historicalRelicModel.getHistoricalRelic().getShowAddress());
				}
			}
		}
		if (historicalRelicModel != null) {
			if (historicalRelicModel.getHistoricalRelic() != null) {
				if (StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getTitle())) {
					queryHelper.addCondition("title = ?", historicalRelicModel.getHistoricalRelic().getTitle());
				}
			}
		}
		if (historicalRelicModel != null) {
			if (historicalRelicModel.getHistoricalRelic() != null) {
				if (StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getHistoricalTime())) {
					queryHelper.addCondition("historicalTime = ?", historicalRelicModel.getHistoricalRelic().getHistoricalTime());
				}
			}
		}
		if (historicalRelicModel != null) {
			if (historicalRelicModel.getHistoricalRelic() != null) {
				if (StringUtils.isNotBlank(historicalRelicModel.getHistoricalRelic().getType())) {
					queryHelper.addCondition("type = ?", historicalRelicModel.getHistoricalRelic().getType());
				}
			}
		}
		page = historicalRelicService.list(queryHelper, page.getPgNo(), page.getPgSize());
		model.addAttribute("page", page);
		model.addAttribute("historicalRelicModel", historicalRelicModel);
		
		return "/webPage/sys/project/historicalRelic/historicalRelic.jsp";
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
	        String fileName=new String("文物.xlsx".getBytes("UTF-8"), "iso8859-1");
	        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
			OutputStream os = response.getOutputStream();
			historicalRelicService.ExportModel(os,com.my.project.entity.HistoricalRelic.class);
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
			List<com.my.project.entity.HistoricalRelic> list = historicalRelicService.list();
			historicalRelicService.ExportDatas(os,com.my.project.entity.HistoricalRelic.class,list);
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
			historicalRelicService.importDatas(in, fileName,com.my.project.entity.HistoricalRelic.class,null);
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
	public String delete(String[] ids,com.my.controller.sys.project.historicalRelic.model.HistoricalRelicModel historicalRelicModel,PageUtils page,Model model) {
		try {
			if(ids!=null){
				for (String id : ids) {
					System.out.println("删除数据：【"+id+"】");
					historicalRelicService.deleteById(id);
				}
			}
			return "redirect:/sys/project/historicalRelic/dataForPage.action";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
}
