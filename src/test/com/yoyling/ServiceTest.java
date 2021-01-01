package com.yoyling;

import com.yoyling.domain.Content;
import com.yoyling.service.ContentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceTest {
	@Test
	public void run1() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		ContentService contentsService = (ContentService) ac.getBean("contentService");
		Content content = contentsService.selectByPrimaryKey(1);
		System.out.println(content);
	}
}
