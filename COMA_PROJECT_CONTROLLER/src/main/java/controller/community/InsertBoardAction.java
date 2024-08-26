package controller.community;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward=new ActionForward();
		forward.setPath("BoardInsertAction");//글작성페이지로 이동
		forward.setRedirect(true);//리다이렉트 방식으로 이동
		return forward;
	}

}
