package com.yoyling.mapper;

import com.yoyling.domain.Tag;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

	List<Tag> selectAllTag();
}