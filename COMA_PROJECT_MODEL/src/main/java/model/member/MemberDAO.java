package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.JDBCUtil;

public class MemberDAO {
	//아이디로 찾기
	private final String SERCHID = "SELECT ID,NAME,PHONENUMBER,PROFILE,MDATE,CURRENT_POINT,TOTAL_POINT,CREW_ID,CDATE,LOCATION,MDATE\r\n"
			+ "FROM MEMBER\r\n"
			+ "WHERE ID = ?";
	//아이디 비밀번호로 찾기
	private final String SERCHIDPASS="SELECT ID,NAME,PHONENUMBER,PROFILE,MDATE,CURRENT_POINT,TOTAL_POINT,CREW_ID,CDATE,LOCATION\r\n"
			+ "FROM MEMBER\r\n"
			+ "WHERE ID = ? AND PASSWORD=?";
	//크루에 속한 회원목록 조회
	private final String SERCHCREW="SELECT ID,NAME,PHONENUMBER,PROFILE,MDATE,CURRENT_POINT,TOTAL_POINT,CREW_ID,CDATE,LOCATION\r\n"
			+ "FROM MEMBER\r\n"
			+ "WHERE CREW_ID = ?";
	//랭킹높은순으로 정렬
	private final String SERCHRANK="SELECT ID, NAME, PASSWORD, PHONENUMBER, MDATE, PROFILE, CURRENT_POINT, TOTAL_POINT, CREW_ID, CDATE, LOCATION\r\n"
			+ "FROM MEMBER\r\n"
			+ "ORDER BY TOTAL_POINT DESC;";
	//회원가입
	private final String INSERT="INSERT INTO MEMBER(ID,NAME,PASSWORD,PHONENUMBER,LOCATION) \r\n"
			+ "VALUES(?,?,?,?,?);";
	//회원탈퇴
	private final String DELETE="DELETE FROM MEMBER WHERE ID = ?";
	//회원정보 업데이트 (크루가입x)
	private final String UPDATE="UPDATE MEMBER\r\n"
			+ "SET\r\n"
			+ "	NAME = ?,\r\n"
			+ "	PROFILE = ?,\r\n"
			+ "	PHONENUMBER = ?,\r\n"
			+ "	LOCATION = ?\r\n"
			+ "WHERE ID = ?";
	//크루가입 (크루가입시 가입날짜입력때문에 분리)
	private final String UPDATECREW = "UPDATE MEMBER\r\n" //FIXME
			+ "SET\r\n"
			+ "	CREW_ID = ?,\r\n"
			+ "	CDATE = SYSDATE\r\n"
			+ "WHERE ID = ?";

