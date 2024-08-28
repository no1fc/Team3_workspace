package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.JDBCUtil;

public class MemberDAO {
	//아이디로 찾기 FIXME 관리자 권한 추가(char값 'T','F')
	private final String SEARCH_ID = "SELECT MEMBER_ID,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_PHONE,MEMBER_PROFILE,MEMBER_REGISTRATION_DATE,MEMBER_CURRENT_POINT,MEMBER_TOTAL_POINT,MEMBER_CREW_NUM,MEMBER_CREW_JOIN_DATE,MEMBER_LOCATION,MEMBER_ROLE\r\n"
			+ "FROM MEMBER\r\n"
			+ "WHERE MEMBER_ID = ?";
	
	//아이디 비밀번호로 찾기
	private final String SEARCH_ID_PASSWORD="SELECT MEMBER_ID,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_PHONE,MEMBER_PROFILE,MEMBER_REGISTRATION_DATE,MEMBER_CURRENT_POINT,MEMBER_TOTAL_POINT,MEMBER_CREW_NUM,MEMBER_CREW_JOIN_DATE,MEMBER_LOCATION,MEMBER_ROLE\r\n"
			+ "FROM MEMBER\r\n"
			+ "WHERE MEMBER_ID = ? AND MEMBER_PASSWORD=?";
	
	//크루에 속한 회원목록 조회
	private final String SEARCH_CREW="SELECT MEMBER_ID,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_PHONE,MEMBER_PROFILE,MEMBER_REGISTRATION_DATE,MEMBER_CURRENT_POINT,MEMBER_TOTAL_POINT,MEMBER_CREW_NUM,MEMBER_CREW_JOIN_DATE,MEMBER_LOCATION,MEMBER_ROLE\r\n"
			+ "FROM MEMBER\r\n"
			+ "WHERE MEMBER_CREW_NUM = ?";
	
	//랭킹높은순으로 정렬 관리자 FIXME 관리자 권한이 아닌사람들만
	private final String SEARCH_RANK="SELECT MEMBER_ID,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_PHONE,MEMBER_PROFILE,MEMBER_REGISTRATION_DATE,MEMBER_CURRENT_POINT,MEMBER_TOTAL_POINT,MEMBER_CREW_NUM,MEMBER_CREW_JOIN_DATE,MEMBER_LOCATION,MEMBER_ROLE\r\n"
			+ "FROM MEMBER\r\n"
			+ "ORDER BY MEMBER_TOTAL_POINT DESC\r\n"
			+ "WHERE MEMBER_ROLE='F';";
	
	//회원가입
	private final String INSERT="INSERT INTO MEMBER(MEMBER_ID,MEMBER_NAME,MEMBER_PASSWORD,MEMBER_PHONE,MEMBER_LOCATION) \r\n"
			+ "VALUES(?,?,?,?,?)";
	
	//회원탈퇴
	private final String DELETE="DELETE FROM MEMBER WHERE MEMBER_ID = ?";
	
	//회원정보 업데이트 MEMBER_PASSWORD, MEMBER_PROFILE, MEMBER_PHONE, MEMBER_LOCATION, MEMBER_ID
	private final String UPDATE_ALL="UPDATE MEMBER\r\n"
			+ "SET\r\n"
			+ "	MEMBER_PASSWORD = ?,\r\n"
			+ "	MEMBER_PROFILE = ?,\r\n"
			+ "	MEMBER_PHONE = ?,\r\n"
			+ "	MEMBER_LOCATION = ?\r\n"
			+ "WHERE MEMBER_ID = ?";
	
	//회원정보 업데이트 (profile X) MEMBER_PASSWORD, MEMBER_PHONE, MEMBER_LOCATION, MEMBER_ID
	private final String UPDATE_WITHOUT_PROFILE="UPDATE MEMBER\r\n"
			+ "SET\r\n"
			+ "	MEMBER_PASSWORD = ?,\r\n"
			+ "	MEMBER_PHONE = ?,\r\n"
			+ "	MEMBER_LOCATION = ?\r\n"
			+ "WHERE MEMBER_ID = ?";
	
	//크루가입 (크루가입시 가입날짜입력때문에 분리)
	private final String UPDATE_CREW = "UPDATE MEMBER\r\n" //FIXME 관리자 권한이 아닌 사람들만
			+ "SET\r\n"
			+ "MEMBER_CREW_NUM = ?,\r\n"
			+ "MEMBER_CREW_JOIN_DATE = SYSDATE\r\n"
			+ "WHERE MEMBER_ID = ? AND MEMBER_ROLE='F'";
	
	//관리자 권한 변경 ROLE, ID
	private final String UPDATE_ADMIN ="UPDATE MEMBER SET MEMBER_ROLE = '?' WHERE MEMBER_ID = '?'";
	
