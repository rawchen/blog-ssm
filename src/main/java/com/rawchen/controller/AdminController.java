package com.rawchen.controller;

import com.rawchen.domain.*;
import com.rawchen.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.rawchen.utils.StringUtil.listToString;
import static com.rawchen.utils.StringUtil.stringToList;

@Controller
public class AdminController extends BaseController {

	/**
	 * 跳转dashboard.html后台首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin")
	public String toDashboard(Model model) {
		model.addAttribute("totalContent",contentService.selectNumberOfArticles());
		model.addAttribute("totalCategory",categoryService.selectCountOfCategory());
		model.addAttribute("totalTag",tagService.selectCountOfTag());
		model.addAttribute("totalComment",99);
		return "dashboard";
	}

	/**
	 * 跳转edit.html新增博客页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminEdit")
	public String adminEdit(Model model) {
		model.addAttribute("serverName",request.getServerName());

		List<Category> categories = categoryService.selectAllCategory();
		model.addAttribute("categories",categories);

		List<Tag> tags = tagService.selectAllTag();
		List<String> tags1 = new ArrayList<>();
		for (Tag tag:tags) {
			tags1.add(tag.getName());
		}
		model.addAttribute("tags",listToString(tags1));

		return "edit";
	}

	/**
	 * 跳转modify.html修改博客页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminModify")
	public String adminModify(Model model,@RequestParam(defaultValue = "0") int cid) {
		Content content = contentService.selectByPrimaryKey(cid);
		User user = (User) session.getAttribute("USER_SESSION");

		//判断是否属于此user
		if ((!user.getUid().equals(content.getAuthorId())) && (!"1".equals(String.valueOf(user.getUid())))) {
			return "redirect:/adminBlog";
		}
		String tags = "";
		List<String> strings = stringToList(content.getTagList());
		for (String s : strings) {
			tags = tags + (tagService.findTagById(Integer.parseInt(s)).getName()+",");
		}
		if (!"".equals(tags)) {
			tags = tags.substring(0, tags.length()-1);
		}
		content.setTagList(tags);
		model.addAttribute("content",content);
		model.addAttribute("serverName",request.getServerName());

		List<Category> categories = categoryService.selectAllCategory();
		model.addAttribute("categories",categories);

		return "modify";
	}

	/**
	 * 提交博客编写表单
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminEditBlog")
	public String adminEditBlog(Model model) {

		String blogId = "";
		if (request.getParameter("blogId") != null) {
			blogId = request.getParameter("blogId");
		}

		String blogTitle = request.getParameter("blogTitle");
		String blogSlug = request.getParameter("blogSlug");
		String blogCategory = request.getParameter("blogCategory");

		List<String> blogTag = new ArrayList<>();
		if (request.getParameterValues("blogTag") != null) {
			if ("".equals(blogId)) {//新增
				blogTag = Arrays.asList(request.getParameterValues("blogTag"));
			}else {//更新
				if ("".equals(request.getParameterValues("blogTag")[0])) {
					blogTag.clear();
				}else {
					blogTag = stringToList(request.getParameterValues("blogTag")[0]);
				}
			}
		}

		String blogDescription = request.getParameter("blogDescription");
		String blogType = request.getParameter("blogType");
		String blogContentsOrder = "0";
		if ("on".equals(request.getParameter("blogContentOrder"))) {
			blogContentsOrder = "1";
		}
		String blogContentStatus = request.getParameter("blogContentStatus");
		String blogPassword = request.getParameter("blogPassword");
		String blogThumb = request.getParameter("blogThumb");
		String blogContent = request.getParameter("content");

		Content content = new Content();
		content.setCgid(categoryService.selectCategoryBySlug(blogCategory));
		content.setTitle(blogTitle);
		content.setSlug(blogSlug);

		try {
			content.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(request.getParameter("blogCreatedTime")));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		content.setModifiedTime(new Date());
		content.setContentText(blogContent);
		content.setContentOrder(Integer.parseInt(blogContentsOrder));
		User user = (User) session.getAttribute("USER_SESSION");
		if ("".equals(blogId)) {//新增
			content.setViews(0);
			content.setAuthorId(user.getUid());
		}else {
			content.setViews(contentService.selectContentViewsBycontentId(Integer.parseInt(blogId)));
			content.setCid(Integer.parseInt(blogId));
			content.setAuthorId(contentService.selectContentAuthorIdBycontentId(Integer.parseInt(blogId)));//修改时
		}
		content.setContentType(blogType);
		content.setContentStatus(blogContentStatus);
		content.setContentStatus(blogContentStatus);
		content.setPassword(blogPassword);
		if ("".equals(blogThumb) || blogThumb == null) {
			content.setThumb(StringUtil.randomContentThumb());
		} else {
			content.setThumb(blogThumb);
		}
		content.setDescription(blogDescription);

		List<String> tagListNew = new ArrayList<>();
		for (String s:blogTag) {
			int i = tagService.findTagIdByName(s);
			if (i != -1) {
				//根据tagid增加1次count
				//tagService.updateTagCount(i);
				tagListNew.add(String.valueOf(i));
			} else {
				tagService.insert(new Tag(null,s,1));
				tagListNew.add(String.valueOf(tagService.findTagIdByName(s)));
			}
			content.setTagList(listToString(tagListNew));
		}

		int result = 0;
		if ("".equals(blogId)) {//新增
			result = contentService.insert(content);
		}else {
			result = contentService.updateContent(content);
		}

		if (result != 0) {
			//插入文章后全局修正tag数量
			List<Content> contents = contentService.selectAllContent();
			StringBuilder a = new StringBuilder();
			for (Content c : contents) {
				a.append(",").append(c.getTagList());
			}
			String[] b = a.toString().split(",");
			List<Tag> tags = tagService.selectAllTag();
			for (Tag tag : tags) {
				int tagCount = 0;
				for (String s : b) {
					if (s.equals(String.valueOf(tag.getTid()))) {
						tagCount++;
					}
				}
				tagService.updateTagCount(tag.getTid(),tagCount);
			}

			model.addAttribute("message", "1");
		} else {
			model.addAttribute("message", "0");
		}
		return "redirect:/adminBlog";

	}

	/**
	 * 获取便签列表
	 * @return
	 */
	@RequestMapping("/adminGetTagList")
	@ResponseBody
	public Map<String,Object> adminGetTagList() {
		Map<String, Object> map = new HashMap<>();
		List<Tag> tags = tagService.selectAllTag();
		map.put("tags",tags);
		return map;
	}

