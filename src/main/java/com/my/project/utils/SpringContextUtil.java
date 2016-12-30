package com.my.project.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
	    throws BeansException {
	SpringContextUtil.applicationContext = applicationContext;
    }
    
    public static ApplicationContext getApplicationContext() {
	return applicationContext;
    }
    
    /**
     * 获取容器中的对象
     * @param name 容器中bean的名称
     * @return
     * @throws Exception
     */
    public static Object getBean(String name)throws Exception {
	return applicationContext.getBean(name);
    }
}
