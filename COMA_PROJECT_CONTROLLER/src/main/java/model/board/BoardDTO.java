package model.board;

public class BoardDTO {
	private int board_id;              // 게시판 글 번호
	private String board_title;        // 게시판제목
	private String board_content;      // 글 내용
	private int board_cnt;             // 조회수
	private String board_location;     // 게시글의 위치 지역
	private String writer;		   	     // 작성자
	private String board_serchKeyword; // 사용자 텍스트 기반 검색 
	private String board_condition;    // 개발자 데이터 검색 조건 지역, 작성자, 날짜 범위 기능이면 필요
	private int board_pagemax;         // 페이지 최대 값
	private int board_pagemin;         // 페이지 최소 값
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getBoard_cnt() {
		return board_cnt;
	}
	public void setBoard_cnt(int board_cnt) {
		this.board_cnt = board_cnt;
	}
	public String getBoard_location() {
		return board_location;
	}
	public void setBoard_location(String board_location) {
		this.board_location = board_location;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getBoard_serchKeyword() {
		return board_serchKeyword;
	}
	public void setBoard_serchKeyword(String board_serchKeyword) {
		this.board_serchKeyword = board_serchKeyword;
	}
	public String getBoard_condition() {
		return board_condition;
	}
	public void setBoard_condition(String board_condition) {
		this.board_condition = board_condition;
	}
	public int getBoard_pagemax() {
		return board_pagemax;
	}
	public void setBoard_pagemax(int board_pagemax) {
		this.board_pagemax = board_pagemax;
	}
	public int getBoard_pagemin() {
		return board_pagemin;
	}
	public void setBoard_pagemin(int board_pagemin) {
		this.board_pagemin = board_pagemin;
	}
	@Override
	public String toString() {
		return "BoardDTO [board_id=" + board_id + ", board_title=" + board_title + ", board_content=" + board_content
				+ ", board_cnt=" + board_cnt + ", board_location=" + board_location + ", writer=" + writer
				+ ", board_serchKeyword=" + board_serchKeyword + ", board_condition=" + board_condition
				+ ", board_pagemax=" + board_pagemax + ", board_pagemin=" + board_pagemin + "]";
	}
	
}
