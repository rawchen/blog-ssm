package com.yoyling.controller;

import com.yoyling.domain.Content;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OtherController extends BaseController {

	@RequestMapping("/index")
	public String index(Model model){

		int numberOfArticles = contentService.selectNumberOfArticles();
		model.addAttribute("numberOfArticles",numberOfArticles);

		List<Content> contents = contentService.selectAllContent();
		model.addAttribute("contents",contents);

		List<Content> recommendContents = contentService.selectRecommendContent();
		model.addAttribute("recommendContents",recommendContents);



		String qqLink = optionsService.selectValueByName("qq_link");
		String emailLink = optionsService.selectValueByName("email_link");
		String githubLink = optionsService.selectValueByName("github_link");
		String location = optionsService.selectValueByName("location");
		String icp = optionsService.selectValueByName("icp");
		String description = optionsService.selectValueByName("description");
		String websiteTitle = optionsService.selectValueByName("website_title");
		String avatar = optionsService.selectValueByName("avatar");

		Map<String, String> optionsMap = new HashMap<>();
		optionsMap.put("qqLink",qqLink);
		optionsMap.put("emailLink",emailLink);
		optionsMap.put("githubLink",githubLink);
		optionsMap.put("location",location);
		optionsMap.put("icp",icp);
		optionsMap.put("description",description);
		optionsMap.put("websiteTitle",websiteTitle);
		optionsMap.put("avatar",avatar);


		model.addAttribute("optionsMap",optionsMap);

		return "index";
	}

	@RequestMapping("/detail2")
	public String toDetails2(Model model){
		Content c = contentService.selectByPrimaryKey(1);
		model.addAttribute("content",c);
		return "detail2";
	}

	@RequestMapping("/{page}")
	public String test(@PathVariable String page) {
		return page;
	}

	@RequestMapping("/test")
	public String toIndex(Model model){
		model.addAttribute("t","1234");
		return "login";
	}

	@RequestMapping("/json")
	@ResponseBody
	public Map<String, String> toJson(){
		Map<String, String> map = new HashMap<>();
		map.put("data","111");
		map.put("data2","222");
		return map;
	}

	@RequestMapping("/json2")
	@ResponseBody
	public Map<String, String> toJson2(){
		Map<String, String> map = new HashMap<>();
		map.put("data",request.getServerName());
		return map;
	}
}
