package controller.community;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.reply.ReplyDAO;
import model.reply.ReplyDTO;

public class ReplyUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int board_num = Integer.parseInt(request.getParameter("board_id"));//되돌아갈 글번호
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
			//업데이트 가능
			String reply_writer_id = login;//세션에 있는 사용자의 아이디
			String reply_content = request.getParameter("reply_content");
			int reply_num=Integer.parseInt(request.getParameter("reply_id"));

			
			ReplyDTO replyDTO = new ReplyDTO();
			ReplyDAO replyDAO = new ReplyDAO();
			replyDTO.setReply_num(reply_num);
			replyDTO.setReply_content(reply_content);
			
			
			replyDAO.update(replyDTO);//업데이트
			

		}
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return forward;

	}

}
