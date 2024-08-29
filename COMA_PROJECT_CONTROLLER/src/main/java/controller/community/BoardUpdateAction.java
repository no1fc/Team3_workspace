package controller.community;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class BoardUpdateAction implements Action {

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
	         //main 페이지로 전달해줍니다.
	         path = "LOGINPAGEACTION.do";
	      }
	      else {
	         //업데이트 가능
	         String board_title = request.getParameter("title");
	         String board_writer_id = login;//세션에 있는 사용자의 아이디
	         String board_content = request.getParameter("content");
	         int board_num=Integer.parseInt(request.getParameter("board_num"));


	         BoardDTO boardDTO = new BoardDTO();
	         BoardDAO boardDAO = new BoardDAO();

	         boardDTO.setBoard_num(board_num);
	         boardDTO.setBoard_content(board_content);
	         boardDTO.setBoard_title(board_title);

	         boardDTO.setBoard_condition("BOARD_UPDATE_CONTENT_TITLE");
	         boardDAO.update(boardDTO);//업데이트

	         request.setAttribute("MEMBER_ID", login);//로그인한 사용자 정보


	      }
	      forward.setPath(path);
	      forward.setRedirect(flagRedirect);
	      return forward;


	   }


}
