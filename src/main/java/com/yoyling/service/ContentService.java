package com.yoyling.service;

import com.yoyling.domain.Content;

import java.util.List;

public interface ContentService {
	Content selectByPrimaryKey(Integer cid);

	List<Content> selectAllContent();

	int selectNumberOfArticles();

	List<Content> selectRecommendContent();
}
