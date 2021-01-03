package com.yoyling.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtil {

	public static List<String> stringToList(String string) {
		List<String> items = Arrays.asList(string.split("\\s*,\\s*"));
		return items;
	}

	public static String listToString(List<String> list) {
		String items = "";
		for (String s:list) {
			items = items + s + ",";
		}
		if (items.endsWith(",")) {
			items = items.substring(0,items.length()-1);
		}
		return items;
	}
}