	//관리자가 아닌 신규회원 출력 (기간 7일)
	private final String ALL_NEW ="SELECT MEMBER_ID,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_PHONE,MEMBER_PROFILE,MEMBER_REGISTRATION_DATE,MEMBER_CURRENT_POINT,MEMBER_TOTAL_POINT,MEMBER_CREW_NUM,MEMBER_CREW_JOIN_DATE,MEMBER_LOCATION,MEMBER_ROLE\r\n"
			+ "FROM MEMBER\r\n"
			+ "WHERE MEMBER_REGISTRATION_DATE >= SYSDATE - INTERVAL '7' DAY AND MEMBER_ROLE='F'";

	public boolean insert(MemberDTO memberDTO) {
		System.out.println("member.MemberDAO.insert 시작");
		Connection conn=JDBCUtil.connect();
		PreparedStatement pstmt=null;
		try {
			//회원가입 MEMBER_ID, MEMBER_NAME, MEMBER_PASSWORD, MEMBER_PHONE, MEMBER_LOCATION
			pstmt=conn.prepareStatement(INSERT);
			pstmt.setString(1, memberDTO.getMember_id());
			pstmt.setString(2, memberDTO.getMember_name());
			pstmt.setString(3, memberDTO.getMember_password());
			pstmt.setString(4, memberDTO.getMember_phone());
			pstmt.setString(5, memberDTO.getMember_location());
			int rs = pstmt.executeUpdate();
			if(rs<=0) {
				System.err.println("member.MemberDAO.insert 실패");
				return false;
			}

		} catch (SQLException e) {
			System.out.println("member.MemberDAO.insert SQL문 실패");
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt,conn);
		}
		System.out.println("member.MemberDAO.insert 성공");
		return true;
	}
	public boolean update(MemberDTO memberDTO) {
		System.out.println("member.MemberDAO.update 시작");
		Connection conn=JDBCUtil.connect();
		PreparedStatement pstmt=null;
		try {
			//회원정보 업데이트 MEMBER_PASSWORD, MEMBER_PROFILE, MEMBER_PHONE, MEMBER_LOCATION, MEMBER_ID
			if(memberDTO.getMember_condition().equals("MEMBER_UPDATE_ALL")) {
				pstmt=conn.prepareStatement(UPDATE_ALL);
				pstmt.setString(1, memberDTO.getMember_password());
				pstmt.setString(2, memberDTO.getMember_profile());
				pstmt.setString(3, memberDTO.getMember_phone());
				pstmt.setString(4, memberDTO.getMember_location());
				pstmt.setString(5, memberDTO.getMember_id());
			}
			//회원정보 업데이트 (profile X) MEMBER_PASSWORD, MEMBER_PHONE, MEMBER_LOCATION, MEMBER_ID
			else if(memberDTO.getMember_condition().equals("MEMBER_UPDATE_WITHOUT_PROFILE")) {
				pstmt=conn.prepareStatement(UPDATE_WITHOUT_PROFILE);
				pstmt.setString(1, memberDTO.getMember_password());
				pstmt.setString(3, memberDTO.getMember_phone());
				pstmt.setString(4, memberDTO.getMember_location());
				pstmt.setString(5, memberDTO.getMember_id());
			}
			//크루가입 MEMBER_CREW_NUM, MEMBER_ID
			else if(memberDTO.getMember_condition().equals("MEMBER_UPDATE_CREW")) {
				pstmt=conn.prepareStatement(UPDATE_CREW);
				pstmt.setInt(1, memberDTO.getMember_crew_num());
				pstmt.setString(2, memberDTO.getMember_id());
			}
			//관리자 권한 변경 MEMBER_ROLE, MEMBER_ID
			else if(memberDTO.getMember_condition().equals("MEMBER_UPDATE_ADMIN")) {
				pstmt=conn.prepareStatement(UPDATE_ADMIN);
				pstmt.setString(1, memberDTO.getMember_role());
				pstmt.setString(2, memberDTO.getMember_id());
			}
			else {
				System.err.println("condition 틀림");
				return false;
			}
			int rs = pstmt.executeUpdate();
			if(rs<=0) {
				System.err.println("member.MemberDAO.update 실패");
				return false;
			}
		} catch (SQLException e) {
			System.err.println("member.MemberDAO.update SQL문 실패");
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt,conn);
		}
		System.out.println("member.MemberDAO.update 성공");
		return true;
	}
	public boolean delete(MemberDTO memberDTO) {
		System.out.println("member.MemberDAO.delete 시작");
		Connection conn=JDBCUtil.connect();
		PreparedStatement pstmt=null;
		try {
			//회원탈퇴 MEMBER_ID
			pstmt=conn.prepareStatement(DELETE);
			pstmt.setString(1, memberDTO.getMember_id());
			int rs = pstmt.executeUpdate();
			if(rs<=0) {
				System.err.println("member.MemberDAO.delete 실패");
				return false;
			}

		} catch (SQLException e) {
			System.err.println("member.MemberDAO.delete SQL문 실패");
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt,conn);
		}
		System.out.println("member.MemberDAO.delete 성공");
		return true;
	}
	public MemberDTO selectOne(MemberDTO memberDTO) {
		System.out.println("member.MemberDAO.selectOne 시작");
		MemberDTO data = null;
		Connection conn=JDBCUtil.connect();
		PreparedStatement pstmt=null;
		try {
			//아이디로 검색 MEMBER_ID
			if(memberDTO.getMember_condition().equals("MEMBER_SEARCH_ID")) {
				pstmt=conn.prepareStatement(SEARCH_ID);
				pstmt.setString(1, memberDTO.getMember_id());
			}
			//아이디,비밀번호로 검색 MEMBER_ID, MEMBER_PASSWORD
			else if(memberDTO.getMember_condition().equals("MEMBER_SEARCH_ID_PASSWORD")) {
				pstmt=conn.prepareStatement(SEARCH_ID_PASSWORD);
				pstmt.setString(1, memberDTO.getMember_id());
				pstmt.setString(2, memberDTO.getMember_password());
			}
			else {
				System.err.println("condition 틀림");
				return null;
			}
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("memberDAO.selectOne 맞는 데이터 존재함");
				data = new MemberDTO();
				data.setMember_id(rs.getString("MEMBER_ID"));
				data.setMember_name(rs.getString("MEMBER_NAME"));
				data.setMember_password(rs.getString("MEMBER_PASSWORD"));
				data.setMember_phone(rs.getString("MEMBER_PHONE"));
				data.setMember_registration_date(rs.getDate("MEMBER_REGISTRATION_DATE"));
				data.setMember_profile(rs.getString("MEMBER_PROFILE"));
				data.setMember_current_point(rs.getInt("MEMBER_CURRENT_POINT"));
				data.setMember_total_point(rs.getInt("MEMBER_TOTAL_POINT"));
				data.setMember_crew_num(rs.getInt("MEMBER_CREW_NUM"));
				data.setMember_crew_join_date(rs.getDate("MEMBER_CREW_JOIN_DATE"));
				data.setMember_location(rs.getString("MEMBER_LOCATION"));
				data.setMember_role(rs.getString("MEMBER_ROLE"));
				//FIXME role추가 String값, 'T','F'만 가능
				}
		} catch (SQLException e) {
			System.err.println("member.MemberDAO.selectOne SQL문 실패");
			return null;
		}finally {
			JDBCUtil.disconnect(pstmt,conn);
		}
		System.out.println("member.MemberDAO.selectOne 성공");
		return data;
	}
	public ArrayList<MemberDTO> selectAll(MemberDTO memberDTO) {
		System.out.println("member.MemberDAO.selectAll 시작");
		ArrayList<MemberDTO> datas = new ArrayList<MemberDTO>();
		MemberDTO data = null;
		int rsCnt=1;//로그용
		Connection conn=JDBCUtil.connect();
		PreparedStatement pstmt=null;
		try {
			//랭킹높은순으로 정렬
			if(memberDTO.getMember_condition().equals("MEMBER_SEARCH_RANK")) {
				pstmt=conn.prepareStatement(SEARCH_RANK);
			}
			//크루에 속한 회원목록 조회 MEMBER_CREW_NUM
			else if(memberDTO.getMember_condition().equals("MEMBER_SEARCH_CREW")) {
				pstmt=conn.prepareStatement(SEARCH_CREW);
				pstmt.setInt(1, memberDTO.getMember_crew_num());
			}
			//관리자가 아닌 신규회원 출력 (기간 7일)
			else if(memberDTO.getMember_condition().equals("MEMBER_ALL_NEW")) {
				pstmt=conn.prepareStatement(ALL_NEW);
			}
			else {
				System.err.println("condition 틀림");
				return datas;
			}
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rsCnt+"번행 출력중..");
				data = new MemberDTO();
				data.setMember_id(rs.getString("MEMBER_ID"));
				data.setMember_name(rs.getString("MEMBER_NAME"));
				data.setMember_password(rs.getString("MEMBER_PASSWORD"));
				data.setMember_phone(rs.getString("MEMBER_PHONE"));
				data.setMember_registration_date(rs.getDate("MEMBER_REGISTRATION_DATE"));
				data.setMember_profile(rs.getString("MEMBER_PROFILE"));
				data.setMember_current_point(rs.getInt("MEMBER_CURRENT_POINT"));
				data.setMember_total_point(rs.getInt("MEMBER_TOTAL_POINT"));
				data.setMember_crew_num(rs.getInt("MEMBER_CREW_NUM"));
				data.setMember_crew_join_date(rs.getDate("MEMBER_CREW_JOIN_DATE"));
				data.setMember_location(rs.getString("MEMBER_LOCATION"));
				data.setMember_role(rs.getString("MEMBER_ROLE"));
				datas.add(data);
				rsCnt++;
				}

		} catch (SQLException e) {
			System.err.println("member.MemberDAO.selectAll SQL문 실패");
			return datas;
		}finally {
			JDBCUtil.disconnect(pstmt,conn);
		}
		System.out.println("member.MemberDAO.selectAll 성공");
		return datas;
	}
}
