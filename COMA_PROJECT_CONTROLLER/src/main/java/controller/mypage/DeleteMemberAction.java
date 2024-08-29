package controller.mypage;

import controller.common.Action;
import controller.common.ActionForward;
import controller.funtion.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class DeleteMemberAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//기본으로 넘어가야하는 페이지 와 redirect 여부를 설정
		ActionForward forward = new ActionForward();
		String path = "MYPAGEPAGEACTION.do";
		boolean flagRedirect = true;

		//로그인 정보가 있는지 확인해주고
		String login = LoginCheck.Success(request, response);
		
		//만약 로그인 정보가 없다면
		if(login == null) {
			//main 페이지로 전달해줍니다.
			path = "LOGINPAGEACTION.do";
		}
		else {
			MemberDTO data = new MemberDTO();
			MemberDAO memberDAO = new MemberDAO();
			//사용자 아이디를 DTO에 등록
			data.setMember_id(login);
			System.out.println("DeletememberAction.java 로그 : "+login);
			//delete 를 성공하지 못했다면 Mypage로 보냅니다.
			boolean flag = memberDAO.delete(data);
			if(flag) {//멤버 삭제에 성공했다면 logout 페이지로 넘어갑니다.
				path = "LOGOUTPAGEACTION.do";
			}
		}
		forward.setPath(path);
		forward.setRedirect(flagRedirect);
		return forward;
	}

}
