package test;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class Fileupload
 */
@MultipartConfig(
		// 멀티파트 설정
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50, // 50MB
		fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		location = "/img"
)
public class Fileupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Fileupload() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//servlet forword로 imgTest.jsp로 넘기기 위해 작성
		RequestDispatcher rqdc = request.getRequestDispatcher("imgTest.jsp");
		//uploadPath2 = 이미지가 저장된 위치를 불러와서 넘겨줍니다.
		String uploadPath2 = getServletContext().getContextPath()+"/profile_img/" + request.getAttribute("fileName");
		//저장된 이미지를 불러와야 하기 때문에 imgPath로 넘겨줍니다.
		request.setAttribute("imgPath", uploadPath2);
		rqdc.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = "";
		try {
		    // 파일 파트 가져오기
		    Part filePart = request.getPart("image");

		    // 파일 이름 가져오기
		    fileName = filePart.getSubmittedFileName();

		    // 업로드 경로 설정
		    //webapp파일에 위치 찾기
		    String uploadPath = getServletContext().getRealPath("/profile_img/"); // 기본 서버에 저장 @MultipartConfig으로 \tmp0\work\Catalina\localhost\test\img 에 저장했음

		    // 파일 저장
		    String filePath = uploadPath + fileName;
		    filePart.write(filePath);
		    
		    // 로그 : 응답 출력
		    System.out.println("로그 File " + filePath);

		} catch (IllegalStateException e) {
		    e.printStackTrace();
		    System.out.println("part error");
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("upload error");
		}
		
		request.setAttribute("fileName", fileName);
		doGet(request, response);
	}

}
