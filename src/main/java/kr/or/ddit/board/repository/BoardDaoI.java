package kr.or.ddit.board.repository;

import java.util.List;

import kr.or.ddit.board.model.BoardInfoVo;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.model.CommentVo;
import kr.or.ddit.board.model.FileVo;
import kr.or.ddit.common.model.PageVo;

public interface BoardDaoI {
	
	List<BoardInfoVo> selectAllBoardInfo();
	
	int insertBoardInfo(BoardInfoVo vo);

	int modifyBoardInfo(BoardInfoVo vo);
	
	List<BoardVo> searchPagingBoard(PageVo vo);
	
	int allBoardCnt(PageVo vo);
	
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
