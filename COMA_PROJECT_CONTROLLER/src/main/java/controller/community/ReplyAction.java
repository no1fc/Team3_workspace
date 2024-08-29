package controller.community;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.reply.ReplyDAO;
import model.reply.ReplyDTO;

public class ReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//댓글달기
		//댓글의 작성자, 내용, 작성한 글의 번호를 받아서 insert

		//넘어갈 페이지를 주기 위한 변수
		int reply_board_num = Integer.parseInt(request.getParameter("board_id"));

		//기본으로 넘어가야하는 페이지 와 redirect 여부를 설정
		ActionForward forward = new ActionForward();
		String path = "BOARDONEPAGEACTION.do?board_num="+reply_board_num;
		boolean flagRedirect = true;

		//로그인 정보가 있는지 확인해주고
		String login = LoginCheck.Success(request, response);

		//만약 로그인 정보가 없다면
		if(login == null) {
			//로그인 페이지로 전달해줍니다.
			path = "LOGINPAGEACTION.do";
		}
		else {

			String reply_content = request.getParameter("reply_content");
			String reply_writer_id = login;//세션에 있는 사용자의 아이디
			
			ReplyDTO replyDTO = new ReplyDTO();
			ReplyDAO replyDAO = new ReplyDAO();
			replyDTO.setReply_board_num(reply_board_num);
			replyDTO.setReply_content(reply_content);
			
			replyDTO.setReply_writer_id(reply_writer_id);
			replyDAO.insert(replyDTO);
		}		
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return forward;
	}

}

