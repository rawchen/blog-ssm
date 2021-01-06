package com.yoyling.service;

import com.yoyling.domain.Tag;

import java.util.List;

public interface TagService {

	List<Tag> selectAllTag();

	List<Tag> fuzzyQueryTag(String tagName);

	int findTagIdByName(String s);

	int insert(Tag tag);
}
