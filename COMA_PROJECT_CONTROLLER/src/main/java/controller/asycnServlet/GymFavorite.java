package controller.asycnServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/GymFavorite")
public class GymFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GymFavorite() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		//------------------------------------------------------------
		//좋아요 여부를 확인하는 로직 시작
		//TODO (암벽장 번호 / 사용자 아이디) Favorite DTO에 값을 추가해줍니다.
		//TODO Favorite selectOne 으로 여부를 확인해줍니다.
		//null 이라면
		
		//좋아요 여부를 확인하는 로직 종료
		//------------------------------------------------------------
		

		
	}

}
