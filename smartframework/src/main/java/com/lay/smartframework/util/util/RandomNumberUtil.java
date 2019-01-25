package com.lay.smartframework.util.util;

import java.util.Random;

public class RandomNumberUtil {
	/**
	 * 生成随机数的String字符串 如果不足6位用000补齐
	 * 
	 * @return
	 */
	public static  String genSMSCode() {
		Random random = new Random();
		String code = String.valueOf(random.nextInt(999999));

		if (code.length() == 6) {
			return code;
		} else {
			int max = 6 - code.length();
			for (int i = 0; i < max; i++) {
				code = "0" + code;
			}

			return code;
		}
	}
	/**
	 * 随机生成指定位数随机数
	 * @param count
	 * @return
	 */
	public static  String genRandomCode(int count) {
		Random random = new Random();
		double number=Math.pow(10, count);
		int num=(int)number-1;
		String code = String.valueOf(random.nextInt(num));

		if (code.length() == count) {
			return code;
		} else {
			int max = count - code.length();
			for (int i = 0; i < max; i++) {
				code = "0" + code;
			}

			return code;
		}
	}
	
	public static void main(String[] args) {
		for(int i=0;i<5;i++){
			System.out.println(genRandomCode(3));
		}
		
	}
}
