package com.rawchen;

import com.rawchen.domain.*;
import com.rawchen.service.*;
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

	@Test
	public void run7() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
		TagService tagService = (TagService) ac.getBean("tagService");
		List<Tag> tags = tagService.fuzzyQueryTag("i");
		for (Tag t:tags) {
			System.out.println(t);
		}
	}

	@Test
	public void run8() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		CategoryService categoryService = (CategoryService) ac.getBean("categoryService");
		int c = categoryService.selectCategoryBySlug("se");
		System.out.println(c);
	}

	@Test
	public void run9() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
		TagService tagService = (TagService) ac.getBean("tagService");
		int a = tagService.findTagIdByName("IO");
		System.out.println(a);
	}

	@Test
	public void run10() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		ContentService contentsService = (ContentService) ac.getBean("contentService");
		Content content = contentsService.findContentBySlugName("ioc");
		System.out.println(content);
	}

	@Test
	public void run11() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		UserService userService = (UserService) ac.getBean("userService");
		User t = new User();
		t.setName("rawchen");
		t.setPassword("rawchen");
		User user = userService.selectUserByNameAndPassword(t);
		System.out.println(user);
	}

	@Test
	public void run12() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		CommentService commentService = (CommentService) ac.getBean("commentService");
		List<Comment> comments = commentService.selectCommentListByContentId(1);
		for (Comment comment : comments) {
			System.out.println(comment);
		}
	}

	@Test
	public void run13() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		LogService logService = (LogService) ac.getBean("logService");
		List<Integer> ys = logService.selectYesterdayPvUvIndexGuestbook();
		for (Integer y : ys) {
			System.out.println(y);
		}
	}

	@Test
	public void run15() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		FileService fileService = (FileService) ac.getBean("fileService");
		List<File> files = fileService.selectAllFile();
		for (File f : files) {
			System.out.println(f);
		}
	}

	@Test
	public void run16() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		ContentService contentsService = (ContentService) ac.getBean("contentService");
		List<Content> contents = contentsService.selectAllContent();
		for (Content c : contents) {
			System.out.println(c);
			System.out.println(c.gettList());
		}
	}

	@Test
	public void run17() {
		ApplicationContext ac = new
				ClassPathXmlApplicationContext("classpath:spring-context.xml");
		LogService logService = (LogService) ac.getBean("logService");
		List<Integer> ys = logService.selectSevenDaysUv();
		for (Integer y : ys) {
			System.out.println(y);
		}
	}

}
