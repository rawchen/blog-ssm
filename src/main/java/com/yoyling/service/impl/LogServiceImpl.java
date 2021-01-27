package com.yoyling.service.impl;

import com.yoyling.domain.Log;
import com.yoyling.service.LogService;
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
	public List<Integer> selectLastWeekPvList() {
		List<Integer> l = new ArrayList<>();
		int r7 = logMapper.selectApartDaysPv(7);
		int r6 = logMapper.selectApartDaysPv(6);
		int r5 = logMapper.selectApartDaysPv(5);
		int r4 = logMapper.selectApartDaysPv(4);
		int r3 = logMapper.selectApartDaysPv(3);
		int r2 = logMapper.selectApartDaysPv(2);
		int r1 = logMapper.selectApartDaysPv(1);
		l.add(r7);
		l.add(r6);
		l.add(r5);
		l.add(r4);
		l.add(r3);
		l.add(r2);
		l.add(r1);
		return l;
	}

	@Override
	public List<Integer> selectLastWeekUvList() {
		List<Integer> l = new ArrayList<>();
		int r7 = logMapper.selectApartDaysUv(7);
		int r6 = logMapper.selectApartDaysUv(6);
		int r5 = logMapper.selectApartDaysUv(5);
		int r4 = logMapper.selectApartDaysUv(4);
		int r3 = logMapper.selectApartDaysUv(3);
		int r2 = logMapper.selectApartDaysUv(2);
		int r1 = logMapper.selectApartDaysUv(1);
		l.add(r7);
		l.add(r6);
		l.add(r5);
		l.add(r4);
		l.add(r3);
		l.add(r2);
		l.add(r1);
		return l;
	}
}
