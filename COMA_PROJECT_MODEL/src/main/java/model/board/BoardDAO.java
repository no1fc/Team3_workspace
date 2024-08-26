package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

import model.JDBCUtil;

public class BoardDAO {
	// 전체 글 출력
	private final String ALL = "SELECT \r\n"
			+ "ID AS BOARD_ID,\r\n"
			+ "TITLE AS BOARD_TITLE,\r\n"
			+ "CONTENT AS BOARD_CONTENT,\r\n"
			+ "CNT AS BOARD_CNT,\r\n"
			+ "LOCATION AS BOARD_LOCATION,\r\n"
			+ "BOARD_MEMBER_ID\r\n"
			+ "\r\n"
			+ "FROM BOARD;";
	// 최신글5개 검색 ROUWNUM 
	private final String ALLROWNUM = "SELECT \r\n"
			+ "    ID,            -- 게시글 번호\r\n"
			+ "    TITLE,         -- 게시글 제목\r\n"
			+ "    CONTENT,       -- 게시글 내용\r\n"
			+ "    CNT,           -- 조회수\r\n"
			+ "    LOCATION,      -- 작성자 지역\r\n"
			+ "    NAME           -- 작성자 이름\r\n"
			+ "FROM (\r\n"
			+ "    SELECT \r\n"
			+ "        B.ID, \r\n"
			+ "        B.TITLE, \r\n"
			+ "        B.CONTENT, \r\n"
			+ "        B.CNT, \r\n"
			+ "        B.LOCATION, \r\n"
			+ "        M.NAME\r\n"
			+ "    FROM \r\n"
			+ "        BOARD B\r\n"
			+ "    JOIN \r\n"
			+ "        MEMBER M\r\n"
			+ "    ON \r\n"
			+ "        B.BOARD_MEMBER_ID = M.ID\r\n"
			+ "    ORDER BY \r\n"
			+ "        B.ID DESC   -- 게시글 번호를 기준으로 내림차순 정렬\r\n"
			+ ") \r\n"
			+ "WHERE ROWNUM <= 5;  ";
	// 전체 글 몇 개 인지
	private final String ALLCOUNT = "SELECT COUNT(ID) AS TOTAL_COUNT\r\n"
			+ "FROM BOARD;";
	// 작성자로 글 검색 
	private final String SERCH_ID = "SELECT \r\n"
			+ "    B.ID AS BOARD_ID, \r\n"
			+ "    B.TITLE AS BOARD_TITLE, \r\n"
			+ "    B.CONTENT AS BOARD_CONTENT, \r\n"
			+ "    M.NAME AS MEMBER_NAME, \r\n"
			+ "    B.CNT AS BOARD_CNT, \r\n"
			+ "    B.LOCATION AS BOARD_LOCATION\r\n"
			+ "FROM BOARD B\r\n"
			+ "JOIN MEMBER M \r\n"
			+ "ON B.BOARD_MEMBER_ID = M.ID\r\n"
			+ "WHERE M.ID = '?';";
	//사용자 id로 작성한 글 목록 출력
	private final String ALLSERCH_ID = "SELECT \r\n"
			+ "    B.ID AS BOARD_ID,               -- 게시글 번호\r\n"
			+ "    B.TITLE AS BOARD_TITLE,        -- 게시글 제목\r\n"
			+ "    B.CONTENT AS BOARD_CONTENT,    -- 게시글 내용\r\n"
			+ "    B.CNT AS BOARD_CNT,            -- 게시글 조회수\r\n"
			+ "    B.LOCATION AS BOARD_LOCATION,  -- 작성자 지역\r\n"
			+ "    B.BOARD_MEMBER_ID AS BOARD_MEMBER_ID  -- 글 작성자의 사용자 ID\r\n"
			+ "FROM \r\n"
			+ "    BOARD B";
	// 게시글 작성
	private final String BOARD_INSERT ="INSERT INTO BOARD (ID, TITLE, CONTENT, CNT, LOCATION, BOARD_MEMBER_ID)\r\n"
			+ "VALUES (?,'?','?',?,'?','?');"; 
	// 게시글 삭제
	private final String BOARD_DELETE ="DELETE FROM BOARD\r\n"
			+ "WHERE ID = ?;"; // 

