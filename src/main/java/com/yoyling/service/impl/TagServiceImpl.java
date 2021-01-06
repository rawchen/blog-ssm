package com.yoyling.service.impl;

import com.yoyling.domain.Tag;
import com.yoyling.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagService")
public class TagServiceImpl extends BaseServiceImpl implements TagService {
	@Override
	public List<Tag> selectAllTag() {
		return tagMapper.selectAllTag();
	}

	@Override
	public List<Tag> fuzzyQueryTag(String tagName) {
		return tagMapper.fuzzyQueryTag(tagName);
	}

	@Override
	public int findTagIdByName(String s) {
		return tagMapper.findTagIdByName(s);
	}

	@Override
	public int insert(Tag tag) {
		return tagMapper.insert(tag);
	}
}
