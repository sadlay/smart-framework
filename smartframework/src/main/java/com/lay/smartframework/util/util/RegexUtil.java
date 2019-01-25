package com.lay.smartframework.util.util;


public class RegexUtil {
	private static boolean isMatches(String str, String regexStr, boolean isTrim) {
		if ((str == null) || (regexStr == null)) {
			return false;
		}

		if (isTrim) {
			str = str.trim();
		}
		return str.matches(regexStr);
	}

	public static boolean isInt(String str, boolean isTrim) {
		return isMatches(str, "^-?\\d+$", isTrim);
	}

	public static boolean isIntPlus(String str, boolean isTrim) {
		return isMatches(str, "^\\d+$", isTrim);
	}

	public static boolean isIntDec(String str, boolean isTrim) {
		return isMatches(str, "^-\\d+$", isTrim);
	}

	public static boolean isLong(String str, boolean isTrim) {
		return isMatches(str, "^-?\\d+$", isTrim);
	}

	public static boolean isLongPlus(String str, boolean isTrim) {
		return isMatches(str, "^\\d+$", isTrim);
	}

	public static boolean isLongDec(String str, boolean isTrim) {
		return isMatches(str, "^-\\d+$", isTrim);
	}

	public static boolean isDouble(String str, boolean isTrim, boolean acceptE) {
		if (acceptE) {
			return isMatches(str, "^-?\\d+(\\.\\d+((e|E)\\d+)?)?$", isTrim);
		}
		return isMatches(str, "^-?\\d+(\\.\\d+)?$", isTrim);
	}

	public static boolean isDoublePlus(String str, boolean isTrim,
			boolean acceptE) {
		if (acceptE) {
			return isMatches(str, "^\\d+(\\.\\d+((e|E)\\d+)?)?$", isTrim);
		}
		return isMatches(str, "^\\d+(\\.\\d+)?$", isTrim);
	}

	public static boolean isDoubleDec(String str, boolean isTrim,
			boolean acceptE) {
		if (acceptE) {
			return isMatches(str, "^-\\d+(\\.\\d+((e|E)\\d+)?)?$", isTrim);
		}
		return isMatches(str, "^-\\d+(\\.\\d+)?$", isTrim);
	}

	public static boolean isPhone(String str, boolean isTrim) {
		return isMatches(str, "^((13|14|15|18)[0-9]{9}|(170|176|177|178)[0-9]{8})$", isTrim);
	}

	public static boolean isDate(String str, boolean isTrim) {
		return isMatches(str, "^\\d{4}-[0-1]{0,1}[0-9]{1}-[0-3]{0,1}[0-9]{1}$",
				isTrim);
	}
	public static boolean isTel(String str,boolean isTrim){
		return isMatches(str, "^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$", isTrim);
		
	}
	public static boolean isEmail(String str,boolean isTrim){
		return isMatches(str, "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", isTrim);
		
	}
	public static void main(String[] args) {
		//boolean b = isDate("1922-13-0", false);
		boolean b = isPhone("17338136156", true);
		System.out.println(b);
	}
}
