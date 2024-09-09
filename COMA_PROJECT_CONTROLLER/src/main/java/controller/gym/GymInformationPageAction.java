package controller.gym;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GymInformationPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String path = "GymMainPage.jsp"; // view에서 알려줄 예정
		boolean flag_Redirect = false; // 값을 전달해야하게 때문에 forward 방식으로 전달해야한다.
		
		//---------------------------------------------------------------------------
		//TODO 암벽장 정보 로직 시작
		
		//TODO View에서 전달해준 암벽장 번호를 DTO에 저장하고
		
		//TODO gym selectOne으로 Model에 암벽장정보를 요청합니다.
		//데이터 : 암벽장 번호 / 암벽장 사진 / 암벽장 설명 / 암벽장 주소 / 암벽장 가격
		
		
		//암벽장 정보 로직 종료
		//---------------------------------------------------------------------------
		//TODO 좋아요 여부 로직 시작
		
		//TODO View에서 전달해준 암벽장 번호와 사용자 아이디를 DTO에 저장하고
		//TODO Favorite selectOne으로 Model에 좋아요 여부를 요청합니다.
		
		//만약 있으면 좋아요 한 사용자.
		
		//만약 없으면 좋아요 안한 사용자.
		
		
		//좋아요 여부 로직 종료
		//---------------------------------------------------------------------------
		//TODO 해당 암벽장에서 승리한 크루 목록 로직 시작
		
		//TODO View에서 전달해준 암벽장 번호와 사용자 아이디를 DTO에 저장하고
		//TODO Battle selectAll으로 Model에 해당 암벽장에서 승리한 크루 목록을 요청하고
		//데이터 : 승리크루 이름 / 승리크루 사진 / 승리크루 경기날짜 / MVP 이름
		
		
		//해당 암벽장에서 승리한 크루 목록 로직 종료
		//---------------------------------------------------------------------------
		//TODO 해당 암벽장에 등록되어 있는 크루전 정보 로직 시작
		
		//TODO View에서 전달해준 암벽장 번호와 사용자 아이디를 DTO에 저장하고
		//TODO Battle selectOne으로 Model에 해당 암벽장에서 크루전 정보 요청
		//데이터 : 크루전 번호 / 크루전 날짜
		
		
		//해당 암벽장에 등록되어 있는 크루전 정보 로직 종료
		//---------------------------------------------------------------------------
		//TODO 사용자 포인트 요청 로직 시작
		
		//TODO 사용자 아이디를 DTO에 저장하고
		//TODO Member selectOne으로 Model에 해당 사용자의 사용가능 포인트요청
		//데이터 : 사용가능 포인트
		
		//사용자 포인트 요청 로직 종료
		//---------------------------------------------------------------------------	
				
		//View로 암벽장 승리 크루 전달 model_battle_record_datas
		//View로 암벽장 정보 전달 
		/*
		model_gym_num
		model_gym_profile
		model_gym_description
		model_gym_location
		model_gym_price
		*/
		//View로 좋아요 여부 전달 model_favorite
		//View로 암벽장 크루전 정보 전달
		/*
		model_battle_num
		model_battle_game_date
		*/
		//View로 사용 가능 포인트 전달 model_gym_member_current_point
		
		
		ActionForward forward = new ActionForward();
		forward.setPath(path);
		forward.setRedirect(flag_Redirect);		
		return forward;
	}

}
