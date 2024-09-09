package controller.gym;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CrewBattleApplicationAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String path = ".jsp"; // view에서 알려줄 예정 alert 창 띄우기 위한 JavaScript 페이지
		boolean flag_Redirect = false; // 값을 전달해야하게 때문에 forward 방식으로 전달해야한다.
		ActionForward forward = new ActionForward();
		
		//------------------------------------------------------------
		//TODO 해당 페이지에서 공통으로 사용할 변수 and 객체
		//View에서 전달해주는 (크루전 번호 / 크루전 게임일 / 암벽장 번호)변수
		
		//------------------------------------------------------------
		//암벽장에 크루전을 신청하는 사용자가 크루장인지 확인 로직 시작
		//TODO (사용자 아이디 / 크루 번호) 를 Crew DTO에 추가합니다.
		//TODO Crew selectOne으로 해당 사용자가 크루장인지 확인합니다.
		//null : error_message 크루전은 크루장만 개최하실 수 있습니다.
		// 		 path 암벽장 페이지
		//암벽장에 크루전을 신청하는 사용자가 크루장인지 확인 로직 종료
		//------------------------------------------------------------
		//크루전 개최 되어있는지 확인하기 위한 로직 시작
		//TODO (크루전 번호 / 크루전 게임일) 을 Battle DTO에 추가합니다.
		//TODO Battle selectOne으로 해당 크루전이 개최되어 있는지 확인합니다.
		//TODO 개최되어 있다면 게임일을 확인해줍니다.
		//만약 게임일이 없다면 : 게임일을 추가 Battle update 를 진행.
		//					 false: error_message 크루전 개최에 실패하였습니다. (사유 : 게임일 등록 실패)
		// 							path 암벽장 페이지
		//TODO 개최되어 있지 않은 크루전 번호라면
		//error_message : 크루전 개최에 실패하였습니다. (사유 : 없는 크루전)
		//path : 암벽장 메인 페이지
		
		//크루전 개최 되어있는지 확인하기 위한 로직 종료
		//------------------------------------------------------------
		//크루전 등록 로직 시작
		//TODO (크루전 번호 / 크루 번호) 를 Battle_record DTO에 추가합니다.
		//TODO model 의 Battle_record 에 Insert 해줍니다.
		//True : error_message 크루전 등록에 성공하였습니다.
		//false: error_message 크루전 등록에 실패하였습니다.
		
		//path : 암벽장 페이지
		
		//크루전 등록 로직 종료
		//------------------------------------------------------------
		
		forward.setPath(path);
		forward.setRedirect(flag_Redirect);		
		return forward;
	
	}

}
