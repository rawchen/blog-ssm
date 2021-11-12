package com.rawchen.listener;

import com.rawchen.service.OptionsService;
import com.rawchen.utils.StringUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StartupListener implements ServletContextListener, ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		OptionsService optionsService = applicationContext.getBean(OptionsService.class);
		String qqLink = optionsService.selectValueByName("qq_link");
		String emailLink = optionsService.selectValueByName("email_link");
		String githubLink = optionsService.selectValueByName("github_link");
		String location = optionsService.selectValueByName("location");
		String icp = optionsService.selectValueByName("icp");
		String description = optionsService.selectValueByName("description");
		String websiteTitle = optionsService.selectValueByName("website_title");
		String websiteIco = optionsService.selectValueByName("website_ico");
		String avatar = optionsService.selectValueByName("avatar");
		String postsListSize = optionsService.selectValueByName("posts_list_size");
		String hobby = optionsService.selectValueByName("hobby");

		Map<String, Object> optionsMap = new HashMap<>();
		optionsMap.put("qqLink",qqLink);
		optionsMap.put("emailLink",emailLink);
		optionsMap.put("githubLink",githubLink);
		optionsMap.put("location",location);
		optionsMap.put("icp",icp);
		optionsMap.put("description",description);
		optionsMap.put("websiteTitle",websiteTitle);
		optionsMap.put("avatar",avatar);
		optionsMap.put("websiteIco",websiteIco);
		optionsMap.put("postsListSize",postsListSize);
		List<String> hobbyList = StringUtil.stringToList(hobby);
		optionsMap.put("hobby",hobby);
		optionsMap.put("hobbyList",hobbyList);
		sce.getServletContext().setAttribute("applicationOptionsMap", optionsMap);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		StartupListener.applicationContext = applicationContext;
	}
}
