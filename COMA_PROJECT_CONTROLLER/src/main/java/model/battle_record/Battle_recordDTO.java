package model.battle_record;

public class Battle_recordDTO {
	private int model_battle_record_num;          // 크루전 참여기록 PK
	private int model_battle_record_match_num;    // 크루전 FK
	private int model_battle_record_crew_num;     // 크루 FK
	private String model_battle_record_is_winner; // 승리여부 
	private String model_battle_record_mvp;       // 크루전 MVP
	
	//DTO에만 존재하는 데이터
	private int model_battle_record_total;            // 전체 게시글 총수
	private int model_battle_record_max_num;          // 페이지네이션 데이터
	private int model_battle_record_min_num;          // 페이지네이션 데이터
	private String model_battle_record_searchKeyword; // 사용자 텍스트 기반검색
	private String model_battle_record_conditon;      // 개발자 데이터 검색
	public int getModel_battle_record_num() {
		return model_battle_record_num;
	}
	public void setModel_battle_record_num(int model_battle_record_num) {
		this.model_battle_record_num = model_battle_record_num;
	}
	public int getModel_battle_record_match_num() {
		return model_battle_record_match_num;
	}
	public void setModel_battle_record_match_num(int model_battle_record_match_num) {
		this.model_battle_record_match_num = model_battle_record_match_num;
	}
	public int getModel_battle_record_crew_num() {
		return model_battle_record_crew_num;
	}
	public void setModel_battle_record_crew_num(int model_battle_record_crew_num) {
		this.model_battle_record_crew_num = model_battle_record_crew_num;
	}
	public String getModel_battle_record_is_winner() {
		return model_battle_record_is_winner;
	}
	public void setModel_battle_record_is_winner(String model_battle_record_is_winner) {
		this.model_battle_record_is_winner = model_battle_record_is_winner;
	}
	public String getModel_battle_record_mvp() {
		return model_battle_record_mvp;
	}
	public void setModel_battle_record_mvp(String model_battle_record_mvp) {
		this.model_battle_record_mvp = model_battle_record_mvp;
	}
	public int getModel_battle_record_total() {
		return model_battle_record_total;
	}
	public void setModel_battle_record_total(int model_battle_record_total) {
		this.model_battle_record_total = model_battle_record_total;
	}
	public int getModel_battle_record_max_num() {
		return model_battle_record_max_num;
	}
	public void setModel_battle_record_max_num(int model_battle_record_max_num) {
		this.model_battle_record_max_num = model_battle_record_max_num;
	}
	public int getModel_battle_record_min_num() {
		return model_battle_record_min_num;
	}
	public void setModel_battle_record_min_num(int model_battle_record_min_num) {
		this.model_battle_record_min_num = model_battle_record_min_num;
	}
	public String getModel_battle_record_searchKeyword() {
		return model_battle_record_searchKeyword;
	}
	public void setModel_battle_record_searchKeyword(String model_battle_record_searchKeyword) {
		this.model_battle_record_searchKeyword = model_battle_record_searchKeyword;
	}
	public String getModel_battle_record_conditon() {
		return model_battle_record_conditon;
	}
	public void setModel_battle_record_conditon(String model_battle_record_conditon) {
		this.model_battle_record_conditon = model_battle_record_conditon;
	}
	@Override
	public String toString() {
		return "Battle_recordDTO [model_battle_record_num=" + model_battle_record_num
				+ ", model_battle_record_match_num=" + model_battle_record_match_num + ", model_battle_record_crew_num="
				+ model_battle_record_crew_num + ", model_battle_record_is_winner=" + model_battle_record_is_winner
				+ ", model_battle_record_mvp=" + model_battle_record_mvp + ", model_battle_record_total="
				+ model_battle_record_total + ", model_battle_record_max_num=" + model_battle_record_max_num
				+ ", model_battle_record_min_num=" + model_battle_record_min_num
				+ ", model_battle_record_searchKeyword=" + model_battle_record_searchKeyword
				+ ", model_battle_record_conditon=" + model_battle_record_conditon + "]";
	}
	
	
}
