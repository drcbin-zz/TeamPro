package cn.edu.dlnu.tools;

import java.util.regex.Pattern;

public class StringTool {
	public static  String getIndexValueOrDefault(String[] strings, int index, String defaultValue) {
		try {
			return strings[index];
		} catch (Exception e) {
			// TODO: handle exception
			return defaultValue;
		}
	}
	
	public static Integer changeToIntegerOrDefault(String string, Integer def) {
		try {
			return new Integer(string);
		} catch (Exception e) {
			// TODO: handle exception
			return def;
		}
	}
	
	public static String getDateOrDefault(String string, String defaultString) {
		if (isDate(string)) {
			return string;
		}
		return defaultString;
	}
	
	
	public static boolean isDate(String string) {
		try {
			String patternString = "^(\\d{4})-([1-9]|0[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])$";
			if (Pattern.matches(patternString, string)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}