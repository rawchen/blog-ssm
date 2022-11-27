package com.rawchen.service.impl;

import com.rawchen.domain.MyFile;
import com.rawchen.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fileService")
public class FileServiceImpl extends BaseServiceImpl implements FileService {
	@Override
	public List<MyFile> selectAllFile() {
		return fileMapper.selectAllFile();
	}

	@Override
	public List<MyFile> selectFileListWithUid(int userId) {
		return fileMapper.selectFileListWithUid(userId);
	}

	@Override
	public int insert(MyFile newFile) {
		return fileMapper.insert(newFile);
	}

	@Override
	public int deleteByPrimaryKey(int fid) {
		return fileMapper.deleteByPrimaryKey(fid);
	}

	@Override
	public MyFile selectFileByFid(int fid) {
		return fileMapper.selectByPrimaryKey(fid);
	}
}
