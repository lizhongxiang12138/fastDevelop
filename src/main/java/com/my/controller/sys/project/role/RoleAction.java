package com.my.controller.sys.project.role;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.components.ElseIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.controller.sys.project.role.model.RoleModel;
import com.my.project.entity.TbMenu;
import com.my.project.entity.TbRole;
import com.my.project.entity.relation.RoleMenuRelation;
import com.my.project.service.MenuService;
import com.my.project.service.RoleMenuRelationService;
import com.my.project.service.RoleService;
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
@RequestMapping(value="/sys/project/role")
public class RoleAction {
	@Autowired 
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleMenuRelationService roleMenuRelationService;
	
	/**
	 * 
	 * 描述:获取角色，角色数据使用json格式返回
	 *
	 */
	@RequestMapping(value="/getroles")
	public @ResponseBody List<TbRole> getRoles(RoleModel model) {
		List<TbRole> roles;
		/**
		 * 查询根角色
		 */
		QueryHelper qh = new QueryHelper(TbRole.class, "m");
		qh.addCondition("m.roleGrade=?", 0+"");
		roles = roleService.list(qh);

		return roles;
	}
	
	/**
	 * 
	 * 描述:保存修改角色
	 * @throws Exception 
	 *
	 */
	@RequestMapping("/save")
	public @ResponseBody Map save(RoleModel roleModel) throws Exception{
		Map resultMap = new HashMap();
		String resultMess = "false";
		if(roleModel.getRole()!=null){
			if(StringUtils.isNotBlank(roleModel.getRole().getId())){//更新
				//根据id查询角色
				TbRole tempRole = roleService.findById(roleModel.getRole().getId());
				OverridUtils.overridNotNull(tempRole,roleModel.getRole(),TbRole.class);
				roleModel.setRole(tempRole);
				roleService.update(roleModel);
				resultMess="success";
			}else{
				roleService.save(roleModel);
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
	 * @param roleModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/form")
	public String form(RoleModel roleModel,Model model) {
		//读取菜单
		QueryHelper queryHelper = new QueryHelper(TbMenu.class, "m");
		queryHelper.addCondition("m.menuGrade=?","1");
		List<TbMenu> menus = menuService.list(queryHelper);
		model.addAttribute("menus", menus);
		
		if(roleModel.getRole()!=null){
			if(StringUtils.isNotBlank(roleModel.getRole().getId())){
				roleModel.setRole(roleService.findById(roleModel.getRole().getId()));
				
				QueryHelper qh = new QueryHelper(RoleMenuRelation.class, "rm");
				qh.addCondition("rm.roleId=?", roleModel.getRole().getId());
				List<RoleMenuRelation> rms = roleMenuRelationService.list(qh);
				
				if(rms!=null&&rms.size()>0){
					ArrayList<String> strs = new ArrayList<String>();
					for (RoleMenuRelation rm : rms) {
						strs.add(rm.getMenuId());
					}
					roleModel.setMenuList(strs.toArray(new String[rms.size()]));
				}
			}
		}
		model.addAttribute("roleModel", roleModel);
		return "/webPage/sys/project/role/form.jsp";
	}
	
	/**
	 * 
	 * 描述:查询数据，分页查询
	 * @author: 李忠翔
	 * @date： 2016年7月11日
	 * @param roleModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/dataForPage")
	public String dataForPage(RoleModel roleModel,PageUtils page,Model model) {
		QueryHelper queryHelper = new QueryHelper(TbRole.class, "r");
		queryHelper.addOrder("roleName", QueryHelper.ORDER_BY_ASC);//默认字段升序排序
		/*
		 * 添加条件
		 */
		if (roleModel != null) {
			if (roleModel.getRole() != null) {
				if (StringUtils.isNotBlank(roleModel.getRole().getRoleName())) {
					queryHelper.addCondition("roleName like ?", "%" + roleModel.getRole().getRoleName() + "%");
				}
			}
		}
		page = roleService.list(queryHelper, page.getPgNo(), page.getPgSize());
		model.addAttribute("page", page);
		model.addAttribute("roleModel", roleModel);
		
		return "/webPage/sys/project/role/role.jsp";
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
	        String fileName=new String("角色数据.xlsx".getBytes("UTF-8"), "iso8859-1");
	        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
			OutputStream os = response.getOutputStream();
			roleService.ExportModel(os,TbRole.class);
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
			List<TbRole> list = roleService.list();
			roleService.ExportDatas(os,TbRole.class,list);
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
			String[] uniqueField = {"roleName"};
			roleService.importDatas(in, fileName,TbRole.class,uniqueField);
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
	public String delete(String[] ids,RoleModel roleModel,PageUtils page,Model model) {
		try {
			if(ids!=null){
				for (String id : ids) {
					System.out.println("删除数据：【"+id+"】");
					roleService.deleteById(id);
				}
			}
			return "redirect:/sys/project/role/dataForPage.action";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 权限管理
	 * @param ids
	 * @param roleModel
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("permissions")
	public void permissions(RoleModel roleModel,PageUtils page,Model model,HttpServletRequest request,HttpServletResponse response) {
	    try {
		String requestMethod = request.getParameter("request");
		if("page".equals(requestMethod)) {
		    request.setAttribute("role", roleModel.getRole());
		    request.getRequestDispatcher("/webPage/sys/project/role/permissions.jsp").forward(request, response);
		}else if("permissionsDetailPage".equals(requestMethod)){
		    request.setAttribute("role",roleModel.getRole());
		    request.setAttribute("menu", roleModel.getMenu());
		    request.getRequestDispatcher("/webPage/sys/project/role/permissionsDetail.jsp").forward(request, response);
		}
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException(e);
	    }
	    
	}
	
}
