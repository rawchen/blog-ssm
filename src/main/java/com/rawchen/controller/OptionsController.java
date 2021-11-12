package com.rawchen.controller;

import com.rawchen.domain.Options;
import com.rawchen.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class OptionsController extends BaseController {
	@RequestMapping("/updateOptions")
	public String updateOptions() {
		String websiteName = request.getParameter("websiteName");
		String ico = request.getParameter("ico");
		String favicon = request.getParameter("favicon");
		String icp = request.getParameter("icp");
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		String postsListSize = request.getParameter("postsListSize");
		List<String> hobbyList = new ArrayList<>();
		if (request.getParameterValues("hobby") != null) {
			hobbyList = Arrays.asList(request.getParameterValues("hobby"));
		}
		String hobbyString = StringUtil.listToString(hobbyList);
		String qqLink = request.getParameter("qqLink");
		String emailLink = request.getParameter("emailLink");
		String githubLink = request.getParameter("githubLink");

		Options o = new Options();

		o.setName("website_title");
		o.setValue(websiteName);
		int a = optionsService.updateOptions(o);

		o.setName("website_ico");
		o.setValue(ico);
		int a2 = optionsService.updateOptions(o);

		o.setName("qq_link");
		o.setValue(qqLink);
		int a3 = optionsService.updateOptions(o);

		o.setName("posts_list_size");
		o.setValue(postsListSize);
		int a4 = optionsService.updateOptions(o);

		o.setName("location");
		o.setValue(location);
		int a5 = optionsService.updateOptions(o);

		o.setName("icp");
		o.setValue(icp);
		int a6 = optionsService.updateOptions(o);

		o.setName("hobby");
		o.setValue(hobbyString);
		int a7 = optionsService.updateOptions(o);

		o.setName("github_link");
		o.setValue(githubLink);
		int a8 = optionsService.updateOptions(o);

		o.setName("email_link");
		o.setValue(emailLink);
		int a9 = optionsService.updateOptions(o);

		o.setName("description");
		o.setValue(description);
		int a10 = optionsService.updateOptions(o);

		o.setName("avatar");
		o.setValue(favicon);
		int a11 = optionsService.updateOptions(o);

		Map<String, Object> optionsMap = new HashMap<>();
		optionsMap.put("qqLink",qqLink);
		optionsMap.put("emailLink",emailLink);
		optionsMap.put("githubLink",githubLink);
		optionsMap.put("location",location);
		optionsMap.put("icp",icp);
		optionsMap.put("description",description);
		optionsMap.put("websiteTitle",websiteName);
		optionsMap.put("avatar",favicon);
		optionsMap.put("websiteIco",ico);
		optionsMap.put("postsListSize",postsListSize);
		optionsMap.put("hobby",hobbyString);
		optionsMap.put("hobbyList",StringUtil.stringToList(hobbyString));
		request.getServletContext().setAttribute("applicationOptionsMap", optionsMap);

		return "redirect:/adminConfig";
	}
}
