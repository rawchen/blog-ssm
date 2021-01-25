package com.yoyling.service;

import com.yoyling.domain.Log;

import java.util.List;

public interface LogService {
	int insert(Log log);

	List<Integer> selectYesterdayPvUvVvIv();
}
