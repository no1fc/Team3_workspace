package controller.common;

import java.io.File;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class FileManager implements ServletContextListener {

	public FileManager() {
	}

	public void contextInitialized(ServletContextEvent sce) {

		String[] folders = 
				//등급 이미지 폴더 , 크루 이미지 폴더 , 사용자 프로필 폴더
			{"grade_folder","crew_img_folder","profile_img"};

		for (String data : folders) {
			// 실제 경로 받아오기
			String folder_path = sce.getServletContext().getRealPath("/"+data+"/");

			// 폴더 경로 로고
			System.out.println("폴더 경로: " + folder_path);

			// File 객체 생성
			File folder = new File(folder_path);

			// 폴더가 존재하는지 확인하고, 없으면 생성
			if (!folder.exists()) {
				boolean flag = folder.mkdirs();  // 폴더를 생성

				if (flag) {
					System.out.println("폴더 생성 완료 : " + folder_path);
				} 
				else {
					System.out.println("폴더 생성 실패");
				}
			} 
			else {
				System.out.println("폴더가 존재 : " + folder_path);
			}
		}
	}

	public void contextDestroyed(ServletContextEvent sce)  { 
	}

}
