package com.rawchen.utils;

import org.springframework.util.DigestUtils;

/**
 * Gravatar全球大头贴获取工具
 */
public class GravatarUtil {
	final static String ORIGN_URL = "https://cravatar.cn/avatar/";

	public static String getGravatarUrlByEmail(String email) {
		if ("".equals(email) || email == null) {
			return "";
		}
		return ORIGN_URL + "" + DigestUtils.md5DigestAsHex(email.getBytes()) + "?s=200";
	}
}