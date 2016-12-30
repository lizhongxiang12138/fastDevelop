package autoGenerateModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import com.my.annotation.excel.ExcelTable;
import com.my.annotation.page.PageMate;
import com.my.project.entity.DataType;
import com.my.project.entity.GenerateCode;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 
 * @author 李忠翔 实现模块的代码生成 2016年8月21日
 */
public class AutoGenerateUtil {

    public static void main(String[] args) throws Exception {
	AutoGenerateUtil autoGenerateUtil = new AutoGenerateUtil(
		GenerateCode.class);
	/*
	 * 生成Dao
	 */
	String interfaceName = autoGenerateUtil.generateDao();
	autoGenerateUtil.generateDaoImpl(interfaceName);
	/*
	 * 生成Service
	 */
	interfaceName = autoGenerateUtil.generateService();
	autoGenerateUtil.generateServiceImpl(interfaceName);
	/**
	 * 生成EntityModel
	 */
	String entityModelClassName = autoGenerateUtil
		.generateEntityModel("sys.project");
	/**
	 * 生成controller
	 */
	autoGenerateUtil.generateController(interfaceName,
		entityModelClassName);
	/**
	 * 生成FormJsp
	 */
	autoGenerateUtil.generateFormJsp(entityModelClassName);
	/**
	 * 生成importDatas
	 */
	autoGenerateUtil.generateImportDatas(entityModelClassName);
	/**
	 * 生成page.jsp
	 */
	autoGenerateUtil.generatePageJsp(entityModelClassName);
    }

    private String path;
    private Class clazz;
    private Configuration cfg;
    private Template temp;
    private Properties prop;

    public AutoGenerateUtil(Class clazz) throws Exception {
	this.clazz = clazz;
	// 初始化FreeMarker
	URL resource = this.getClass().getResource("");
	System.out.println(resource.getPath());
	this.path = resource.getPath();
	this.cfg = new Configuration();
	cfg.setDirectoryForTemplateLoading(new File(path));
	cfg.setObjectWrapper(new DefaultObjectWrapper());
	// 加载代码生成的配置文件配置文件
	InputStream in = this.getClass()
		.getResourceAsStream("config.properties");
	prop = new Properties();
	prop.load(in);
    }

    /**
     * 生成Dao接口
     * 
     * @return
     * @throws Exception
     */
    public String generateDao() throws Exception {
	this.temp = cfg.getTemplate("Dao.ftl");
	Map<String, Object> root = new HashMap<String, Object>();
	String className = "";
	String entitySimpleName = clazz.getSimpleName();
	if (entitySimpleName.contains("Tb")) {
	    className = entitySimpleName.replace("Tb", "") + "Dao";
	} else {
	    className = entitySimpleName + "Dao";
	}
	root.put("className", className);
	root.put("entity", clazz.getName());
	String packageName = prop.getProperty("classPath") + ".dao";
	root.put("packageName", packageName);

	String classPath = prop.getProperty("projectPath")
		+ packageName.replace(".", "\\");
	System.out.println(classPath);
	File file = new File(classPath, className + ".java");
	if (!file.exists()) {
	    file.createNewFile();
	}
	OutputStreamWriter out = new OutputStreamWriter(
		new FileOutputStream(file));

	temp.process(root, out);
	out.flush();
	out.close();
	System.out.println("[生成Dao接口————代码生成成功！]");
	return packageName + "." + className;
    }

    /**
     * 生成Dao实现类
     * 
     * @return
     * @throws Exception
     */
    public void generateDaoImpl(String interfaceName) throws Exception {
	this.temp = cfg.getTemplate("DaoImpl.ftl");
	/*
	 * 设置参数
	 */
	Map<String, Object> root = new HashMap<String, Object>();
	String className = interfaceName
		.substring(interfaceName.lastIndexOf(".") + 1) + "Impl";
	root.put("className", className);
	root.put("entity", clazz.getName());
	String packageName = prop.getProperty("classPath") + ".dao.impl";
	root.put("packageName", packageName);
	String beanName = interfaceName
		.substring(interfaceName.lastIndexOf(".") + 1);
	beanName = beanName.substring(0, 1).toLowerCase()
		+ beanName.substring(1);
	root.put("beanName", beanName);
	root.put("interfaceName", interfaceName);
	/*
	 * 输出文件
	 */
	String classPath = prop.getProperty("projectPath")
		+ packageName.replace(".", "\\");
	System.out.println(classPath);
	File file = new File(classPath, className + ".java");
	if (!file.exists()) {
	    file.createNewFile();
	}
	OutputStreamWriter out = new OutputStreamWriter(
		new FileOutputStream(file));
	// OutputStreamWriter out = new OutputStreamWriter(System.out);
	temp.process(root, out);
	out.flush();
	out.close();
	System.out.println("[生成Dao实现类————代码生成成功！]");
    }

