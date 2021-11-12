package com.rawchen.utils;

import java.util.HashMap;
import java.util.Map;

public class SmileUtil {
	public static String StringConvertSmile(String c) {
		if (c == null) {
			return null;
		}
		Map<String, String> smile = new HashMap<>();
		String url = "https://rawchen.com/usr/plugins/Smilies/tieba";
		String front = "<img class=\"smile-img\" src=\""+url+"/";
		String end = "\">";
		smile.put(":mrgreen:",front+"icon_mrgreen.png"+end);
		smile.put(":neutral:",front+"icon_neutral.png"+end);
		smile.put(":twisted:",front+"icon_twisted.png"+end);
		smile.put(":arrow:",front+"icon_arrow.png"+end);
		smile.put(":shock:",front+"icon_eek.png"+end);
		smile.put(":smile:",front+"icon_smile.png"+end);
		smile.put(":???:",front+"icon_confused.png"+end);
		smile.put(":cool:",front+"icon_cool.png"+end);
		smile.put(":evil:",front+"icon_evil.png"+end);

		smile.put(":grin:",front+"icon_biggrin.png"+end);
		smile.put(":idea:",front+"icon_idea.png"+end);
		smile.put(":oops:",front+"icon_redface.png"+end);
		smile.put(":razz:",front+"icon_razz.png"+end);
		smile.put(":roll:",front+"icon_rolleyes.png"+end);
		smile.put(":wink:",front+"icon_wink.png"+end);
		smile.put(":cry:",front+"icon_cry.png"+end);
		smile.put(":eek:",front+"icon_surprised.png"+end);
		smile.put(":lol:",front+"icon_lol.png"+end);

		smile.put(":mad:",front+"icon_mad.png"+end);
		smile.put(":sad:",front+"icon_sad.png"+end);
		smile.put(":!:",front+"icon_exclaim.png"+end);
		smile.put(":?:",front+"icon_question.png"+end);
		smile.put(":nidongde:",front+"nidongde.png"+end);
		smile.put(":xiaoniao:",front+"xiaoniao.png"+end);
		smile.put(":wuzuixiao:",front+"wuzuixiao.png"+end);
		smile.put(":weiqu:",front+"weiqu.png"+end);
		smile.put(":yamiedie:",front+"yamiedie.png"+end);

		c = c.replace(":mrgreen:",smile.get(":mrgreen:"));
		c = c.replace(":neutral:",smile.get(":neutral:"));
		c = c.replace(":twisted:",smile.get(":twisted:"));
		c = c.replace(":arrow:",smile.get(":arrow:"));
		c = c.replace(":shock:",smile.get(":shock:"));
		c = c.replace(":smile:",smile.get(":smile:"));
		c = c.replace(":???:",smile.get(":???:"));
		c = c.replace(":cool:",smile.get(":cool:"));
		c = c.replace(":evil:",smile.get(":evil:"));
		c = c.replace(":grin:",smile.get(":grin:"));
		c = c.replace(":idea:",smile.get(":idea:"));
		c = c.replace(":oops:",smile.get(":oops:"));
		c = c.replace(":razz:",smile.get(":razz:"));
		c = c.replace(":roll:",smile.get(":roll:"));
		c = c.replace(":wink:",smile.get(":wink:"));
		c = c.replace(":cry:",smile.get(":cry:"));
		c = c.replace(":eek:",smile.get(":eek:"));
		c = c.replace(":lol:",smile.get(":lol:"));
		c = c.replace(":mad:",smile.get(":mad:"));
		c = c.replace(":sad:",smile.get(":sad:"));
		c = c.replace(":!:",smile.get(":!:"));
		c = c.replace(":?:",smile.get(":?:"));
		c = c.replace(":nidongde:",smile.get(":nidongde:"));
		c = c.replace(":xiaoniao:",smile.get(":xiaoniao:"));
		c = c.replace(":wuzuixiao:",smile.get(":wuzuixiao:"));
		c = c.replace(":weiqu:",smile.get(":weiqu:"));
		c = c.replace(":yamiedie:",smile.get(":yamiedie:"));
		return c;
	}
}
