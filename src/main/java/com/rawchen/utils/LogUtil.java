package com.rawchen.utils;

import com.rawchen.domain.Log;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class LogUtil {
	public static Log insertLog(HttpServletRequest request,String apiPath) {

		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		Browser browser = userAgent.getBrowser();
		OperatingSystem os = userAgent.getOperatingSystem();

		Log log = new Log();
		log.setUa(request.getHeader("User-Agent"));

		log.setBrowserName(browser.getName());
		log.setOsName(os.getName());
		log.setIp(StringUtil.getIpAddr(request));
		log.setAccessTime(new Date());
		log.setReferer(request.getHeader("Referer"));
		log.setApiPath(apiPath);
		return log;

	}
}