    /**
     * 生成Service接口
     * 
     * @return
     * @throws Exception
     */
    public String generateService() throws Exception {
	this.temp = cfg.getTemplate("Service.ftl");
	Map<String, Object> root = new HashMap<String, Object>();
	String className = "";
	String entitySimpleName = clazz.getSimpleName();
	if (entitySimpleName.contains("Tb")) {
	    className = entitySimpleName.replace("Tb", "") + "Service";
	} else {
	    className = entitySimpleName + "Service";
	}
	root.put("className", className);
	root.put("entity", clazz.getName());
	String packageName = prop.getProperty("classPath") + ".service";
	root.put("packageName", packageName);

	String classPath = prop.getProperty("projectPath")
		+ packageName.replace(".", "\\");
	System.out.println(classPath);
	File file = new File(classPath, className + ".java");
	if (!file.exists()) {
	    file.createNewFile();
	}
	OutputStreamWriter out = new OutputStreamWriter(
		new FileOutputStream(file));
	// OutputStreamWriter out = new OutputStreamWriter(System.out);
	temp.process(root, out);
	out.flush();
	out.close();
	System.out.println("[生成Service接口————代码生成成功！]");
	return packageName + "." + className;
    }

    /**
     * 生成Service实现类
     * 
     * @return
     * @throws Exception
     */
    public void generateServiceImpl(String interfaceName) throws Exception {
	this.temp = cfg.getTemplate("ServiceImpl.ftl");
	/*
	 * 设置参数
	 */
	Map<String, Object> root = new HashMap<String, Object>();
	String className = interfaceName
		.substring(interfaceName.lastIndexOf(".") + 1) + "Impl";
	root.put("className", className);
	root.put("entity", clazz.getName());
	String packageName = prop.getProperty("classPath") + ".service.impl";
	root.put("packageName", packageName);
	String beanName = interfaceName
		.substring(interfaceName.lastIndexOf(".") + 1);
	beanName = beanName.substring(0, 1).toLowerCase()
		+ beanName.substring(1);
	root.put("beanName", beanName);
	root.put("interfaceName", interfaceName);
	/*
	 * 输出文件
	 */
	String classPath = prop.getProperty("projectPath")
		+ packageName.replace(".", "\\");
	System.out.println(classPath);
	File file = new File(classPath, className + ".java");
	if (!file.exists()) {
	    file.createNewFile();
	}
	OutputStreamWriter out = new OutputStreamWriter(
		new FileOutputStream(file));
	// OutputStreamWriter out = new OutputStreamWriter(System.out);
	temp.process(root, out);
	out.flush();
	out.close();
	System.out.println("[生成Service实现类————代码生成成功！]");
    }

    /**
     * 生成EntityModel模型类
     * 
     * @return
     * @throws Exception
     */
    public String generateEntityModel(String classChildPath) throws Exception {
	this.temp = cfg.getTemplate("EntityModel.ftl", "UTF-8");
	/*
	 * 设置参数
	 */
	Map<String, Object> root = new HashMap<String, Object>();
	String className = "";
	String entitySimpleName = clazz.getSimpleName();
	if (entitySimpleName.contains("Tb")) {
	    className = entitySimpleName.replace("Tb", "") + "Model";
	} else {
	    className = entitySimpleName + "Model";
	}
	root.put("className", className);
	String packageName = prop.getProperty("controllerClassPath") + "."
		+ classChildPath//
		+ "."
		+ (entitySimpleName.replace("Tb", "").substring(0, 1)
			.toLowerCase()
			+ entitySimpleName.replace("Tb", "").substring(1))
		+ ".model";
	root.put("packageName", packageName);
	Collection<Map<String, String>> properties = new HashSet<Map<String, String>>();
	Map<String, String> propContent = new HashMap<String, String>();
	propContent.put("entity", clazz.getName());
	propContent.put("entityName", (clazz.getSimpleName().replace("Tb", ""))
		.substring(0, 1).toLowerCase()
		+ (clazz.getSimpleName().replace("Tb", "").substring(1)));
	properties.add(propContent);
	root.put("properties", properties);
	/*
	 * 输出文件
	 */
	String classPath = prop.getProperty("projectPath")
		+ packageName.replace(".", "\\");
	System.out.println(classPath);
	File file = new File(classPath, className + ".java");
	if (!file.getParentFile().exists()) {
	    file.getParentFile().mkdirs();
	}
	if (!file.exists()) {
	    file.createNewFile();
	}
	OutputStreamWriter out = new OutputStreamWriter(
		new FileOutputStream(file));
	// OutputStreamWriter out = new OutputStreamWriter(System.out);
	temp.process(root, out);
	out.flush();
	out.close();
	System.out.println("[EntityModel————代码生成成功！]");
	return packageName + "." + className;
    }

