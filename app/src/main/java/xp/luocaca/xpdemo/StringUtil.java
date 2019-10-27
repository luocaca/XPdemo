package xp.luocaca.xpdemo;

import java.lang.Character.UnicodeBlock;
import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isEmpty(CharSequence value) {
        return value == null || "".equals(value.toString().trim()) || "null".equalsIgnoreCase(value.toString());
    }

    public static boolean isLetter(String value) {
        return "^[a-zA-Z]*".matches(value);
    }

    public static boolean isChinese(char c) {
        UnicodeBlock ub = UnicodeBlock.of(c);
        if (ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == UnicodeBlock.GENERAL_PUNCTUATION || ub == UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String... value) {
        return !isEmpty(value);
    }

    public static String trafficFormat(double byteValue) {
        if (byteValue == Double.MAX_VALUE) {
            return "Unlimited";
        }
        if (byteValue > 1.073741824E9d) {
            double result = byteValue / 1.073741824E9d;
            return String.format("%.2f", new Object[]{Double.valueOf(result)}) + "GB";
        } else if (byteValue > 1048576.0d) {
            double result2 = byteValue / 1048576.0d;
            return String.format("%.2f", new Object[]{Double.valueOf(result2)}) + "MB";
        } else if (byteValue > 1024.0d) {
            double result3 = byteValue / 1048576.0d;
            return String.format("%.2f", new Object[]{Double.valueOf(result3)}) + "KB";
        } else {
            double result4 = byteValue / 1048576.0d;
            return String.format("%.2f", new Object[]{Double.valueOf(result4)}) + "Byte";
        }
    }

    public static boolean isEmail(String value) {
        if (value == null) {
            return false;
        }
        return Pattern.matches("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", value);
    }

    public static boolean isEmpty(String... value) {
        if (value == null) {
            return true;
        }
        for (int i = 0; i < value.length; i++) {
            if (value[i] == null || "".equals(value[i].trim()) || "null".equalsIgnoreCase(value[i])) {
                return true;
            }
        }
        return false;
    }

    public static String[] doSplit(String value, String split) {
        if (value == null || split == null) {
            return null;
        }
        char[] temp_array = value.toCharArray();
        int strLength = value.length();
        int strInLength = split.length();
        int strInTimes = 0;
        int[] strIndex = new int[strLength];
        int i = 0;
        int ii = 0;
        while (i <= strLength - strInLength) {
            String temp_str = "";
            for (int j = i; j < i + strInLength; j++) {
                temp_str = temp_str + temp_array[j];
            }
            if (temp_str.equals(split)) {
                strInTimes++;
                strIndex[ii] = i;
                i += strInLength;
                ii++;
            } else {
                i++;
            }
        }
        if (strInTimes < 1) {
            return new String[]{value};
        }
        String[] back_str = new String[(strInTimes + 1)];
        back_str[0] = value.substring(0, strIndex[0]);
        for (int k = 1; k < strInTimes; k++) {
            back_str[k] = value.substring(strIndex[k - 1] + strInLength, strIndex[k]);
        }
        back_str[strInTimes] = value.substring(strIndex[strInTimes - 1] + strInLength, value.length());
        return back_str;
    }

    public static boolean isPassword(String value) {
        if (value != null && value.length() >= 5 && value.indexOf(" ") <= 0) {
            return true;
        }
        return false;
    }
}
