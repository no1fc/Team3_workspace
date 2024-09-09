package controller.community;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.reply.ReplyDAO;
import model.reply.ReplyDTO;

public class ReplyUpdateAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        int board_num = Integer.parseInt(request.getParameter("board_id")); // 되돌아갈 글 번호
        ActionForward forward = new ActionForward();
        String path = "BOARDONEPAGEACTION.do?board_num=" + board_num; 
        // 댓글 수정 후 해당 글 보는 페이지로 이동하기 위해 글 번호를 get 방식으로 전달
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
            // 댓글 업데이트 가능
            String reply_writer_id = member_id; // 세션에 있는 사용자의 아이디
            String reply_content = request.getParameter("reply_content"); // 댓글 내용
            int reply_num = Integer.parseInt(request.getParameter("reply_id")); // 댓글 번호

            System.out.println("댓글 번호: " + reply_num);
            System.out.println("사용자 ID: " + reply_writer_id);
            System.out.println("댓글 수정 내용: " + reply_content);

            ReplyDTO replyDTO = new ReplyDTO();
            ReplyDAO replyDAO = new ReplyDAO();
            replyDTO.setModel_reply_num(reply_num); // 댓글 번호
            replyDTO.setModel_reply_content(reply_content); // 댓글 내용

            boolean updateResult = replyDAO.update(replyDTO); // 업데이트
            
        }

        forward.setPath(path);
        forward.setRedirect(flagRedirect);
        return forward;
    }
}