    /**
     * 生成Controller
     * 
     * @return
     * @throws Exception
     */
    public String generateController(String serviceInterface,
	    String entityModelClassName) throws Exception {
	this.temp = cfg.getTemplate("Controller.ftl", "UTF-8");
	/*
	 * 设置参数
	 */
	Map<String, Object> root = new HashMap<String, Object>();
	String className = "";
	String entitySimpleName = clazz.getSimpleName();
	if (entitySimpleName.contains("Tb")) {
	    className = entitySimpleName.replace("Tb", "") + "Action";
	} else {
	    className = entitySimpleName + "Action";
	}
	root.put("className", className);
	String packageName = entityModelClassName
		.substring(0, entityModelClassName.lastIndexOf("."))
		.replace(".model", "");
	root.put("packageName", packageName);
	root.put("entity", clazz.getName());
	root.put("serviceInterface", serviceInterface);
	String serviceInterfaceName = serviceInterface
		.substring(serviceInterface.lastIndexOf(".") + 1);
	serviceInterfaceName = serviceInterfaceName.substring(0, 1)
		.toLowerCase() + serviceInterfaceName.substring(1);
	root.put("serviceInterfaceName", serviceInterfaceName);
	root.put("entityModel", entityModelClassName);
	String entityModelName = entityModelClassName
		.substring(entityModelClassName.lastIndexOf(".") + 1);
	entityModelName = entityModelName.substring(0, 1).toLowerCase()
		+ entityModelName.substring(1);
	root.put("entityModelName", entityModelName);
	root.put("entityClassName", entitySimpleName.replace("Tb", ""));
	String requestUrl = (entityModelClassName
		.replace(prop.getProperty("controllerClassPath"), ""));
	requestUrl = requestUrl.substring(0, requestUrl.lastIndexOf(".model"));
	requestUrl = requestUrl.replace(".", "/");
	root.put("requestUrl", requestUrl);
	String excel_CH = "数据模板";
	ExcelTable excelTable = (ExcelTable) clazz
		.getAnnotation(ExcelTable.class);
	if (excelTable != null) {
	    excel_CH = excelTable.title();
	}
	root.put("excel_CH", excel_CH);
	/*
	 * 添加查询条件
	 */
	Collection<Map<String, String>> fields = new HashSet<Map<String, String>>();
	Map<String, String> f;
	Field[] declaredFields = clazz.getDeclaredFields();
	for (Field field : declaredFields) {
	    f = new HashMap<String, String>();
	    f.put("fieldName", field.getName());
	    fields.add(f);
	}
	root.put("fields", fields);
	Collection<Map<String, String>> properties = new HashSet<Map<String, String>>();
	Map<String, String> propContent = new HashMap<String, String>();
	propContent.put("entity", clazz.getName());
	propContent.put("entityName", (clazz.getSimpleName().replace("Tb", ""))
		.substring(0, 1).toLowerCase()
		+ (clazz.getSimpleName().replace("Tb", "").substring(1)));
	properties.add(propContent);
	root.put("properties", properties);
	/*
	 * 输出文件
	 */
	String classPath = prop.getProperty("projectPath")
		+ packageName.replace(".", "\\");
	System.out.println(classPath);
	File file = new File(classPath, className + ".java");
	if (!file.getParentFile().exists()) {
	    file.getParentFile().mkdirs();
	}
	if (!file.exists()) {
	    file.createNewFile();
	}
	OutputStreamWriter out = new OutputStreamWriter(
		new FileOutputStream(file));
	// OutputStreamWriter out = new OutputStreamWriter(System.out);
	temp.process(root, out);
	out.flush();
	out.close();
	System.out.println("[Controller————代码生成成功！]");
	return requestUrl;
    }