	public boolean insert(MemberDTO memberDTO) {
		System.out.println("member.MemberDAO.insert 시작");
		Connection conn=JDBCUtil.connect();
		PreparedStatement pstmt=null;
		try {
			//회원가입 ID,NAME,PASSWORD,PHONENUMBER,LOCATION
			pstmt=conn.prepareStatement(INSERT);
			pstmt.setString(1, memberDTO.getMember_id());
			pstmt.setString(2, memberDTO.getMember_name());
			pstmt.setString(3, memberDTO.getMember_password());
			pstmt.setString(4, memberDTO.getMember_phoneNumber());
			pstmt.setString(5, memberDTO.getMember_location());
			int rs = pstmt.executeUpdate();
			if(rs<=0) {
				System.err.println("member.MemberDAO.insert 실패");
				return false;
			}

		} catch (SQLException e) {
			System.out.println("member.MemberDAO.insert SQL문 실패");
		}
		JDBCUtil.disconnect(pstmt,conn);
		System.out.println("member.MemberDAO.insert 성공");
		return true;
	}
	public boolean update(MemberDTO memberDTO) {
		System.out.println("member.MemberDAO.update 시작");
		Connection conn=JDBCUtil.connect();
		PreparedStatement pstmt=null;
		try {
			//회원정보 업데이트 NAME, PROFILE, PHONENUMBER, LOCATION, ID
			if(memberDTO.getMember_condition().equals("UPDATE")) {
				pstmt=conn.prepareStatement(UPDATE);
				pstmt.setString(1, memberDTO.getMember_name());
				pstmt.setString(2, memberDTO.getMember_profile());
				pstmt.setString(3, memberDTO.getMember_phoneNumber());
				pstmt.setString(4, memberDTO.getMember_location());
				pstmt.setString(5, memberDTO.getMember_id());
			}
			//크루가입 CREW_ID, ID
			else if(memberDTO.getMember_condition().equals("UPDATECREW")) {
				pstmt=conn.prepareStatement(UPDATECREW);
				pstmt.setInt(1, memberDTO.getMember_crew_id());
				pstmt.setString(2, memberDTO.getMember_id());
			}
			int rs = pstmt.executeUpdate();
			if(rs<=0) {
				System.err.println("member.MemberDAO.update 실패");
				return false;
			}
		} catch (SQLException e) {
			System.err.println("member.MemberDAO.update SQL문 실패");
		}
		JDBCUtil.disconnect(pstmt,conn);
		System.out.println("member.MemberDAO.update 성공");
		return true;
	}
	public boolean delete(MemberDTO memberDTO) {
		System.out.println("member.MemberDAO.delete 시작");
		Connection conn=JDBCUtil.connect();
		PreparedStatement pstmt=null;
		try {
			//회원탈퇴 ID
			pstmt=conn.prepareStatement(DELETE);
			pstmt.setString(1, memberDTO.getMember_id());
			int rs = pstmt.executeUpdate();
			if(rs<=0) {
				System.err.println("member.MemberDAO.delete 실패");
				return false;
			}

		} catch (SQLException e) {
			System.err.println("member.MemberDAO.delete SQL문 실패");
		}
		JDBCUtil.disconnect(pstmt,conn);
		System.out.println("member.MemberDAO.delete 성공");
		return true;
	}
	public MemberDTO selectOne(MemberDTO memberDTO) {
		System.out.println("member.MemberDAO.selectOne 시작");
		MemberDTO data = null;
		Connection conn=JDBCUtil.connect();
		PreparedStatement pstmt=null;
		try {
			if(memberDTO.getMember_condition().equals("SERCHID")) {//아이디로 검색
				pstmt=conn.prepareStatement(SERCHID);
				pstmt.setString(1, memberDTO.getMember_id());
			}
			else if(memberDTO.getMember_condition().equals("SERCHIDPASS")) {//아이디,비밀번호로 검색
				pstmt=conn.prepareStatement(SERCHIDPASS);
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
				data.setMember_id(rs.getString("ID"));
				data.setMember_name(rs.getString("NAME"));
				data.setMember_password(rs.getString("PASSWORD"));
				data.setMember_phoneNumber(rs.getString("PHONENUMBER"));
				data.setMember_mdate(rs.getDate("MDATE"));
				data.setMember_profile(rs.getString("PROFILE"));
				data.setMember_current_point(rs.getInt("CURRENT_POINT"));
				data.setMember_total_point(rs.getInt("TOTAL_POINT"));
				data.setMember_crew_id(rs.getInt("CREW_ID"));
				data.setMember_cdate(rs.getDate("CDATE"));
				data.setMember_location(rs.getString("LOCATION"));
				}
		} catch (SQLException e) {
			System.err.println("member.MemberDAO.selectOne SQL문 실패");
		}
		JDBCUtil.disconnect(pstmt,conn);
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
			if(memberDTO.getMember_condition().equals("SERCHRANK")) {
				pstmt=conn.prepareStatement(SERCHRANK);
			}
			//크루에 속한 회원목록 조회 CREW_ID
			else if(memberDTO.getMember_condition().equals("SERCHCREW")) {
				pstmt=conn.prepareStatement(SERCHCREW);
				pstmt.setInt(1, memberDTO.getMember_crew_id());
			}
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rsCnt+"번행 출력중..");
				data = new MemberDTO();
				data.setMember_id(rs.getString("ID"));
				data.setMember_name(rs.getString("NAME"));
				data.setMember_password(rs.getString("PASSWORD"));
				data.setMember_phoneNumber(rs.getString("PHONENUMBER"));
				data.setMember_mdate(rs.getDate("MDATE"));
				data.setMember_profile(rs.getString("PROFILE"));
				data.setMember_current_point(rs.getInt("CURRENT_POINT"));
				data.setMember_total_point(rs.getInt("TOTAL_POINT"));
				data.setMember_crew_id(rs.getInt("CREW_ID"));
				data.setMember_cdate(rs.getDate("CDATE"));
				data.setMember_location(rs.getString("LOCATION"));
				datas.add(data);
				rsCnt++;
				}

		} catch (SQLException e) {
			System.err.println("member.MemberDAO.selectAll SQL문 실패");
		}
		JDBCUtil.disconnect(pstmt,conn);
		System.out.println("member.MemberDAO.selectAll 성공");
		return datas;
	}
}
