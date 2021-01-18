package com.yoyling.service;

import com.yoyling.domain.User;

public interface UserService {
	User selectUserByNameAndPassword(User u);

	int insert(User u);

	User findUserByUserName(String userName);
}
