package com.yoyling;

import org.junit.Test;

import java.util.List;

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

}
