package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.board.model.BoardInfoVo;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDaoI {
	
	
	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);
	
}
