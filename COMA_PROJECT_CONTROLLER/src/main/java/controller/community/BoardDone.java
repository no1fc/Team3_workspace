package controller.community;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardDone implements Action {

//취소
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

        // 글 작성 취소 시, 전체 게시글 페이지로 리다이렉트
        forward.setRedirect(true);
        forward.setPath("boardAll.do");

        return forward;
	}
	
}
