package com.rawchen.service.impl;

import com.rawchen.domain.Content;
import com.rawchen.service.ContentService;
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

	@Override
	public List<Content> selectContentListByTid(int tid) {
		String t = String.valueOf(tid);
		return contentMapper.selectContentListByTid(t);
	}

	@Override
	public int updateContentViewsBySlug(String slugName) {
		return contentMapper.updateContentViewsBySlug(slugName);
	}

	@Override
	public Integer selectContentAuthorIdBycontentId(int contentId) {
		return contentMapper.selectContentAuthorIdBycontentId(contentId);
	}

	@Override
	public int selectCommentCountByCid(Integer cid) {
		return contentMapper.selectCommentCountByCid(cid);
	}

	@Override
	public int deleteByPrimaryKey(int cid) {
		int a = contentMapper.deleteByPrimaryKey(cid);
		if (a == 1) {
			int b = commentMapper.deleteByCid(cid);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteSelectContent(String[] sids) {
		try {
			if (sids != null && sids.length > 0) {
				for (String sid: sids) {
					int a = contentMapper.deleteByPrimaryKey(Integer.parseInt(sid));
					//删除所属的评论
					int b = commentMapper.deleteByCid(Integer.parseInt(sid));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int selectContentOrderByCid(int parseInt) {
		return contentMapper.selectContentOrderByCid(parseInt);
	}

	@Override
	public int changeContentOrderByCid(int parseInt) {
		return contentMapper.changeContentOrderByCid(parseInt);
	}

	@Override
	public int updateContentCgidDefaultByCid(Integer cid) {
		return contentMapper.updateContentCgidDefaultByCid(cid);
	}

	@Override
	public int updateContentTagListByCid(Content content) {
		return contentMapper.updateContentTagListByCid(content);
	}

	@Override
	public String selectSlugByCid(Integer cid) {
		return contentMapper.selectSlugByCid(cid);
	}

	@Override
	public List<Content> selectContentListByLike(String searchWord) {
		return contentMapper.selectContentListByLike(searchWord);
	}

	@Override
	public List<Content> selectPostSizeContentWithHot(int postsListSize) {
		return contentMapper.selectPostSizeContentWithHot(postsListSize);
	}

	@Override
	public List<Content> selectContentListWithUid(int userId) {
		return contentMapper.selectContentListWithUid(userId);
	}

	@Override
	public int updateContent(Content content) {
		return contentMapper.updateByPrimaryKeyWithBLOBs(content);
	}

	@Override
	public Integer selectContentViewsBycontentId(int parseInt) {
		return contentMapper.selectContentViewsBycontentId(parseInt);
	}
}
