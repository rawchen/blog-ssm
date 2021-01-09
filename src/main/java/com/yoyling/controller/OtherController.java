package com.yoyling.controller;

import com.yoyling.domain.Category;
import com.yoyling.domain.Content;
import com.yoyling.domain.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yoyling.utils.StringUtil.stringToList;

@Controller
public class OtherController extends BaseController {

	@RequestMapping("/index")
	public String index(Model model){

		int numberOfArticles = contentService.selectNumberOfArticles();
		model.addAttribute("numberOfArticles",numberOfArticles);

		List<Content> contents = contentService.selectAllContent();
		for (Content c : contents) {

			//查询设置评论数
			c.setCommentCount(99);

			c.setCategoryName(categoryService.selectByPrimaryKey(c.getCgid()).getCgName());
			c.setCategorySlug(categoryService.selectByPrimaryKey(c.getCgid()).getCgSlug());

			List<Tag> tags = new ArrayList<>();
			List<String> strings = stringToList(c.getTagList());
			for (String s : strings) {
				tags.add(tagService.findTagById(Integer.parseInt(s)));
			}
			c.settList(tags);
		}
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
		String websiteIco = optionsService.selectValueByName("website_ico");
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
		optionsMap.put("websiteIco",websiteIco);


		model.addAttribute("optionsMap",optionsMap);

		return "index";
	}

	@RequestMapping("/category/{cgid}")
	public String category(@PathVariable int cgid, Model model) {

		//查询所有category实体
		List<Category> categories = categoryService.selectAllCategory();
		for (Category c : categories) {
			c.setCount(contentService.selectContentCountByCgid(c.getCgid()));
		}
		model.addAttribute("categories", categories);

		//查询所有content实体
		List<Content> contents = contentService.selectContentListByCgid(cgid);
		for (Content c : contents) {

			//查询设置评论数
			c.setCommentCount(99);

			c.setCategoryName(categoryService.selectByPrimaryKey(c.getCgid()).getCgName());
			c.setCategorySlug(categoryService.selectByPrimaryKey(c.getCgid()).getCgSlug());

			List<Tag> tags = new ArrayList<>();
			List<String> strings = stringToList(c.getTagList());
			for (String s : strings) {
				tags.add(tagService.findTagById(Integer.parseInt(s)));
			}
			c.settList(tags);
		}
		model.addAttribute("contents", contents);

		List<Content> recommendContents = contentService.selectRecommendContent();
		model.addAttribute("recommendContents", recommendContents);

		String qqLink = optionsService.selectValueByName("qq_link");
		String emailLink = optionsService.selectValueByName("email_link");
		String githubLink = optionsService.selectValueByName("github_link");
		String location = optionsService.selectValueByName("location");
		String icp = optionsService.selectValueByName("icp");
		String description = optionsService.selectValueByName("description");
		String websiteTitle = optionsService.selectValueByName("website_title");
		String websiteIco = optionsService.selectValueByName("website_ico");
		String avatar = optionsService.selectValueByName("avatar");

		Map<String, String> optionsMap = new HashMap<>();
		optionsMap.put("qqLink", qqLink);
		optionsMap.put("emailLink", emailLink);
		optionsMap.put("githubLink", githubLink);
		optionsMap.put("location", location);
		optionsMap.put("icp", icp);
		optionsMap.put("description", description);
		optionsMap.put("websiteTitle", websiteTitle);
		optionsMap.put("avatar", avatar);
		optionsMap.put("websiteIco", websiteIco);
		optionsMap.put("categoryId", String.valueOf(cgid));

		model.addAttribute("optionsMap", optionsMap);

		return "category";
	}

	@RequestMapping("/detail2")
	public String toDetails2(Model model){
		Content c = contentService.selectByPrimaryKey(1);
		model.addAttribute("content",c);
		return "detail2";
	}

	@RequestMapping("/{categoryName}/{slugName}")
	public String showContent() {
		return "detail";
	}

	@RequestMapping("/{page}")
	public String test(@PathVariable String page) {
		return page;
	}

	@RequestMapping("/json")
	@ResponseBody
	public Map<String, String> toJson(){
		Map<String, String> map = new HashMap<>();
		map.put("data","111");
		map.put("data2","222");
		return map;
	}
}
