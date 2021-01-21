package com.yoyling.service.impl;

import com.yoyling.domain.Options;
import com.yoyling.service.OptionsService;
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
