package com.rawchen.service.impl;

import com.rawchen.domain.File;
import com.rawchen.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fileService")
public class FileServiceImpl extends BaseServiceImpl implements FileService {
	@Override
	public List<File> selectAllFile() {
		return fileMapper.selectAllFile();
	}

	@Override
	public List<File> selectFileListWithUid(int userId) {
		return fileMapper.selectFileListWithUid(userId);
	}

	@Override
	public int insert(File newFile) {
		return fileMapper.insert(newFile);
	}

	@Override
	public int deleteByPrimaryKey(int fid) {
		return fileMapper.deleteByPrimaryKey(fid);
	}

	@Override
	public File selectFileByFid(int fid) {
		return fileMapper.selectByPrimaryKey(fid);
	}
}
