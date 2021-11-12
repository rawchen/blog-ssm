package com.rawchen.mapper;

import com.rawchen.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByNameAndPassword(User u);

    User findUserByUserName(String userName);

	int updateScreenNameAndMailAndUrl(User u);

    int updatePassword(User u);

    List<User> selectAllUser();
}