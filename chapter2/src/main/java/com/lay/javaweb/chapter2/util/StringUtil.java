package com.lay.javaweb.chapter2.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 字符串工具类
 * @Author: lay
 * @Date: Created in 16:35 2019/1/23
 * @Modified By:IntelliJ IDEA
 */
public class StringUtil {
    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    static String lineSeparator = System.getProperty("line.separator", "\n");

    //判断字符串是否为空
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    //判断字符串是否非空
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    //首字母小写
    public static String toLowerCaseFirstOne(String s) {
        return Character.isLowerCase(s.charAt(0)) ? s : Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }


    //首字母大写
    public static String toUpperCaseFirstOne(String s) {
        return Character.isUpperCase(s.charAt(0)) ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    //驼峰转下划线
    public static String enCodeUnderlined(String s) {
        char[] chars = toLowerCaseFirstOne(s).toCharArray();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < chars.length; ++i) {
            if (Character.isUpperCase(chars[i])) {
                temp.append("_");
            }

            temp.append(Character.toLowerCase(chars[i]));
        }

        return temp.toString();
    }


    //下划线转驼峰
    public static String deCodeUnderlined(String str) {
        String[] splitArr = str.split("_");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < splitArr.length; ++i) {
            if (i == 0) {
                sb.append(splitArr[0].toLowerCase());
            } else {
                sb.append(toUpperCaseFirstOne(splitArr[i].toLowerCase()));
            }
        }

        return sb.toString();
    }

    public static String trimAllWhitespace(String str) {
        if (str != null && str.length() > 0) {
            StringBuilder sb = new StringBuilder(str);
            int index = 0;

            while (sb.length() > index) {
                if (Character.isWhitespace(sb.charAt(index))) {
                    sb.deleteCharAt(index);
                } else {
                    ++index;
                }
            }

            return sb.toString();
        } else {
            return str;
        }
    }

    public static String substringBeforeLast(String str, String separator) {
        if (!isEmpty(str) && !isEmpty(separator)) {
            int pos = str.lastIndexOf(separator);
            return pos == -1 ? str : str.substring(0, pos);
        } else {
            return str;
        }
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean endsWith(String str, String suffix, boolean ignoreCase) {
        if (str != null && suffix != null) {
            if (suffix.length() > str.length()) {
                return false;
            } else {
                int strOffset = str.length() - suffix.length();
                return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
            }
        } else {
            return str == null && suffix == null;
        }
    }

    public static boolean startsWith(String str, String prefix, boolean ignoreCase) {
        if (str != null && prefix != null) {
            return prefix.length() > str.length() ? false : str.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
        } else {
            return str == null && prefix == null;
        }
    }

    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        } else if (separator == null) {
            return "";
        } else {
            int pos = str.indexOf(separator);
            return pos == -1 ? "" : str.substring(pos + separator.length());
        }
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(trimAllWhitespace(" fsdfsd sdfds fsd "));
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        } else {
            return false;
        }
    }

    public static boolean isNullOrEmptyObject(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else {
            if (obj instanceof Object[]) {
                Object[] object = (Object[]) ((Object[]) obj);
                if (object.length == 0) {
                    return true;
                }
            }

            return false;
        }
    }


    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String[] split(String str, char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    public static String beforeLast(String str, char separatorChar) {
        int pos = str.lastIndexOf(separatorChar);
        return pos == -1 ? "" : str.substring(0, pos);
    }

    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        } else {
            int len = str.length();
            if (len == 0) {
                return EMPTY_STRING_ARRAY;
            } else {
                List list = new ArrayList();
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;

                while (true) {
                    while (i < len) {
                        if (str.charAt(i) == separatorChar) {
                            if (match || preserveAllTokens) {
                                list.add(str.substring(start, i));
                                match = false;
                                lastMatch = true;
                            }

                            ++i;
                            start = i;
                        } else {
                            lastMatch = false;
                            match = true;
                            ++i;
                        }
                    }

                    if (match || preserveAllTokens && lastMatch) {
                        list.add(str.substring(start, i));
                    }

                    return (String[]) ((String[]) list.toArray(new String[list.size()]));
                }
            }
        }
    }

}
