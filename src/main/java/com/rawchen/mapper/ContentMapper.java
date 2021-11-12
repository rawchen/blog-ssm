package com.rawchen.mapper;

import com.rawchen.domain.Content;

import java.util.List;

public interface ContentMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKeyWithBLOBs(Content record);

    int updateByPrimaryKey(Content record);

	List<Content> selectAllContent();

	int selectNumberOfArticles();

	List<Content> selectRecommendContent();

	Content findContentBySlugName(String slugName);

	int selectContentCountByCgid(Integer cgid);

	List<Content> selectContentListByCgid(int cgid);

	List<Content> selectContentListByTid(String t);

	int updateContentViewsBySlug(String slugName);

	int selectContentAuthorIdBycontentId(int contentId);

	int selectCommentCountByCid(Integer cid);

	int selectContentOrderByCid(int parseInt);

	int changeContentOrderByCid(int parseInt);

	int updateContentCgidDefaultByCid(Integer cid);

	int updateContentTagListByCid(Content content);

	String selectSlugByCid(Integer cid);

	List<Content> selectContentListByLike(String searchWord);

	List<Content> selectPostSizeContentWithHot(int postsListSize);

	int deleteByAuthorId(int parseInt);

	List<Content> selectContentListWithUid(int userId);

	Integer selectContentViewsBycontentId(int parseInt);
}