package com.yoyling.controller;

import com.yoyling.domain.Category;
import com.yoyling.domain.Content;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CategoryController extends BaseController {
	@RequestMapping("/updateCategory")
	@ResponseBody
	public Map<String,Object> updateCategory(Category category){
		Map<String, Object> map = new HashMap<>();
		int a = categoryService.updateCategory(category);
		if (a == 1) {
			map.put("data", "更新分类成功");
		} else {
			map.put("data", "更新分类失败");
		}
		return map;
	}

	@RequestMapping("/deleteCategory")
	@ResponseBody
	public Map<String,Object> deleteCategory(int cgid){
		Map<String, Object> map = new HashMap<>();
		List<Content> contentList = contentService.selectAllContent();
		for (Content content : contentList) {
			if (content.getCgid() == cgid) {
				//根据content的id修改cgid
				int a = contentService.updateContentCgidDefaultByCid(content.getCid());
			}
		}

		int a = categoryService.deleteCategory(cgid);
		if (a == 1) {
			map.put("data", "删除分类成功");
		} else {
			map.put("data", "删除分类失败");
		}
		return map;
	}

	@RequestMapping("/insertCategory")
	@ResponseBody
	public Map<String,Object> insertCategory(Category category){
		Map<String, Object> map = new HashMap<>();
		int a = categoryService.insertCategory(category);
		if (a == 1) {
			map.put("data", "增加分类成功");
		} else {
			map.put("data", "增加分类失败");
		}
		return map;
	}


}
