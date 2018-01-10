package com.iotek.ssm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

public class MyUtil {
	/**
	 * 登陆的时候，将用户输入的密码再一次进行调用，如果得到的密文和数据库
	 * 里的密文相匹配，则可以登陆。
	 * @param src
	 * @return
	 */
	public static String md5(String src){
		StringBuffer buffer = new StringBuffer();
		char[] chars = {'0','1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f'};
		byte[] bytes = src.getBytes();
		//algorithm算法
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] target = md.digest(bytes);
			for (byte b : target) {
				buffer.append(chars[(b>>4)&0x0F]);
				buffer.append(chars[b&0x0F]);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	/**
	 * 统计某年某月的工作日
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getWorkdays(int year,int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH,month-1);
		cal.set(Calendar.DATE,1);
		int count=0;
		while(cal.get(Calendar.MONTH)<month) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if(!(day==Calendar.SUNDAY||day==Calendar.SATURDAY)) {
				count++;
			}
		}
		return count;
	}
	
}
