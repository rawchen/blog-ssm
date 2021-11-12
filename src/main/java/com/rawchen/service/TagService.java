package com.rawchen.service;

import com.rawchen.domain.Tag;

import java.util.List;

public interface TagService {

	List<Tag> selectAllTag();

	List<Tag> fuzzyQueryTag(String tagName);

	int findTagIdByName(String s);

	int insert(Tag tag);

	Tag findTagById(int tagId);

	int selectCountOfTag();

	int updateTagCount(int tagId,int count);

	int updateTagName(Tag tag);

	int deleteTag(int tid);
}
