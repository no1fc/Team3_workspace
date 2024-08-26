package model.crew_board;

public class Crew_boardDTO {
	private int crew_board_id; //크루 글 번호
	private String crew_board_writer; //크루 글 작성자
	private String crew_board_content; //크루 글 내용
	private String crew_board_title; //크루 글 제목
	private String crew_board_condition; //개발자 데이터
	
	public int getCrew_board_id() {
		return crew_board_id;
	}
	public void setCrew_board_id(int crew_board_id) {
		this.crew_board_id = crew_board_id;
	}
	public String getCrew_board_writer() {
		return crew_board_writer;
	}
	public void setCrew_board_writer(String crew_board_writer) {
		this.crew_board_writer = crew_board_writer;
	}
	public String getCrew_board_content() {
		return crew_board_content;
	}
	public void setCrew_board_content(String crew_board_content) {
		this.crew_board_content = crew_board_content;
	}
	public String getCrew_board_title() {
		return crew_board_title;
	}
	public void setCrew_board_title(String crew_board_title) {
		this.crew_board_title = crew_board_title;
	}
	public String getCrew_board_condition() {
		return crew_board_condition;
	}
	public void setCrew_board_condition(String crew_board_condition) {
		this.crew_board_condition = crew_board_condition;
	}
	@Override
	public String toString() {
		return "Crew_boardDTO [crew_board_id=" + crew_board_id + ", crew_board_writer=" + crew_board_writer
				+ ", crew_board_content=" + crew_board_content + ", crew_board_title=" + crew_board_title
				+ ", crew_board_condition=" + crew_board_condition + "]";
	}
	
	
}
