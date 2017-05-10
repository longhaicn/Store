package com.jky.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public final class Tools {
	
	public static void main(String[] args) {
		Date date= new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
		String str = sdf.format(date);//将当前时间格式化为需要的类型
		System.out.println(str);//输出结果
		
	}
	
	
	/**
	 * 判断是否为空
	 * @param str 要判断的字符串
	 * @return true为空
	 */
	public static boolean isEmpty(String str){
		if(str!=null && str.trim().length()>0){
			return false;
		}
		
		return true;
	}
	public static String getCaptcha() {
		Random random = new Random();
		String captcha = "";
		int numNumber = 0;
		int lowerCaseNumber = 0;
		int upperCaseNumber = 0;
		int m = 5;
		while (m != 0) {
			int c = random.nextInt(3);
			if (c == 0 && numNumber < 3) {
				numNumber++;
				m--;
			} else if (c == 1 && lowerCaseNumber < 3) {
				lowerCaseNumber++;
				m--;
			} else if (c == 2 && upperCaseNumber < 3) {
				upperCaseNumber++;
				m--;
			}
		}

		while (captcha.length() != 5) {
			int c = random.nextInt(3);
			if (c == 0 && numNumber > 0) {
				captcha = captcha + (char) (random.nextInt(10) + 48);
				numNumber--;
			} else if (c == 1 && lowerCaseNumber > 0) {
				captcha = captcha + (char) (random.nextInt(26) + 97);
				lowerCaseNumber--;
			} else if (c == 2 && upperCaseNumber > 0) {
				captcha = captcha + (char) (random.nextInt(26) + 65);
				upperCaseNumber--;
			}
		}
		return captcha;
	}
	public static String getDateTime() {
		Date date= new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
		String str = sdf.format(date);//将当前时间格式化为需要的类型
		System.out.println(str);//输出结果
		return str;
		
		
	}
}
