package com.rawchen.mapper;

import com.rawchen.domain.Log;
import com.rawchen.domain.dto.SevenDayLog;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKeyWithBLOBs(Log record);

    int updateByPrimaryKey(Log record);

	Integer selectYesterdayUv();

    Integer selectYesterdayPv();

    Integer selectTodayUv();

    Integer selectTodayPv();

    Integer selectYesterdayAccessByApi(String s);

    Integer selectTodayAccessByApi(String s);

    Integer selectYesterdayAccessLikeApi(String s);

    Integer selectTodayAccessLikeApi(String s);

    List<SevenDayLog> selectSevenDaysPv();

    List<SevenDayLog> selectSevenDaysUv();
}