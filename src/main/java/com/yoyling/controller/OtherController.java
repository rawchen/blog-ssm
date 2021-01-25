package com.yoyling.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.yoyling.domain.*;
import com.yoyling.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
		totalPages = (totalPages % postsListSize) == 0 ? totalPages/postsListSize : (totalPages/postsListSize) + 1;
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

		int numberOfArticles = contents.size();
		model.addAttribute("numberOfArticles",numberOfArticles);

		model.addAttribute("searchWord",searchWord);

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

		//设置热门文字
		model.addAttribute("tagHot", 1);

		//查询所有category实体
		List<Tag> tags = tagService.selectAllTag();
		model.addAttribute("tags", tags);

		//查询默认热门postsListSize个帖子
		int postsListSize = Integer.parseInt((String) ((Map) request.getServletContext().getAttribute("applicationOptionsMap")).get("postsListSize"));
		List<Content> contents = contentService.selectPostSizeContentWithHot(postsListSize);
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
		//不设置热门文字
		model.addAttribute("tagHot", 0);

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

	@RequestMapping("/guestbook")
	public String showGuestbook(Model model) {

		List<Content> contents = contentService.selectAllContent();
		for (Content c : contents) {
			c.setCategorySlug(categoryService.selectByPrimaryKey(c.getCgid()).getCgSlug());
		}
		model.addAttribute("contents",contents);

		List<Content> recommendContents = contentService.selectRecommendContent();
		model.addAttribute("recommendContents",recommendContents);

		List<Comment> comments = commentService.selectCommentListByContentId(0);
		for (Comment comment : comments) {
			comment.setMail(GravatarUtil.getGravatarUrlByEmail(comment.getMail()));
			comment.setCreatedDisplay(DateTimeUtil.dateWord(comment.getCreated()));
			comment.setParentNickName(commentService.selectCommentAuthorById(comment.getParent()));
		}
		model.addAttribute("comments",comments);

		return "guestbook";
	}

	@ResponseBody
	@RequestMapping(value = "/upFile", method = RequestMethod.POST)
	public Map<String,Object> articleUpFile(HttpServletRequest req, @RequestParam("editormd-image-file") MultipartFile picpaths) throws JsonProcessingException {
		System.out.println("进入函数");
		Map<String, Object> map = new HashMap<>();

		String url = "blog/" + StringUtil.getDateToString(new Date()) + "/";
		String realPath = req.getServletContext().getRealPath("/upload/");

		File file = new File(realPath, url);
		if (!file.exists() && file.mkdirs()) {
		}
		String originalFileName = picpaths.getOriginalFilename();
		String newFileSName = "";
		if (originalFileName.indexOf(".") != -1) {
			newFileSName = originalFileName.substring(originalFileName.lastIndexOf("."));
		}
		String upPicFileName = System.currentTimeMillis() + newFileSName;
		file = new File(file, upPicFileName);
		String contextPath = req.getServletContext().getContextPath();
		url = contextPath + "/upload/" + url + upPicFileName;
		try {
			picpaths.transferTo(file);
			map.put("success", 1);
			map.put("message", "上传成功");
			map.put("url", url);// 拼接自己的地址
		} catch (Exception e) {
			map.put("success", 0);
			map.put("message", "上传失败");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value="/fileupload")
	public Map<String,Object> fileupload(HttpServletRequest request,@RequestParam("editormd-image-file") MultipartFile upload) throws Exception {
		System.out.println("SpringMVC方式的文件上传...");
		Map<String, Object> resultJs = new HashMap<>();
		// 先获取到要上传的文件目录
		String path = request.getSession().getServletContext().getRealPath("/upload");
		// 创建File对象，一会向该路径下上传文件
		File file = new File(path);
		// 判断路径是否存在，如果不存在，创建该路径
		if(!file.exists()) {
			file.mkdirs();
		}
		// 获取到上传文件的名称
		String filename = upload.getOriginalFilename();
		String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		// 把文件的名称唯一化
		filename = uuid+"_"+filename;
		// 上传文件

		try {
			upload.transferTo(new File(file,filename));
			resultJs.put("success", 1);
			resultJs.put("message", "上传成功");
			resultJs.put("url", path);// 拼接自己的地址
		} catch (Exception e) {
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
		boolean isHaveAuthorAndMail = author == null || "".equals(author) || mail == null || "".equals(mail);
		int contentId = 0;
		if (request.getParameter("contentId") == null || "".equals(request.getParameter("contentId"))) {
		} else {
			contentId = Integer.parseInt(request.getParameter("contentId"));
		}

		Comment comment = new Comment();
		comment.setCid(contentId);
		comment.setCreated(new Date());

		User sessionUser = (User) session.getAttribute("USER_SESSION");
		if (sessionUser == null) {
			if (isHaveAuthorAndMail) {
				return "redirect:/index";
			} else {
				comment.setAuthorid(0);
				comment.setMail(mail);
				comment.setUrl(url);
				comment.setAuthor(author);
			}
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
		if (contentId == 0) {
			return "redirect:/guestbook";
		} else {
			return "redirect:/articles/"+contentService.selectByPrimaryKey(contentId).getSlug();
		}
	}

	@RequestMapping("/comment/{cid}/{coid}")
	public String commentReply(@PathVariable int cid,@PathVariable int coid) {
		String commentText = request.getParameter("commentText");
		commentText = SmileUtil.StringConvertSmile(commentText);
		String author = request.getParameter("author");
		String mail = request.getParameter("mail");
		String url = request.getParameter("url");
		boolean isHaveAuthorAndMail = author == null || "".equals(author) || mail == null || "".equals(mail);

		Comment comment = new Comment();
		comment.setCid(cid);
		comment.setParent(coid);
		comment.setCreated(new Date());
		User sessionUser = (User) session.getAttribute("USER_SESSION");
		if (sessionUser == null) {
			if (isHaveAuthorAndMail) {
				return "redirect:/index";
			} else {
				comment.setAuthorid(0);
				comment.setMail(mail);
				comment.setUrl(url);
				comment.setAuthor(author);
			}
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

		if (cid != 0) {
			return "redirect:/articles/"+contentService.selectByPrimaryKey(cid).getSlug();
		} else {
			return "redirect:/guestbook";
		}
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
