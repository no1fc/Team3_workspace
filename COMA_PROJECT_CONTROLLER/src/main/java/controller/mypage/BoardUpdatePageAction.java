package controller.mypage;


import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class BoardUpdatePageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//기본으로 넘어가야하는 페이지 와 redirect 여부를 설정
		ActionForward forward = new ActionForward();
		String path = "updateediting.jsp";
		boolean flagRedirect = false;

	      //로그인 정보가 있는지 확인해주고
	      String login[] = LoginCheck.Success(request, response);
	      //사용자 아이디
	      String member_id = login[0];
	      
		//만약 로그인 정보가 없다면
		if(member_id == null) {
			//LoginPageAction 페이지로 전달해줍니다.
			path = "LOGINPAGEACTION.do";
			//리다이렉트 방식으로 보내줍니다.
			flagRedirect = true;
		}
		else {
			BoardDAO boardDAO = new BoardDAO();
			BoardDTO data = new BoardDTO();
			//사용자가 선택한 글번호를 받아서
			data.setModel_board_num(Integer.parseInt(request.getParameter("board_num")));
			data.setModel_board_condition("BOARD_ONE");
			//model 에 전달하여 글 내용을 받아오고
			data = boardDAO.selectOne(data);
			
			//만약 데이터가 null 이라면 mypage.do 로 전달
			if(data == null) {
				request.setAttribute("MYPAGEPAGEACTION.do", false);
			}
			else {
				//해당 글 내용을 view 로 전달해줍니다.
				request.setAttribute("BOARD_NUM", data.getModel_board_num());
				request.setAttribute("BOARD_TITLE", data.getModel_board_title());
				request.setAttribute("BOARD_CONTENT", data.getModel_board_content());
				request.setAttribute("boardDelete", true);
			}

			
		}
		
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return forward;
	}
}
