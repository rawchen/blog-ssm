package com.yoyling.controller;

import com.yoyling.domain.Content;
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
		int a = contentService.deleteByPrimaryKey(cid);
		return "redirect:/adminBlog";
	}

	@RequestMapping("/deleteSelectContent")
	public String deleteSelectContent() {
		String[] sids = request.getParameterValues("cid");
		int a = contentService.deleteSelectContent(sids);
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