    /**
     * 生成form.jsp
     * 
     * @return
     * @throws Exception
     */
    public void generateFormJsp(String entityModelClassName) throws Exception {
	this.temp = cfg.getTemplate("form.ftl", "UTF-8");
	/*
	 * 设置参数
	 */
	Map<String, Object> root = new HashMap<String, Object>();
	String idName = "";
	String classSimpleName = clazz.getSimpleName();
	classSimpleName = classSimpleName.substring(0, 1).toLowerCase()
		+ classSimpleName.substring(1);
	idName = classSimpleName + ".id";
	root.put("idName", idName);
	String idValue_EL = "";
	String entityModelClassSimpleName = entityModelClassName
		.substring(entityModelClassName.lastIndexOf(".") + 1);
	entityModelClassSimpleName = entityModelClassSimpleName.substring(0, 1)
		.toLowerCase() + entityModelClassSimpleName.substring(1);// 转换为小写
	idValue_EL = "${" + entityModelClassSimpleName + "." + classSimpleName
		+ ".id" + "}";
	root.put("idValue_EL", idValue_EL);
	root.put("modelName", entityModelClassSimpleName);
	String validateJs = "${path}"+prop.getProperty("jsImportUrl")
				+"/"+prop.getProperty("validateJsName");
	root.put("validate_js", validateJs);
	/*
	 * 表单选项
	 */
	Collection<Map<String, String>> fields = new HashSet<Map<String, String>>();
	Map<String, String> f;
	Field[] declaredFields = clazz.getDeclaredFields();
	for (Field field : declaredFields) {
	    PageMate pageMate = field.getAnnotation(PageMate.class);// 获取PageMate注解
	    if (pageMate != null) {
		f = new HashMap<String, String>();
		f.put("name_CH", pageMate.fieldName());
		f.put("validType_id", pageMate.dataTypeId());
		f.put("name", classSimpleName + "." + field.getName());
		f.put("value_EL", "${" + entityModelClassSimpleName + "."
			+ classSimpleName + "." + field.getName() + "}");
		fields.add(f);
	    }
	}
	root.put("fields", fields);
	/*
	 * 输出文件
	 */
	String classPath = prop.getProperty("webPagePath");
	String requestUrl = (entityModelClassName
		.replace(prop.getProperty("controllerClassPath"), ""));
	requestUrl = requestUrl.substring(0, requestUrl.lastIndexOf(".model"));
	requestUrl = requestUrl.replace(".", "\\");
	classPath = classPath + requestUrl;
	System.out.println(classPath);
	File file = new File(classPath, "form.jsp");
	if (!file.getParentFile().exists()) {
	    file.getParentFile().mkdirs();
	}
	if (!file.exists()) {
	    file.createNewFile();
	}
	OutputStreamWriter out = new OutputStreamWriter(
		new FileOutputStream(file));
	// OutputStreamWriter out = new OutputStreamWriter(System.out);
	temp.process(root, out);
	out.flush();
	out.close();
	System.out.println("[form.jsp————代码生成成功！]");
    }

    /**
     * 生成importDatas.jsp
     * 
     * @return
     * @throws Exception
     */
    public void generateImportDatas(String entityModelClassName)
	    throws Exception {
	this.temp = cfg.getTemplate("importDatas.ftl", "UTF-8");
	/*
	 * 设置参数
	 */
	Map<String, Object> root = new HashMap<String, Object>();
	String requestUrl = (entityModelClassName
		.replace(prop.getProperty("controllerClassPath"), ""));
	requestUrl = requestUrl.substring(0, requestUrl.lastIndexOf(".model"));
	requestUrl = requestUrl.replace(".", "/");
	root.put("requestUrl", requestUrl);
	/*
	 * 输出文件
	 */
	String classPath = prop.getProperty("webPagePath");
	requestUrl = (entityModelClassName
		.replace(prop.getProperty("controllerClassPath"), ""));
	requestUrl = requestUrl.substring(0, requestUrl.lastIndexOf(".model"));
	requestUrl = requestUrl.replace(".", "\\");
	classPath = classPath + requestUrl;
	System.out.println(classPath);
	File file = new File(classPath, "importDatas.jsp");
	if (!file.getParentFile().exists()) {
	    file.getParentFile().mkdirs();
	}
	if (!file.exists()) {
	    file.createNewFile();
	}
	OutputStreamWriter out = new OutputStreamWriter(
		new FileOutputStream(file));
	// OutputStreamWriter out = new OutputStreamWriter(System.out);
	temp.process(root, out);
	out.flush();
	out.close();
	System.out.println("[importDatas.jsp————代码生成成功！]");
    }

