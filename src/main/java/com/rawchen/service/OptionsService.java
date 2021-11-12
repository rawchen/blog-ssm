package com.rawchen.service;

import com.rawchen.domain.Options;

public interface OptionsService {

	String selectValueByName(String name);

	int updateOptions(Options options);
}
