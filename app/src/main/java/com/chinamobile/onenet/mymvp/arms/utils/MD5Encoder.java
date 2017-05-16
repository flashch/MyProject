package com.chinamobile.onenet.mymvp.arms.utils;

import java.security.MessageDigest;

/**
* @Name: MD5Encoder
* @Description: md5对字符串加密
* @Author: chenhao
* @Create Date: 2017/1/18 0018 15:00
*/

public class MD5Encoder {
	
	public static String encode(String string) throws Exception {
	    byte[] hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
	    StringBuilder hex = new StringBuilder(hash.length * 2);
	    for (byte b : hash) {
	        if ((b & 0xFF) < 0x10) {
	        	hex.append("0");
	        }
	        hex.append(Integer.toHexString(b & 0xFF));
	    }
	    return hex.toString();
	}
}
