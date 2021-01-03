package com.yoyling.mapper;

import com.yoyling.domain.Content;

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
}