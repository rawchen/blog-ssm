package com.yoyling.controller;

import com.yoyling.domain.Comment;
import com.yoyling.domain.User;
import com.yoyling.utils.DateTimeUtil;
import com.yoyling.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController extends BaseController {

	/**
	 * 获取博客列表
	 *
	 * @return
	 */
	@RequestMapping("/adminGetCommentList")
	@ResponseBody
	public Map<String, Object> adminGetCommentList() {
		Map<String, Object> map = new HashMap<>();
		List<Comment> comments = commentService.selectAllComment();
		for (Comment comment : comments) {
			comment.setContentSlug(contentService.selectSlugByCid(comment.getCid()));
			comment.setCreatedDisplay(DateTimeUtil.dateWord(comment.getCreated()));
		}
		map.put("data", comments);
		return map;
	}

	@RequestMapping("/deleteComment/{coid}")
	public String deleteContent(@PathVariable int coid) {
		int a = commentService.deleteByPrimaryKey(coid);
		return "redirect:/adminComment";
	}

	@RequestMapping("/replyComment")
	@ResponseBody
	public Map<String, Object> replyComment(int coid, int cid, String text, Model model) {
		Map<String, Object> map = new HashMap<>();
		Comment comment = new Comment();
		comment.setCreated(new Date());
		comment.setParent(coid);
		comment.setCid(cid);
		comment.setAuthorid(((User)session.getAttribute("USER_SESSION")).getUid());
		comment.setAuthor(((User)session.getAttribute("USER_SESSION")).getScreenname());
		comment.setMail(((User)session.getAttribute("USER_SESSION")).getMail());
		comment.setUrl(((User)session.getAttribute("USER_SESSION")).getUrl());
		comment.setIp(StringUtil.getIpAddr(request));
		comment.setAgent(request.getHeader("User-Agent"));
		comment.setText(text);
		int a = commentService.insert(comment);
		if (a == 1) {
			map.put("data", "success");
		} else {
			map.put("data","fail");
		}
		return map;
	}

	@RequestMapping("/deleteSelectComment")
	public String deleteSelectComment() {
		String[] coids = request.getParameterValues("coid");
		int a = commentService.deleteSelectComment(coids);
		return "redirect:/adminComment";
	}

}
