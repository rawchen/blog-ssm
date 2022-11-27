package com.rawchen.mapper;

import com.rawchen.domain.MyFile;

import java.util.List;

public interface FileMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(MyFile record);

    int insertSelective(MyFile record);

	MyFile selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(MyFile record);

    int updateByPrimaryKey(MyFile record);

	List<MyFile> selectAllFile();

	List<MyFile> selectFileListWithUid(int userId);
}