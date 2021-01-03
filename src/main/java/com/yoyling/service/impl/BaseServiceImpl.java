package com.yoyling.service.impl;

import com.yoyling.mapper.CategoryMapper;
import com.yoyling.mapper.ContentMapper;
import com.yoyling.mapper.OptionsMapper;
import com.yoyling.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基础业务类，用来继承
 */
@Service
public class BaseServiceImpl {

	@Autowired
	protected ContentMapper contentMapper;

	@Autowired
	protected OptionsMapper optionsMapper;

	@Autowired
	protected CategoryMapper categoryMapper;

	@Autowired
	protected TagMapper tagMapper;


}
