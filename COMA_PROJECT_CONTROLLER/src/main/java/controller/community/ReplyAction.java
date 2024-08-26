package controller.community;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletResponse;
import model.reply.ReplyDAO;
import model.reply.ReplyDTO;

public class ReplyAction implements Action{

	@Override
	public ActionForward execute(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response) {
		// 요청에서 게시글 번호와 댓글 내용을 가져옴
        
        String replyContent = request.getParameter("reply_content");
        int board_id = Integer.parseInt(request.getParameter("board_id")); // 문자열을 정수형으로 변환
        String reply_member_id = request.getParameter("reply_member_id");
        
        // 댓글 정보를 DTO에 설정
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReply_board_num(board_id);
        replyDTO.setReply_content(replyContent);
        ///이름 reply_member_id
        replyDTO.setReply_writer_id(reply_member_id);
        
        // 댓글을 데이터베이스에 저장
        ReplyDAO replyDAO = new ReplyDAO();
//        boolean flage = replyDAO.insert(replyDTO);
        
        ActionForward forward = new ActionForward();
        
//        if (flage) {
//            // 댓글 추가 성공 시, 해당 게시글 페이지로 리다이렉트
//            forward.setRedirect(true);//포워드 방식으로
//            forward.setPath("BoardOnePage.do?board_id=" + board_id);
//        } else {
//            // 댓글 추가 실패 시, 에러 페이지 
//            forward.setRedirect(true);
//            forward.setPath("errorPage.do");  //에러 페이지로 리다이렉트
//        }
        
        return forward;
    }
}