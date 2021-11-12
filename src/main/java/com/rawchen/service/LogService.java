package com.rawchen.service;

import com.rawchen.domain.Log;

import java.util.List;

public interface LogService {
	int insert(Log log);

	List<Integer> selectYesterdayPvUvIndexGuestbook();

	List<Integer> selectTodayPvUvIndexGuestbook();

	List<Integer> selectSevenDaysPv();

	List<Integer> selectSevenDaysUv();
}
