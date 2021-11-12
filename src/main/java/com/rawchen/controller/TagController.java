package com.rawchen.controller;

import com.rawchen.domain.Content;
import com.rawchen.domain.Tag;
import com.rawchen.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rawchen.utils.StringUtil.stringToList;

@Controller
public class TagController extends BaseController {

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

	@RequestMapping("/updateTag")
	@ResponseBody
	public Map<String,Object> updateTag(Tag tag){
		Map<String, Object> map = new HashMap<>();
		int a = tagService.updateTagName(tag);
		if (a == 1) {
			map.put("data", "更新标签成功");
		} else {
			map.put("data", "更新标签失败");
		}
		return map;
	}

	@RequestMapping("/deleteTag")
	@ResponseBody
	public Map<String,Object> deleteTag(int tid){
		Map<String, Object> map = new HashMap<>();

		//将所有文章的这个tig从tag_list中删除
		List<Content> contentList = contentService.selectAllContent();
		for (Content content : contentList) {
			String tagList = content.getTagList();
			if (tagList == null || "".equals(tagList)) {
				continue;
			}

			List<String> strings = StringUtil.stringToList(tagList);
			List<String> newTagList = new ArrayList<>();
			for (String string : strings) {
				if (!string.equals(String.valueOf(tid))) {
					newTagList.add(string);
				}
			}
			String t = StringUtil.listToString(newTagList);

			content.setTagList(t);

			int a = contentService.updateContentTagListByCid(content);
		}
		int b = tagService.deleteTag(tid);
		if (b == 1) {
			map.put("data", "删除标签成功");
		} else {
			map.put("data", "删除标签失败");
		}
		return map;
	}
}
