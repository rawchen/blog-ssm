package com.yoyling.controller;

import com.yoyling.domain.Content;
import com.yoyling.domain.Tag;
import com.yoyling.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TagController extends BaseController {

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
		System.out.println(tid);
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
