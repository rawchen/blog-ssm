package com.yoyling;

import com.yoyling.domain.Category;
import com.yoyling.domain.Content;
import com.yoyling.domain.Tag;
import com.yoyling.service.CategoryService;
import com.yoyling.service.ContentService;
import com.yoyling.service.OptionsService;
import com.yoyling.service.TagService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ServiceTest {
	@Test
	public void run1() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		ContentService contentsService = (ContentService) ac.getBean("contentService");
		Content content = contentsService.selectByPrimaryKey(1);
		System.out.println(content);
	}

	@Test
	public void run2() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		ContentService contentsService = (ContentService) ac.getBean("contentService");
		int n = contentsService.selectNumberOfArticles();
		System.out.println(n);
	}

	@Test
	public void run3() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		ContentService contentsService = (ContentService) ac.getBean("contentService");
		List<Content> contents = contentsService.selectRecommendContent();
		for (Content c : contents) {
			System.out.println(c);
		}
	}

	@Test
	public void run4() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		OptionsService optionsService = (OptionsService) ac.getBean("optionsService");
		String value = optionsService.selectValueByName("qq_link");
		System.out.println(value);
	}

	@Test
	public void run5() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		CategoryService categoryService = (CategoryService) ac.getBean("categoryService");
		List<Category> categories = categoryService.selectAllCategory();
		for (Category c:categories) {
			System.out.println(c);
		}
	}

	@Test
	public void run6() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
		TagService tagService = (TagService) ac.getBean("tagService");
		List<Tag> tags = tagService.selectAllTag();
		for (Tag t:tags) {
			System.out.println(t);
		}
	}

}
