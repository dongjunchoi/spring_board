package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.BoardInfoVo;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.model.CommentVo;
import kr.or.ddit.board.model.FileVo;
import kr.or.ddit.board.repository.BoardDao;
import kr.or.ddit.common.model.PageVo;

@Service("boardService")
public class BoardService implements BoardServiceI {

	
	@Resource(name = "boardDao")
	private BoardDao dao;
	
	@Override
	public List<BoardInfoVo> selectAllBoardInfo() {
		// TODO Auto-generated method stub
		return dao.selectAllBoardInfo();
	}

	@Override
	public int insertBoardInfo(BoardInfoVo vo) {
		// TODO Auto-generated method stub
		return dao.insertBoardInfo(vo);
	}

	@Override
	public int modifyBoardInfo(BoardInfoVo vo) {
		// TODO Auto-generated method stub
		return dao.modifyBoardInfo(vo);
	}

	@Override
	public Map<String,Object> searchPagingBoard(PageVo vo) {
		
		Map<String,Object> map = new HashMap<>();
		
		List<BoardVo> boardList = dao.searchPagingBoard(vo);
		int boardCnt = dao.allBoardCnt(vo);
		
		map.put("boardList", boardList);
		map.put("boardCnt", boardCnt);
		
		return map;
	}

	@Override
	public int registBoard(BoardVo vo) {
		// TODO Auto-generated method stub
		return dao.registBoard(vo);
	}

	@Override
	public BoardVo selectBoardPost(BoardVo vo) {
		// TODO Auto-generated method stub
		return dao.selectBoardPost(vo);
	}

	@Override
	public int registComentBoard(BoardVo vo) {
		// TODO Auto-generated method stub
		return dao.registComentBoard(vo);
	}

	@Override
	public int insertFile(FileVo vo) {
		// TODO Auto-generated method stub
		return dao.insertFile(vo);
	}

	@Override
	public int selectMaxPostNo() {
		// TODO Auto-generated method stub
		return dao.selectMaxPostNo();
	}

	@Override
	public List<FileVo> selectFileList(FileVo vo) {
		// TODO Auto-generated method stub
		return dao.selectFileList(vo);
	}

	@Override
	public FileVo selectFile(FileVo vo) {
		// TODO Auto-generated method stub
		return dao.selectFile(vo);
	}

	@Override
	public int deleteBoardPost(BoardVo vo) {
		// TODO Auto-generated method stub
		return dao.deleteBoardPost(vo);
	}

	@Override
	public int registComment(CommentVo vo) {
		// TODO Auto-generated method stub
		return dao.registComment(vo);
	}

	@Override
	public List<CommentVo> selectBoardComment(CommentVo vo) {
		// TODO Auto-generated method stub
		return dao.selectBoardComment(vo);
	}

	@Override
	public int deleteComment(CommentVo vo) {
		// TODO Auto-generated method stub
		return dao.deleteComment(vo);
	}

	@Override
	public int deleteFile(FileVo vo) {
		// TODO Auto-generated method stub
		return dao.deleteFile(vo);
	}

	@Override
	public int modifyBoard(BoardVo vo) {
		// TODO Auto-generated method stub
		return dao.modifyBoard(vo);
	}

	@Override
	public BoardInfoVo selectBoardInfo(int bor_no) {
		// TODO Auto-generated method stub
		return dao.selectBoardInfo(bor_no);
	}

}
