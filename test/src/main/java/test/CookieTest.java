package test;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CookieTest
 */
public class CookieTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       // 예시: 사용자가 "Electronics" 카테고리의 제품 페이지를 방문했다고 가정
        String category = "Electronics";
        
        // 쿠키 생성 (카테고리 정보를 저장)
        Cookie categoryCookie = new Cookie("lastCategory", category);
        
        // 쿠키 유효 시간 설정 (7일)
        categoryCookie.setMaxAge(60 * 60 * 24 * 7); // 7일 동안 유지
        
        // 쿠키를 응답에 추가
        response.addCookie(categoryCookie);

        // 제품 페이지로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cookieTestPage.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
