package com.yoyling.service;

import com.yoyling.domain.Options;

public interface OptionsService {

	String selectValueByName(String name);

	int updateOptions(Options options);
}
