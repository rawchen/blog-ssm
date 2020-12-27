package com.yoyling;

import com.yoyling.domain.Contents;
import com.yoyling.service.ContentsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceTest {
	@Test
	public void run1() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		ContentsService contentsService = (ContentsService) ac.getBean("contentsService");
		Contents content = contentsService.selectByPrimaryKey(13);
		System.out.println(content);
	}
}
