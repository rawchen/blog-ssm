package com.yoyling.utils;

import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StringUtil {

	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	/**
	 * 在 isEmpty 的基础上进行了为空（字符串都为空格、制表符、tab 的情况）的判断。
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str != null && (strLen = str.length()) != 0) {
			for(int i = 0; i < strLen; ++i) {
				// 判断字符是否为空格、制表符、tab
				if (!Character.isWhitespace(str.charAt(i))) {
					return false;
				}
			}
			return true;
		} else {
			return true;
		}
	}

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
