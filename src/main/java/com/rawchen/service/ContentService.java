package com.rawchen.service;

import com.rawchen.domain.Content;

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

	Integer selectContentAuthorIdBycontentId(int contentId);

	int selectCommentCountByCid(Integer cid);

	int deleteByPrimaryKey(int cid);

	int deleteSelectContent(String[] sids);

	int selectContentOrderByCid(int parseInt);

	int changeContentOrderByCid(int parseInt);

	int updateContentCgidDefaultByCid(Integer cid);

	int updateContentTagListByCid(Content content);

	String selectSlugByCid(Integer cid);

	List<Content> selectContentListByLike(String searchWord);

	List<Content> selectPostSizeContentWithHot(int postsListSize);

	List<Content> selectContentListWithUid(int userId);

	int updateContent(Content content);

	Integer selectContentViewsBycontentId(int parseInt);
}
