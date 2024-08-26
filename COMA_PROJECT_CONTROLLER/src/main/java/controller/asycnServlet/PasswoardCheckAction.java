package controller.asycnServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.member.MemberDAO;
import model.member.MemberDTO;

import java.io.IOException;

@WebServlet("/passwoardcheck")
public class PasswoardCheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PasswoardCheckAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO data = new MemberDTO();
		//PrintWriter out = response.getWriter();
		
		boolean flag = false;
		
		//view 에서 사용자 ID를 보내주면 MemberDTO 에 값을 추가
		data.setMember_id((String)request.getSession().getAttribute("MEMBER_ID"));
		data.setMember_password(request.getParameter("member_passwoard"));
		data.setMember_condition("MEMBER_SERCHIDPASS");		
		//model 에 사용자 ID를 넘겨 값이 있는 지 확인 후
		data = memberDAO.selectOne(data);
		
		//값이 없으면 true 를 반환 합니다.
		if(data != null) {
			flag = true;
		}
		
		// view 로 값을 전달 합니다.
		response.getWriter().print(flag);
		
	}

}
