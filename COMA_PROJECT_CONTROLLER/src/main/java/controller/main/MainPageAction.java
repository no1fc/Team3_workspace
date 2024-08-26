package controller.main;

import java.util.ArrayList;

import controller.common.Action;
import controller.common.ActionForward;
import controller.member.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.board.BoardDAO;
import model.board.BoardDTO;


public class MainPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String path = "main.jsp";
		boolean flagRedirect = false;

		//로그인 정보가 있는지 확인해주고
		String login = LoginCheck.Success(request, response);
		
		 String condition = request.getParameter("condition");
         String keyword = request.getParameter("keyword");
         //page네이션부분
         int pageNum = Integer.parseInt(request.getParameter("pageNum"));//pageNum수정
         int pageSize = 10;
         int minPage = 1;
         int maxPage = 0;
         if(pageNum <= 1) {
        	 //maxPage와 pagemin이 페이지 넘버1로 고정
        	 minPage = 1;
        	 maxPage = minPage *pageSize;
         }
         else {
        	  minPage = ((pageNum - 1) * pageSize) + 1;
        	  maxPage = pageNum * pageSize ;
         }
         
         int listNum = 0;
         BoardDTO boardDTO = new BoardDTO();
         BoardDAO boardDAO = new BoardDAO();
         //만약 키워드와 커디션이 없다면 안돼 돌아가 저리가
         //있다면 키워드별로 검색
         //id, name, 제목으로 검색
         //글검색 부분
         if(condition.equals("뷰에서 보내주는 아이디 검색")) {
        	 //아이디로 검색했을 때는
        	 //dao의 selectAll의 condition ALLSEARCH_ID
        	 boardDTO.setBoard_condition("ALLSERCH_ID");
        	 boardDTO.setBoard_serchKeyword(keyword);
        	 //selectone해서 페이지 전체 글 개수를 받는다
        	 //dto를 사용해서 보낸다.
        	 listNum = boardDAO.selectOne(boardDTO).getBoard_pagemax();
        	 
         }
         else if(condition.equals("뷰에서 보내주는 이름검색")) {
        	 //allsearch_name
        	 boardDTO.setBoard_condition("ALLSEARCH_NAME");
        	 boardDTO.setBoard_serchKeyword(keyword);
        	 //
        	 listNum = boardDAO.selectOne(boardDTO).getBoard_pagemax();
         }
         else if(condition.equals("뷰에서 보내주는 제목검색")) {
        	 //allsearch_title
        	 boardDTO.setBoard_condition("ALLSEARCH_TITLE");
        	 boardDTO.setBoard_serchKeyword(keyword);
        	 listNum = boardDAO.selectOne(boardDTO).getBoard_pagemax();
         }
         else {
        	 boardDTO.setBoard_condition("ALL");
        	 //ALL
        	 listNum = boardDAO.selectOne(boardDTO).getBoard_pagemax();
         }
        //잘라서 보내줘야한다.
         //어케 자르누
         //boardDTO에 pagemin pagemax 사용해서 잘라
         boardDTO.setBoard_pagemin(minPage);
         boardDTO.setBoard_pagemax(maxPage);
         
         
         ArrayList<BoardDTO> boardList=boardDAO.selectAll(boardDTO);//여따가
         
         request.setAttribute("board", boardList);
        
         
         
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return forward;
		
		
	}
}
	//메인페이지에서는 글목록보여주겠지 BOARD
	

