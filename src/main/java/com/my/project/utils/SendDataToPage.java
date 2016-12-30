package com.my.project.utils;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/**
 * 
 * @author 李忠翔
 * 描述:向浏览器发送数据工具类
 *
 */
public class SendDataToPage {
	/**
	 * 发送文本到页面
	 * @param text
	 * @throws Exception 
	 */
	public static void sendText(String text) throws Exception{
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(text.getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw(new RuntimeException(e));
		}
	}
}
