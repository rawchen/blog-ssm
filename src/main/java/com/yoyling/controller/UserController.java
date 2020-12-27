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
	public Map<String,Object> userLogin(User u, @RequestParam(value = "remember", required = false) String remember) {
		Map<String, Object> map = new HashMap<>();
		return map;
	}
}
