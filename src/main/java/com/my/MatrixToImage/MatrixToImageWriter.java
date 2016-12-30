package com.my.MatrixToImage;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.google.zxing.common.BitMatrix;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * @author 李忠翔
 * @date : 2016年2月19日 上午10:55:49
 * 描述：输出二维码
 */ 
public final class MatrixToImageWriter {
	//二维码码的背景颜色和码色
	private static final int BLACK = 0x00000000;  
	private static final int WHITE = 0xFFFFFFFF; 
	
	
	public MatrixToImageWriter() {
	}

	/**
	 * 输出二维码到图形流中
	 * @param matrix 二维码数据
	 * @return
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix,String backgroundURL){
		try {
			int width = matrix.getWidth();
			int mw = matrix.getWidth();
			int height = matrix.getHeight();
			int mh = matrix.getHeight();
			BufferedImage image = null; 
			if(backgroundURL!=null){
				if(StringUtils.isNotBlank(backgroundURL)){
					image=backgroundStretch(width+150, height+100, backgroundURL);
					int ww=image.getWidth();
					int hh=image.getHeight();
					
					//画二维码图形
					int w1 = width-60;
					int h1 = height-60;
					for(int x=0;x<width;x++){
						for(int y=0;y<height;y++){
							if(((x>(width/2-w1/2))&&(x<(width/2+w1/2)))&&((y>(height/2-h1/2))&&(y<(height/2+h1/2)))){
								image.setRGB(x+(ww/2-width/2), y+(hh/2-height/2),matrix.get(x, y)?BLACK:WHITE);
							}
						}
					}
					
				}
			}else {
				image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				
				//画二维码图形
				for(int x=0;x<width;x++){
					for(int y=0;y<height;y++){
						image.setRGB(x, y,matrix.get(x, y)?BLACK:WHITE);
					}
				}
			}
			return image;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
	/**
	 * 返回带图标的二维码
	 * @param matrix
	 * @param logoURL logo的地址
	 * @param backgroundURL 背景图片地址
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage matrixImageWithLogo(BitMatrix matrix , String logoURL,String backgroundURL)
			throws Exception{
		
		try {
			
			BufferedImage image = toBufferedImage(matrix,backgroundURL);
			
			//压缩图标
			BufferedImage img = null;
			if(StringUtils.isNotBlank(logoURL)){
				File file = new File(logoURL);
				if(file.exists()){
					Image read = ImageIO.read(file); 
					img = new BufferedImage(30,30,BufferedImage.TYPE_INT_BGR);
					img.getGraphics().drawImage(read,0,0,30, 30, null);	
				}
			}
			
			//把图标覆盖到二维码上
			image.getGraphics().drawImage(img, image.getWidth()/2-15, image.getHeight()/2-15, 30, 30, null);
			return image;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 压缩拉伸图片
	 * @param width 宽度
	 * @param height 高度
	 * @param backgroundURL 地址
	 * @return
	 */
	public static BufferedImage backgroundStretch(int width,int height, String backgroundURL) {
		try {
			//读取原图片
			File file = new File(backgroundURL);
			BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
			if(file.exists()){
				Image read = ImageIO.read(file); 

				image.getGraphics().drawImage(read,0,0,width, height, null);		
			}
			return image;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	} 
}
