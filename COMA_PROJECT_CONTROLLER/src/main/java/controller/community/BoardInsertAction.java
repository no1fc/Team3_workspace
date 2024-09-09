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
		String path = "MAINPAGEACTION.do";//메인페이지로 이동
		boolean flagRedirect = true;//포워드 방식으로

	      //로그인 정보가 있는지 확인해주고
	      String login[] = LoginCheck.Success(request, response);
	      //사용자 아이디
	      String member_id = login[0];
		
		System.out.println("로그인 확인: " + login);//로그인 확인 로그
		//만약 로그인 정보가 없다면
		if(member_id == null) {
			//로그인 페이지로 전달해줍니다.
			path = "LOGINPAGEACTION.do";
		}
		else {
	        // 요청에서 게시글 제목과 내용을 가져옴
			
	        String boardTitle = request.getParameter("title");
	        String boardContent = request.getParameter("content");
//	        String boardLocation = request.getParameter("board_location"); //지역은 아직 구현xx
	        
	        String boardLocation = "서울특별시"; // 중프에 추가할 예정이라 하드코딩으로 작성함
	        String board_writer = member_id;//세션에 있는 사용자의 아이디
	        
	        System.out.println("게시글 제목: " + boardTitle);//제목 로그
            System.out.println("게시글 내용: " + boardContent);//내용 로그
            System.out.println("게시글 작성자: " + board_writer);//작성자 로그
	        
	        
	        // 게시글의 제목, 내용, 지역, 작성자를 DTO에 설정
	        BoardDTO boardDTO = new BoardDTO();
	        boardDTO.setModel_board_title(boardTitle);
	        boardDTO.setModel_board_content(boardContent);
	        
	        boardDTO.setModel_board_location(boardLocation);
	        boardDTO.setModel_board_writer_id(board_writer);
	        // 게시글을 데이터베이스에 저장
	        BoardDAO boardDAO = new BoardDAO();
	        
	        boolean flag = boardDAO.insert(boardDTO);//글 insert 

	        if (!flag) {
	             //실패했을때
	        	path = "INSERTBOARDPAGEACTION.do";//글작성페이지로 다시 돌아온다
	        } 
	    }
    	forward.setPath(path);
    	forward.setRedirect(flagRedirect);
		return forward;
	
	}
	
}
