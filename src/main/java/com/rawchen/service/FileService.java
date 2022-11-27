package com.rawchen.service;

import com.rawchen.domain.MyFile;

import java.util.List;

public interface FileService {

	List<MyFile> selectAllFile();

	List<MyFile> selectFileListWithUid(int userId);

	int insert(MyFile newFile);

	int deleteByPrimaryKey(int fid);

	MyFile selectFileByFid(int fid);
}
