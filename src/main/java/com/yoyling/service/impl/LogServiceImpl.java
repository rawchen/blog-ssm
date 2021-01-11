package com.yoyling.service.impl;

import com.yoyling.domain.Log;
import com.yoyling.service.LogService;
import org.springframework.stereotype.Service;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl implements LogService {
	@Override
	public int insert(Log log) {
		return logMapper.insert(log);
	}
}
