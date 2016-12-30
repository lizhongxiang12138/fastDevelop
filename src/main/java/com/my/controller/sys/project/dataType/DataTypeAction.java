package com.my.controller.sys.project.dataType;

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
@RequestMapping(value="/sys/project/dataType")
public class DataTypeAction {
	@Autowired 
	private com.my.project.service.DataTypeService dataTypeService;
	
	/**
	 * 
	 * 描述:保存修改角色
	 * @throws Exception 
	 *
	 */
	@RequestMapping("/save")
	public @ResponseBody Map save(com.my.controller.sys.project.dataType.model.DataTypeModel dataTypeModel) throws Exception{
		Map resultMap = new HashMap();
		String resultMess = "false";
		if(dataTypeModel.getDataType()!=null){
			if(StringUtils.isNotBlank(dataTypeModel.getDataType().getId())){//更新
				//根据id查询角色
				com.my.project.entity.DataType tempDataType = dataTypeService.findById(dataTypeModel.getDataType().getId());
				OverridUtils.overridNotNull(tempDataType,dataTypeModel.getDataType(),com.my.project.entity.DataType.class);
				dataTypeModel.setDataType(tempDataType);
				dataTypeService.update(dataTypeModel.getDataType());
				resultMess="success";
			}else{
				dataTypeService.save(dataTypeModel.getDataType());
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
	 * @param dataTypeModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/form")
	public String form(com.my.controller.sys.project.dataType.model.DataTypeModel dataTypeModel,Model model) {		
		if(dataTypeModel.getDataType()!=null){
			if(StringUtils.isNotBlank(dataTypeModel.getDataType().getId())){
				dataTypeModel.setDataType(dataTypeService.findById(dataTypeModel.getDataType().getId()));								
			}
		}
		model.addAttribute("dataTypeModel", dataTypeModel);
		return "/webPage/sys/project/dataType/form.jsp";
	}
	
	/**
	 * 
	 * 描述:查询数据，分页查询
	 * @author: 李忠翔
	 * @date： 2016年7月11日
	 * @param dataTypeModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/dataForPage")
	public String dataForPage(com.my.controller.sys.project.dataType.model.DataTypeModel dataTypeModel,PageUtils page,Model model) {
		QueryHelper queryHelper = new QueryHelper(com.my.project.entity.DataType.class, "r");
		//queryHelper.addOrder("roleName", QueryHelper.ORDER_BY_ASC);//默认字段升序排序
		/*
		 * 添加条件
		 */
		if (dataTypeModel != null) {
			if (dataTypeModel.getDataType() != null) {
				if (StringUtils.isNotBlank(dataTypeModel.getDataType().getRegex())) {
					queryHelper.addCondition("regex = ?", dataTypeModel.getDataType().getRegex());
				}
			}
		}
		if (dataTypeModel != null) {
			if (dataTypeModel.getDataType() != null) {
				if (StringUtils.isNotBlank(dataTypeModel.getDataType().getErrorMess())) {
					queryHelper.addCondition("errorMess = ?", dataTypeModel.getDataType().getErrorMess());
				}
			}
		}
		if (dataTypeModel != null) {
			if (dataTypeModel.getDataType() != null) {
				if (StringUtils.isNotBlank(dataTypeModel.getDataType().getId())) {
					queryHelper.addCondition("id = ?", dataTypeModel.getDataType().getId());
				}
			}
		}
		if (dataTypeModel != null) {
			if (dataTypeModel.getDataType() != null) {
				if (StringUtils.isNotBlank(dataTypeModel.getDataType().getTypeName())) {
					queryHelper.addCondition("typeName = ?", dataTypeModel.getDataType().getTypeName());
				}
			}
		}
		if (dataTypeModel != null) {
			if (dataTypeModel.getDataType() != null) {
				if (StringUtils.isNotBlank(dataTypeModel.getDataType().getTypeCode())) {
					queryHelper.addCondition("typeCode = ?", dataTypeModel.getDataType().getTypeCode());
				}
			}
		}
		page = dataTypeService.list(queryHelper, page.getPgNo(), page.getPgSize());
		model.addAttribute("page", page);
		model.addAttribute("dataTypeModel", dataTypeModel);
		
		return "/webPage/sys/project/dataType/dataType.jsp";
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
	        String fileName=new String("数据类型.xlsx".getBytes("UTF-8"), "iso8859-1");
	        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
			OutputStream os = response.getOutputStream();
			dataTypeService.ExportModel(os,com.my.project.entity.DataType.class);
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
			List<com.my.project.entity.DataType> list = dataTypeService.list();
			dataTypeService.ExportDatas(os,com.my.project.entity.DataType.class,list);
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
			dataTypeService.importDatas(in, fileName,com.my.project.entity.DataType.class,null);
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
	public String delete(String[] ids,com.my.controller.sys.project.dataType.model.DataTypeModel dataTypeModel,PageUtils page,Model model) {
		try {
			if(ids!=null){
				for (String id : ids) {
					System.out.println("删除数据：【"+id+"】");
					dataTypeService.deleteById(id);
				}
			}
			return "redirect:/sys/project/dataType/dataForPage.action";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
}
