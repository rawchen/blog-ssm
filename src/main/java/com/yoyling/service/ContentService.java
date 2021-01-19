package com.yoyling.service;

import com.yoyling.domain.Content;

import java.util.List;

public interface ContentService {

	Content selectByPrimaryKey(Integer cid);

	List<Content> selectAllContent();

	int selectNumberOfArticles();

	List<Content> selectRecommendContent();

	int insert(Content content);

	Content findContentBySlugName(String slugName);

	int selectContentCountByCgid(Integer cgid);

	List<Content> selectContentListByCgid(int cgid);

	List<Content> selectContentListByTid(int tid);

	int updateContentViewsBySlug(String slugName);

	Integer selectContentauthorIdBycontentId(int contentId);

	int selectCommentCountByCid(Integer cid);
}
