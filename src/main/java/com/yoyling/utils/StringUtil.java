package com.yoyling.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtil {
	public static List<String> stringToList(String string) {
		List<String> items = Arrays.asList(string.split("\\s*,\\s*"));
		return items;
	}
}
