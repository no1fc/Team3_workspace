package model.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.JDBCUtil;

public class ReplyDAO {
	private final String REPLY_SERCH ="SELECT R.ID AS RID, R.REPLY_BOARD_ID AS BID, R.CONTENT, M.NAME \r\n"
			+ "FROM REPLY R \r\n"
			+ "JOIN BOARD B ON R.REPLY_BOARD_ID = B.ID \r\n"
			+ "JOIN MEMBER M ON R.REPLY_MEMBER_ID = M.ID \r\n"
			+ "WHERE R.REPLY_BOARD_ID = ?;";
	
	private boolean insert(ReplyDTO replyDTO) {//기능 미구현
		
		return false;
	}
	private boolean update(ReplyDTO replyDTO) {//기능 미구현
		return false;
	}
	private boolean delete(ReplyDTO replyDTO) {//기능 미구현
		return false;
	}
	private ReplyDTO selectOne(ReplyDTO replyDTO){//기능 미구현
		return replyDTO;
	}
	public ArrayList<ReplyDTO> selectAll(ReplyDTO replyDTO){
		System.out.println("reply.ReplayDAO.selectAll 시작");
		ArrayList<ReplyDTO> datas = new ArrayList<ReplyDTO>();
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(REPLY_SERCH);
			pstmt.setInt(1,replyDTO.getReply_board_id());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ReplyDTO data = new ReplyDTO();
				data.setReply_board_id(rs.getInt("RID"));
				data.setReply_content(rs.getString("CONTENT"));
				data.setReply_id(rs.getInt("BID"));
				data.setReply_member_id(rs.getString("NAME"));
				datas.add(data);
			}
			
		}catch(SQLException e) {
			System.err.println("reply.ReplyDAO selectAll SQL문 실패");
		}
		JDBCUtil.disconnect(pstmt, conn);
		System.out.println("reply.ReplayDAO.selectAll 성공");
		return datas;
		
	}
}
