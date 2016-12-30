package com.my.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.my.project.utils.QueryHelper;
import com.my.project.utils.SpringContextUtil;

/**
 * 
 * @author 自定义select标签
 *
 */
public class SelectTag extends SimpleTagSupport {

    private String name;
    private String beanClassName;
    private String value;
    private String valueName;
    private String where;
    private String selectValue;
    
    StringWriter sw = new StringWriter();

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
	return sessionFactory.getCurrentSession();
    }

    @Override
    public void doTag() throws JspException, IOException {
	try {
	    sessionFactory = (SessionFactory) SpringContextUtil.getBean("sessionFactory");
	    if(beanClassName!=null && value!=null && valueName!=null && where!=null) {
		Class beanClass = Class.forName(beanClassName);
	        QueryHelper qh = new QueryHelper(beanClass, "o");
	        String[] conditionStr = where.split(",");
	        for(int i=0;i<conditionStr.length;i++) {
	            if(!"1=1".equals(where)) {
	        	qh.addCondition(conditionStr[i].split("=")[0]+"=?", conditionStr[i].split("=")[1]);
	            }
	        }
	        List objs =  list(qh);
	        String html = "<select name=\""+name+"\">" ;
	        html=html+"<option value =\"\">请选择</option>";
	        for (Object o : objs) {
		    Method getValue = beanClass.getMethod("get"+value.substring(0,1).toUpperCase()+value.substring(1, value.length()));
		    Method getValueName = beanClass.getMethod("get"+valueName.substring(0,1).toUpperCase()+valueName.substring(1, valueName.length()));
		    String valueStr = getValue.invoke(o).toString();
		    String valueNameStr = getValueName.invoke(o).toString();
		    if(selectValue.trim()!="") {
			if(selectValue.equals(valueStr)) {
			    html=html+"<option value ="+valueStr+" selected=\"selected\">"+valueNameStr+"</option>";
			}else {
			    html=html+"<option value ="+valueStr+">"+valueNameStr+"</option>";
			}
		    }else {
			html=html+"<option value ="+valueStr+">"+valueNameStr+"</option>";
		    }
		}
	        html = html+"</select>" ;
	        //输出
	        getJspContext().getOut().println(html);
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public List list(QueryHelper queryHelper) {
	Session session = sessionFactory.openSession();
	Query q = session.createQuery(queryHelper.getHqlStr());
	if (queryHelper.getParamaters() != null
		&& queryHelper.getParamaters().size() > 0) {
	    for (int i = 0; i < queryHelper.getParamaters().size(); i++) {
		q.setParameter(i, queryHelper.getParamaters().get(i));
	    }
	}
	Transaction tx = session.beginTransaction();
	List list=null;
	try {
	    list = q.list();
	    tx.commit();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    if(tx!=null) tx.rollback();
	    e.printStackTrace();
	}finally {
	    session.close();
	}
	return list;
    }

    public String getBeanClassName() {
	return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
	this.beanClassName = beanClassName;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public String getValueName() {
	return valueName;
    }

    public void setValueName(String valueName) {
	this.valueName = valueName;
    }

    public String getWhere() {
	return where;
    }

    public void setWhere(String where) {
	this.where = where;
    }

    public String getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
