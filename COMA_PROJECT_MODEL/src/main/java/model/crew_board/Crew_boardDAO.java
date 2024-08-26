package model.crew_board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.JDBCUtil;

public class Crew_boardDAO {
	//전체글 출력
	private final String ALL ="SELECT \r\n"
			+ "	C.ID,\r\n"
			+ "	M.NAME,\r\n"
			+ "	C.CONTENT,\r\n"
			+ "	C.TITLE\r\n"
			+ "FROM \r\n"
			+ "	CREW_BOARD C\r\n"
			+ "JOIN \r\n"
			+ "    crew_board M\r\n"
			+ "ON \r\n"
			+ "    C.WRITER = M.ID";
	//최신글 5개만 출력
	private final String ALLROWNUM ="SELECT ID,NAME,CONTENT,TITLE \r\n"
			+ "FROM (\r\n"
			+ "    SELECT \r\n"
			+ "    	C.ID,\r\n"
			+ "   		M.NAME,\r\n"
			+ "   		C.CONTENT,\r\n"
			+ "   		C.TITLE\r\n"
			+ "    FROM \r\n"
			+ "    	CREW_BOARD C\r\n"
			+ "    JOIN \r\n"
			+ "    	crew_board M\r\n"
			+ "    ON \r\n"
			+ "    	C.WRITER = M.ID\r\n"
			+ "    ORDER BY C.ID DESC\r\n"
			+ ") \r\n"
			+ "WHERE \r\n"
			+ "	ROWNUM <= 5;";
	//PK로 글검색
	private final String SELECTONE ="SELECT \r\n"
			+ "	C.ID,\r\n"
			+ "	M.NAME,\r\n"
			+ "	C.CONTENT,\r\n"
			+ "	C.TITLE\r\n"
			+ "FROM \r\n"
			+ "	CREW_BOARD C\r\n"
			+ "JOIN \r\n"
			+ "    crew_board M\r\n"
			+ "ON \r\n"
			+ "    C.WRITER = M.ID\r\n"
			+ "WHERE\r\n"
			+ "	C.ID=?";
	private boolean insert(Crew_boardDTO Crew_boardDTO) {//기능 미구현
		System.out.println("crew_board.crew_boardDAO.insert 시작");
		System.out.println("crew_board.crew_boardDAO.insert 성공");
		return true;
	}
	private boolean update(Crew_boardDTO Crew_boardDTO) {//기능 미구현
		System.out.println("crew_board.crew_boardDAO.update 시작");
		System.out.println("crew_board.crew_boardDAO.update 성공");
		return true;
	}
	private boolean delete(Crew_boardDTO Crew_boardDTO) {//기능 미구현
		System.out.println("crew_board.crew_boardDAO.delete 시작");
		System.out.println("crew_board.crew_boardDAO.delete 성공");
		return true;
	}
	public Crew_boardDTO selectOne(Crew_boardDTO Crew_boardDTO) {
		System.out.println("crew_board.crew_boardDAO.selectOne 시작");
		Crew_boardDTO data = null;
		Connection conn=JDBCUtil.connect();
		PreparedStatement pstmt=null;
		try {
			//크루 커뮤니티 글 pk 검색 C.ID
			pstmt=conn.prepareStatement(SELECTONE);
			pstmt.setInt(1, Crew_boardDTO.getCrew_board_id());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("crew_boardDAO.selectOne 맞는 데이터 존재함");
				data = new Crew_boardDTO();
				data.setCrew_board_id(rs.getInt("C.ID"));
				data.setCrew_board_content(rs.getString("C.CONTENT"));
				data.setCrew_board_writer(rs.getString("M.NAME"));
				data.setCrew_board_title(rs.getString("C.TITLE"));
			}
		} catch (SQLException e) {
			System.err.println("crew_board.crew_boardDAO.selectOne SQL문 실패");
		}
		JDBCUtil.disconnect(pstmt,conn);
		System.out.println("crew_board.crew_boardDAO.selectOne 성공");
		return data;
	}
	public ArrayList<Crew_boardDTO> selectAll(Crew_boardDTO Crew_boardDTO) {
		System.out.println("crew_board.crew_boardDAO.selectAll 시작");
		ArrayList<Crew_boardDTO> datas = new ArrayList<Crew_boardDTO>();
		Crew_boardDTO data = null;
		int rsCnt=1;//로그용
		Connection conn=JDBCUtil.connect();
		PreparedStatement pstmt=null;
		try {
			//크루 커뮤니티 글 전체출력
			if(Crew_boardDTO.getCrew_board_condition().equals("ALL")) {
				pstmt=conn.prepareStatement(ALL);
			}
			//크루 커뮤니티 글 최신 5개만 출력
			else if(Crew_boardDTO.getCrew_board_condition().equals("ALLROWNUM")) {
				pstmt=conn.prepareStatement(ALLROWNUM);
			}
			else {
				System.err.println("condition값 잘못됨");
				return datas;
			}
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rsCnt+"번행 출력중..");
				data = new Crew_boardDTO();
				data.setCrew_board_id(rs.getInt("C.ID"));
				data.setCrew_board_content(rs.getString("C.CONTENT"));
				data.setCrew_board_writer(rs.getString("M.NAME"));
				data.setCrew_board_title(rs.getString("C.TITLE"));
				datas.add(data);
				rsCnt++;
			}

		} catch (SQLException e) {
			System.err.println("crew_board.crew_boardDAO.selectAll SQL문 실패");
		}
		JDBCUtil.disconnect(pstmt,conn);
		System.out.println("crew_board.crew_boardDAO.selectAll 성공");
		return datas;
	}
}
