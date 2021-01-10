package com.yoyling.utils;

import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StringUtil {

	public static List<String> stringToList(String string) {
		List<String> items = new ArrayList<>();
		if (string != null) {
			items = Arrays.asList(string.split("\\s*,\\s*"));
		}
		return items;
	}

	public static String listToString(List<String> list) {
		if (list == null) {
			return "";
		}
		String items = "";
		for (String s:list) {
			items = items + s + ",";
		}
		if (items.endsWith(",")) {
			items = items.substring(0,items.length()-1);
		}
		return items;
	}

	public static String passwordToMd5(String password) {
		return DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(password.getBytes()).getBytes());
	}

	public static String getDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateNowStr = sdf.format(date);
		return dateNowStr;
	}


}
