package com.rawchen.controller;


import com.rawchen.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 基础控制器，用来继承。
 * 1、自动注入所有所需服务层接口
 * 2、设置request、response、session对象
 */
@Controller
public class BaseController {

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected HttpSession session;

	@Resource
	protected ContentService contentService;

	@Resource
	protected OptionsService optionsService;

	@Resource
	protected CategoryService categoryService;

	@Resource
	protected TagService tagService;

	@Resource
	protected UserService userService;

	@Resource
	protected LogService logService;

	@Resource
	protected CommentService commentService;

	@Resource
	protected FileService fileService;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession(true);
	}
}
