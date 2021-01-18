package com.yoyling.service.impl;

import com.yoyling.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 基础业务类，用来继承。
 */
@Service
public class BaseServiceImpl {

	@Resource
	protected ContentMapper contentMapper;

	@Resource
	protected OptionsMapper optionsMapper;

	@Resource
	protected CategoryMapper categoryMapper;

	@Resource
	protected TagMapper tagMapper;

	@Resource
	protected UserMapper userMapper;

	@Resource
	protected LogMapper logMapper;

	@Resource
	protected CommentMapper commentMapper;

}
