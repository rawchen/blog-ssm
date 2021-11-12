package com.rawchen.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置Cookie工具类
 */
public class CookieUtil {

	/**
	 * 设置用户登录cookie
	 * @param author
	 * @param mail
	 * @param url
	 * @param request
	 * @param response
	 */
	public static void setUserLoginCookie(String author, String mail, String url
			, HttpServletRequest request, HttpServletResponse response) {
		Cookie authorCookie = new Cookie("authorCookie", author);
		Cookie mailCookie = new Cookie("mailCookie", mail);
		Cookie urlCookie = new Cookie("urlCookie", url);
		//设置Cookie的父路经
		authorCookie.setPath(request.getContextPath() + "/");
		mailCookie.setPath(request.getContextPath() + "/");
		urlCookie.setPath(request.getContextPath() + "/");
		//获取是否保存Cookie（例如：复选框的值）

		//保存Cookie的时间长度，单位为秒
		authorCookie.setMaxAge(7 * 24 * 60 * 60);//7天
		mailCookie.setMaxAge(7 * 24 * 60 * 60);
		urlCookie.setMaxAge(7 * 24 * 60 * 60);

		//加入Cookie到响应头
		response.addCookie(authorCookie);
		response.addCookie(mailCookie);
		response.addCookie(urlCookie);
	}

	public static void setCookie(String author, String mail, String url
			, HttpServletRequest request, HttpServletResponse response) {

	}
}

