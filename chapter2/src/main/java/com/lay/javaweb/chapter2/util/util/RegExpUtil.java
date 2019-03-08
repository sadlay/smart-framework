package com.lay.javaweb.chapter2.util.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtil {
	 //邮箱
    //public static final String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static final String EMAIL = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";

    //手机号
  //  public static final String PHONE = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";
    public static final String PHONE = "^1[0-9]{9}$";
    
    //正整数
    public static final String POSITIVE_INTEGER = "^[1-9]\\d*|0$";

    //负整数
    public static final String NEGATIVE_INTEGER = "^-[1-9]\\d*|0$";

    //邮编
    public static final String POST_CODE = "[0-9]\\d{5}(?!\\d)";

    //纯英文字母（包括大小写）
    public static final String LETTER = "^[A-Za-z]+$";

    //字母+数字
    public static final String LETTER_NUM = "^[A-Za-z0-9]+$";

    //字母+数字+下划线
    public static final String LETTER_NUM_UNDERLINE = "^\\w+$";

    //身份证
    public static final String IDCARD = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +
            "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +
            "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";

    /**
     * 判断字符串是否为空
     * @param string
     * @return
     */
    public static boolean isEmpty(String string){
        if(string == null || "".equals(string.trim())){
            return true;
        }
        return false;
    }

    /**
     * 判断是否匹配
     * @param string
     * @param regExp
     * @return
     */
    public static boolean isMatches(String string, String regExp){
        if(isEmpty(string)){
            return false;
        }
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    //验证邮箱
    public static boolean isEmail(String string){
        return isMatches(string, EMAIL);
    }

    //验证手机号
    public static boolean isPhone(String string){
        return isMatches(string, PHONE);
    }

    //验证正整数
    public static boolean isPositiveInteger(String string){
        return isMatches(string, POSITIVE_INTEGER);
    }

    //验证负整数
    public static boolean isNegativeInteger(String string){
        return isMatches(string, NEGATIVE_INTEGER);
    }

    //验证邮编
    public static boolean isPostCode(String string){
        return isMatches(string, POST_CODE);
    }

    //验证字母
    public static boolean isLetter(String string){
        return isMatches(string, LETTER);
    }

    //验证字母+数字组成的字符串
    public static boolean isLetterNum(String string){
        return isMatches(string, LETTER_NUM);
    }

    //验证字母+数字+下划线组成的字符串
    public static boolean isLetterNumUnderline(String string){
        return isMatches(string, LETTER_NUM_UNDERLINE);
    }

    //验证身份证
    public static boolean isIdCard(String string){
        return isMatches(string, IDCARD);
    }
}
