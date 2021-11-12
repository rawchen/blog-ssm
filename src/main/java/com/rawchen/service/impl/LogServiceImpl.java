package com.rawchen.service.impl;

import com.rawchen.domain.Log;
import com.rawchen.domain.dto.SevenDayLog;
import com.rawchen.service.LogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl implements LogService {
	@Override
	public int insert(Log log) {
		return logMapper.insert(log);
	}

	@Override
	public List<Integer> selectYesterdayPvUvIndexGuestbook() {
		List<Integer> y = new ArrayList<>();
		y.add(logMapper.selectYesterdayPv());
		y.add(logMapper.selectYesterdayUv());
		y.add(logMapper.selectYesterdayAccessByApi("/index"));
		y.add(logMapper.selectYesterdayAccessByApi("/guestbook"));
		y.add(logMapper.selectYesterdayAccessLikeApi("/articles/"));
		return y;
	}

	@Override
	public List<Integer> selectTodayPvUvIndexGuestbook() {
		List<Integer> t = new ArrayList<>();
		t.add(logMapper.selectTodayPv());
		t.add(logMapper.selectTodayUv());
		t.add(logMapper.selectTodayAccessByApi("/index"));
		t.add(logMapper.selectTodayAccessByApi("/guestbook"));
		t.add(logMapper.selectTodayAccessLikeApi("/articles/"));
		return t;
	}

	@Override
	public List<Integer> selectSevenDaysPv() {
		List<Integer> l = new ArrayList<>();
		List<SevenDayLog> logs = logMapper.selectSevenDaysPv();
		for (SevenDayLog log : logs) {
			l.add(log.getAccessValue());
		}
		return l;
	}

	@Override
	public List<Integer> selectSevenDaysUv() {
		List<Integer> l = new ArrayList<>();
		List<SevenDayLog> logs = logMapper.selectSevenDaysUv();
		for (SevenDayLog log : logs) {
			l.add(log.getAccessValue());
		}
		return l;
	}
}