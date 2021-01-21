package com.yoyling.controller;

import com.github.pagehelper.PageHelper;
import com.yoyling.domain.*;
import com.yoyling.utils.*;
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

import static com.yoyling.utils.StringUtil.getIpAddr;
import static com.yoyling.utils.StringUtil.stringToList;

@Controller
public class OtherController extends BaseController {

	@RequestMapping("/index")
	public String index(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
						Model model){

		//插入日志
		logService.insert(LogUtil.insertLog(request,"/index"));

		int numberOfArticles = contentService.selectNumberOfArticles();
		model.addAttribute("numberOfArticles",numberOfArticles);

		int postsListSize = Integer.parseInt((String) ((Map) request.getServletContext().getAttribute("applicationOptionsMap")).get("postsListSize"));
		PageHelper.startPage(pageNum, postsListSize);
		List<Content> contents = contentService.selectAllContent();
		for (Content c : contents) {

			//查询设置评论数
			c.setCommentCount(contentService.selectCommentCountByCid(c.getCid()));

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
		long totalPages = PageHelper.count(()->contentService.selectAllContent());
		totalPages = totalPages / postsListSize + 1;
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("pageHelper", 1);

		List<Content> recommendContents = contentService.selectRecommendContent();
		model.addAttribute("recommendContents",recommendContents);

		return "index";
	}

	@RequestMapping("/searchContent")
	public String searchContent(@RequestParam(value = "searchWord", defaultValue = "") String searchWord, Model model){

		model.addAttribute("totalPages", 0);
		model.addAttribute("currentPage", 0);
		model.addAttribute("pageHelper", 0);

		int numberOfArticles = contentService.selectNumberOfArticles();
		model.addAttribute("numberOfArticles",numberOfArticles);

		List<Content> contents = contentService.selectContentListByLike(searchWord);
		for (Content c : contents) {

			//查询设置评论数
			c.setCommentCount(contentService.selectCommentCountByCid(c.getCid()));

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
			c.setCommentCount(contentService.selectCommentCountByCid(c.getCid()));

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

	@RequestMapping("/articles/{slugName}")
	public String showContent(Model model, @PathVariable String slugName) {
		int a = contentService.updateContentViewsBySlug(slugName);
		Content content =  contentService.findContentBySlugName(slugName);
		model.addAttribute("content",content);
		List<Comment> comments = commentService.selectCommentListByContentId(content.getCid());
		for (Comment comment : comments) {
			comment.setMail(GravatarUtil.getGravatarUrlByEmail(comment.getMail()));
			comment.setCreatedDisplay(DateTimeUtil.dateWord(comment.getCreated()));
			comment.setParentNickName(commentService.selectCommentAuthorById(comment.getParent()));
		}
		model.addAttribute("comments",comments);
		return "detail";
	}

	@RequestMapping("/tag")
	public String tag(Model model) {

		//查询所有category实体
		List<Tag> tags = tagService.selectAllTag();
		model.addAttribute("tags", tags);

		//查询所有content实体
		List<Content> contents = contentService.selectAllContent();
		for (Content c : contents) {
			//查询设置评论数
			c.setCommentCount(contentService.selectCommentCountByCid(c.getCid()));

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
			c.setCommentCount(contentService.selectCommentCountByCid(c.getCid()));

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

	@RequestMapping("/comment")
	public String comment() {
		String commentText = request.getParameter("commentText");
		commentText = SmileUtil.StringConvertSmile(commentText);
		String author = request.getParameter("author");
		String mail = request.getParameter("mail");
		String url = request.getParameter("url");
		int contentId = Integer.valueOf(request.getParameter("contentId"));

		Comment comment = new Comment();
		comment.setCid(contentId);
		comment.setCreated(new Date());

		User sessionUser = (User) session.getAttribute("USER_SESSION");
		if (sessionUser == null) {
			comment.setAuthorid(0);
			comment.setMail(mail);
			comment.setUrl(url);
			comment.setAuthor(author);

		} else {
			comment.setAuthorid(sessionUser.getUid());
			comment.setMail(sessionUser.getMail());
			comment.setUrl(sessionUser.getUrl());
			comment.setAuthor(sessionUser.getScreenname());
		}

		comment.setIp(getIpAddr(request));
		comment.setAgent(request.getHeader("User-Agent"));
		comment.setText(commentText);

		comment.setParent(0);
		int a = commentService.insert(comment);
		CookieUtil.setUserLoginCookie(author, mail, url, request, response);

		return "redirect:/articles/"+contentService.selectByPrimaryKey(contentId).getSlug();
	}

	@RequestMapping("/comment/{cid}/{coid}")
	public String commentReply(@PathVariable int cid,@PathVariable int coid) {
		System.out.println(coid);
		String commentText = request.getParameter("commentText");
		commentText = SmileUtil.StringConvertSmile(commentText);
		String author = request.getParameter("author");
		String mail = request.getParameter("mail");
		String url = request.getParameter("url");

		Comment comment = new Comment();

		comment.setCid(cid);
		comment.setParent(coid);
		comment.setCreated(new Date());
		User sessionUser = (User) session.getAttribute("USER_SESSION");
		if (sessionUser == null) {
			comment.setAuthorid(0);
			comment.setMail(mail);
			comment.setUrl(url);
			comment.setAuthor(author);

		} else {
			comment.setAuthorid(sessionUser.getUid());
			comment.setMail(sessionUser.getMail());
			comment.setUrl(sessionUser.getUrl());
			comment.setAuthor(sessionUser.getScreenname());
		}
		comment.setIp(getIpAddr(request));
		comment.setAgent(request.getHeader("User-Agent"));
		comment.setText(commentText);

		int a = commentService.insert(comment);
		CookieUtil.setUserLoginCookie(author, mail, url, request, response);

		return "redirect:/articles/"+contentService.selectByPrimaryKey(cid).getSlug();
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
