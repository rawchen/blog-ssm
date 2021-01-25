package com.yoyling.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.yoyling.domain.Content;
import com.yoyling.domain.Tag;
import com.yoyling.utils.DateTimeUtil;
import com.yoyling.utils.LogUtil;
import com.yoyling.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

import static com.yoyling.utils.DateTimeUtil.todayDateConvertString;
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
	@RequestMapping("/getTodayYesterdayAccess")
	public Map<String,Object> getTodayYesterdayAccess() {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		Map<String, Object> mapYesterday = new HashMap<>();
		Map<String, Object> mapToday = new HashMap<>();
		List<Integer> y = logService.selectYesterdayPvUvVvIv();
		mapYesterday.put("pv",24);
		mapYesterday.put("uv",y.get(1));
		mapYesterday.put("vv",y.get(0));
		mapYesterday.put("iv",y.get(0));

		List<Integer> t = logService.selectYesterdayPvUvVvIv();
		mapToday.put("pv",y.get(0));
		mapToday.put("uv",y.get(1));
		mapToday.put("vv",y.get(0));
		mapToday.put("iv",y.get(0));

		map.put("code", 0);
		map.put("info", "success");

		dataMap.put(DateTimeUtil.yesterdayDateConvertString(),mapYesterday);
		dataMap.put(todayDateConvertString(),mapToday);

		map.put("data",dataMap);

		return map;
	}

//	@RequestMapping("/{page}")
//	public String test(@PathVariable String page) {
//		return page;
//	}
}
