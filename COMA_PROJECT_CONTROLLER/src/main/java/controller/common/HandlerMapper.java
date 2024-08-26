package controller.common;

import java.util.HashMap;
import java.util.Map;

import controller.main.MainPageAction;
import controller.member.ChangeMemberAction;
import controller.member.LoginAction;
import controller.member.LoginPageAction;
import controller.member.SignUpAction;
import controller.member.SignUpPageAction;
import controller.mypage.BoardDeleteAtion;
import controller.mypage.BoardUpdatePageAction;
import controller.mypage.ChangeMemberPageAction;
import controller.mypage.DeleteMemberAction;
import controller.mypage.MyCrewBoardAction;
import controller.mypage.MypagePageAction;

public class HandlerMapper {
	//Map 에 요청과 Action 값을 저장할 수 있도록 만들어 줍니다
	private Map<String, Action> mapper;
	
	public HandlerMapper() {
		//HashMap으로 Map 을 초기화해주고
		this.mapper = new HashMap<String, Action>();
		
		//HashMap에 요청과 Action 값을 추가해줍니다.
		//MainPage Action
		//Page 이동 Action
		this.mapper.put("/MAIN.do", new MainPageAction()); //메인 페이지 이동
		
		//기능 Action
		
		//-------------------------------------------------------------------------------------------------
		//Member Action
		//Page 이동 Action
		this.mapper.put("/LOGINPAGEACTION.do", new LoginPageAction()); //로그인 가입 페이지 이동
		this.mapper.put("/SIGNUPPAGEACTION.do", new SignUpPageAction()); //회원 가입 페이지 이동
		
		//기능 Action
		this.mapper.put("/LOGINACTION.do", new LoginAction()); //로그인 기능
		this.mapper.put("/SIGNUPACTION.do", new SignUpAction()); //회원가입 기능
		this.mapper.put("/CHANGEMEMBERACTION.do", new ChangeMemberAction()); //회원정보 수정
		
		//-------------------------------------------------------------------------------------------------
		//MyPage Action
		//Page 이동 Action
		this.mapper.put("/MYPAGEPAGEACTION.do", new MypagePageAction()); //MyPage 페이지 이동
		this.mapper.put("/CHANGEMEMBERPAGEACTION.do", new ChangeMemberPageAction()); //회원 정보 수정 페이지 이동
		
		//기능 Action
		this.mapper.put("/DELETEMEMBERACTION.do", new DeleteMemberAction()); //회원 탈퇴 기능
		this.mapper.put("/MYCREWBOARDACTION.do", new MyCrewBoardAction()); //내가 게시판에 작성한 글 확인		
		
		//-------------------------------------------------------------------------------------------------
		//BoardOnePage Action
		//이동 Action
		this.mapper.put("/BOARDUPDATEPAGEACTION.do", new BoardUpdatePageAction()); //작성글 수정 페이지 이동
		
		//기능 Action
		this.mapper.put("/BOARDDELETEACTION.do", new BoardDeleteAtion()); //작성글 삭제 기능
		//-------------------------------------------------------------------------------------------------
	}
	
	//요청을 받아와 Action 을 반환해줍니다.
	public Action getAction(String command) {
		return this.mapper.get(command);
	}
	
}
