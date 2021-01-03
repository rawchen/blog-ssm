package com.yoyling.controller;

import com.yoyling.domain.Category;
import com.yoyling.domain.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController extends BaseController {

	@RequestMapping("/admin")
	public String toDashboard(Model model) {
		model.addAttribute("totalContent",99);
		model.addAttribute("totalCategory",99);
		model.addAttribute("totalTag",99);
		model.addAttribute("totalComment",99);

		String websiteTitle = optionsService.selectValueByName("website_title");
		String websiteIco = optionsService.selectValueByName("website_ico");
		String avatar = optionsService.selectValueByName("avatar");

		Map<String, String> optionsMap = new HashMap<>();
		optionsMap.put("websiteTitle",websiteTitle);
		optionsMap.put("avatar",avatar);
		optionsMap.put("websiteIco",websiteIco);

		model.addAttribute("optionsMap",optionsMap);

		return "dashboard";
	}

	@RequestMapping("/adminEdit")
	public String adminEdit(Model model) {
		model.addAttribute("serverName",request.getServerName());
		String websiteTitle = optionsService.selectValueByName("website_title");
		String websiteIco = optionsService.selectValueByName("website_ico");
		String avatar = optionsService.selectValueByName("avatar");
		model.addAttribute("websiteTitle",websiteTitle);
		model.addAttribute("websiteIco",websiteIco);
		model.addAttribute("avatar",avatar);

		List<Category> categories = categoryService.selectAllCategory();
		model.addAttribute("categories",categories);

		List<Tag> tags = tagService.selectAllTag();

		return "edit";
	}

	@RequestMapping("/adminEditBlog")
	public String adminEditBlog(Model model) {
		String blogTitle = request.getParameter("blogTitle");
		String blogSlug = request.getParameter("blogSlug");
		String blogCategory = request.getParameter("blogCategory");
//		String[] blogTag = request.getParameterValues("blogTag");
		String blogDescription = request.getParameter("blogDescription");
		String blogType = request.getParameter("blogType");
		String blogContentsOrder = request.getParameter("blogContentOrder");
		String blogContentStatus = request.getParameter("blogContentStatus");
		String blogPassword = request.getParameter("blogPassword");
		String blogThumb = request.getParameter("blogThumb");
		String content = request.getParameter("content");
		System.out.println(blogTitle);
		System.out.println(blogSlug);
		System.out.println(blogCategory);
//		for (String a:blogTag) {
//			System.out.println(a);
//		}
		System.out.println(blogDescription);
		System.out.println(blogType);
		System.out.println(blogContentsOrder);
		System.out.println(blogContentStatus);
		System.out.println(blogPassword);
		System.out.println(blogThumb);
		System.out.println(content);

		return "blog-mgr";
	}


}
