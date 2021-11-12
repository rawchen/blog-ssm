package com.rawchen.service;

import com.rawchen.domain.File;

import java.util.List;

public interface FileService {

	List<File> selectAllFile();

	List<File> selectFileListWithUid(int userId);

	int insert(File newFile);

	int deleteByPrimaryKey(int fid);

	File selectFileByFid(int fid);
}
