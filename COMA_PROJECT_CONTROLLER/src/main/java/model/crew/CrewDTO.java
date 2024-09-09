package model.crew;

public class CrewDTO {
	private int model_crew_num;             //크루 번호
	private String model_crew_name;	        //크루 이름
	private String model_crew_description;	//크루 설명
	private int model_crew_max_member_size; //크루 최대 인원수
	private String model_crew_leader;	    //크루 리더
	private String model_crew_match_status; //크루전 신청여부 Y,N(char1)

	//DTO에만 존재하는 데이터 
	private int model_crew_member_id;		    //크루 가입한 사람 
	private String model_crew_new_member;   //신규 가입한 크루원
	private String model_crew_join_date;    //크루 가입 날짜 
	private int model_crew_current_member;  //현재 크루원 수 
	private int model_crew_total;           // 크루 게시글 총 개수 
	private int model_crew_max_num;         // 페이지네이션 데이터
	private int model_crew_min_num;         // 페이지네이션 데이터
	private String model_crew_conditon;     // 개발자 데이터 검색
	public int getModel_crew_num() {
		return model_crew_num;
	}
	public void setModel_crew_num(int model_crew_num) {
		this.model_crew_num = model_crew_num;
	}
	public String getModel_crew_name() {
		return model_crew_name;
	}
	public void setModel_crew_name(String model_crew_name) {
		this.model_crew_name = model_crew_name;
	}
	public String getModel_crew_description() {
		return model_crew_description;
	}
	public void setModel_crew_description(String model_crew_description) {
		this.model_crew_description = model_crew_description;
	}
	public int getModel_crew_max_member_size() {
		return model_crew_max_member_size;
	}
	public void setModel_crew_max_member_size(int model_crew_max_member_size) {
		this.model_crew_max_member_size = model_crew_max_member_size;
	}
	public String getModel_crew_leader() {
		return model_crew_leader;
	}
	public void setModel_crew_leader(String model_crew_leader) {
		this.model_crew_leader = model_crew_leader;
	}
	public String getModel_crew_match_status() {
		return model_crew_match_status;
	}
	public void setModel_crew_match_status(String model_crew_match_status) {
		this.model_crew_match_status = model_crew_match_status;
	}
	public int getModel_crew_member_id() {
		return model_crew_member_id;
	}
	public void setModel_crew_member_id(int model_crew_member_id) {
		this.model_crew_member_id = model_crew_member_id;
	}
	public String getModel_crew_new_member() {
		return model_crew_new_member;
	}
	public void setModel_crew_new_member(String model_crew_new_member) {
		this.model_crew_new_member = model_crew_new_member;
	}
	public String getModel_crew_join_date() {
		return model_crew_join_date;
	}
	public void setModel_crew_join_date(String model_crew_join_date) {
		this.model_crew_join_date = model_crew_join_date;
	}
	public int getModel_crew_current_member() {
		return model_crew_current_member;
	}
	public void setModel_crew_current_member(int model_crew_current_member) {
		this.model_crew_current_member = model_crew_current_member;
	}
	public int getModel_crew_total() {
		return model_crew_total;
	}
	public void setModel_crew_total(int model_crew_total) {
		this.model_crew_total = model_crew_total;
	}
	public int getModel_crew_max_num() {
		return model_crew_max_num;
	}
	public void setModel_crew_max_num(int model_crew_max_num) {
		this.model_crew_max_num = model_crew_max_num;
	}
	public int getModel_crew_min_num() {
		return model_crew_min_num;
	}
	public void setModel_crew_min_num(int model_crew_min_num) {
		this.model_crew_min_num = model_crew_min_num;
	}
	public String getModel_crew_conditon() {
		return model_crew_conditon;
	}
	public void setModel_crew_conditon(String model_crew_conditon) {
		this.model_crew_conditon = model_crew_conditon;
	}
	@Override
	public String toString() {
		return "CrewDTO [model_crew_num=" + model_crew_num + ", model_crew_name=" + model_crew_name
				+ ", model_crew_description=" + model_crew_description + ", model_crew_max_member_size="
				+ model_crew_max_member_size + ", model_crew_leader=" + model_crew_leader + ", model_crew_match_status="
				+ model_crew_match_status + ", model_crew_member_id=" + model_crew_member_id
				+ ", model_crew_new_member=" + model_crew_new_member + ", model_crew_join_date=" + model_crew_join_date
				+ ", model_crew_current_member=" + model_crew_current_member + ", model_crew_total=" + model_crew_total
				+ ", model_crew_max_num=" + model_crew_max_num + ", model_crew_min_num=" + model_crew_min_num
				+ ", model_crew_conditon=" + model_crew_conditon + "]";
	}
	

}
