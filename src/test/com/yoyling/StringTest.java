package com.yoyling;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.yoyling.utils.StringUtil.listToString;
import static com.yoyling.utils.StringUtil.stringToList;

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

}
