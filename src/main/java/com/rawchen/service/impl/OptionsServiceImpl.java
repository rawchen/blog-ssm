package com.rawchen.service.impl;

import com.rawchen.domain.Options;
import com.rawchen.service.OptionsService;
import org.springframework.stereotype.Service;

@Service("optionsService")
public class OptionsServiceImpl extends BaseServiceImpl implements OptionsService {
	@Override
	public String selectValueByName(String name) {
		return optionsMapper.selectValueByName(name);
	}

	@Override
	public int updateOptions(Options options) {
		return optionsMapper.updateByPrimaryKeyWithBLOBs(options);
	}
}
