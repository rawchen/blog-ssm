package com.yoyling.utils;

import org.springframework.util.DigestUtils;

public class GravatarUtil {
	final static String ORIGN_URL = "https://gravatar.loli.net/avatar/";

	public static String getGravatarUrlByEmail(String email) {
		return ORIGN_URL + "" + DigestUtils.md5DigestAsHex(email.getBytes()) + "?s=200";
	}
}