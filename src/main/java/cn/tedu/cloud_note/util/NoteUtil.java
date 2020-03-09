package cn.tedu.cloud_note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

public class NoteUtil {
	/*
	 * ����UUID�㷨��������
	 */
	public static String createId(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	public static String md5(String str){
		try {
			//��str����md5������������byte[]
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] input =str.getBytes();
			byte[] output=md.digest(input);
			//����Base64�㷨��byte[]�����ַ�������
			return Base64.encodeBase64String(output);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoteException("�������ʧ��");
		}
	}
	public static void main(String[] ars){
		System.out.println(md5("123456"));
		System.out.println(md5("123456"));
		System.out.println(createId());
		System.out.println(md5(createId()));
	}
}
