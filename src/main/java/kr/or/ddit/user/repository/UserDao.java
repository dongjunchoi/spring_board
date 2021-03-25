package kr.or.ddit.user.repository;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;


@Repository("userDao")
public class UserDao implements UserDaoI {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;

	@Override
	public UserVo selectUser(String userid) {
		
		return template.selectOne("users.selectUser",userid);
	}

	
}
