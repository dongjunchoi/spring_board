package kr.or.ddit.common.model;

public class PageVo {
	private int page;
	private int pageSize;
	private int boardNo;
	
	public PageVo() {}
	
	public PageVo(int page, int pageSize, int boardNo) {
		this.page = page;
		this.pageSize = pageSize;
		this.boardNo = boardNo;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "PageVo [page=" + page + ", pageSize=" + pageSize + ", boardNo=" + boardNo + "]";
	}

	
	

	
	
}
