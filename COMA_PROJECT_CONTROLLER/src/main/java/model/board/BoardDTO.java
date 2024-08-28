package model.board;

public class BoardDTO {
	private int board_num;          // 게시판 글 번호
	private String board_title;     // 게시판제목
	private String board_content;   // 글 내용
	private int board_cnt;          // 글 조회수
	private String board_location;  // 게시글의 위치 지역
	private String board_writer_id; // 작성자 FK
	
	//DTO에만 존재하는 데이터
	private int board_total; //전체 커뮤니티 게시글 총 개수
	private int board_max_num; //페이지네이션 데이터
	private int board_min_num; //페이지네이션 데이터
	private String board_searchKeyword;    // 사용자 텍스트 기반 검색 FIXME
	private String board_condition;       // 개발자 데이터 검색 조건 지역, 작성자, 날짜 범위 기능이면 필요
	
	
	
	public int getBoard_max_num() {
		return board_max_num;
	}
	public void setBoard_max_num(int board_max_num) {
		this.board_max_num = board_max_num;
	}
	public int getBoard_min_num() {
		return board_min_num;
	}
	public void setBoard_min_num(int board_min_num) {
		this.board_min_num = board_min_num;
	}
	public int getBoard_total() {
		return board_total;
	}
	public void setBoard_total(int board_total) {
		this.board_total = board_total;
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
	public String getBoard_searchKeyword() {
		return board_searchKeyword;
	}
	public void setBoard_searchKeyword(String board_searchKeyword) {
		this.board_searchKeyword = board_searchKeyword;
	}
	public String getBoard_condition() {
		return board_condition;
	}
	public void setBoard_condition(String board_condition) {
		this.board_condition = board_condition;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getBoard_writer_id() {
		return board_writer_id;
	}
	public void setBoard_writer_id(String board_writer_id) {
		this.board_writer_id = board_writer_id;
	}
	@Override
	public String toString() {
		return "BoardDTO [board_num=" + board_num + ", board_title=" + board_title + ", board_content=" + board_content
				+ ", board_cnt=" + board_cnt + ", board_location=" + board_location + ", board_writer_id="
				+ board_writer_id + ", board_total=" + board_total + ", board_max_num=" + board_max_num
				+ ", board_min_num=" + board_min_num + ", board_searchKeyword=" + board_searchKeyword
				+ ", board_condition=" + board_condition + "]";
	}
	
	
}