package controller.member;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		//기본으로 넘어가야하는 페이지 와 redirect 여부를 설정
		ActionForward forward = new ActionForward();
		String path = "MAINPAGEACTION.do";
		boolean flagRedirect = false;

		//로그인 정보가 있는지 확인해주고
		String login = LoginCheck.Success(request, response);

		//만약 로그인 정보가 없다면
		if(login == null) {
			MemberDAO dao = new MemberDAO();
			MemberDTO data = new MemberDTO();
			//사용자의 아이디와 비밀번호를 model 로 전달하여 확인하고
			System.out.println(request.getParameter("email") + " " + request.getParameter("password"));
			data.setMember_id(request.getParameter("email"));
			data.setMember_password(request.getParameter("password"));
			data.setMember_condition("MEMBER_SEARCH_ID_PASSWORD");//추가할 예정
			data = dao.selectOne(data);
			
			//만약 data 가 null 을 반환하면
			if(data == null) {
				System.err.println("(LoginAction.java) data null 로그");
				//로그인을 실패한 것 이기 때문에 login.jsp 페이지로 반환합니다.
				path = "LOGINPAGEACTION.do";
				//로그인 여부를 전달하여 사용자가 로그인 여부를 확인할 수 있도록 만들어 줍니다.
				request.setAttribute("LOGIN_CHECK", false);
			}
			//null 이 아니라면
			else {
				System.err.println("(LoginAction.java) data.getMember_id 로그 : " + data.getMember_id());
				//session 에 값을 저장해둡니다.
				HttpSession session = request.getSession();
				session.setAttribute("MEMBER_ID", data.getMember_id());
				//자동로그인을 등록했다면 cookie 에 로그인 정보를 저장해줍니다.	
				String auto = (String)request.getParameter("AUTO_LOGIN");
				System.out.println(auto);
				if(auto != null){
					Cookie cookie = new Cookie("MEMBER_ID", data.getMember_id());
					// 쿠키 유효 시간 설정 (7일)
					cookie.setMaxAge(60 * 60 * 24 * 7); // 7일 동안 유지
					//쿠키를 추가헤줍니다.
					response.addCookie(cookie);
				}
				//로그인 여부를 전달하여 사용자가 로그인 여부를 확인할 수 있도록 만들어 줍니다.
				request.setAttribute("LOGIN_CHECK", true);
			}
		}
		
		//최종적으로 보낼 내용을 입력하여 값을 반환해줍니다.
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return  forward;
	}

}
