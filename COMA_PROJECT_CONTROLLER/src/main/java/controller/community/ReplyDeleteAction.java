package controller.community;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.reply.ReplyDAO;
import model.reply.ReplyDTO;

public class ReplyDeleteAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        int board_num = Integer.parseInt(request.getParameter("board_num")); // 댓글이 속한 게시글 번호
        ActionForward forward = new ActionForward();
        String path = "BOARDONEPAGEACTION.do?board_num=" + board_num; 
        // 댓글 삭제 후 해당 글 하나 보는 페이지로 돌아오기 위해 글의 번호를 get 방식으로 전달
        boolean flagRedirect = true; // 리다이렉트 방식 사용

        //로그인 정보가 있는지 확인해주고
        String login[] = LoginCheck.Success(request, response);
        //사용자 아이디
        String member_id = login[0];
        
        System.out.println("로그인 확인: " + login);

        // 만약 로그인 정보가 없다면
        if (member_id == null) {
            // 로그인 페이지로 전달
            path = "LOGINPAGEACTION.do";
           
        } else {
            // 댓글 삭제
            int reply_num = Integer.parseInt(request.getParameter("replyId")); // 댓글 PK
            String reply_id = member_id; // 세션에 있는 사용자의 아이디

            System.out.println("댓글 번호: " + reply_num);
            System.out.println("사용자 ID: " + reply_id);

            ReplyDTO replyDTO = new ReplyDTO();
            ReplyDAO replyDAO = new ReplyDAO();
            replyDTO.setModel_reply_writer_id(reply_id); // 사용자 아이디
            replyDTO.setModel_reply_num(reply_num); // 댓글 번호

            boolean deleteResult = replyDAO.delete(replyDTO); // 댓글 삭제
            
        }

        forward.setPath(path);
        forward.setRedirect(flagRedirect);
        return forward;
    }
}
