package com.my.controller.sys.project.generateCode;

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

import com.my.controller.sys.project.generateCode.model.GenerateCodeModel;
import com.my.project.entity.GenerateCode;
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
@RequestMapping(value="/sys/project/generateCode")
public class GenerateCodeAction {
	@Autowired 
	private com.my.project.service.GenerateCodeService generateCodeService;
	
	/**
	 * 
	 * 描述:保存修改角色
	 * @throws Exception 
	 *
	 */
	@RequestMapping("/save")
	public @ResponseBody Map save(com.my.controller.sys.project.generateCode.model.GenerateCodeModel generateCodeModel) throws Exception{
		Map resultMap = new HashMap();
		String resultMess = "false";
		if(generateCodeModel.getGenerateCode()!=null){
			if(StringUtils.isNotBlank(generateCodeModel.getGenerateCode().getId())){//更新
				//根据id查询角色
				com.my.project.entity.GenerateCode tempGenerateCode = generateCodeService.findById(generateCodeModel.getGenerateCode().getId());
				OverridUtils.overridNotNull(tempGenerateCode,generateCodeModel.getGenerateCode(),com.my.project.entity.GenerateCode.class);
				generateCodeModel.setGenerateCode(tempGenerateCode);
				generateCodeService.update(generateCodeModel.getGenerateCode());
				resultMess="success";
			}else{
			    	generateCodeModel.getGenerateCode().setState(GenerateCode.AVAILABLE);
				generateCodeService.save(generateCodeModel.getGenerateCode());
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
	 * @param generateCodeModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/form")
	public String form(com.my.controller.sys.project.generateCode.model.GenerateCodeModel generateCodeModel,Model model) {		
		if(generateCodeModel.getGenerateCode()!=null){
			if(StringUtils.isNotBlank(generateCodeModel.getGenerateCode().getId())){
				generateCodeModel.setGenerateCode(generateCodeService.findById(generateCodeModel.getGenerateCode().getId()));								
			}
		}
		model.addAttribute("generateCodeModel", generateCodeModel);
		return "/webPage/sys/project/generateCode/form.jsp";
	}
	
	/**
	 * 
	 * 描述:查询数据，分页查询
	 * @author: 李忠翔
	 * @date： 2016年7月11日
	 * @param generateCodeModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/dataForPage")
	public String dataForPage(com.my.controller.sys.project.generateCode.model.GenerateCodeModel generateCodeModel,PageUtils page,Model model) {
		QueryHelper queryHelper = new QueryHelper(com.my.project.entity.GenerateCode.class, "r");
		//queryHelper.addOrder("roleName", QueryHelper.ORDER_BY_ASC);//默认字段升序排序
		/*
		 * 添加条件
		 */
		if (generateCodeModel != null) {
			if (generateCodeModel.getGenerateCode() != null) {
				if (StringUtils.isNotBlank(generateCodeModel.getGenerateCode().getId())) {
					queryHelper.addCondition("id = ?", generateCodeModel.getGenerateCode().getId());
				}
			}
		}
		if (generateCodeModel != null) {
			if (generateCodeModel.getGenerateCode() != null) {
				if (StringUtils.isNotBlank(generateCodeModel.getGenerateCode().getClassName())) {
					queryHelper.addCondition("className = ?", generateCodeModel.getGenerateCode().getClassName());
				}
			}
		}
		if (generateCodeModel != null) {
			if (generateCodeModel.getGenerateCode() != null) {
				if (StringUtils.isNotBlank(generateCodeModel.getGenerateCode().getModuleName())) {
					queryHelper.addCondition("moduleName like ?", "%"+generateCodeModel.getGenerateCode().getModuleName()+"%");
				}
			}
		}
		queryHelper.addOrder("state", QueryHelper.ORDER_BY_DESC);
		page = generateCodeService.list(queryHelper, page.getPgNo(), page.getPgSize());
		model.addAttribute("page", page);
		model.addAttribute("generateCodeModel", generateCodeModel);
		model.addAttribute("stateMap", GenerateCode.STATESTRMAP);
		
		return "/webPage/sys/project/generateCode/generateCode.jsp";
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
	        String fileName=new String("自动生成代码模块.xlsx".getBytes("UTF-8"), "iso8859-1");
	        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
			OutputStream os = response.getOutputStream();
			generateCodeService.ExportModel(os,com.my.project.entity.GenerateCode.class);
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
			List<com.my.project.entity.GenerateCode> list = generateCodeService.list();
			generateCodeService.ExportDatas(os,com.my.project.entity.GenerateCode.class,list);
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
			generateCodeService.importDatas(in, fileName,com.my.project.entity.GenerateCode.class,null);
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
	public String delete(String[] ids,com.my.controller.sys.project.generateCode.model.GenerateCodeModel generateCodeModel,PageUtils page,Model model) {
		try {
			if(ids!=null){
				for (String id : ids) {
					System.out.println("删除数据：【"+id+"】");
					generateCodeService.deleteById(id);
				}
			}
			return "redirect:/sys/project/generateCode/dataForPage.action";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 生成代码请求
	 * @param generateCodeModel
	 * @return
	 */
	@RequestMapping("generateCode")
	public @ResponseBody Map<String,Object> generateCode(GenerateCodeModel generateCodeModel) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			generateCodeService.generateCode(generateCodeModel.getGenerateCode().getId());
			resultMap.put("mess", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("mess", "系统出错了");
		}
		
		return resultMap;
	} 
	
	/**
	 * 解锁
	 * @param generateCodeModel
	 * @return
	 */
	@RequestMapping("removeLock")
	public @ResponseBody Map<String,Object> removeLock(GenerateCodeModel generateCodeModel) {
	    HashMap<String, Object> resultMap = new HashMap<String, Object>();
	    
	    try {
		if(generateCodeModel==null) {
		    resultMap.put("mess", "系统出错了");
		    return resultMap;
		}
		if(generateCodeModel.getGenerateCode()==null) {
		    resultMap.put("mess", "系统出错了");
		    return resultMap;
		}
		if (StringUtils.isBlank(generateCodeModel.getGenerateCode().getId())) {
		    resultMap.put("mess", "系统出错了");
		    return resultMap;
		}
		changeState(generateCodeModel,GenerateCode.AVAILABLE);
		resultMap.put("mess", "success");
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		resultMap.put("mess", "系统出错了");
	    }
	    
	    return resultMap;
	}
	
	/**
	 * 锁定
	 * @param generateCodeModel
	 * @return
	 */
	@RequestMapping("lock")
	public @ResponseBody Map<String,Object> lock(GenerateCodeModel generateCodeModel) {
	    HashMap<String, Object> resultMap = new HashMap<String, Object>();
	    
	    try {
		if(generateCodeModel==null) {
		    resultMap.put("mess", "系统出错了");
		    return resultMap;
		}
		if(generateCodeModel.getGenerateCode()==null) {
		    resultMap.put("mess", "系统出错了");
		    return resultMap;
		}
		if (StringUtils.isBlank(generateCodeModel.getGenerateCode().getId())) {
		    resultMap.put("mess", "系统出错了");
		    return resultMap;
		}
		changeState(generateCodeModel,GenerateCode.LOCK);
		resultMap.put("mess", "success");
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		resultMap.put("mess", "系统出错了");
	    }
	    
	    return resultMap;
	}

	/**
	 * @param generateCodeModel
	 * @throws Exception
	 */
	private void changeState(GenerateCodeModel generateCodeModel,Integer state)
		throws Exception {
	    GenerateCode g = generateCodeService.findById(generateCodeModel.getGenerateCode().getId());
	    g.setState(state);
	    generateCodeService.update(g);
	} 
}
