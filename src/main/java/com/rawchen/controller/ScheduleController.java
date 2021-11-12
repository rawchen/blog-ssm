package com.rawchen.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@EnableScheduling
public class ScheduleController {

	/**
	 * 每1秒执行一次
	 * @return
	 */
	@RequestMapping("/ts")
	@ResponseBody
//	@Scheduled(fixedDelay = 1000)
	public Map<String,Object> ts() {
		Map<String, Object> map = new HashMap<>();
		map.put("data", "success");
		System.out.println(1);
		return map;
	}

	/**
	 * 定时执行 每天10:12
	 */
	@Scheduled(cron = "0 12 10 * * ?")
	public void tscron(){
		System.out.println(new Date());
	}
}
