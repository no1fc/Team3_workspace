package controller.mypage;

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

public class MypagePageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//기본으로 넘어가야하는 페이지 와 redirect 여부를 설정
		ActionForward forward = new ActionForward();
		String path = "mypage.jsp";
		boolean flagRedirect = false;

		//로그인 정보가 있는지 확인해주고
		String login = LoginCheck.Success(request, response);
		//만약 로그인 정보가 없다면
		if(login == null) {
			//main 페이지로 전달해줍니다.
			path = "LOGINPAGEACTION.do";
			//리다이렉트 방식으로 보내줍니다.
			flagRedirect = true;
		}
		else {
			MemberDAO memberDAO = new MemberDAO();
			MemberDTO memberDTO = new MemberDTO();
			MemberDTO babymember = new MemberDTO();
			BoardDAO boardDAO = new BoardDAO();
			BoardDTO boardDTO = new BoardDTO();
			
			System.out.println("MyPage 로그인 정보 로그 : "+login);
			//사용자 정보 이름, 전화번호, 아이디, 프로필 이미지, 권한 전달
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
			memberDTO.setMember_profile(request.getServletContext().getContextPath()+ "/profile_img/" + filename);
			
			//관리자 권한이 있다면
			if(memberDTO.getMember_role().equals("T")) {
				//사용자 정보 이름, 전화번호, 아이디, 프로필 이미지, 권한 전달
				babymember.setMember_condition("MEMBER_ALL_NEW");
				ArrayList<MemberDTO> member_list = memberDAO.selectAll(babymember);
				request.setAttribute("MEMBER_LIST", member_list);
			}
			
			//사용자가 입력한 글 목록 출력 후 전달
			//boardDTO.setBoard_writer_id(login); 모델에 mypage에서 쓸 컨디션 추가 부탁해야함
			boardDTO.setBoard_searchKeyword(login);
			//이후 구현 예정
			System.err.println("MyPagePageAction 로그 페이지 네이션 하드코딩 해둔 상태");
			boardDTO.setBoard_min_num(0);//페이지 네이션 하드코딩했음
			boardDTO.setBoard_max_num(300);//페이지 네이션 하드코딩했음
			//------------------------------------------------------------------
			boardDTO.setBoard_condition("BOARD_ALL_SEARCH_MATCH_ID");
			ArrayList<BoardDTO> boardList = boardDAO.selectAll(boardDTO);
			System.out.println(boardList);
			request.setAttribute("MEMBER_ID", login);
			request.setAttribute("MEMBERDATA", memberDTO);
			request.setAttribute("BOARD", boardList);
		}
		
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return forward;
	}

}
