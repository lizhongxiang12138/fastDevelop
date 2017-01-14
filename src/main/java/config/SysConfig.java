package config;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 * 系统配置
 * @author admin
 *
 */
public final class SysConfig {
    /**
     * 网页首页
     */
    public static String indexPage;
    /**
     * 后台系统首页
     */
    public static String sysIndexPage;
    /**
     * 登录页面
     */
    public static String loginPage;
    
    static {
	/**
	 * 初始化数据，读取sysConfig.properties
	 */
	Properties prop = new Properties();
	InputStream in = SysConfig.class.getResourceAsStream("/sysConfig.properties");
	try {
	    prop.load(in);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	indexPage=prop.getProperty("indexPage");
	sysIndexPage=prop.getProperty("sysIndexPage");
	loginPage=prop.getProperty("loginPage");
    }
}
