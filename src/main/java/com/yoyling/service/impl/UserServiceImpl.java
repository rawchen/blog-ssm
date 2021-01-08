package com.yoyling.service.impl;

import com.yoyling.domain.User;
import com.yoyling.service.UserService;
import com.yoyling.utils.StringUtil;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Override
	public User selectUserByNameAndPassword(User u) {
		String pwd = u.getPassword();
		u.setPassword(StringUtil.passwordToMd5(pwd));
		return userMapper.selectUserByNameAndPassword(u);
	}
}
