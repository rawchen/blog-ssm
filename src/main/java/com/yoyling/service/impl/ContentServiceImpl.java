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

	@Override
	public int selectNumberOfArticles() {
		int num = contentMapper.selectNumberOfArticles();
		if (num!=0) {
			return num;
		}else {
			return 0;
		}
	}

	@Override
	public List<Content> selectRecommendContent() {
		return contentMapper.selectRecommendContent();
	}

	@Override
	public int insert(Content content) {
		return contentMapper.insert(content);
	}

	@Override
	public Content findContentBySlugName(String slugName) {
		return contentMapper.findContentBySlugName(slugName);
	}

	@Override
	public int selectContentCountByCgid(Integer cgid) {
		int num = contentMapper.selectContentCountByCgid(cgid);
		if (num!=0) {
			return num;
		}else {
			return 0;
		}
	}

	@Override
	public List<Content> selectContentListByCgid(int cgid) {
		return contentMapper.selectContentListByCgid(cgid);
	}
}
