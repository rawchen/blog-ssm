package com.yoyling.service.impl;

import com.yoyling.domain.User;
import com.yoyling.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Override
	public User selectUserByNameAndPassword(User u) {
		return userMapper.selectUserByNameAndPassword(u);
	}
}
