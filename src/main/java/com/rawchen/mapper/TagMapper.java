package com.rawchen.mapper;

import com.rawchen.domain.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

	List<Tag> selectAllTag();

	List<Tag> fuzzyQueryTag(String tagName);

	int findTagIdByName(String s);

	Tag findTagById(int tagId);

	int selectCountOfTag();

	int updateTagCount(@Param("tid") int tagId,@Param("count") int count);

	int updateTagName(Tag tag);

}