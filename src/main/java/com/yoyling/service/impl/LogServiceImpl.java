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
	public List<Integer> selectYesterdayPvUvVvIv() {
		List<Integer> y = new ArrayList<>();
		y.add(logMapper.selectYesterdayUv());
		y.add(logMapper.selectYesterdayPv());

		return y;
	}
}
