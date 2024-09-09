package controller.common;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@MultipartConfig(
		// 멀티파트 설정
		maxFileSize = 1024 * 50, // 한 파일을 저장한때 10KB
		maxRequestSize = 1024 * 50, // 여러 파일을 저장한때 50KB
		fileSizeThreshold = 1024 * 2, // 메모리에 저장할때 크기 2KB
		location = "/" // 파일이 저장되는 경로
		)
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HandlerMapper mapper;
    public FrontController() {
        super();
        this.mapper = new HandlerMapper();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) {
		String url = request.getRequestURI();
		String cp = request.getContextPath();
		String command = url.substring(cp.length());
		
		//현재 실행되고 있는 요청 확인
		System.err.println("현재 요청 사항 : "+command);
		
		Action action = this.mapper.getAction(command);
		ActionForward forward = action.execute(request, response);

		//forward 가 없으면 잘못된 요청으로 error 발생
		if(forward == null) {
			try {
				response.sendError(404);
			} catch (IOException e) {
				System.out.println("잘못된 요청입니다.");
				e.printStackTrace();
			}
		}
		else {
			//리다이렉트 방식으로 들어오면 실행
			if(forward.isRedirect()) {
				try {
					response.sendRedirect(forward.getPath());
					System.out.println("Redirect 요청 : " + forward.getPath());
				} catch (IOException e) {
					System.err.println("파일 입출력 네트워크 오류 발생으로 오류 발생.");
					e.printStackTrace();
				}
			}
			//포워드 방식으로 들어오면 실행
			else if(!forward.isRedirect()) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				try {
					dispatcher.forward(request, response);
					System.out.println("Forward 요청 : " + forward.getPath());
				} catch (IOException e) {
					System.err.println("파일 입출력 네트워크 오류 발생으로 오류 발생.");
					e.printStackTrace();
				} catch (ServletException e) {
					System.err.println("servlet 처리 오류 발생으로 오류 발생.");
					e.printStackTrace();
				}
			}
		}
	}
}
