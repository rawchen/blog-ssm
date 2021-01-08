package com.yoyling.controller;

import com.yoyling.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@RequestMapping("/userLogin")
	@ResponseBody
	public Map<String,Object> userLogin(User u,
												 @RequestParam(value = "remember", required = false) String remember) {
		Map<String, Object> map = new HashMap<>();
		User user = userService.selectUserByNameAndPassword(u);

		if (user == null) {
			map.put("data","error");
		} else {
			map.put("data","success");

			//登录成功设置session
			session.setMaxInactiveInterval(60*60*24*7);
			session.setAttribute("USER_SESSION", user);
			//7天session
			System.out.println(remember);
		}
		return map;
	}
}
