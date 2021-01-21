package com.yoyling.service;

import com.yoyling.domain.Comment;

import java.util.List;

public interface CommentService {
	List<Comment> selectCommentListByContentId(int id);

	String selectCommentAuthorById(Integer coid);

	int insert(Comment comment);

	List<Comment> selectAllComment();

	int deleteByPrimaryKey(int coid);

	int deleteSelectComment(String[] coids);
}
