package controller.common;

import java.util.Iterator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		forward.setPath("ckeditorTest01.jsp");
		forward.setRedirect(true);
		return forward;
	}

}
