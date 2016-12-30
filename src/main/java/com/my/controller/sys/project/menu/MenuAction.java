package com.my.controller.sys.project.menu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.my.controller.sys.project.menu.model.MenuModel;
import com.my.project.entity.TbMenu;
import com.my.project.entity.TbRole;
import com.my.project.service.MenuService;
import com.my.project.utils.OverridUtils;
import com.my.project.utils.QueryHelper;

/**
 * 
 * 描述: 菜单模块请求控制器
 * 
 * @author 李忠翔 时间 : 2016年6月26日
 */
@Controller
@RequestMapping(value = "/sys/project/menu")
public class MenuAction {
    @Autowired
    private MenuService menuService;

    /**
     * 
     * 描述:获取菜单，菜单数据使用json格式返回
     *
     */
    @RequestMapping(value = "/getmenus")
    public @ResponseBody List<TbMenu> getMenus(MenuModel model) {
	List<TbMenu> menus = null;
	try {
	    QueryHelper qh = new QueryHelper(TbMenu.class, "m");
	    qh.addCondition("m.menuGrade=?", 0 + "");
	    qh.addOrder("text", QueryHelper.ORDER_BY_DESC);
	    menus = menuService.list(qh);
	    menuService.setChildren(menus);
	    /**
	     * 查询根菜单
	     */
//	    for (int i = 0; i < menus.size(); i++) {
//		qh = new QueryHelper(TbMenu.class, "m");
//		qh.addCondition("m.menuParent=?", menus.get(i).getId());
//		qh.addOrder("text", QueryHelper.ORDER_BY_DESC);
//		menus.get(i).setChildren(menuService.list(qh));
//	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return menus;
    }

    /**
     * 
     * 描述:保存修改菜单
     * 
     * @throws Exception
     *
     */
    @RequestMapping("/save")
    public @ResponseBody Map save(MenuModel menuModel) throws Exception {
	Map resultMap = new HashMap();
	String resultMess = "false";
	if (menuModel.getMenu() != null) {
	    if (StringUtils.isNotBlank(menuModel.getMenu().getId())) {// 更新
		// 根据id查询菜单
		TbMenu tempMenu = menuService
			.findById(menuModel.getMenu().getId());
		OverridUtils.overridNotNull(tempMenu, menuModel.getMenu(),
			TbMenu.class);
		menuService.update(tempMenu);
		resultMess = "success";
	    } else {
		// 新增
		menuService.save(menuModel.getMenu());
		resultMess = "success";
	    }
	}

	resultMap.put("mess", resultMess);
	return resultMap;
    }

    /**
     * 
     * 描述:删除
     * 
     * @author: 李忠翔 @date： 2016年7月5日
     * @param menuModel
     * @return
     */
    @RequestMapping("/delete")
    public @ResponseBody Map delete(MenuModel menuModel) {
	Map resultMap = new HashMap();
	String resultMess = "false";

	try {
	    if (menuModel.getMenu() != null) {
		if (StringUtils.isNotBlank(menuModel.getMenu().getId())) {
		    menuService.deleteById(menuModel.getMenu().getId());
		    resultMess = "success";
		}
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	resultMap.put("mess", resultMess);
	return resultMap;
    }

    /**
     * 
     * 描述:初始化编辑页面
     * 
     * @author: 李忠翔 @date： 2016年7月7日
     * @param menuModel
     * @param model
     * @return
     */
    @RequestMapping("/form")
    public String form(MenuModel menuModel, Model model) {
	if (menuModel.getMenu() != null) {
	    if (StringUtils.isNotBlank(menuModel.getMenu().getId())) {
		menuModel.setMenu(
			menuService.findById(menuModel.getMenu().getId()));
		model.addAttribute("menuModel", menuModel);
	    }
	}
	return "/webPage/sys/project/menu/form.jsp";
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
	    String fileName = new String("菜单资源数据.xlsx".getBytes("UTF-8"),
		    "iso8859-1");
	    response.setHeader("Content-Disposition",
		    "attachment;fileName=" + fileName);
	    OutputStream os = response.getOutputStream();
	    menuService.ExportModel(os, TbMenu.class);
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
	    String fileName = new String("菜单资源数据.xlsx".getBytes("UTF-8"),
		    "iso8859-1");
	    response.setHeader("Content-Disposition",
		    "attachment;fileName=" + fileName);
	    OutputStream os = response.getOutputStream();
	    List<TbMenu> list = menuService.list();
	    menuService.ExportDatas(os, TbMenu.class, list);
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
	    String[] uniqueField = { "text" };
	    menuService.importDatas(in, fileName, TbMenu.class, uniqueField);
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
}
