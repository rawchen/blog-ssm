package com.rawchen.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class DateTimeUtil {

	public static String dateWord(Date from){
		long diff = new Date().getTime()/1000 - from.getTime()/1000;

		/* 如果是一天 */
		if (diff >= 0 && diff < 86400) {
			/* 如果是一小时 */
			if (diff < 3600) {
				/* 如果是一分钟 */
				if (diff < 60) {
					if (0 == diff) {
						return "刚刚";
					} else {
						if (diff == 1) {
							return "一秒前";
						} else {
							return diff + "秒前";
						}
					}
				}

				long min = diff / 60;
				if (min == 1) {
					return "一分钟前";
				} else {
					return min + "分钟前";
				}
			}

			long hour = diff / 3600;
			if (hour == 1) {
				return "一小时前";
			} else {
				return hour + "小时前";
			}
		}

		/* 如果是昨天 */
		if (diff > 0 && diff < 172800) {
			return "昨天 " + new SimpleDateFormat("HH:mm").format(from);
		}

		/* 如果是一个星期 */
		if (diff > 0 && diff < 604800) {
			long day = diff / 86400;
			if (day == 1) {
				return "一天前";
			} else {
				return day + "天前";
			}
		}

		/* 如果是同一年 */
		if (new SimpleDateFormat("yyyy").format(new Date()).equals(new SimpleDateFormat("yyyy").format(from))) {
			return new SimpleDateFormat("MM月dd日").format(from);
		}

		return new SimpleDateFormat("yyyy年MM月dd日").format(from);
	}

	public static String yesterdayDateConvertString() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		Date time = calendar.getTime();
		return new SimpleDateFormat("yyyyMMdd").format(time);
	}

	public static String todayDateConvertString() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	public static String calculateApartDayConvertString(int x) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -x);
		Date time = calendar.getTime();
		return new SimpleDateFormat("yyyyMMdd").format(time);
	}

	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Scanner scan = new Scanner(System.in);
		while (true) {
			if (scan.hasNextLine()) {
				String str = scan.nextLine();
				System.out.print(str+"   ");
				try {
					Date date= simpleDateFormat.parse(str);
					System.out.println(DateTimeUtil.dateWord(date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

