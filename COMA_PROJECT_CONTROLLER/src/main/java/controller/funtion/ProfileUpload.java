package controller.funtion;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;



public class ProfileUpload {

	public static String upload(HttpServletRequest request) {
		String fileName = "";
		try {
		    // 파일 파트 가져오기
		    Part filePart = request.getPart("image");

		    // 파일 이름 가져오기
		    fileName = filePart.getSubmittedFileName();
		    // 파일 이름에서 파일 형식 만 가져오기
		    //파일 형식은 .으로 시작하기 때문에 마지막 .xxx 를 가져오기 위해
		    //substring 과 lastIndexOf 를 사용
		    String fileform = fileName.substring(fileName.lastIndexOf("."));
	        
		    ServletContext context = request.getServletContext();
		    // 업로드 경로 설정
		    //webapp파일에 위치 찾기
		    //String uploadPath = ""; // 기본 서버에 저장 @MultipartConfig으로 \tmp0\work\Catalina\localhost\COMA_PROJECT_CONTROLLER\profile_img 에 저장했음
		    String uploadPath = context.getRealPath("/profile_img/"); // COMA_PROJECT_CONTROLLER\profile_img\ 에 저장했음
	        File uploadfolder = new File(uploadPath);
	        if (!uploadfolder.exists()) {
	        	uploadfolder.mkdir(); // 디렉토리가 없으면 생성
	        }
	        
	        HttpSession session = request.getSession();

	        //사용자 아이디 + 사용자가 올린 파일 형식
	        fileName = (String)session.getAttribute("MEMBER_ID") + fileform;

	        
		    // 파일 저장
	        // profile_img 폴더 주소 + 사용자 아이디.사용자가 올린 파일 형식
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
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ServletException error");
		}
		return fileName;
	}

}
