package controller.community;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class BoardInsertAction implements Action{
//글작성페이지

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
	        // 요청에서 게시글 제목과 내용, 번호을 가져옴
			int board_id=Integer.parseInt(request.getParameter("board_id"));
	        String boardTitle = request.getParameter("board_title");
	        String boardContent = request.getParameter("board_content");
	        
	        // 게시글 정보를 DTO에 설정
	        BoardDTO boardDTO = new BoardDTO();
	        boardDTO.setBoard_title(boardTitle);
	        boardDTO.setBoard_content(boardContent);
	        boardDTO.setBoard_id(board_id);
	        
	        // 게시글을 데이터베이스에 저장
	        BoardDAO boardDAO = new BoardDAO();
	        boolean flag = boardDAO.insert(boardDTO);

	        ActionForward forward = new ActionForward();

	        if (flag) {
	            // 글 작성 성공 시, 전체 게시글 페이지로 리다이렉트
	            forward.setRedirect(true);
	            forward.setPath("boardAll.do");
	        } else {
	            // 글 작성 실패 시, 메인 페이지로 리다이렉트
	            forward.setRedirect(true);
	            forward.setPath("mainPage.do");
	        }

	        return forward;
	    }
	
	}
	

