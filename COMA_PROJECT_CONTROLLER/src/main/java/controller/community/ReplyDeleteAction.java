package controller.community;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.reply.ReplyDAO;
import model.reply.ReplyDTO;


public class ReplyDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		//기본으로 넘어가야하는 페이지 와 redirect 여부를 설정
		ActionForward forward = new ActionForward();
		String path = "BOARDONEPAGEACTION.do?board_num="+board_num;
		boolean flagRedirect = true;

		//로그인 정보가 있는지 확인해주고
		String login = LoginCheck.Success(request, response);
		
		//만약 로그인 정보가 없다면
		if(login == null) {
			//main 페이지로 전달해줍니다.
			path = "LOGINPAGEACTION.do";
		}
		else {
			int reply_num = Integer.parseInt(request.getParameter("replyId"));
			String reply_id = login;//세션에 있는 사용자의 아이디
			
			ReplyDTO replyDTO = new ReplyDTO();
			ReplyDAO replyDAO = new ReplyDAO();
			//사용자 아이디를 DTO에 등록
			replyDTO.setReply_writer_id(reply_id);
			replyDTO.setReply_num(reply_num);
			//delete 를 성공하지 못했다면 Mypage로 보냅니다.
			replyDAO.delete(replyDTO);
		
		}
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return forward;
	}

}

