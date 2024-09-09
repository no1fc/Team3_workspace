package controller.gym;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GymReservationAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String path = ".jsp"; // view에서 알려줄 예정 alert 창 띄우기 위한 JavaScript 페이지
		boolean flag_Redirect = false; // 값을 전달해야하게 때문에 forward 방식으로 전달해야한다.
		
		//------------------------------------------------------------
		//TODO 해당 페이지에서 공통으로 사용할 변수 and 객체
		//View에서 전달해주는 (암벽장 번호 / 예약일 / 사용한 포인트 / 암벽장 가격)변수
		//예약금액 변수
		//View에서 이동할 페이지 변수
		//Reservation DTO DAO 객체
		//Member DTO DAO 객체
		
		//------------------------------------------------------------
		//사용자가 해당 암벽장에 예약한 정보가 있는지 확인하기 위한 로직 시작
		//TODO (예약일)을 받아오고 login 정보를 ReservationDTO에 추가합니다.
		//TODO Reservation selectOne 을 요청
		//TODO 요청 값이 null 이 아니라면 해당 날짜에 이미 예약되어있는 사용자 이므로
		//not null == error_message : 해당 날짜에는 이미 예약되어있습니다. (예약 번호 : Reservation PK 값)
		//path			 			: 암벽장 페이지
		
		//사용자가 해당 암벽장에 예약한 정보가 있는지 확인하기 위한 로직 종료		
		//------------------------------------------------------------
		//사용 포인트 변경하는 로직 시작
		//TODO (사용자 아이디)을 MemberDTO에 추가합니다.
		//TODO 사용자의 현재 포인트를 SelectOne으로 요청하고
		//TODO 해당 사용자의 현재 포인트 - 사용 포인트를 use_Point 변수에 추가
		//use_Point 값이
		//음수 == error_message : 현재 포인트가 부족하여 예약에 실패하였습니다.
		//path				   : 암벽장 페이지	
		//양수 : 예약금액 = 암벽장금액 - use_Point
		
		//사용 포인트 변경하는 로직 종료
		//------------------------------------------------------------
		//예약 정보 저장 하기 위한 로직 시작
		//TODO (암벽장 번호 / 예약일 / 예약금액 / 암벽장 가격)을 ReservationDTO에 추가합니다.
		//TODO 사용자가 실제 사용한 금액을 계산할 로직을 추가 후
		//TODO model 에 Reservation 테이블에 Update 해줍니다.
		//TODO 저장 여부에 따른 값 전달
		//True == error_message  : 예약에 성공하였습니다.
		//false == error_message : 예약에 실패하였습니다.
		//path					 : 암벽장 페이지
		
		//예약 정보 저장 하기 위한 로직 종료
		//------------------------------------------------------------
		
		ActionForward forward = new ActionForward();
		forward.setPath(path);
		forward.setRedirect(flag_Redirect);		
		return forward;
	}

}
