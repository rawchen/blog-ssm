package com.yoyling.service.impl;

import com.yoyling.domain.Content;
import com.yoyling.service.ContentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contentService")
public class ContentServiceImpl extends BaseServiceImpl implements ContentService {

	@Override
	public Content selectByPrimaryKey(Integer cid) {
		return contentMapper.selectByPrimaryKey(cid);
	}

	@Override
	public List<Content> selectAllContent() {
		return contentMapper.selectAllContent();
	}
}
