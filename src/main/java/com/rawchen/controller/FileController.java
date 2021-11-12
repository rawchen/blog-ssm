package com.rawchen.controller;

import com.rawchen.domain.Content;
import com.rawchen.domain.File;
import com.rawchen.domain.User;
import com.rawchen.utils.FileUtil;
import com.rawchen.utils.LogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
	public Map<String,Object> uploadFileList(HttpServletRequest req, @RequestParam(value = "files[]") MultipartFile[] files) {
		Map<String, Object> map = new HashMap<>();

		User user = (User) session.getAttribute("USER_SESSION");
		int uid = user.getUid();

		String url = "file/";
		String realPath = req.getServletContext().getRealPath("/upload/");
		java.io.File file = new java.io.File(realPath, url);
		if (!file.exists() && file.mkdirs()) {
		}
		map.put("success", 1);

		for (MultipartFile mFile : files) {
			try {
				String fileName = mFile.getOriginalFilename();

				if (new java.io.File(realPath, url + fileName).exists()) {
					map.put("success", -1);
					map.put("message", "文件已存在");
					break;
				}

				File newFile = new File();
				newFile.setPath("/upload/file/"+fileName);
				newFile.setAuthorId(uid);
				newFile.setFileStatus("publish");
				newFile.setName(fileName);
				if (!fileName.contains(".")) {
					map.put("success", 0);
					map.put("message", "上传失败");
					break;
				}
				//修正上传判断
				mFile.transferTo(new java.io.File(realPath,url + fileName));

				newFile.setFileType(FileUtil.imageType(fileName.substring(fileName.lastIndexOf(".")+1)));
				newFile.setCreatedTime(new Date());
				newFile.setModifiedTime(new Date());
				newFile.setDownloadCount(0);
				newFile.setFileSize(FileUtil.formatBytes(mFile.getSize()));
				fileService.insert(newFile);

				map.put("message", "上传成功");

			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", 0);
				map.put("message", "上传失败");
				return map;
			}
		}
		return map;
	}

	@RequestMapping("/deleteFile/{fid}")
	public String deleteFile(@PathVariable int fid, HttpServletRequest req) {
		User user = (User) session.getAttribute("USER_SESSION");

		String url = "file/";
		String realPath = req.getServletContext().getRealPath("/upload/");
		java.io.File file1 = new java.io.File(realPath, url + fileService.selectFileByFid(fid).getName());
		if (file1.exists()) {
			file1.delete();
		}
		int a = fileService.deleteByPrimaryKey(fid);
		return "redirect:/adminFile";
	}

	@RequestMapping("/deleteSelectFile")
	public String deleteSelectFile(HttpServletRequest req) {
		String[] fids = request.getParameterValues("fid");
		try {
			if (fids != null && fids.length > 0) {
				for (String fid: fids) {
					String url = "file/";
					String realPath = req.getServletContext().getRealPath("/upload/");
					java.io.File file1 = new java.io.File(realPath, url + fileService.selectFileByFid(Integer.parseInt(fid)).getName());
					if (file1.exists()) {
						file1.delete();
					}
					int a = fileService.deleteByPrimaryKey(Integer.parseInt(fid));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/adminFile";
	}

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
