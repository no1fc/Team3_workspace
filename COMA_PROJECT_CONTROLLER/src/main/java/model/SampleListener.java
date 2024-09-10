package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.board.BoardDTO;
import model.product.ProductDTO;
		
@WebListener
public class SampleListener implements ServletContextListener {
	// 전체 글 개수 TOTAL
	private final String ONE_COUNT = "SELECT COUNT(*) AS BOARD_TOTAL FROM BOARD";
	// 게시글 작성 BOARD_NUM,BOARD_TITLE,BOARD_CONTENT,BOARD_LOCATION,BOARD_WRITER_ID
	private final String INSERT ="INSERT INTO BOARD (BOARD_NUM,BOARD_TITLE,BOARD_CONTENT,BOARD_LOCATION,BOARD_WRITER_ID)\r\n"
			+ "VALUES ((SELECT NVL(MAX(BOARD_NUM),0)+1 FROM BOARD),?,?,?,?)";

	public void contextInitialized(ServletContextEvent sce)  { 
		// 웹 서버 구동(실행)시 한번 수행될 코드 부분
		System.out.println("model.SampleListener 시작");
		Crawling crawling = new Crawling(); //크롤링 객체 선언
		ArrayList<ProductDTO> datas=crawling.makeSampleProduct();
		// 샘플 데이터 DB에 저장하는 코드
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			// 전체 게시판 크롤링 부분
			// 게시판 글 개수 조회 BOARD_TOTAL
			pstmt = conn.prepareStatement(ONE_COUNT);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("BOARD_TOTAL")<= 0){//글 개수가 0개 이하라면 실행
					System.out.println("model.SampleListener.makeSampleBoard 크롤링 시작");
					ArrayList<BoardDTO> crawling_board_datas=crawling.makeSampleBoard();

					for(BoardDTO board_data : crawling_board_datas) {
						// 게시글 작성 BOARD_NUM,BOARD_TITLE,BOARD_CONTENT,BOARD_LOCATION,BOARD_WRITER_ID
						try {
							pstmt = conn.prepareStatement(INSERT);
							pstmt.setString(1,board_data.getModel_board_title().replace("'", "\'"));
							pstmt.setString(2,board_data.getModel_board_content().replace("'", "\'"));
							pstmt.setString(3,"서울특별시");
							pstmt.setString(4, board_data.getModel_board_writer_id());
							int result = pstmt.executeUpdate();
							if(result<=0) {
								System.err.println("board.BoardDAO.makeSampleBoard 데이터 없음");
							}
						}catch(SQLException e) {
							System.err.println("board.BoardDAO.makeSampleBoard.INSERT SQL문 실패");
						}
					}
					System.out.println("model.SampleListener.makeSampleBoard 크롤링 완료");
				}
				else {
					System.out.println("model.SampleListener.makeSampleBoard 크롤링 필요없음");
				}
			}
			
			// 상품 크롤링 부분
			System.out.println("model.SampleListener.makeSampleProduct 크롤링 시작");
			ArrayList<ProductDTO> crawling_product_datas=crawling.makeSampleProduct();
			int PK=0;//자동부여하는 PK
			for(ProductDTO product_data : crawling_product_datas) {
				ProductDTO productDTO = new ProductDTO();
				productDTO.setModel_product_name(product_data.getModel_product_name());
				productDTO.setModel_product_profile(product_data.getModel_product_profile());
				productDTO.setModel_product_default_price(product_data.getModel_product_default_price());
				productDTO.setModel_product_discount_price(product_data.getModel_product_discount_price());
				productDTO.setModel_product_discount_rate(product_data.getModel_product_discount_rate());
				productDTO.setModel_product_link(product_data.getModel_product_link());
				productDTO.setModel_product_num(PK);
				datas.add(productDTO);
				PK++;
			}
			System.out.println("model.SampleListener.makeSampleProduct 크롤링 완료");
		}
		catch(SQLException e) {
			System.err.println("board.BoardDAO.ONE_COUNT SQL문 실패");
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		// 서버단위로 저장되는 application에 담아서 전송
		sce.getServletContext().setAttribute("product_datas", datas);
		System.out.println("model.SampleListener 끝");
	}

	public void contextDestroyed(ServletContextEvent sce)  { 
		// 웹 서버 종료시 한번 수행될 코드 부분

		// ex) DB 연결 해제
	}

}
