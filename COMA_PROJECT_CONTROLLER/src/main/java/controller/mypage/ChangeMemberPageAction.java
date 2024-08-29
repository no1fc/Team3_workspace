package controller.mypage;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class ChangeMemberPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//기본으로 넘어가야하는 페이지 와 redirect 여부를 설정
		ActionForward forward = new ActionForward();
		String path = "editmypage.jsp";
		boolean flagRedirect = false;

		//로그인 정보가 있는지 확인해주고
		String login = LoginCheck.Success(request, response);
		
		//만약 로그인 정보가 없다면
		if(login == null) {
			//main 페이지로 전달해줍니다.
			path = "LOGINPAGEACTION.do";
			//포워드 방식으로 보내줍니다.
			flagRedirect = true;
		}
		else {
			MemberDAO dao = new MemberDAO();
			MemberDTO data = new MemberDTO();
			
			//사용자 아이디를 model에 전달하고
			data.setMember_id(login);
			data.setMember_condition("MEMBER_SEARCH_ID");
			//전달해준 사용자 정보를 받아와 줍니다.
			data = dao.selectOne(data);
			String profile = "";
			if(data.getMember_profile() == null) {
				profile = "default.png";
			}
			else {
				profile = data.getMember_profile();
			}
			data.setMember_profile(request.getServletContext().getContextPath()+ "/profile_img/" + profile);
			request.setAttribute("data", data);
			request.setAttribute("MEMBER_ID", login);
		}
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return forward;
	}

}
