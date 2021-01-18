package com.yoyling.service.impl;

import com.yoyling.domain.Comment;
import com.yoyling.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl implements CommentService {
	@Override
	public List<Comment> selectCommentListByContentId(int id) {
		return commentMapper.selectCommentListByContentId(id);
	}

	@Override
	public String selectCommentAuthorById(Integer coid) {
		return commentMapper.selectCommentAuthorById(coid);
	}

	@Override
	public int insert(Comment comment) {
		return commentMapper.insert(comment);
	}
}
