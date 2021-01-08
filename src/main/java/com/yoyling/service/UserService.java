package com.yoyling.service;

import com.yoyling.domain.User;

public interface UserService {
	User selectUserByNameAndPassword(User u);
}
