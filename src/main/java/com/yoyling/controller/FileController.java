package com.yoyling.controller;

import com.yoyling.domain.Content;
import com.yoyling.domain.File;
import com.yoyling.utils.LogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FileController extends BaseController {
	@RequestMapping("/file")
	public String showFiles(Model model) {

		//插入日志
		logService.insert(LogUtil.insertLog(request,"/file"));

		List<Content> contents = contentService.selectAllContent();
		for (Content c : contents) {
			c.setCategorySlug(categoryService.selectByPrimaryKey(c.getCgid()).getCgSlug());
		}

		List<File> files = fileService.selectAllFile();
		model.addAttribute("files",files);
		for (File file : files) {
			System.out.println(file);
		}

		model.addAttribute("contents",contents);

		List<Content> recommendContents = contentService.selectRecommendContent();
		model.addAttribute("recommendContents",recommendContents);
		return "file";

	}

	/**
	 * 跳转file-mgr.html页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminFile")
	public String adminUser(Model model) {
		return "file-mgr";
	}

	@ResponseBody
	@RequestMapping("/uploadFileList")
	public Map<String,Object> uploadFileList(HttpServletRequest req, @RequestParam(value = "files[]") MultipartFile[] picpaths) {
		Map<String, Object> map = new HashMap<>();

		String url = "file/";
		String realPath = req.getServletContext().getRealPath("/upload/");

		java.io.File file = new java.io.File(realPath, url);
		if (!file.exists() && file.mkdirs()) {
		}
//		String originalFileName = picpaths.getOriginalFilename();
		System.out.println(picpaths);
		System.out.println(picpaths.length);
		for (MultipartFile picpath : picpaths) {
			System.out.println(picpath);
			System.out.println(picpath.getOriginalFilename());
		}
		String newFileSName = "";
//		if (originalFileName.indexOf(".") != -1) {
//			newFileSName = originalFileName;
//		}

//		String upPicFileName = System.currentTimeMillis() + newFileSName;
		String upPicFileName = "" + newFileSName;
		file = new java.io.File(file, upPicFileName);
		String contextPath = req.getServletContext().getContextPath();
		url = contextPath + "/upload/" + url + upPicFileName;
		try {
//			picpaths.transferTo(file);
			map.put("success", 1);
			map.put("message", "上传成功");
			map.put("url", url);// 拼接自己的地址
		} catch (Exception e) {
			map.put("success", 0);
			map.put("message", "上传失败");
		}
		return map;
	}

//	@ResponseBody
//	@RequestMapping(value = "/uploadFileList", method = RequestMethod.GET)
//	public Map<String,Object> uploadFileList(MultipartFile[] picpaths) {
//		Map<String, Object> map = new HashMap<String,Object>();
//		for (MultipartFile picpath : picpaths) {
//			System.out.println(picpath.getOriginalFilename());
//		}
//		return map;
//	}

	/**
	 * admin获取文件列表
	 * @return
	 */
	@RequestMapping("/adminGetFileList")
	@ResponseBody
	public Map<String,Object> adminGetFileList() {
		Map<String, Object> map = new HashMap<>();
		List<File> files = fileService.selectAllFile();
		map.put("data",files);
		return map;
	}

	/**
	 * user获取文件列表
	 * @param userId
	 * @return
	 */
	@RequestMapping("/userGetFileList")
	@ResponseBody
	public Map<String,Object> userGetFileList(@RequestParam(value="userId") int userId) {
		Map<String, Object> map = new HashMap<>();
		List<File> files = fileService.selectFileListWithUid(userId);
		map.put("data",files);
		return map;
	}
}