	public boolean insert(BoardDTO boardDTO) {//PK 제목 내용 받아서 
		System.out.println("board.BoardDAO.insert 시작");
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(BOARD_INSERT); //SQL구문 설정 
			pstmt.setInt(1,boardDTO.getBoard_id());         //게시판 id
			pstmt.setString(2,boardDTO.getBoard_title());   //제목
			pstmt.setString(3,boardDTO.getBoard_content()); //내용
			pstmt.setString(4,boardDTO.getBoard_location());//지역
			
		}
		catch(SQLException e) {
			System.err.println("board.BoardDAO.insert SQL문 실패");
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		System.out.println("board.BoardDAO.insert 성공");
		return true;
	}
	private boolean update(BoardDTO boardDTO) {//기능 미구현 
		System.out.println("board.BoardDAO.update 시작");
		System.out.println("board.BoardDAO.update 성공");
		return true;
	}
	public boolean delete(BoardDTO boardDTO) {//글 삭제
		System.out.println("board.BoardDAO.delete 시작");
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt =null;
		try {
			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, boardDTO.getBoard_id()); //pk값 받아서 삭제 
			int result = pstmt.executeUpdate();
			if(result <= 0) {//삭제 실패시 false 반환
				return false;
			}
		}catch(SQLException e) {
			System.err.println("board.BoardDAO.delete SQL문 실패");
			return false; //예외 발생시 false 반환 
		}finally {
			JDBCUtil.disconnect(pstmt, conn); //연결해제
		}
		System.out.println("board.BoardDAO.delete 성공");
		return true; //삭제 성공시 true 반환 
	}
	public BoardDTO selectOne(BoardDTO boardDTO) {
		System.out.println("board.BoardDAO.selectOne 시작");
		BoardDTO data =null;
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		int rsCnt=1; //조회수
		
		try {
			pstmt = conn.prepareStatement(SERCH_ID);
			pstmt.setInt(1,boardDTO.getBoard_id());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new BoardDTO();
				data.setBoard_title(rs.getString("BOARD_TITLE"));
				data.setBoard_content(rs.getString("BOARD_CONTENT"));
				data.setWriter(rs.getString("MEMVER_NAME"));
				data.setBoard_cnt(rs.getInt("BOARD_CNT"));
				data.setBoard_location(rs.getString("BOARD_LOCATION"));
				rsCnt++;
				
			}
		}catch(SQLException e) {
			System.err.println("board.BoardDAO.selectOne SQL문 실패");
			
		}
		JDBCUtil.disconnect(pstmt, conn);		
		System.out.println("board.BoardDAO.selectOne 성공");
     return data;
	}
	public ArrayList<BoardDTO> selectAll(BoardDTO boardDTO){
		System.out.println("board.BoardDAO.selectAll 시작");
		ArrayList<BoardDTO> datas = new ArrayList<BoardDTO>();
		Connection conn =JDBCUtil.connect();
		PreparedStatement pstmt = null;
		int rsCnt=1;
		try {
			String condition =  boardDTO.getCondition();
			  // 전체 글 출력
			if(condition.equals("ALL")) {
				pstmt = conn.prepareStatement(ALL);	

			  // 최신글5개 검색 ROUWNUM 
			}else if(condition.equals("ALLROWNUM")){
				pstmt = conn.prepareStatement(ALLROWNUM);
			} // 전체 글 몇 개 인지 출력
			else if(condition.equals("ALLCOUNT")){
				pstmt = conn.prepareStatement(ALLCOUNT);

			} //사용자 id로 작성한 글 목록 출력
			else if(condition.equals("ALLSERCH_ID")) {
				pstmt = conn.prepareStatement(ALLSERCH_ID);

			}else {
				System.err.println("컨디션값 에러");
			}
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO data = new BoardDTO();
				 data.setBoard_id(rs.getInt("BOARD_ID"));
				 data.setBoard_title(rs.getString("BOARD_TITLE"));
				 data.setBoard_content(rs.getString("BOARD_CONTENT"));
				 data.setBoard_cnt(rs.getInt("BOARD_CNT"));
				 data.setBoard_location(rs.getString("BOARD_LOCATION"));
				 data.setWriter(rs.getString("BOARD_MEMBER_ID"));
				 datas.add(data);
				 rsCnt++;
			}

		} catch (SQLException e) {
			System.err.println("board.BoardDAO.selectAll SQL문 실패");
		}
		JDBCUtil.disconnect(pstmt, conn);
		
		System.out.println("board.BoardDAO.selectAll 성공");
		return datas;

	}
}

