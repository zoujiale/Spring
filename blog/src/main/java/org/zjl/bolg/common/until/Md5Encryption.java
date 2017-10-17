package org.zjl.bolg.common.until;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/** 
* @ClassName: Md5Encryption 
* @Description: MD5加密工具类
* @author zou
* @date 2017年10月16日 下午4:13:29 
* 
*/

public class Md5Encryption {
	
	//使用UTF-8编码
	private static final String CHAR_SET = "UTF-8";
	//使用MD5的加密方式
	private static final String ENCRYPTION_METHOD= "MD5";
	
	/**
	 * 
	 * @param password 传入来的密码
	 * @return 返回MD5加密后的密码
	 * @throws NoSuchAlgorithmException
	 */
	public static String  getmd5Encryption(String password) throws NoSuchAlgorithmException {
		
		// 得到加密实列，并使用MD5加密方式
		MessageDigest md5 =MessageDigest.getInstance(ENCRYPTION_METHOD);
		String md5Password = "";
		try {
			 // 拿到传过来的密码，并将他转换utf-8格式
			byte[] passWord = password.getBytes(CHAR_SET);   
			// 将添加的password计算成MD5
			md5.update(passWord);	
			// 得到Md5的信息
			byte[] bs = md5.digest();
			// 将Md5信息从16转32位
			md5Password = conversion(bs);
			return md5Password;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private static String conversion(byte[] bs) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bs.length; i++) {
			// 将16转32
			int temp = bs[i] & 0xFF;
			if (temp <= 16) {
				sb.append(0);
			}
			sb.append(Integer.toHexString(temp));
		}
		
		return sb.toString();
	}
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String string = getmd5Encryption("123456");
		
		System.out.println(string);
	}
	
}
