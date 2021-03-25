package kr.or.ddit.board.repository;

import java.util.List;

import javax.annotation.Resource;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardInfoVo;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.model.CommentVo;
import kr.or.ddit.board.model.FileVo;
import kr.or.ddit.common.model.PageVo;

@Repository("boardDao")
public class BoardDao implements BoardDaoI{

	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	
	@Override
	public List<BoardInfoVo> selectAllBoardInfo() {
	
		
		return template.selectList("boardinfo.selectAllBoardInfo");
	}

	@Override
	public int insertBoardInfo(BoardInfoVo vo) {

		
		return template.insert("boardinfo.insertBoardInfo", vo);
	}

	@Override
	public int modifyBoardInfo(BoardInfoVo vo) {
	
		
		return template.update("boardinfo.modifyBoardInfo", vo);
	}

	@Override
	public List<BoardVo> searchPagingBoard(PageVo vo) {

		
		return template.selectList("board.searchPagingBoard",vo);
	}

	@Override
	public int allBoardCnt(PageVo vo) {

		
		return template.selectOne("board.allBoardCnt",vo);
	}

	@Override
	public int registBoard(BoardVo vo) {

		
		return template.insert("board.registBoard", vo);
	}

	@Override
	public BoardVo selectBoardPost(BoardVo vo) {

		
		return template.selectOne("board.selectBoardPost", vo);
	}

	@Override
	public int registComentBoard(BoardVo vo) {

		
		return template.insert("board.registComentBoard", vo);
	}

	@Override
	public int insertFile(FileVo vo) {

		
		return template.insert("file.insertFile", vo);
	}

	@Override
	public int selectMaxPostNo() {

		return template.selectOne("board.selectMaxPostNo");
	}

	@Override
	public List<FileVo> selectFileList(FileVo vo) {

		
		return template.selectList("file.selectFileList",vo);
	}

	@Override
	public FileVo selectFile(FileVo vo) {

		return template.selectOne("file.selectFile", vo);
	}

	@Override
	public int deleteBoardPost(BoardVo vo) {

		
		return template.delete("board.deleteBoardPost", vo);
	}

	@Override
	public int registComment(CommentVo vo) {
	
		
		return template.insert("comment.registComment", vo);
	}

	@Override
	public List<CommentVo> selectBoardComment(CommentVo vo) {

		
		return template.selectList("comment.selectBoardComment", vo);
	}

	@Override
	public int deleteComment(CommentVo vo) {
	
		
		return template.delete("comment.deleteComment", vo);
	}

	@Override
	public int deleteFile(FileVo vo) {
	
		
		return template.delete("file.deleteFile", vo);
	}

	@Override
	public int modifyBoard(BoardVo vo) {

		
		return template.update("board.modifyBoard", vo);
	}

	@Override
	public BoardInfoVo selectBoardInfo(int bor_no) {
	
		
		return template.selectOne("boardinfo.selectBoardInfo", bor_no);
	}
	
	
	
	
	
	
	

}
