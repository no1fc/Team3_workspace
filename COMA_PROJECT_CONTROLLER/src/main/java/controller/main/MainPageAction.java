package controller.main;

import java.util.ArrayList;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class MainPageAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = new ActionForward(); // 포워드 객체 생성
        String path = "main.jsp"; // 메인 페이지 경로 설정
        boolean flagRedirect = false; // 포워드 방식 사용 여부 설정 (false = forward 방식)

        // 로그인 정보가 있는지 확인
        String login = LoginCheck.Success(request, response);

        // 검색 조건과 키워드를 요청에서 가져옴
        String condition = request.getParameter("board_list"); // 검색 조건
        String keyword = request.getParameter("board_keyword"); // 검색 키워드
        
        int pageNum = 1; // 페이지 번호 초기화
        // 페이지네이션 부분
        if(request.getParameter("page") != null) {
            pageNum = Integer.parseInt(request.getParameter("page")); // 페이지 번호가 있을 경우 변환하여 저장
        }
        int boardSize = 10; // 한 페이지에 표시할 게시글 수 설정
        int minBoard = 1; // 최소 게시글 수 초기화
        int maxBoard = 1; // 최대 게시글 수 초기화
        
        // 페이지 번호에 따라 최소 및 최대 게시글 수 설정
        if(pageNum <= 1) {
            // 페이지 번호가 1 이하일 경우
            minBoard = 1; // 최소 게시글 번호를 1로 설정
            maxBoard = minBoard * boardSize; // 최대 게시글 번호 계산
        }
        else {
            // 페이지 번호가 2 이상일 경우
            minBoard = ((pageNum - 1) * boardSize) + 1; // 최소 게시글 번호 계산
            maxBoard = pageNum * boardSize; // 최대 게시글 번호 계산
        }
        

        int listNum = 0; // 게시글 총 개수를 저장할 변수 초기화
        BoardDTO boardDTO = new BoardDTO(); // 게시글 DTO 객체 생성
        BoardDAO boardDAO = new BoardDAO(); // 게시글 DAO 객체 생성
        
        // 검색 조건과 키워드가 있는지 확인
        if(condition != null) {    
            // 글 검색 부분
            if(condition.equals("아이디")) {
                // 아이디로 검색했을 때
                boardDTO.setBoard_condition("BOARD_ALL_SEARCH_PATTERN_ID"); // 조건 설정
                boardDTO.setBoard_searchKeyword(keyword); // 검색 키워드 설정
                // selectOne 메소드를 호출하여 검색 조건에 맞는 게시글 수를 가져옴
                
                BoardDTO boardCount = new BoardDTO();
                boardCount.setBoard_condition("BOARD_ONE_SEARCH_ID_COUNT");
                boardCount.setBoard_searchKeyword(keyword);
                boardCount = boardDAO.selectOne(boardCount);
                listNum = boardCount.getBoard_total();
                System.out.println("전체 페이지 개수 (아이디 검색)"+listNum);
            }
            else if(condition.equals("작성자")) {
                // 작성자로 검색했을 때
                boardDTO.setBoard_condition("BOARD_ALL_SEARCH_NAME"); // 조건 설정
                boardDTO.setBoard_searchKeyword(keyword); // 검색 키워드 설정
                
                BoardDTO boardCount = new BoardDTO();
                boardCount.setBoard_condition("BOARD_ONE_SEARCH_NAME_COUNT");
                boardCount.setBoard_searchKeyword(keyword);
                boardCount = boardDAO.selectOne(boardCount);
                
                listNum = boardCount.getBoard_total();
                System.out.println("전체 페이지 개수 (이름 검색)"+listNum);
            }
            else if(condition.equals("글 제목")) {
                // 글 제목으로 검색했을 때
                boardDTO.setBoard_condition("BOARD_ALL_SEARCH_TITLE"); // 조건 설정
                boardDTO.setBoard_searchKeyword(keyword); // 검색 키워드 설정
                
                BoardDTO boardCount = new BoardDTO();
                boardCount.setBoard_condition("BOARD_ONE_SEARCH_TITLE_COUNT");
                boardCount.setBoard_searchKeyword(keyword);
                boardCount = boardDAO.selectOne(boardCount);
                listNum = boardCount.getBoard_total();
                System.out.println("전체 페이지 개수 (제목 검색)"+listNum);
            }

        }
        else {
            // 검색 조건이 없는 경우 전체 검색
        	
            boardDTO.setBoard_condition("BOARD_ALL"); // 전체 게시글 조회 조건 설정
            
            BoardDTO boardCount = new BoardDTO();
            boardCount.setBoard_condition("BOARD_ONE_COUNT");
            boardCount = boardDAO.selectOne(boardCount);
            listNum = boardCount.getBoard_total();
            System.out.println("전체 페이지 개수 (전체 검색)"+listNum);
        }
        
        // 게시글을 페이지 단위로 잘라서 조회해야 함
        // boardDTO에 minPage와 maxPage 값을 설정하여 조회 범위를 지정해야 함
        // 현재는 주석 처리된 상태
        boardDTO.setBoard_min_num(minBoard);
        boardDTO.setBoard_max_num(maxBoard);

        // 게시글 목록 조회
        ArrayList<BoardDTO> boardList = boardDAO.selectAll(boardDTO); // 설정된 조건으로 게시글 목록 조회

        request.setAttribute("BOARD", boardList); // 조회된 게시글 목록을 요청 객체에 저장
        request.setAttribute("totalCount", listNum); // 전체 글 개수
        request.setAttribute("currentPage", pageNum); // 현재 페이지 번호
        if(login != null) {
        	request.setAttribute("MEMBER_ID", login);// 로그인 정보
        } 
        
        forward.setPath(path); // 이동할 페이지 설정
        forward.setRedirect(flagRedirect); // 페이지 이동 방식 설정 (포워드)
        return forward; // 설정된 페이지로 이동
    }
}
// 메인 페이지에서는 게시글 목록을 보여주게 됩니다 (BOARD)
