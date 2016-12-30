package com.my.MatrixToImage.action;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.my.MatrixToImage.MatrixToImageWriter;

/**
 * 获取二维码
 * @author 李忠翔
 *
 */
public class MatrixToImageAction {

	public void getMatrixImage(){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();
			response.setHeader("pragma", "mo-cache");
			response.setHeader("cache-control", "no-cache");
			response.setDateHeader("expires", 0);
			
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			//存入二维码的字符串数据数据
			String contents = "http://lzx12138.imwork.net:19269/ynpt/product/product_mProDetailUI.action?productId="+request.getParameter("productId");
			String proMakeTime =(String) request.getParameter("proMakeTime");
			String proBatch =(String) request.getParameter("proBatch");
			//将生产日期加入二维码数据
			if(StringUtils.isNotBlank(proMakeTime)){
				contents+="&proMakeTime="+proMakeTime;
			}
			//将生产批次加入二维码数据
			if(StringUtils.isNotBlank(proBatch)){
				contents+="&proBatch="+proBatch;
			}
			//二维码数据获取
			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix matrix = multiFormatWriter.encode(contents, BarcodeFormat.QR_CODE, 200, 200, hints);
			//BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
			BufferedImage image = MatrixToImageWriter.matrixImageWithLogo(matrix, "C:\\Users\\Administrator\\Desktop\\matrixLogo.jpg","C:\\Users\\Administrator\\Desktop\\matrixLogo.jpg");
			
			ServletOutputStream out = response.getOutputStream();
			ImageIO.write(image, "jpeg", out);
			
			out.flush();
			out.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
