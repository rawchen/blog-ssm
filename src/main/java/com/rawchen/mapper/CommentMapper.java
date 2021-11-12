package com.rawchen.mapper;

import com.rawchen.domain.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer coid);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer coid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectCommentListByContentId(int cid);

    String selectCommentAuthorById(Integer coid);

	List<Comment> selectAllCommment();

	int deleteByCid(int parseInt);

	List<Comment> selectCommentListWithUserId(int userId);
}