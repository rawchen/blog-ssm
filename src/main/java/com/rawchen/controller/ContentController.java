package com.rawchen.controller;

import com.rawchen.domain.Comment;
import com.rawchen.domain.Content;
import com.rawchen.domain.Tag;
import com.rawchen.utils.DateTimeUtil;
import com.rawchen.utils.GravatarUtil;
import com.rawchen.utils.LogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rawchen.utils.StringUtil.stringToList;

@Controller
public class ContentController extends BaseController {
	/**
	 * admin获取博客列表
	 * @return
	 */
	@RequestMapping("/adminGetContentList")
	@ResponseBody
	public Map<String,Object> adminGetContentList() {
		Map<String, Object> map = new HashMap<>();
		List<Content> contents = contentService.selectAllContent();
		for (Content content : contents) {
			content.setCategoryName(categoryService.selectCategoryNameById(content.getCgid()));
		}
		map.put("data",contents);
		return map;
	}

	/**
	 * user获取博客列表
	 * @param userId
	 * @return
	 */
	@RequestMapping("/userGetContentList")
	@ResponseBody
	public Map<String,Object> userGetContentList(@RequestParam(value="userId") int userId) {
		Map<String, Object> map = new HashMap<>();
		List<Content> contents = contentService.selectContentListWithUid(userId);
		for (Content content : contents) {
			content.setCategoryName(categoryService.selectCategoryNameById(content.getCgid()));
		}
		map.put("data",contents);
		return map;
	}

	@RequestMapping("/deleteContent/{cid}")
	public String deleteContent(@PathVariable int cid) {
		int res = contentService.deleteByPrimaryKey(cid);

		//删除文章后全局修正tag数量
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
		return "redirect:/adminBlog";
	}

	@RequestMapping("/deleteSelectContent")
	public String deleteSelectContent() {
		String[] sids = request.getParameterValues("cid");
		int res = contentService.deleteSelectContent(sids);

		//删除文章后全局修正tag数量
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
		return "redirect:/adminBlog";
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

	@RequestMapping("/articles/{slugName}")
	public String showContent(Model model, @PathVariable String slugName) {
		//插入日志
		logService.insert(LogUtil.insertLog(request,"/articles/" + slugName));

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

	@RequestMapping("/changeContentOrder")
	@ResponseBody
	public Map<String,Object> changeContentOrder(@RequestParam(value="contentId") String contentId) {
		Map<String, Object> map = new HashMap<>();
		if (contentService.changeContentOrderByCid(Integer.parseInt(contentId)) == 1) {
			map.put("data", "success");
		} else {
			map.put("data", "fail");
		}
		return map;
	}

}
