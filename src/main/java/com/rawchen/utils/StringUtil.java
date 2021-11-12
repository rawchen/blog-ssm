package com.rawchen.utils;

import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static Map<String, String> getOsAndBrowserInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap();
		String browserDetails = request.getHeader("User-Agent" );
		String userAgent = browserDetails;
		String user = userAgent.toLowerCase();

		String os = "";
		String browser = "";
		//=================OS Info=======================
		if (userAgent.toLowerCase().contains("windows" )) {
			os = "Windows";
		} else if (userAgent.toLowerCase().contains("mac" )) {
			os = "Mac";
		} else if (userAgent.toLowerCase().contains("x11" )) {
			os = "Unix";
		} else if (userAgent.toLowerCase().contains("android" )) {
			os = "Android";
		} else if (userAgent.toLowerCase().contains("iphone" )) {
			os = "iPhone";
		} else {
			os = "UnKnown";
		}
		//===============Browser===========================
		if (user.contains("edge" )) {
			browser = (userAgent.substring(userAgent.indexOf("Edge" )).split(" " )[0]).replace("/" , "-" );
		} else if (user.contains("msie" )) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE" )).split(";" )[0];
			browser = substring.split(" " )[0].replace("MSIE" , "IE" ) + "-" + substring.split(" " )[1];
		} else if (user.contains("safari" ) && user.contains("version" )) {
			browser = (userAgent.substring(userAgent.indexOf("Safari" )).split(" " )[0]).split("/" )[0]
					+ "-" + (userAgent.substring(userAgent.indexOf("Version" )).split(" " )[0]).split("/" )[1];
		} else if (user.contains("opr" ) || user.contains("opera" )) {
			if (user.contains("opera" )) {
				browser = (userAgent.substring(userAgent.indexOf("Opera" )).split(" " )[0]).split("/" )[0]
						+ "-" + (userAgent.substring(userAgent.indexOf("Version" )).split(" " )[0]).split("/" )[1];
			} else if (user.contains("opr" )) {
				browser = ((userAgent.substring(userAgent.indexOf("OPR" )).split(" " )[0]).replace("/" , "-" ))
						.replace("OPR" , "Opera" );
			}
		} else if (user.contains("chrome" )) {
			browser = (userAgent.substring(userAgent.indexOf("Chrome" )).split(" " )[0]).replace("/" , "-" );
			browser = browser.substring(0,browser.indexOf('.'));
		} else if ((user.contains("mozilla/7.0" )) || (user.contains("netscape6" )) ||
				(user.contains("mozilla/4.7" )) || (user.contains("mozilla/4.78" )) ||
				(user.contains("mozilla/4.08" )) || (user.contains("mozilla/3" ))) {
			browser = "Netscape-?";
		} else if (user.contains("firefox" )) {
			browser = (userAgent.substring(userAgent.indexOf("Firefox" )).split(" " )[0]).replace("/" , "-" );
		} else if (user.contains("rv" )) {
			String IEVersion = (userAgent.substring(userAgent.indexOf("rv" )).split(" " )[0]).replace("rv:" , "-" );
			browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
		} else {
			browser = "UnKnown";
		}
		map.put("os" , os);
		map.put("browser" , browser);
		return map;
	}

	public static String randomContentThumb() {
		int m = 0;
		int n = 9;
		int temp=m+(int)(Math.random()*(n+1-m));
		return "https://cdn.jsdelivr.net/gh/rawchen/JsDelivr/ContentThumb/"+temp+".jpg";
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
		if ("".equals(password) || password == null) {
			return "";
		}
		return DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(password.getBytes()).getBytes());
	}

	public static String getDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateNowStr = sdf.format(date);
		return dateNowStr;
	}

	public static boolean isContainChinese(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static boolean isLetter(String str) {
		if ("".equals(str)) {
			return false;
		}
		String regex = "^[a-zA-Z]+$";
		return str.matches(regex);
	}

	public static boolean isRobotComment(String commentText, String authorName) {
		String encode ="GB2312";

		//如果不包含任意简体中文都是机器人
		if (!isContainChinese(commentText) && !isContainChinese(authorName)) {
			return true;
		}
		try {
			if (!commentText.equals(new String(commentText.getBytes(encode), encode))) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
