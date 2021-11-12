package com.rawchen.controller;

import com.rawchen.domain.Category;
import com.rawchen.domain.Content;
import com.rawchen.domain.Tag;
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
public class CategoryController extends BaseController {

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
