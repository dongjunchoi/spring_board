package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardInfoVo;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.model.CommentVo;
import kr.or.ddit.board.model.FileVo;
import kr.or.ddit.common.model.PageVo;

public interface BoardServiceI {
	
	List<BoardInfoVo> selectAllBoardInfo();
	
	int insertBoardInfo(BoardInfoVo vo);
	
	int modifyBoardInfo(BoardInfoVo vo);
	
	Map<String,Object> searchPagingBoard(PageVo vo);
	
	int registBoard(BoardVo vo);
	
	BoardVo selectBoardPost(BoardVo vo);
	
	int registComentBoard(BoardVo vo);
	
	int insertFile(FileVo vo);
	
	int selectMaxPostNo();
	
	List<FileVo> selectFileList(FileVo vo);
	
	FileVo selectFile(FileVo vo);
	
	int deleteBoardPost(BoardVo vo);
	
	int registComment(CommentVo vo);
	
	List<CommentVo> selectBoardComment(CommentVo vo);
	
	int deleteComment(CommentVo vo);
	
	int deleteFile(FileVo vo);
	
	int modifyBoard(BoardVo vo);
	
	BoardInfoVo selectBoardInfo(int bor_no);
	
}
