package controller.community;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.board.BoardDAO;
import model.board.BoardDTO;


public class BoardInsertAction implements Action{
//글작성페이지

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//기본으로 넘어가야하는 페이지 와 redirect 여부를 설정
		ActionForward forward = new ActionForward();
		String path = "MAINPAGEACTION.do";
		boolean flagRedirect = true;

		//로그인 정보가 있는지 확인해주고
		String login = LoginCheck.Success(request, response);

		//만약 로그인 정보가 없다면
		if(login == null) {
			//main 페이지로 전달해줍니다.
			path = "LOGINPAGEACTION.do";
		}
		else {
	        // 요청에서 게시글 제목과 내용을 가져옴
			
	        String boardTitle = request.getParameter("title");
	        String boardContent = request.getParameter("content");
//	        String boardLocation = request.getParameter("board_location");
	        String boardLocation = "서울특별시"; // 중프에 추가할 예정이라 하드코딩으로 작성함
	        String board_writer = login;//세션에 있는 사용자의 아이디
	        // 게시글 정보를 DTO에 설정
	        BoardDTO boardDTO = new BoardDTO();
	        boardDTO.setBoard_title(boardTitle);
	        boardDTO.setBoard_content(boardContent);
	        
	        boardDTO.setBoard_location(boardLocation);
	        boardDTO.setBoard_writer_id(board_writer);
	        // 게시글을 데이터베이스에 저장
	        BoardDAO boardDAO = new BoardDAO();
	        
	        boolean flag = boardDAO.insert(boardDTO);

	        if (!flag) {
	             //실패했을때
	        	path = "INSERTBOARDPAGEACTION.do";
	        	flagRedirect = true;//실패했을때 작성한 글 모두 삭제됨	
	        } 
	    }
    	forward.setPath(path);
    	forward.setRedirect(flagRedirect);
		return forward;
	
	}
	
}
