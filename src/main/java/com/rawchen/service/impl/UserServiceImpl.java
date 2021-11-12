package com.rawchen.service.impl;

import com.rawchen.domain.User;
import com.rawchen.service.UserService;
import com.rawchen.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Override
	public User selectUserByNameAndPassword(User u) {
		String pwd = u.getPassword();
		u.setPassword(StringUtil.passwordToMd5(pwd));
		return userMapper.selectUserByNameAndPassword(u);
	}

	@Override
	public int insert(User u) {
		return userMapper.insert(u);
	}

	@Override
	public User findUserByUserName(String userName) {
		return userMapper.findUserByUserName(userName);
	}

	@Override
	public int updateScreenNameAndMailAndUrl(User u) {
		return userMapper.updateScreenNameAndMailAndUrl(u);
	}

	@Override
	public int updatePassword(User u) {
		return userMapper.updatePassword(u);
	}

	@Override
	public List<User> selectAllUser() {
		return userMapper.selectAllUser();
	}

	@Override
	public int deleteByPrimaryKey(int uid) {
		int a = userMapper.deleteByPrimaryKey(uid);
		if (a == 1) {
			int b = contentMapper.deleteByAuthorId(uid);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteSelectUser(String[] uids) {
		try {
			if (uids != null && uids.length > 0) {
				for (String uid: uids) {
					int a = userMapper.deleteByPrimaryKey(Integer.parseInt(uid));
					//删除所属的帖子
					int b = contentMapper.deleteByAuthorId(Integer.parseInt(uid));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}
