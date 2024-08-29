package controller.member;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class SignUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//기본으로 넘어가야하는 페이지 와 redirect 여부를 설정
		ActionForward forward = new ActionForward();
		String path = "LOGINPAGEACTION.do";
		boolean flagRedirect = true;

		//로그인 정보가 있는지 확인해주고
		String login = LoginCheck.Success(request, response);
		
		//만약 로그인 정보가 있다면
		if(login != null) {
			//main 페이지로 전달해줍니다.
			path = "MAINPAGEACTION.do";
			//포워드 방식으로 보내줍니다.
			flagRedirect = false;
		}
		else {
			MemberDAO dao = new MemberDAO();
			MemberDTO data = new MemberDTO();
			
			data.setMember_id(request.getParameter("member_id"));//사용자 아이디
			data.setMember_password(request.getParameter("member_password"));// 비밀번호
			data.setMember_name(request.getParameter("member_name"));//이름
			data.setMember_phone(request.getParameter("member_phoneNumber"));//전환번호
			data.setMember_location(request.getParameter("member_location"));//지역
			
			//model 에 등록을 요청하고
			boolean flag = dao.insert(data);
			
			//만약 성공했다면 회원가입 성공했다는 내용을 전달해주고
			if(flag) {
				System.err.println("회원가입 성공 로그");
				request.setAttribute("SIGNUP_CHECK", flag);
			}
			//실패했다면 실패 내용을 전달해준다.
			else {
				path = "SIGNUPPAGEACTION.do";
				request.setAttribute("SIGNUP_CHECK", flag);
			}
			
		}
		
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return forward;
	}

}
