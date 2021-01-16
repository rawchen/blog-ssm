package com.yoyling.mapper;

import com.yoyling.domain.Comment;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer coid);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer coid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
}