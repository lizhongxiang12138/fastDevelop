package ${packageName};

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
@RequestMapping(value="${requestUrl}")
public class ${className} {
	@Autowired 
	private ${serviceInterface} ${serviceInterfaceName};
	
	/**
	 * 
	 * 描述:保存修改角色
	 * @throws Exception 
	 *
	 */
	@RequestMapping("/save")
	public @ResponseBody Map save(${entityModel} ${entityModelName}) throws Exception{
		Map resultMap = new HashMap();
		String resultMess = "false";
		if(${entityModelName}.get${entityClassName}()!=null){
			if(StringUtils.isNotBlank(${entityModelName}.get${entityClassName}().getId())){//更新
				//根据id查询角色
				${entity} temp${entityClassName} = ${serviceInterfaceName}.findById(${entityModelName}.get${entityClassName}().getId());
				OverridUtils.overridNotNull(temp${entityClassName},${entityModelName}.get${entityClassName}(),${entity}.class);
				${entityModelName}.set${entityClassName}(temp${entityClassName});
				${serviceInterfaceName}.update(${entityModelName}.get${entityClassName}());
				resultMess="success";
			}else{
				${serviceInterfaceName}.save(${entityModelName}.get${entityClassName}());
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
	 * @param ${entityModelName}
	 * @param model
	 * @return
	 */
	@RequestMapping("/form")
	public String form(${entityModel} ${entityModelName},Model model) {		
		if(${entityModelName}.get${entityClassName}()!=null){
			if(StringUtils.isNotBlank(${entityModelName}.get${entityClassName}().getId())){
				${entityModelName}.set${entityClassName}(${serviceInterfaceName}.findById(${entityModelName}.get${entityClassName}().getId()));								
			}
		}
		model.addAttribute("${entityModelName}", ${entityModelName});
		return "/webPage${requestUrl}/form.jsp";
	}
	
	/**
	 * 
	 * 描述:查询数据，分页查询
	 * @author: 李忠翔
	 * @date： 2016年7月11日
	 * @param ${entityModelName}
	 * @param model
	 * @return
	 */
	@RequestMapping("/dataForPage")
	public String dataForPage(${entityModel} ${entityModelName},PageUtils page,Model model) {
		QueryHelper queryHelper = new QueryHelper(${entity}.class, "r");
		//queryHelper.addOrder("roleName", QueryHelper.ORDER_BY_ASC);//默认字段升序排序
		/*
		 * 添加条件
		 */
		<#list fields as f>
		if (${entityModelName} != null) {
			if (${entityModelName}.get${entityClassName}() != null) {
				if (StringUtils.isNotBlank(${entityModelName}.get${entityClassName}().get${f.fieldName?cap_first}())) {
					queryHelper.addCondition("${f.fieldName} = ?", ${entityModelName}.get${entityClassName}().get${f.fieldName?cap_first}());
				}
			}
		}
		</#list>
		page = ${serviceInterfaceName}.list(queryHelper, page.getPgNo(), page.getPgSize());
		model.addAttribute("page", page);
		model.addAttribute("${entityModelName}", ${entityModelName});
		
		return "/webPage${requestUrl}/${entityClassName?uncap_first}.jsp";
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
	        String fileName=new String("${excel_CH}.xlsx".getBytes("UTF-8"), "iso8859-1");
	        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
			OutputStream os = response.getOutputStream();
			${serviceInterfaceName}.ExportModel(os,${entity}.class);
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
			String fileName=new String("${excel_CH}.xlsx".getBytes("UTF-8"), "iso8859-1");
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
			OutputStream os = response.getOutputStream();
			List<${entity}> list = ${serviceInterfaceName}.list();
			${serviceInterfaceName}.ExportDatas(os,${entity}.class,list);
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
			${serviceInterfaceName}.importDatas(in, fileName,${entity}.class,null);
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
	public String delete(String[] ids,${entityModel} ${entityModelName},PageUtils page,Model model) {
		try {
			if(ids!=null){
				for (String id : ids) {
					System.out.println("删除数据：【"+id+"】");
					${serviceInterfaceName}.deleteById(id);
				}
			}
			return "redirect:${requestUrl}/dataForPage.action";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
}
