package controller.gym;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GymMainPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String path = "GymMainPage.jsp"; // view에서 알려줄 예정
		boolean flag_Redirect = false; // 값을 전달해야하게 때문에 forward 방식으로 전달해야한다.
		
		
		//TODO 페이지 네이션을 위해 암벽장 전체 개수를 요청 selectOne
		
		//TODO 페이지 네이션을 위한 페이지 개수를 구하는 로직을 구현
		
		//TODO 페이지네이션 값과 condition 값을 DTO에 추가하여 (6개출력)
		//TODO 암벽장 리스트를 model에 요청 selectAll
		//TODO 암벽장 테이블에서 받을 값(암벽장 번호 / 암벽장 이름 / 암벽장 주소)
		
		//TODO 암벽장 리스트를 View로 전달
		
		//TODO 암벽장 전체 개수를 View로 전달
		
		//TODO 암벽장 페이지 페이지 번호를 전달.
		
		ActionForward forward = new ActionForward();
		forward.setPath(path);
		forward.setRedirect(flag_Redirect);		
		return forward;
	}

}