	/**
	 * 模糊查询标签列表
	 * @param tagName
	 * @return
	 */
	@RequestMapping("/fuzzyQueryTag")
	@ResponseBody
	public Map<String,Object> fuzzyQueryTag(@RequestParam(value="tagName")String tagName){
		Map<String, Object> map = new HashMap<>();
		List<Tag> tags = tagService.fuzzyQueryTag(tagName);
		map.put("tags",tags);
		return map;
	}

	/**
	 * 通过文章缩略名获取文章实体
	 * @param slugName
	 * @return
	 */
	@RequestMapping("/findContentBySlugName")
	@ResponseBody
	public Map<String,Object> findContentBySlugName(@RequestParam(value="slugName")String slugName){
		Map<String, Object> map = new HashMap<>();
		Content c = contentService.findContentBySlugName(slugName);
		boolean slugExist = false;
		if (c != null) {
			slugExist = true;
		}
		map.put("slugOrigin",slugName);
		map.put("slugExist", slugExist);
		return map;
	}

	/**
	 * 跳转blog-mgr.html页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminBlog")
	public String adminBlog(Model model) {
		return "blog-mgr";
	}

	/**
	 * 跳转user-mgr.html页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminUser")
	public String adminUser(Model model) {
		return "user-mgr";
	}

	/**
	 * 跳转category-mgr.html页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminCategory")
	public String adminCategory(Model model) {
		List<Category> categories = categoryService.selectAllCategory();
		for (Category c : categories) {
			c.setCount(contentService.selectContentCountByCgid(c.getCgid()));
		}
		model.addAttribute("categories",categories);
		return "category-mgr";
	}

	/**
	 * 跳转tag-mgr.html页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminTag")
	public String adminTag(Model model) {
		List<Tag> tags = tagService.selectAllTag();
		model.addAttribute("tags",tags);
		return "tag-mgr";
	}

	/**
	 * 跳转comment-mgr.html页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminComment")
	public String adminComment(Model model) {
		List<Comment> comments = commentService.selectAllComment();
		model.addAttribute("comments",comments);
		return "comment-mgr";
	}

	/**
	 * 跳转configuration.html页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminConfig")
	public String adminConfig(Model model) {
		return "configuration";
	}

}
