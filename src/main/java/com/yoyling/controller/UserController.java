package com.yoyling.controller;

import com.yoyling.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController extends BaseController {

	/**
	 * 首页跳转Login页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String toLogin(Model model) {
		String websiteTitle = optionsService.selectValueByName("website_title");
		String websiteIco = optionsService.selectValueByName("website_ico");
		Map<String, String> optionsMap = new HashMap<>();
		optionsMap.put("websiteTitle",websiteTitle);
		optionsMap.put("websiteIco",websiteIco);
		model.addAttribute("optionsMap",optionsMap);
		return "login";
	}

	/**
	 * 通过ajax登录系统后台
	 * @param u
	 * @param remember
	 * @return
	 */
	@RequestMapping("/userLogin")
	@ResponseBody
	public Map<String,Object> userLogin(User u, @RequestParam(value = "remember", required = false) String remember) {
		Map<String, Object> map = new HashMap<>();
		User user = userService.selectUserByNameAndPassword(u);
		if (user == null) {
			map.put("data","error");
		} else {
			map.put("data","success");
			session.setAttribute("USER_SESSION", user);
			if ("on".equals(remember)) {
				session.setMaxInactiveInterval(60*60*24*7);
			}
		}
		return map;
	}
}
