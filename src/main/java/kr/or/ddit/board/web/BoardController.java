package kr.or.ddit.board.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.model.BoardInfoVo;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.model.CommentVo;
import kr.or.ddit.board.model.FileVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.util.FileUtil;

@RequestMapping("board")
@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	
	@Resource(name = "boardService")
	private BoardService boardService;
	
	
	@RequestMapping("mainController")
	public String mainController(Model model) {
		
		model.addAttribute("boardInfoList", boardService.selectAllBoardInfo());
		
		return "board/main";
	}
	
	@RequestMapping(path = "registBoardInfo",method = RequestMethod.GET)
	public String registBoardInfo(Model model) {
		
		model.addAttribute("boardInfoList", boardService.selectAllBoardInfo());
		
		return "board/boardInfoView";
	}
	
	
	@RequestMapping(path = "registBoardInfo",method = RequestMethod.POST)
	public String registBoardInfo(String boardName, String check, Model model) {
		
		int flag = 0;
		
		if(check.equals("t")) {
			flag = 1;
		}
		
		BoardInfoVo boardInfo = new BoardInfoVo();
		
		boardInfo.setBor_act(flag);
		boardInfo.setBor_name(boardName);
		
		
		int insertCnt = 0;
		
		try {
			insertCnt = boardService.insertBoardInfo(boardInfo);
		} catch (Exception e) {
			insertCnt = 0;
		}
	
		
		return "redirect:/board/registBoardInfo";
	}
	@RequestMapping(path = "modifyBoardInfo",method = RequestMethod.POST)
	public synchronized String modifyBoardInfo(String boardNo, String check, Model model) {
		
		int intBoardNo = 0;
		int intCheck = 0;
		
		if(!"".equals(boardNo) && boardNo != null) {
			intBoardNo = Integer.parseInt(boardNo);
		}
		
		if(check.equals("t")) {
			intCheck = 1;
		}
		
		BoardInfoVo boardInfo = new BoardInfoVo();
		boardInfo.setBor_no(intBoardNo);
		boardInfo.setBor_act(intCheck);
		
		int updateCnt = 0;
		
		try {
			updateCnt = boardService.modifyBoardInfo(boardInfo);
		} catch (Exception e) {
			e.printStackTrace();
			updateCnt = 0;
		}
		
		return "redirect:/board/registBoardInfo";
	}
	
	
	@RequestMapping(path = "boardView",method = RequestMethod.GET)
	public String boardView(String boardNo, String page, String pageSize, Model model) {

		int pageNo = page == null ?  1 : Integer.parseInt(page);
		int pageSizeNo = pageSize==null ? 10:Integer.parseInt(pageSize); 
		
		int intBoardNo = 0;
		
		if(boardNo!=null) {
			intBoardNo = Integer.parseInt(boardNo);
		}
		
		PageVo pageVo = new PageVo(pageNo, pageSizeNo, intBoardNo);
		
		
		Map<String, Object> map = boardService.searchPagingBoard(pageVo);
		
		List<BoardVo> boardList = (List<BoardVo>)map.get("boardList");
		int boardCnt = (int)map.get("boardCnt");
		int pagination = (int)Math.ceil((double)boardCnt/pageSizeNo);
		
		List<BoardInfoVo> boardInfoList = boardService.selectAllBoardInfo();
		
		BoardInfoVo boardInfo = boardService.selectBoardInfo(intBoardNo);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardInfo", boardInfo);
		model.addAttribute("boardInfoList", boardInfoList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("boardNo", intBoardNo);

		return "board/boardListView";
	}
	
	@RequestMapping(path = "boardPostView",method = RequestMethod.GET)
	public String boardPostView(String boardNo, String postNo, String userId, Model model) {
		
		int boardno = Integer.parseInt(boardNo);
		int postno = Integer.parseInt(postNo);
		
		BoardVo boardVo = new BoardVo();
		boardVo.setBor_no(boardno);
		boardVo.setPost_no(postno);
		boardVo.setUser_id(userId);
	
		FileVo fileVo = new FileVo();
		fileVo.setBor_no(boardno);
		fileVo.setPost_no(postno);
		fileVo.setUser_id(userId);
		
		CommentVo commentVo = new CommentVo();
		commentVo.setBor_no(boardno);
		commentVo.setPost_no(postno);
		commentVo.setUser_id(userId);
		
		BoardVo postVo = boardService.selectBoardPost(boardVo);
	
		List<BoardInfoVo> boardInfoList = boardService.selectAllBoardInfo();
		
		List<FileVo> fileList = boardService.selectFileList(fileVo);
		
		List<CommentVo> commentList = boardService.selectBoardComment(commentVo);
		
		model.addAttribute("commentList", commentList);
		model.addAttribute("boardInfoList", boardInfoList);
		model.addAttribute("postVo", postVo);
		model.addAttribute("fileList", fileList);
		
		return "board/boardPostView";
	}
	
	
	@RequestMapping(path = "registBoard",method = RequestMethod.GET)
	public String registBoard(String boardNo, Model model) {
		
		
		
		
		List<BoardInfoVo> boardInfoList = boardService.selectAllBoardInfo();
		
		model.addAttribute("boardInfoList", boardInfoList);
		model.addAttribute("boardNo", boardNo);
		
		
		
		return "board/registBoard";
	}
	
	@RequestMapping(path = "registBoard",method = RequestMethod.POST)
	public synchronized String registBoard(String boardNo, String userId,String title, String cont,  Model model, RedirectAttributes ra, MultipartHttpServletRequest fileName) {
		
		List<MultipartFile> files = fileName.getFiles("fileName");
		
		int boardno = Integer.parseInt(boardNo);

		BoardVo boardVo = new BoardVo();
		boardVo.setBor_no(boardno);
		boardVo.setUser_id(userId);
		boardVo.setTitle(title);
		boardVo.setCont(cont);
		String filename = "";
		int insertCnt = 0;
		
		try {
			insertCnt = boardService.registBoard(boardVo);
		} catch (Exception e) {
			e.printStackTrace();
			insertCnt = 0;
		}
		
		if(insertCnt == 1) {
			int maxPostNo = boardService.selectMaxPostNo();
			FileVo fileVo = new FileVo();
			fileVo.setBor_no(boardno);
			fileVo.setPost_no(maxPostNo);
			fileVo.setUser_id(userId);
			
			if(files!=null) {
				for(MultipartFile profile : files) {
					if(!("".equals(profile.getOriginalFilename()))) {
						try {
							String uploadPath = "d:" + File.separator + "uploadFile";
							
							File uploadDir = new File(uploadPath);
							
							if(!uploadDir.exists()) {
								uploadDir.mkdirs();
							}
							String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
							String realfilename = "d:/uploadFile/" + UUID.randomUUID().toString()+fileExtension;
							filename = profile.getOriginalFilename();
							
							profile.transferTo(new File(realfilename));
							
							fileVo.setFile_nm(filename);
							fileVo.setRead_file_name(realfilename);
							
							boardService.insertFile(fileVo);
							
						} catch (IllegalStateException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

			ra.addAttribute("boardNo", boardno);
			ra.addAttribute("postNo", maxPostNo);
			ra.addAttribute("userId", userId);
			
			return "redirect:/board/boardPostView";
		}else {
			
			return "redirect:/board/registBoard";
		}
		
		
	}
	
	
	@RequestMapping("fileDownload")
	public void fileDownload(String attNo, String boardNo, String postNo, String userId, HttpServletResponse resp, Model model) throws IOException {
		int attno = Integer.parseInt(attNo);
		int boardno = Integer.parseInt(boardNo);
		int postno = Integer.parseInt(postNo);
		
		
		FileVo vo = new FileVo();
		
		vo.setAtt_no(attno);
		vo.setBor_no(boardno);
		vo.setPost_no(postno);
		vo.setUser_id(userId);
		
		FileVo fileVo = boardService.selectFile(vo);
		
		String fileName ="";
		
		
		String path = "";
		
		
		path = fileVo.getRead_file_name();
		fileName = fileVo.getFile_nm();
		
		logger.debug("path : {} ", path);
		
		logger.debug("fileName : {} ", fileName);
		
		
		resp.setHeader("Content-Disposition", "attachment; filename="+fileName);
		
		FileInputStream fis = new FileInputStream(path);
		ServletOutputStream sos = resp.getOutputStream();
		
		byte[] buff = new byte[512];
		while(fis.read(buff)!=-1) {
			
			sos.write(buff);
			
		}
		
		
		fis.close();
		sos.flush();
		sos.close();
		
	}
	
	@RequestMapping(path = "comentBoardPost",method = RequestMethod.GET)
	public String comentBoardPost(String cBoardNo,String cPostNo, String cUserId, Model model) {
		
		int cBoardno = Integer.parseInt(cBoardNo);
		int cPostno = Integer.parseInt(cPostNo);
		
		List<BoardInfoVo> boardInfoList = boardService.selectAllBoardInfo();
		
		model.addAttribute("boardInfoList", boardInfoList);
		model.addAttribute("cBoardNo", cBoardno);
		model.addAttribute("cPostNo", cPostno);
		model.addAttribute("cUserId", cUserId);
		
		return "board/registComentBoard";
		
	}
	
	@RequestMapping(path = "comentBoardPost",method = RequestMethod.POST)
	public String comentBoardPost(String userId,String title, String cont, String cBoardNo,String cPostNo, String cUserId, Model model, RedirectAttributes ra, MultipartHttpServletRequest fileName) {
		
		List<MultipartFile> files = fileName.getFiles("fileName");
		
		int cBoardno = Integer.parseInt(cBoardNo);
		int cPostno = Integer.parseInt(cPostNo);
	
		
		BoardVo boardVo = new BoardVo();
		boardVo.setBor_no(cBoardno);
		boardVo.setUser_id(userId);
		boardVo.setTitle(title);
		boardVo.setCont(cont);
		boardVo.setBor_c_nm(cBoardno);
		boardVo.setPost_c_no(cPostno);
		boardVo.setRep_user_id(cUserId);
		
		logger.debug("boardVo : {}", boardVo);
		
	
		String filename = "";
		int insertCnt = 0;
		
		try {
			insertCnt = boardService.registComentBoard(boardVo);
		} catch (Exception e) {
			e.printStackTrace();
			insertCnt = 0;
		}
		
		if(insertCnt == 1) {
			int maxPostNo = boardService.selectMaxPostNo();
			FileVo fileVo = new FileVo();
			fileVo.setBor_no(cBoardno);
			fileVo.setPost_no(maxPostNo);
			fileVo.setUser_id(userId);
			
			if(files!=null) {
				for(MultipartFile profile : files) {
					if(!("".equals(profile.getOriginalFilename()))) {
						try {
							String uploadPath = "d:" + File.separator + "uploadFile";
							
							File uploadDir = new File(uploadPath);
							
							if(!uploadDir.exists()) {
								uploadDir.mkdirs();
							}
							String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
							String realfilename = "d:/uploadFile/" + UUID.randomUUID().toString()+fileExtension;
							filename = profile.getOriginalFilename();
							
							profile.transferTo(new File(realfilename));
							
							fileVo.setFile_nm(filename);
							fileVo.setRead_file_name(realfilename);
							
							boardService.insertFile(fileVo);
							
						} catch (IllegalStateException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

			ra.addAttribute("boardNo", cBoardno);
			ra.addAttribute("postNo", maxPostNo);
			ra.addAttribute("userId", userId);
			
			return "redirect:/board/boardPostView";
		}else {
			
			return "redirect:/board/registComentBoard";
		}
		
		
	}
	
	@RequestMapping("deleteBoardPost")
	public String deleteBoardPost(String cBoardNo, String cPostNo, String cUserId, Model model, RedirectAttributes ra) {
		
		int cBoardno = Integer.parseInt(cBoardNo);
		int cPostno = Integer.parseInt(cPostNo);
	
		
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBor_no(cBoardno);
		boardVo.setPost_no(cPostno);
		boardVo.setUser_id(cUserId);
		
		int deleteCnt = 0;
		
		try {
			deleteCnt = boardService.deleteBoardPost(boardVo);
		} catch (Exception e) {
			deleteCnt = 0;
			e.printStackTrace();
		}
		
		
		if(deleteCnt==1) {
			ra.addAttribute("boardNo", cBoardno);
			return "redirect:/board/boardView";
		}else {
			ra.addAttribute("boardNo", cBoardno);
			ra.addAttribute("postNo", cPostno);
			ra.addAttribute("userId", cUserId);
			return "redirect:/board/boardPostView";
			
			
		}
		
		
	}
	
	@RequestMapping("registComent")
	public String registComent(String boardNo,String postNo,String userId,String rUserId,String comment,Model model,RedirectAttributes ra) {
		
		int boardno = Integer.parseInt(boardNo);
		int postno = Integer.parseInt(postNo);
		
		comment = comment.replaceAll("\n","<br>");
		
		CommentVo commentVo = new CommentVo();
		
		commentVo.setBor_no(boardno);
		commentVo.setPost_no(postno);
		commentVo.setCom_con(comment);
		commentVo.setCom_user_id(userId);
		commentVo.setUser_id(rUserId);
		
		logger.debug(commentVo.toString());
		
		
		boardService.registComment(commentVo);
		
		
		ra.addAttribute("boardNo", boardno);
		ra.addAttribute("postNo", postno);
		ra.addAttribute("userId", rUserId);
		
		return "redirect:/board/boardPostView";
	}
	@RequestMapping("deleteComment")
	public String deleteComment(String comNo,String boardNo,String postNo,String rUserId,String comment,Model model,RedirectAttributes ra) {
		
		int comno = Integer.parseInt(comNo);
		int boardno = Integer.parseInt(boardNo);
		int postno = Integer.parseInt(postNo);
		
		CommentVo commentVo = new CommentVo();
		
		commentVo.setCom_no(comno);
		
		boardService.deleteComment(commentVo);
		
		ra.addAttribute("boardNo", boardno);
		ra.addAttribute("postNo", postno);
		ra.addAttribute("userId", rUserId);
		
		return "redirect:/board/boardPostView";
	}
	
	@RequestMapping(path = "modifyBoardPost",method = RequestMethod.GET)
	public String modifyBoardPost(String cBoardNo,String cPostNo,String cUserId, Model model) {
		
		int boardNo = Integer.parseInt(cBoardNo);
		int postNo = Integer.parseInt(cPostNo);

		
		BoardVo vo = new BoardVo();
		vo.setBor_no(boardNo);
		vo.setPost_no(postNo);
		vo.setUser_id(cUserId);
		
		
		BoardVo boardVo = boardService.selectBoardPost(vo);
		
		logger.debug("boardVo : {}" ,boardVo);
		
		List<BoardInfoVo> boardInfoList = boardService.selectAllBoardInfo();
		
		FileVo fileVo = new FileVo();
		fileVo.setBor_no(boardNo);
		fileVo.setPost_no(postNo);
		fileVo.setUser_id(cUserId);
		
		List<FileVo> fileList = boardService.selectFileList(fileVo);
		
		
		model.addAttribute("fileList", fileList);
		model.addAttribute("boardInfoList", boardInfoList);
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("boardNo", boardNo);
		
		
		return "board/modifyBoard";
	}
	
	
	@RequestMapping(path = "modifyBoardPost",method = RequestMethod.POST)
	public String modifyBoardPost(String boardNo,String postNo,String userId,String title,String cont, Model model, RedirectAttributes ra, MultipartHttpServletRequest fileName) {
		
		List<MultipartFile> files = fileName.getFiles("fileName");
		
		int boardno = Integer.parseInt(boardNo);
		int postno = Integer.parseInt(postNo);
		
		BoardVo boardVo = new BoardVo();
		boardVo.setPost_no(postno);
		boardVo.setBor_no(boardno);
		boardVo.setUser_id(userId);
		boardVo.setTitle(title);
		boardVo.setCont(cont);
		
		
		String filename = "";
		int updateCnt = 0;
		
		try {
			updateCnt = boardService.modifyBoard(boardVo);
		} catch (Exception e) {
			e.printStackTrace();
			updateCnt = 0;
		}
		
		if(updateCnt == 1) {
			FileVo fileVo = new FileVo();
			fileVo.setBor_no(boardno);
			fileVo.setPost_no(postno);
			fileVo.setUser_id(userId);
			
			if(files!=null) {
				for(MultipartFile profile : files) {
					if(!("".equals(profile.getOriginalFilename()))) {
						try {
							String uploadPath = "d:" + File.separator + "uploadFile";
							
							File uploadDir = new File(uploadPath);
							
							if(!uploadDir.exists()) {
								uploadDir.mkdirs();
							}
							String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
							String realfilename = "d:/uploadFile/" + UUID.randomUUID().toString()+fileExtension;
							filename = profile.getOriginalFilename();
							
							profile.transferTo(new File(realfilename));
							
							fileVo.setFile_nm(filename);
							fileVo.setRead_file_name(realfilename);
							
							boardService.insertFile(fileVo);
							
						} catch (IllegalStateException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

			ra.addAttribute("boardNo", boardno);
			ra.addAttribute("postNo", postno);
			ra.addAttribute("userId", userId);
			
			return "redirect:/board/boardPostView";
		}else {
			
			ra.addAttribute("boardNo", boardno);
			ra.addAttribute("postNo", postno);
			ra.addAttribute("userId", userId);
			return "redirect:/board/modifyBoard";
		}
		
		
	}
	
	@RequestMapping("deleteFile")
	public String deleteFile(String attno, Model model) {
		
		int attNo = Integer.parseInt(attno);
		
		FileVo fileVo = new FileVo();
		fileVo.setAtt_no(attNo);
		
		int deleteCnt = boardService.deleteFile(fileVo);
		
		model.addAttribute("cnt", deleteCnt);
		
		return "jsonView";
	}
	
	
}
