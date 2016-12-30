package com.my.project.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 
 * @author 李忠翔
 * 描述：字符串加密处理
 *
 */
public class DigestUtil {
	public static String MD5digest(String str) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder encoder64 = new BASE64Encoder();
		byte[] digest = md5.digest(str.getBytes("UTF-8"));
		String result = encoder64.encode(digest);
		return result;
	}
}
