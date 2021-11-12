package com.rawchen.utils;

public class QlogoUtil {
	public static String getQlogoUrlByQnumber(String qq) {
		if ("".equals(qq)) {
			return "http://q1.qlogo.cn/g?b=qq&nk=1&s=640";
		} else {
			return "http://q1.qlogo.cn/g?b=qq&nk=" + qq + "&s=640";
		}
	}
}
