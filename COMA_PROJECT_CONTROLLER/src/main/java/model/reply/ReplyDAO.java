package model.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.JDBCUtil;

public class ReplyDAO {
	//해당글에 댓글목록출력 reply_board_num
	private final String REPLY_SELECTALL ="";
	
	//댓글작성 reply_content, reply_board_num, reply_writer_id
	private final String REPLY_INSERT="";
	
	//댓글 내용 수정 reply_content, reply_num
	private final String REPLY_UPDATE="";
	
	//댓글 삭제 reply_num
	private final String REPLY_DELETE="";
	
//	private boolean insert(ReplyDTO replyDTO) {
//		Connection conn=JDBCUtil.connect();
//		PreparedStatement pstmt=null;
//		try {
//			//댓글작성 reply_content, reply_board_num, reply_writer_id
//			pstmt=conn.prepareStatement(INSERT);
//			pstmt.setString(1, replyDTO.getReply_content());
//			pstmt.setString(2, replyDTO.getReply_board_num());
//			pstmt.setString(3, replyDTO.getReply_writer_id());
//			int rs = pstmt.executeUpdate();
//			if(rs<=0) {
//				System.err.println("member.MemberDAO.insert 실패");
//				return false;
//			}
//
//		} catch (SQLException e) {
//			System.out.println("member.MemberDAO.insert SQL문 실패");
//		}
//		JDBCUtil.disconnect(pstmt,conn);
//		System.out.println("member.MemberDAO.insert 성공");
//		return false;
//	}
//	private boolean update(ReplyDTO replyDTO) {
//		Connection conn=JDBCUtil.connect();
//		PreparedStatement pstmt=null;
//		try {
//			//댓글 내용 수정 reply_content, reply_num
//			pstmt=conn.prepareStatement(UPDATE);
//			pstmt.setString(1, replyDTO.getReply_content());
//			pstmt.setInt(2, replyDTO.getReply_num());
//			int rs = pstmt.executeUpdate();
//			if(rs<=0) {
//				System.err.println("member.MemberDAO.update 실패");
//				return false;
//			}
//
//		} catch (SQLException e) {
//			System.out.println("member.MemberDAO.update SQL문 실패");
//		}
//		JDBCUtil.disconnect(pstmt,conn);
//		System.out.println("member.MemberDAO.update 성공");
//		return false;
//	}
//	private boolean delete(ReplyDTO replyDTO) {
//		Connection conn=JDBCUtil.connect();
//		PreparedStatement pstmt=null;
//		try {
//			//댓글 삭제 reply_num
//			pstmt=conn.prepareStatement(DELETE);
//			pstmt.setString(1, replyDTO.getReply_num());
//			int rs = pstmt.executeUpdate();
//			if(rs<=0) {
//				System.err.println("member.MemberDAO.delete 실패");
//				return false;
//			}
//
//		} catch (SQLException e) {
//			System.out.println("member.MemberDAO.delete SQL문 실패");
//		}
//		JDBCUtil.disconnect(pstmt,conn);
//		System.out.println("member.MemberDAO.delete 성공");
//		return false;
//	}
//	
//	private ReplyDTO selectOne(ReplyDTO replyDTO){//기능 미구현
//		return null;
//	}
//	
//	public ArrayList<ReplyDTO> selectAll(ReplyDTO replyDTO){
//		System.out.println("reply.ReplayDAO.selectAll 시작");
//		ArrayList<ReplyDTO> datas = new ArrayList<ReplyDTO>();
//		int rsCnt=1;
//		Connection conn = JDBCUtil.connect();
//		PreparedStatement pstmt = null;
//		try {
//			//해당글에 댓글목록출력 reply_board_num
//			pstmt=conn.prepareStatement(SELECTALL);
//			pstmt.setInt(1,replyDTO.getReply_board_num());
//			ResultSet rs = pstmt.executeQuery();
//			while(rs.next()) {
//				System.out.println(rsCnt+"번행 출력중...");
//				ReplyDTO data = new ReplyDTO();
//				data.setReply_num(rs.getInt("R.REPLY_NUM"));
//				data.setReply_content(rs.getString("R.REPLY_CONTENT"));
//				data.setReply_board_num(rs.getInt("B.BOARD_NUM"));
//				data.setReply_writer_id(rs.getString("M.MEMBER_NAME"));
//				datas.add(data);
//				rsCnt++;
//			}
//			
//		}catch(SQLException e) {
//			System.err.println("reply.ReplyDAO selectAll SQL문 실패");
//		}
//		JDBCUtil.disconnect(pstmt, conn);
//		System.out.println("reply.ReplayDAO.selectAll 끝");
//		return datas;
//		
//	}
}