    /**
     * 生成page.jsp
     * 
     * @return
     * @throws Exception
     */
    public void generatePageJsp(String entityModelClassName) throws Exception {
	this.temp = cfg.getTemplate("page.ftl", "UTF-8");
	/*
	 * 设置参数
	 */
	Map<String, Object> root = new HashMap<String, Object>();
	String requestUrl = (entityModelClassName
		.replace(prop.getProperty("controllerClassPath"), ""));
	requestUrl = requestUrl.substring(0, requestUrl.lastIndexOf(".model"));
	requestUrl = requestUrl.replace(".", "/");
	root.put("requestUrl", requestUrl);
	/*
	 * 数据别名
	 */
	String dataName_alias = "";
	String classSimpleName = clazz.getSimpleName();
	classSimpleName = classSimpleName.substring(0, 1).toLowerCase()
		+ classSimpleName.substring(1);
	dataName_alias = classSimpleName;
	root.put("dataName_alias", dataName_alias);
	root.put("dataId_EL", "${" + dataName_alias + ".id" + "}");
	/*
	 * 数据
	 */
	String entityModelClassSimpleName = entityModelClassName
		.substring(entityModelClassName.lastIndexOf(".") + 1);
	entityModelClassSimpleName = entityModelClassSimpleName.substring(0, 1)
		.toLowerCase() + entityModelClassSimpleName.substring(1);// 转换为小写
	Collection<Map<String, String>> fields = new HashSet<Map<String, String>>();
	Map<String, String> f;
	Field[] declaredFields = clazz.getDeclaredFields();
	for (Field field : declaredFields) {
	    PageMate pageMate = field.getAnnotation(PageMate.class);// 获取PageMate注解
	    if (pageMate != null) {
		f = new HashMap<String, String>();
		f.put("name_CH", pageMate.fieldName());
		f.put("name", classSimpleName + "." + field.getName());
		f.put("value_EL", "${" + entityModelClassSimpleName + "."
			+ classSimpleName + "." + field.getName() + "}");
		f.put("dataValue_El",
			"${" + dataName_alias + "." + field.getName() + "}");
		fields.add(f);
	    }
	}
	root.put("fields", fields);

	/*
	 * 输出文件
	 */
	String classPath = prop.getProperty("webPagePath");
	requestUrl = (entityModelClassName
		.replace(prop.getProperty("controllerClassPath"), ""));
	requestUrl = requestUrl.substring(0, requestUrl.lastIndexOf(".model"));
	requestUrl = requestUrl.replace(".", "\\");
	classPath = classPath + requestUrl;
	System.out.println(classPath);
	File file = new File(classPath, dataName_alias + ".jsp");
	if (!file.getParentFile().exists()) {
	    file.getParentFile().mkdirs();
	}
	if (!file.exists()) {
	    file.createNewFile();
	}
	OutputStreamWriter out = new OutputStreamWriter(
		new FileOutputStream(file));
	// OutputStreamWriter out = new OutputStreamWriter(System.out);
	temp.process(root, out);
	out.flush();
	out.close();
	System.out.println("[Page.jsp————代码生成成功！]");
    }
    /**
     * 生成validate.js
     * 
     * @return
     * @throws Exception
     */
    public void generateValidateJs(List<DataType> dps) throws Exception {
	this.temp = cfg.getTemplate("validateJs.ftl", "UTF-8");
	/*
	 * 设置参数
	 */
	Map<String, Object> root = new HashMap<String, Object>();
	
	Collection<Map<String, String>> fields = new HashSet<Map<String, String>>();
	Map<String, String> f;
	for (DataType dataType : dps) {
	    f = new HashMap<String, String>();
	    f.put("dataType_id", dataType.getTypeCode());
	    f.put("regex", dataType.getRegex());
	    f.put("errorMess", dataType.getErrorMess());
	    fields.add(f);
	}
	root.put("fields", fields);
	
	/*
	 * 输出文件
	 */
	String filePath = prop.getProperty("projectPath")+prop.getProperty("validateUrl");
	String fileName = prop.getProperty("validateJsName");
	System.out.println(filePath);
	File file = new File(filePath,fileName);
	if (!file.getParentFile().exists()) {
	    file.getParentFile().mkdirs();
	}
	if (!file.exists()) {
	    file.createNewFile();
	}
	OutputStreamWriter out = new OutputStreamWriter(
		new FileOutputStream(file));
	//OutputStreamWriter out = new OutputStreamWriter(System.out);
	temp.process(root, out);
	out.flush();
	out.close();
	System.out.println("[validate.js————代码生成成功！]");
    }
    
}
