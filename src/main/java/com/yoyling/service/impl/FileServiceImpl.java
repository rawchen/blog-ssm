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

	@Override
	public int deleteSelectFile(String[] fids) {
		try {
			if (fids != null && fids.length > 0) {
				for (String uid: fids) {
					int a = userMapper.deleteByPrimaryKey(Integer.parseInt(uid));
					//删除所属的帖子
					int b = contentMapper.deleteByAuthorId(Integer.parseInt(uid));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}
