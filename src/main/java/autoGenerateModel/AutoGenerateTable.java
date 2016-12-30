//package autoGenerateModel;
//
//import org.hibernate.cfg.Configuration;
//import org.hibernate.tool.hbm2ddl.SchemaExport;
//
//public class AutoGenerateTable {
//	public static void main(String[] args) throws Exception {
//		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
//
//
//		// 创建工具类对象
//		SchemaExport export = new SchemaExport(cfg);
//		// 建表
//		// 第一个参数： 是否在控制台打印建表语句
//		// 第二个参数： 是否执行脚本
//		export.create(true, true);
//		export.create(output);
//	}
//}
