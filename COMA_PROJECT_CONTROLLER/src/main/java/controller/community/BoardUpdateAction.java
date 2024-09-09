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
        // 기본으로 넘어가야하는 페이지와 redirect 여부를 설정
        ActionForward forward = new ActionForward();
        String path = "MYPAGEPAGEACTION.do"; // 마이페이지로
        boolean flagRedirect = true; // 포워드 방식

        //로그인 정보가 있는지 확인해주고
        String login[] = LoginCheck.Success(request, response);
        //사용자 아이디
        String member_id = login[0];
        //사용자 크루 정보
        int crew_num = Integer.getInteger(login[1]);
        System.out.println("로그인 확인: " + login);

        // 만약 로그인 정보가 없다면
        if (member_id == null) {
            // 로그인 페이지로 전달
            path = "LOGINPAGEACTION.do";
            
        } else {
            // 로그인이 되어있다면
            // 업데이트 가능
            String board_title = request.getParameter("title"); // 제목 받기
            String board_writer_id = member_id; // 세션에 있는 사용자의 아이디
            String board_content = request.getParameter("content"); // 내용 받기
            int board_num = Integer.parseInt(request.getParameter("board_num")); // 번호 받기

            System.out.println("게시글 제목: " + board_title);
            System.out.println("게시글 작성자 ID: " + board_writer_id);
            System.out.println("게시글 내용: " + board_content);
            System.out.println("게시글 번호: " + board_num);

            BoardDTO boardDTO = new BoardDTO();
            BoardDAO boardDAO = new BoardDAO();

            boardDTO.setModel_board_num(board_num);
            boardDTO.setModel_board_content(board_content);
            boardDTO.setModel_board_title(board_title);

            boardDTO.setModel_board_condition("BOARD_UPDATE_CONTENT_TITLE"); // 글 수정 컨디션
            boolean updateFlag = boardDAO.update(boardDTO); // 업데이트
            
        }

        forward.setPath(path);
        forward.setRedirect(flagRedirect);
        return forward;
    }
}
