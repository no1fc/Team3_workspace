package controller.funtion;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCheck {
	public static String Success(HttpServletRequest request, HttpServletResponse response) {
		//cookie 에 저장된 로그인 정보를 불러오기
		Cookie[] cookies = request.getCookies();
		String cookieMemberID = null;
		//cookies 가 null이 아니라면
		if(cookies != null) {
			//cookies 만큼 배열을 돌리고
			for (Cookie cookie : cookies) {
				//cookie 에 저장된 회원 아이디 정보가 있다면
				if(cookie.getName().equals("MEMBER_ID")) {//쿠키에 저장된 사용자 ID를 불러옵니다.
					//쿠키가 있으면 자동로그인 기간을 추가
					cookie.setMaxAge(60 * 60 * 24 * 7); // 7일 동안 유지
					//쿠키를 추가헤줍니다.
					response.addCookie(cookie);
					//회원 ID를 불러와 cookieID에 저장해줍니다.
					cookieMemberID = cookie.getValue();
				}
			}
		}
		
		//session 에 저장된 로그인 정보를 불러오기
		HttpSession session = request.getSession();
		String sessionMemberID = (String)session.getAttribute("MEMBER_ID");	
		
		//sessionID 가 null 이고 cookieID 가 null 아니면
		if(sessionMemberID == null && cookieMemberID != null) {
			//session 을 갱신하고
			session.setAttribute("MEMBER_ID", cookieMemberID);
			//cookieID를 반환해 줍니다.
			System.err.println("(LoginMemberID.java) cookieID 로그 : " + cookieMemberID);
			return cookieMemberID;
//			return "샘플유저3@naver.com";
		}
		
		System.err.println("(LoginMemberID.java) sessionID 로그 : " + sessionMemberID);
		return sessionMemberID;
//		return "샘플유저3@naver.com";
	}
}
