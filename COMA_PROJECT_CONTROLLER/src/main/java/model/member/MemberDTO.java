package model.member;

import java.sql.Date;

public class MemberDTO {
	private String member_id; //아이디
	private String member_name; //이름
	private String member_password; //비밀번호
	private String member_phone; //휴대폰 번호
	private String member_profile; // 프로필사진 url
	private int member_current_point; //사용가능한 포인트
	private int member_total_point; //누적포인트
	private int member_crew_num; //크루 FK
	private Date member_crew_join_date; //크루 가입날짜
	private String member_location; //사용자 현재지역
	private Date member_registration_date; //회원가입날짜
	private String member_role; //관리자 권한
	private String member_condition; //개발자 데이터
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_profile() {
		return member_profile;
	}
	public void setMember_profile(String member_profile) {
		this.member_profile = member_profile;
	}
	public int getMember_current_point() {
		return member_current_point;
	}
	public void setMember_current_point(int member_current_point) {
		this.member_current_point = member_current_point;
	}
	public int getMember_total_point() {
		return member_total_point;
	}
	public void setMember_total_point(int member_total_point) {
		this.member_total_point = member_total_point;
	}
	public String getMember_location() {
		return member_location;
	}
	public void setMember_location(String member_location) {
		this.member_location = member_location;
	}
	public String getMember_role() {
		return member_role;
	}
	public void setMember_role(String member_role) {
		this.member_role = member_role;
	}
	public String getMember_condition() {
		return member_condition;
	}
	public void setMember_condition(String member_condition) {
		this.member_condition = member_condition;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public int getMember_crew_num() {
		return member_crew_num;
	}
	public void setMember_crew_num(int member_crew_num) {
		this.member_crew_num = member_crew_num;
	}
	public Date getMember_crew_join_date() {
		return member_crew_join_date;
	}
	public void setMember_crew_join_date(Date member_crew_join_date) {
		this.member_crew_join_date = member_crew_join_date;
	}
	public Date getMember_registration_date() {
		return member_registration_date;
	}
	public void setMember_registration_date(Date member_registration_date) {
		this.member_registration_date = member_registration_date;
	}
	@Override
	public String toString() {
		return "MemberDTO [member_id=" + member_id + ", member_name=" + member_name + ", member_password="
				+ member_password + ", member_phone=" + member_phone + ", member_profile=" + member_profile
				+ ", member_current_point=" + member_current_point + ", member_total_point=" + member_total_point
				+ ", member_crew_num=" + member_crew_num + ", member_crew_join_date=" + member_crew_join_date
				+ ", member_location=" + member_location + ", member_registration_date=" + member_registration_date
				+ ", member_role=" + member_role + ", member_condition=" + member_condition + "]";
	}
	
}