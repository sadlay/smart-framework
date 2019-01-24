package com.lay.javaweb.chapter2.util.util;

import java.util.Random;

public class RandomUtil {

	public static Random random = new Random();

	public static String getRandom(int length) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < length; i++) {
			boolean isChar = (random.nextInt(2) % 2 == 0);
			if (isChar) {
				int choice = (random.nextInt(2) % 2 == 0) ? 65 : 97;
				ret.append((char) (choice + random.nextInt(26)));
			} else {
				ret.append(Integer.toString(random.nextInt(10)));
			}
		}
		return ret.toString();
	}
	
	public static String getRandomLetter(int length) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int choice = (random.nextInt(2) % 2 == 0) ? 65 : 97;
			ret.append((char) (choice + random.nextInt(26)));
		}
		return ret.toString();
	}

	public static void main(String[] args) {
		for(int i=0;i<50;i++){
			System.out.println(getRandomLetter(4).toUpperCase());
		}
//		DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
//		BigDecimal fee=new BigDecimal(580);//手续费
//		fee=fee.divide(new BigDecimal(100));
//		System.out.println(df.format(fee));
		
		
	}
}
