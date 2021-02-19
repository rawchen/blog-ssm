package com.yoyling.service.impl;

import com.yoyling.domain.File;
import com.yoyling.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fileService")
public class FileServiceImpl extends BaseServiceImpl implements FileService {
	@Override
	public List<File> selectAllFile() {
		return fileMapper.selectAllFile();
	}
}
