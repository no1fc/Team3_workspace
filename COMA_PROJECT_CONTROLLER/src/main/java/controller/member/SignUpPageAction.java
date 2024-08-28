package controller.member;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignUpPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//기본으로 넘어가야하는 페이지 와 redirect 여부를 설정
		ActionForward forward = new ActionForward();
		String path = "join.jsp";
		boolean flagRedirect = false;
		
		//로그인 정보가 있는지 확인해주고
		String login = LoginCheck.Success(request, response);
		
		//만약 로그인 정보가 있다면
		if(login != null) {
			//main 페이지로 전달해줍니다.
			path = "MAINPAGEACTION.do";
			//포워드 방식으로 보내줍니다.
			flagRedirect = true;
		}
		
		//회원가입 페이지 path 로그
		System.out.println("SignUppageAction path 로그 : "+path + request.getParameter("MEMBER_ID"));
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return forward;
	}

}
