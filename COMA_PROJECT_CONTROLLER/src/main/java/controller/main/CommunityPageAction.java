package controller.main;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommunityPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);//리다이렉트로 페이지이동
		forward.setPath("boardAll.do");//글전체 페이지로 이동
		return forward;//미구현, 지역별 등으로 보낸다
	}

}