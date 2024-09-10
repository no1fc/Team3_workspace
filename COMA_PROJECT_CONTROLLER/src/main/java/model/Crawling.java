package model;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import model.board.BoardDTO;
import model.product.ProductDTO;

public class Crawling {
	private WebDriver driver;
	private String target_url;
	private String default_url;

	public Crawling() {
		// 크롬 옵션 설정
		ChromeOptions options = new ChromeOptions();
		// 헤드리스 모드 추가 (코드 실행시 크롬창이 뜨지않게 함)
		options.addArguments("--headless");
		// 팝업창 제거 옵션 추가
		options.addArguments("--disable-popup-blocking");
		//GPU 가속 비활성화
		options.addArguments("--disable-gpu");
		//샌드박스 비활성화
		options.addArguments("--no-sandbox");

		// 옵션설정한 ChromeDriver 인스턴스 생성
		driver = new ChromeDriver(options);
	}

	public ArrayList<ProductDTO> makeSampleProduct() {
		System.out.println("model.Crawling.makeSampleProduct 시작");
		ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();
		//크롤링할 사이트 url
		default_url = "https://spiri7.com";
		target_url = "";
		//css tag
		String product_button = "#__next > main > div.card.mb-11.w-full > div.px-5.flex.justify-between.items-end.mb-2.font-KoPubWorldDotum > button";
		String product_href= "#__next > main > div.card.px-5.py-2\\.5.pb-24 > div.list-content.flex.flex-wrap >a";
		String product_name = "#__next > main > div:nth-child(3) > h1";
		String product_default_price ="#__next > main > div:nth-child(3) > div:nth-child(4) > div.flex.items-center > span.text-gray-500.text-sm.font-normal.truncate.line-through";
		String product_discount_rate ="#__next > main > div:nth-child(3) > div:nth-child(4) > div.flex.items-center > span.text-red-400.text-base.font-bold.truncate.mr-1";
		String product_discount_price ="#__next > main > div:nth-child(3) > div:nth-child(4) > div.text-red-400.text-xl.font-bold.truncate";
		String product_img = "#__next > main > div.relative.w-full.bg-white > section > div > div.slick-list > div > div.slick-slide.slick-active.slick-current > div > div > div > span > img";


		//크롤링 동작 부분
		try {
			//드라이버에 url 주입
			driver.get(default_url);

			//추천상품 더보기 버튼 찾기
			WebElement button = driver.findElement(By.cssSelector(product_button));
			//버튼 클릭
			button.click();

			// 변경된 URL을 저장
			target_url = driver.getCurrentUrl();
			System.out.println("63 target_url ="+target_url);

			//브라우저 열수있게 1초 대기
			Thread.sleep(1000);


			//페이지 이동후 크롤링
			List<WebElement> product = driver.findElements(By.cssSelector(product_href));
			for(WebElement detail : product) {
				//링크 파밍
				String tag = detail.getAttribute("href").replace("..", default_url);
				System.out.println("74 href = "+ tag);

				//파밍한 링크 접속
				driver.get(tag);
				System.out.println("78");
				//상품 이름
				List<WebElement> productName = driver.findElements(By.cssSelector(product_name));

				//상품 이미지
				List<WebElement> productImg = driver.findElements(By.cssSelector(product_img));

				//상품 원가
				List<WebElement> productDefaultPrice = driver.findElements(By.cssSelector(product_default_price));

				//상품 할인율
				List<WebElement> productDiscountRate = driver.findElements(By.cssSelector(product_discount_rate));

				//상품 할인된 가격
				List<WebElement> productDiscountPrice = driver.findElements(By.cssSelector(product_discount_price));

				for(int i=0;i<productName.size();i++) {
					ProductDTO productDTO = new ProductDTO();
					productDTO.setModel_product_name(productName.get(i).getText());
					productDTO.setModel_product_profile(productImg.get(i).getAttribute("src"));
					productDTO.setModel_product_discount_rate(productDiscountRate.get(i).getText());
					productDTO.setModel_product_default_price(Integer.parseInt(productDefaultPrice.get(i).getText().replace("원", "").replace(",", "")));
					productDTO.setModel_product_discount_price(Integer.parseInt(productDiscountPrice.get(i).getText().replace("원신규회원", "").replace(",", "")));
					productDTO.setModel_product_link(tag);//상품 링크 FIXME
					System.out.println("이미지 = "+productDTO.getModel_product_profile());
					datas.add(productDTO);
				}
				// 들어갔던 링크를 빠져나오기 위해
				driver.navigate().back();

				//빠져나온후 페이지 로드 대기
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.err.println("crawling.makeSampleProduct 크롤링 실패");
			e.printStackTrace();
			return datas;
		}finally {
			//드라이버 종료
			driver.quit();
		}
		System.out.println("model.Crawling.makeSampleProduct 성공");
		return datas;
	}

	public ArrayList<BoardDTO> makeSampleBoard(){
		System.out.println("model.Crawling.makeSampleBoard 시작");
		ArrayList<BoardDTO> datas = new ArrayList<BoardDTO>();
		//크롤링할 사이트 url
		default_url = "https://spiri7.com";
		target_url = "";
		//css tag
		String board_button = "#__next > main > div:nth-child(5) > div > button";
		String board_href= "#__next > main > div.bg-white.py-10.md\\:py-10 > div.w-full > ul > li";
		String board_divs = "div.postContent.py-0.font-KoPubWorldDotum > div > div.w-full.text-base.font-bold.mb-1";
		String board_title = "#__next > main > div.w-full.max-w-5xl.mx-auto.bg-white.py-4.md\\:py-8 > div.sectionPostHeader.px-4.md\\:px-6 > h1";
		String board_content = "#__next > main > div.w-full.max-w-5xl.mx-auto.bg-white.py-4.md\\:py-8 > div.sectionPostContent.md\\:pt-4.md\\:pb-10.pt-2.pb-8 > div";
		String board_writer = "#__next > main > div.w-full.max-w-5xl.mx-auto.bg-white.py-4.md\\:py-8 > div.sectionPostHeader.px-4.md\\:px-6 > div > div.flex.items-center > div.ml-2 > div.text-base.font-bold.tracking-tight";
		//크롤링 동작 부분
		try {
			//드라이버에 url 주입
			driver.get(default_url);

			System.out.println("127 default_url = "+default_url);
			//최신글 더보기 버튼 찾기
			WebElement button = driver.findElement(By.cssSelector(board_button));
			//버튼 클릭
			button.click();

			// 변경된 URL을 저장
			target_url = driver.getCurrentUrl();
			System.out.println("135 target_url ="+target_url);

			//브라우저 열수있게 1초 대기
			Thread.sleep(1000);


			//페이지 이동후 크롤링
			List<WebElement> board = driver.findElements(By.cssSelector(board_href));
			System.out.println("143");
			if(board.isEmpty()) {
				System.err.println("144 board비어있음");
			}
			//System.out.println("147 ="+board.get(6).getText());
			int rsCnt=1;
			int i;
			for(i = 0; i < board.size(); i++) {
				System.err.println("150 rsCnt = " + rsCnt);

				// 현재 게시물의 div 요소를 찾기 ( 이 페이지에서 div를 클릭해야 게시글 상세 페이지로 이동 )
				WebElement board_div;
				try {
					board_div = board.get(i).findElement(By.cssSelector(board_divs));
					System.out.println("참조오류 발생하지않은 인덱스 번호 ="+i);
				}catch (StaleElementReferenceException e) {
					//driver.navigate().back() 코드가 실행되면서 페이지가 다시 로드되어 발생
					//페이지가 뒤로 이동하면 원래 있던 페이지에서 찾은 요소가 유효하지 않게 되어 무효한 요소를 참조하려고 하여 발생
					System.out.println("참조오류 발생한 인덱스 번호 ="+i);
					board = driver.findElements(By.cssSelector(board_href));
					board_div = board.get(i).findElement(By.cssSelector(board_divs));
				}

				// div 클릭
				board_div.click();

				// 페이지가 로드되도록 대기
				Thread.sleep(1000);

				//페이지 이동후 크롤링
				//제목
				WebElement board_div_detail_title = driver.findElement(By.cssSelector(board_title));
				//System.out.println("board_div_detail_title = "+board_div_detail_title.getText());

				//내용
				WebElement board_div_detail_content = driver.findElement(By.cssSelector(board_content));
				//System.out.println("board_div_detail_content = "+board_div_detail_content.getText());

				//작성자
				WebElement board_div_detail_writer = driver.findElement(By.cssSelector(board_writer));
				//System.out.println("board_div_detail_writer = "+board_div_detail_writer.getText());

				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setModel_board_title(board_div_detail_title.getText());
				boardDTO.setModel_board_content(board_div_detail_content.getText());
				boardDTO.setModel_board_writer_id(board_div_detail_writer.getText());
				datas.add(boardDTO);

				// 들어갔던 링크에서 나가기
				driver.navigate().back();

				//나가서 다시 로드되기 기다리기
				Thread.sleep(1000);
				rsCnt++;

			}
		} catch (Exception e) {
			System.err.println("crawling.makeSampleBoard 크롤링 실패");
			e.printStackTrace();
			return datas;
		}finally {
			//드라이버 종료
			driver.quit();
		}
		System.out.println("model.Crawling.makeSampleBoard 성공");
		return datas;
	}

}
