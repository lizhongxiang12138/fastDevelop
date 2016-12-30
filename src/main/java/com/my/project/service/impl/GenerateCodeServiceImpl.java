package com.my.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.rules.TestName;
import org.springframework.stereotype.Service;
import com.my.base.service.impl.BaseServiceImpl;
import com.my.project.entity.GenerateCode;
import com.my.project.entity.TbMenu;
import com.my.project.service.MenuService;
import com.my.project.utils.QueryHelper;
import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

import autoGenerateModel.AutoGenerateUtil;


@Service("generateCodeService")
public class GenerateCodeServiceImpl extends BaseServiceImpl<com.my.project.entity.GenerateCode> implements com.my.project.service.GenerateCodeService {

	@Resource
	private MenuService menuService;
	
	@Override
	public void generateCode(String id) throws Exception {
		
		GenerateCode generateCode = findById(id);
		
		AutoGenerateUtil autoGenerateUtil = new AutoGenerateUtil(Class.forName(generateCode.getClassName()));
		/*
		 * 生成Dao
		 */
		String interfaceName = autoGenerateUtil.generateDao();
		autoGenerateUtil.generateDaoImpl(interfaceName);
		/*
		 * 生成Service
		 */
		interfaceName=autoGenerateUtil.generateService();
		autoGenerateUtil.generateServiceImpl(interfaceName);
		/*
		 * 生成EntityModel
		 */
		String entityModelClassName = autoGenerateUtil.generateEntityModel(generateCode.getPackageChildName());
		/*
		 * 生成controller
		 */
		String requestUrl = autoGenerateUtil.generateController(interfaceName, entityModelClassName);
		/*
		 * 生成FormJsp
		 */
		autoGenerateUtil.generateFormJsp(entityModelClassName);
		/*
		 * 生成importDatas
		 */
		autoGenerateUtil.generateImportDatas(entityModelClassName);
		/*
		 * 生成page.jsp
		 */
		autoGenerateUtil.generatePageJsp(entityModelClassName);
		
		/*
		 * 生成菜单
		 */
		QueryHelper qh = new QueryHelper(TbMenu.class, "m");
		qh.addCondition("text=?",generateCode.getModuleName());
		List<TbMenu> menus = menuService.list(qh);
		if(menus!=null) {
        		if(menus.size()==0){
        		    TbMenu menu = new TbMenu(generateCode.getModuleName(), "0");
        		    menu.setUrl(requestUrl+"/dataForPage.action");
        		    menuService.save(menu);
        		}
    		}
		
	}
	
}
