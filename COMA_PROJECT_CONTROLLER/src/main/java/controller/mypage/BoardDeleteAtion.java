package controller.mypage;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class BoardDeleteAtion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//기본으로 넘어가야하는 페이지 와 redirect 여부를 설정
		ActionForward forward = new ActionForward();
		String path = "MYPAGEPAGEACTION.do";
		boolean flagRedirect = false;

		//로그인 정보가 있는지 확인해주고
		String login = LoginCheck.Success(request, response);
		
		//만약 로그인 정보가 없다면
		if(login == null) {
			//LoginPageAction 페이지로 전달해줍니다.
			path = "LOGINPAGEACTION.do";
			//리다이렉트 방식으로 보내줍니다.
			flagRedirect = true;
		}
		else {
			BoardDAO boardDAO = new BoardDAO();
			BoardDTO data = new BoardDTO();
			//사용자가 선택한 글번호를 받아서
			data.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
			data.setBoard_writer_id(login);
			//model 에 전달해 글을 삭제하고
			boolean flag = boardDAO.delete(data);
			
			if(!flag) {
				request.setAttribute("boardDelete", flag);
			}
			else {
				request.setAttribute("boardDelete", flag);
			}
		}
		
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return forward;
	}

}
