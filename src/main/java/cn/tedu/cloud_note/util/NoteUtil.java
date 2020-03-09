package cn.tedu.cloud_note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

public class NoteUtil {
	/*
	 * 利用UUID算法生成主键
	 */
	public static String createId(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	public static String md5(String str){
		try {
			//将str利用md5处理，处理结果是byte[]
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] input =str.getBytes();
			byte[] output=md.digest(input);
			//利用Base64算法将byte[]处理字符串返回
			return Base64.encodeBase64String(output);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoteException("密码加密失败");
		}
	}
	public static void main(String[] ars){
		System.out.println(md5("123456"));
		System.out.println(md5("123456"));
		System.out.println(createId());
		System.out.println(md5(createId()));
	}
}
