package kr.or.ddit.user.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service("userService")
public class UserService implements UserServiceI {
	
	@Resource(name = "userDao")
	private UserDao dao;


	@Override
	public UserVo selectUser(String userid) {
		// TODO Auto-generated method stub
		return dao.selectUser(userid);
	}

	

}
