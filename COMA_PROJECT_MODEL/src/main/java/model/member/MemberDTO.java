package model.member;

import java.sql.Date;

public class MemberDTO {
	private String member_id; //아이디
	private String member_name; //이름
	private String member_password; //비밀번호
	private String member_phoneNumber; //휴대폰 번호
	private String member_profile; // 프로필사진 url
	private int member_current_point; //사용가능한 포인트
	private int member_total_point; //누적포인트
	private int member_crew_id; //크루 FK
	private Date member_cdate; //크루 가입날짜
	private String member_location; //사용자 현재지역
	private Date member_mdate; //회원가입날짜
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
	public String getMember_phoneNumber() {
		return member_phoneNumber;
	}
	public void setMember_phoneNumber(String member_phoneNumber) {
		this.member_phoneNumber = member_phoneNumber;
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
	public int getMember_crew_id() {
		return member_crew_id;
	}
	public void setMember_crew_id(int member_crew_id) {
		this.member_crew_id = member_crew_id;
	}
	public String getMember_location() {
		return member_location;
	}
	public void setMember_location(String member_location) {
		this.member_location = member_location;
	}
	public Date getMember_cdate() {
		return member_cdate;
	}
	public void setMember_cdate(Date member_cdate) {
		this.member_cdate = member_cdate;
	}
	public Date getMember_mdate() {
		return member_mdate;
	}
	public void setMember_mdate(Date member_mdate) {
		this.member_mdate = member_mdate;
	}
	public String getMember_condition() {
		return member_condition;
	}
	public void setMember_condition(String member_condition) {
		this.member_condition = member_condition;
	}
	@Override
	public String toString() {
		return "MemberDTO [member_id=" + member_id + ", member_name=" + member_name + ", member_password="
				+ member_password + ", member_phoneNumber=" + member_phoneNumber + ", member_profile=" + member_profile
				+ ", member_current_point=" + member_current_point + ", member_total_point=" + member_total_point
				+ ", member_crew_id=" + member_crew_id + ", member_cdate=" + member_cdate + ", member_location="
				+ member_location + ", member_condition=" + member_condition + "]";
	}
	
	
}

