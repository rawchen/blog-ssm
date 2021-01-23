package com.yoyling.controller;

import com.yoyling.domain.Content;
import com.yoyling.domain.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ContentController extends BaseController {
	/**
	 * 获取博客列表
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
