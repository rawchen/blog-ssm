package com.yoyling.service.impl;

import com.yoyling.domain.Contents;
import com.yoyling.service.ContentsService;
import org.springframework.stereotype.Service;

@Service("contentsService")
public class ContentsServiceImpl extends BaseServiceImpl implements ContentsService {

	@Override
	public Contents selectByPrimaryKey(Integer cid) {
		return contentsMapper.selectByPrimaryKey(cid);
	}
}
