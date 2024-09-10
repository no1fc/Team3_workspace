package controller.common;

import java.util.HashMap;
import java.util.Map;

import controller.community.BoardInsertAction;
import controller.community.BoardOnePageAction;
import controller.community.BoardUpdateAction;
import controller.community.InsertBoardPageAction;
import controller.community.ReplyAction;
import controller.community.ReplyDeleteAction;
import controller.community.ReplyUpdateAction;
import controller.gym.CrewBattleApplicationAction;
import controller.gym.GymInformationPageAction;
import controller.gym.GymMainPageAction;
import controller.gym.GymReservationAction;
import controller.gym.GymReservationInformationPageAction;
import controller.main.MainPageAction;
import controller.member.ChangeMemberAction;
import controller.member.JoinAction;
import controller.member.JoinPageAction;
import controller.member.LoginAction;
import controller.member.LoginPageAction;
import controller.member.LogoutAtion;
import controller.mypage.BoardDeleteAtion;
import controller.mypage.BoardUpdatePageAction;
import controller.mypage.ChangeMemberPageAction;
import controller.mypage.DeleteMemberAction;
import controller.mypage.MypagePageAction;
import controller.ranking.CrewRankingPageAction;
import controller.ranking.RankingPageAction;
import controller.store.StorePageAction;

public class HandlerMapper {
	//Map 에 요청과 Action 값을 저장할 수 있도록 만들어 줍니다
	private Map<String, Action> mapper;
	
	public HandlerMapper() {
		//HashMap으로 Map 을 초기화해주고
		this.mapper = new HashMap<String, Action>();
		
		//HashMap에 요청과 Action 값을 추가해줍니다.
		//MainPage Action
		//Page 이동 Action
		this.mapper.put("/MAINPAGEACTION.do", new MainPageAction()); //메인 페이지 이동
		
		//기능 Action
		
		//-------------------------------------------------------------------------------------------------
		//Member Action
		//Page 이동 Action
		this.mapper.put("/LOGINPAGEACTION.do", new LoginPageAction()); //로그인 가입 페이지 이동
		this.mapper.put("/JOINPAGEACTION.do", new JoinPageAction()); //회원 가입 페이지 이동
		
		//기능 Action
		this.mapper.put("/LOGINACTION.do", new LoginAction()); //로그인 기능
		this.mapper.put("/JOINACTION.do", new JoinAction()); //회원가입 기능
		this.mapper.put("/CHANGEMEMBERACTION.do", new ChangeMemberAction()); //회원정보 수정
		this.mapper.put("/LOGOUTPAGEACTION.do", new LogoutAtion());//로그아웃 페이지
		
		//-------------------------------------------------------------------------------------------------
		//MyPage Action
		//Page 이동 Action
		this.mapper.put("/MYPAGEPAGEACTION.do", new MypagePageAction()); //MyPage 페이지 이동
		this.mapper.put("/CHANGEMEMBERPAGEACTION.do", new ChangeMemberPageAction()); //회원 정보 수정 페이지 이동
		this.mapper.put("/BOARDUPDATEPAGEACTION.do", new BoardUpdatePageAction()); //작성글 수정 페이지 이동
		
		//기능 Action
		this.mapper.put("/DELETEMEMBERACTION.do", new DeleteMemberAction()); //회원 탈퇴 기능
		this.mapper.put("/BOARDDELETEACTION.do", new BoardDeleteAtion()); //작성글 삭제 기능
		
		//-------------------------------------------------------------------------------------------------
		//BoardPage Action
		//이동 Action
		this.mapper.put("/BOARDONEPAGEACTION.do", new BoardOnePageAction()); // 글 내용 페이지 이동
		this.mapper.put("/BOARDUPDATAPAGEACTION.do", new BoardUpdatePageAction()); // 글 수정 페이지 이동
		this.mapper.put("/INSERTBOARDPAGEACTION.do", new InsertBoardPageAction()); // 새글 작성 페이지 이동
		
		//기능 Action
		this.mapper.put("/BOARDINSERTACTION.do", new BoardInsertAction()); // 글 수정 기능
		this.mapper.put("/BOARDUPDATEACTION.do", new BoardUpdateAction()); // 글 수정 기능
		this.mapper.put("/REPLYACTION.do", new ReplyAction()); // 댓글 작성 기능
		this.mapper.put("/REPLYDELETEACTION.do", new ReplyDeleteAction()); // 댓글 삭제 기능
		this.mapper.put("/REPLYUPDATEACTION.do", new ReplyUpdateAction()); // 댓글 수정 기능

		//-------------------------------------------------------------------------------------------------
		//Ranking Action
		//Page 이동 Action
		this.mapper.put("/CrewRankingPage.do", new CrewRankingPageAction()); //크루 랭킹 페이지 이동 
		this.mapper.put("/RankingPage.do", new RankingPageAction()); //개인 랭킹 페이지 이동
		
		//-------------------------------------------------------------------------------------------------
		//Gym Action
		//Page 이동 Action
		this.mapper.put("/GymMainPage.do", new GymMainPageAction()); //암벽장 메인 페이지 이동 
		this.mapper.put("/GymInformationPage.do", new GymInformationPageAction()); //암벽장 상세보기 페이지 이동 		
		this.mapper.put("/GymReservationInformationPage.do", new GymReservationInformationPageAction()); //암벽장 예약 상세보기 페이지 이동
		
		//기능 Action
		this.mapper.put("/CrewBattleApplication.do", new CrewBattleApplicationAction()); //크루전 신청 기능
		this.mapper.put("/GymReservation.do", new GymReservationAction()); //암벽장 기능
		//암벽장 좋아요 기능은 Servlet 으로 비동기 처리할 예정
		//-------------------------------------------------------------------------------------------------
		//Store Action
		//Page 이동 Action
		this.mapper.put("/StorePage.do", new StorePageAction()); //상점 페이지 이동 
		
		//-------------------------------------------------------------------------------------------------
		
	}
	
	//요청을 받아와 Action 을 반환해줍니다.
	public Action getAction(String command) {
		return this.mapper.get(command);
	}
	
}
