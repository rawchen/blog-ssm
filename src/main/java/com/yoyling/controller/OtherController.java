package com.yoyling.controller;

import com.yoyling.domain.Category;
import com.yoyling.domain.Content;
import com.yoyling.domain.Tag;
import com.yoyling.utils.LogUtil;
import com.yoyling.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.yoyling.utils.StringUtil.stringToList;

@Controller
public class OtherController extends BaseController {

	@RequestMapping("/index")
	public String index(Model model){

		//插入日志
		logService.insert(LogUtil.insertLog(request,"/index"));

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

		request.getServletContext().setAttribute("applicationOptionsMap", optionsMap);

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

		Map<String, String> optionsMap = new HashMap<>();
		optionsMap.put("categoryId", String.valueOf(cgid));
		model.addAttribute("optionsMap", optionsMap);
		return "category";
	}

	@RequestMapping("/c/{categoryName}/{slugName}")
	public String showContent(Model model, @PathVariable String categoryName, @PathVariable String slugName) {
		int a = contentService.updateContentViewsBySlug(slugName);
		model.addAttribute("content",contentService.findContentBySlugName(slugName));
		return "detail2";
	}

	@RequestMapping("/tag")
	public String tag(Model model) {

		//查询所有category实体
		List<Tag> tags = tagService.selectAllTag();
		for (Tag t : tags) {

		}
		model.addAttribute("tags", tags);

		//查询所有content实体
		List<Content> contents = contentService.selectContentListByCgid(1);
		for (Content c : contents) {

			//查询设置评论数
			c.setCommentCount(99);

			c.setCategoryName(categoryService.selectByPrimaryKey(c.getCgid()).getCgName());
			c.setCategorySlug(categoryService.selectByPrimaryKey(c.getCgid()).getCgSlug());

			List<Tag> tagsList = new ArrayList<>();
			List<String> strings = stringToList(c.getTagList());
			for (String s : strings) {
				tagsList.add(tagService.findTagById(Integer.parseInt(s)));
			}
			c.settList(tagsList);
		}
		model.addAttribute("contents", contents);

		List<Content> recommendContents = contentService.selectRecommendContent();
		model.addAttribute("recommendContents", recommendContents);

		Map<String, String> optionsMap = new HashMap<>();
		optionsMap.put("tagId", "0");

		model.addAttribute("optionsMap", optionsMap);

		return "tag";
	}

	@RequestMapping("/tag/{tid}")
	public String tag(@PathVariable int tid, Model model) {
		//查询所有category实体
		List<Tag> tags = tagService.selectAllTag();
		model.addAttribute("tags", tags);

		//查询所有content实体
		List<Content> contents = contentService.selectContentListByTid(tid);
		for (Content c : contents) {

			//查询设置评论数
			c.setCommentCount(99);

			c.setCategoryName(categoryService.selectByPrimaryKey(c.getCgid()).getCgName());
			c.setCategorySlug(categoryService.selectByPrimaryKey(c.getCgid()).getCgSlug());

			List<Tag> tagList = new ArrayList<>();
			List<String> strings = stringToList(c.getTagList());
			for (String s : strings) {
				tagList.add(tagService.findTagById(Integer.parseInt(s)));
			}
			c.settList(tagList);
		}
		model.addAttribute("contents", contents);

		List<Content> recommendContents = contentService.selectRecommendContent();
		model.addAttribute("recommendContents", recommendContents);

		Map<String, String> optionsMap = new HashMap<>();
		if (!"".equals(String.valueOf(tid))) {
			optionsMap.put("tagId", String.valueOf(tid));
		} else {
			optionsMap.put("tagId", "");
		}
		model.addAttribute("optionsMap", optionsMap);
		return "tag";
	}

	@RequestMapping("/archive")
	public String showArchive(Model model) {

		List<Content> contents = contentService.selectAllContent();
		for (Content c : contents) {
			c.setCategorySlug(categoryService.selectByPrimaryKey(c.getCgid()).getCgSlug());
		}
		model.addAttribute("contents",contents);

		List<Content> recommendContents = contentService.selectRecommendContent();
		model.addAttribute("recommendContents",recommendContents);
		return "archive";
	}

	@ResponseBody
	@RequestMapping("/upFile")

	public Map<String,Object> articleUpFile(HttpServletRequest req, @RequestParam("editormd-image-file") MultipartFile picpaths) {
		String url = "blog/" + StringUtil.getDateToString(new Date()) + "/";
		String realPath = req.getServletContext().getRealPath("/upload/");
// TomcatEmbeddedContext
		File file = new File(realPath, url);
		if (!file.exists() && file.mkdirs()) {
		}
		Map<String, Object> resultJs = new HashMap<>();
		String upPicFileName = System.currentTimeMillis() + ".jpg";
		file = new File(file, upPicFileName);
		String contextPath = req.getServletContext().getContextPath();
		url = contextPath + "/upload/" + url + upPicFileName;
		try {
			picpaths.transferTo(file);
			/* resultJs.put("success", "1");此处不要写 字符串的"1"，只是写为数字不要带引号 */
			resultJs.put("success", 1);
			resultJs.put("message", "上传成功");
			resultJs.put("url", url);// 拼接自己的地址
		} catch (IllegalStateException | IOException e) {
			resultJs.put("success", 0);
			resultJs.put("message", "上传失败");
		}
		return resultJs;
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
