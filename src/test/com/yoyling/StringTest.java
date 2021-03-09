package com.yoyling;

import com.yoyling.utils.DateTimeUtil;
import com.yoyling.utils.FileUtil;
import com.yoyling.utils.GravatarUtil;
import com.yoyling.utils.StringUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.yoyling.utils.DateTimeUtil.calculateApartDayConvertString;
import static com.yoyling.utils.StringUtil.*;

public class StringTest {
	@Test
	public void run1() {
		String a = "1,2,3,4";
		List<String>b = stringToList(a);
		for (String c:b) {
			System.out.println(c);
		}
	}

	@Test
	public void run2() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");

		System.out.println(listToString(list));
	}

	@Test
	public void run3() {
		System.out.println(GravatarUtil.getGravatarUrlByEmail("2221999792@qq.com"));
	}

	@Test
	public void run4() {
		System.out.println(StringUtil.passwordToMd5("yoyling"));
	}

	@Test
	public void run5() {
		System.out.println(DateTimeUtil.yesterdayDateConvertString());
	}

	@Test
	public void run6() {
		System.out.println(calculateApartDayConvertString(1));
	}

	@Test
	public void run7() {
		System.out.println(isLetter("aaaasdsasdfsf1fasfasf"));
	}

	@Test
	public void run8() {
		System.out.println(isContainChinese("mypaydayloan relief  http://loanonlineiuw.com/#  advance payday loan  <a href=\"http://loanonlineiuw.com/# \">new payday loans </a> payday loan stores"));
	}

	@Test
	public void run9() {
		System.out.println(FileUtil.formatBytes(12313123213L));
	}

	@Test
	public void run10() {
		System.out.println(!isContainChinese("My Homework Help") && !isContainChinese("<a href=https://writingserviceray.com/>write a essay about yourself</a> <a href=https://essayhw.com/>research paper</a>"));
	}


}
