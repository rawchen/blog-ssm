package com.yoyling.mapper;

import com.yoyling.domain.Options;

public interface OptionsMapper {

    String selectValueByName(String name);

    int deleteByPrimaryKey(String name);

    int insert(Options record);

    int insertSelective(Options record);

    Options selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(Options record);

    int updateByPrimaryKeyWithBLOBs(Options record);
}