package controller.community;

import java.util.ArrayList;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.board.BoardDAO;
import model.board.BoardDTO;
import model.member.MemberDAO;
import model.member.MemberDTO;
import model.reply.ReplyDAO;
import model.reply.ReplyDTO;

public class BoardOnePageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//글하나 선택 페이지
		//boardDAO에서 selsectone하고 pk받아와서
		//제목 내용 작성자
		//댓글리스트 필요함

		//기본으로 넘어가야하는 페이지 와 redirect 여부를 설정
		ActionForward forward = new ActionForward();
		String path = "post.jsp";
		boolean flagRedirect = false;

		//로그인 정보가 있는지 확인해주고
		String login = LoginCheck.Success(request, response);

		//만약 로그인 정보가 없다면
		if(login == null) {
			//main 페이지로 전달해줍니다.
			path = "LOGINPAGEACTION.do";
		}
		else {

			//글 pk가져오기
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			MemberDAO memberDAO = new MemberDAO();
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setMember_id(login);
			memberDTO.setMember_condition("MEMBER_SEARCH_ID");
			memberDTO = memberDAO.selectOne(memberDTO);
			String filename = "";
			if(memberDTO == null) {
				filename = "default.jpg";
			}
			else {
				filename = memberDTO.getMember_profile();
			}
			request.setAttribute("member_profile", request.getServletContext().getContextPath()+ "/profile_img/" + filename); 
			
			//pk로 selectOne하기
			BoardDAO boardDAO = new BoardDAO();
			BoardDTO boardDTO = new BoardDTO();
			
			boardDTO.setBoard_num(board_num);
			boardDTO.setBoard_condition("BOARD_ONE");//컨디션이름 바꾸기
			boardDTO = boardDAO.selectOne(boardDTO);
			
			int board_cnt = boardDTO.getBoard_cnt()+1;
			boardDTO.setBoard_cnt(board_cnt);
			
			//조회수 증가
			BoardDTO data_cnt = new BoardDTO();
			data_cnt.setBoard_cnt(board_cnt);
			data_cnt.setBoard_num(board_num);
			data_cnt.setBoard_condition("BOARD_UPDATE_CNT");//컨디션 추가
			boardDAO.update(data_cnt);
			
			ReplyDAO replyDAO=new ReplyDAO();
			ReplyDTO replyDTO=new ReplyDTO();
			replyDTO.setReply_board_num(board_num);//boardDTO안에 있는 것들만 보내는 것들로
			ArrayList<ReplyDTO> replyList = replyDAO.selectAll(replyDTO);
			System.out.println(replyList); //댓글 리스트 로그
		
			request.setAttribute("BOARD", boardDTO);//글정보 넘겨주기
			request.setAttribute("REPLY", replyList);//댓글리스트 넘겨주기
			request.setAttribute("MEMBER_ID", login);//로그인정보 넘겨주기
		}
		
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return forward;
	}
}