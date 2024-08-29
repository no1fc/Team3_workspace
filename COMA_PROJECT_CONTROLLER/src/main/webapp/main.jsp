<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Fonts and icons -->
<script src="assets/js/plugin/webfont/webfont.min.js"></script>
<script src="https://kit.fontawesome.com/7f7b0ec58f.js"
	crossorigin="anonymous"></script>

<!-- CSS Files -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/css/plugins.min.css" />
<link rel="stylesheet" href="assets/css/kaiadmin.css" />

</head>
<body>
	<!-- GNB 커스텀 태그 -->
	<mytag:gnb member_id="${MEMBER_ID}" ></mytag:gnb>
	
	<!-- container start -->
	<div class="container">
		<div class="page-inner">
			<div class="row pt-3">
				<div class="col-12 p-0">
					<form action="MAINPAGEACTION.do" method="GET">
						<div class="row">
							<div class="col-md-3 col-lg-3">
								<div class="form-group">
									<select name="board_list"
										class="form-select form-control-lg">
										<option>글 제목</option>
										<option>작성자</option>
										<option>아이디</option>
									</select>
								</div>

							</div>
							<div class="col-md-6 col-lg-7">
								<div class="form-group">
									<div class="input-icon">
										<input name="board_keyword" type="text" class="form-control"
											placeholder="검색어를 입력해주세요" /> <span class="input-icon-addon">
											<button type="submit" class="btn">
												<i class="fa fa-search"></i>
											</button>
										</span>
									</div>
								</div>
							</div>
							<div
								class="col-md-3 col-lg-2 d-flex align-items-center justify-content-end">
								<a href="INSERTBOARDPAGEACTION.do" class="d-block btn">
									<button type="button"
										class="btn btn-primary btn-round px-5 py-3">글 작성</button>
								</a>
							</div>
						</div>
					</form>
				</div>
			</div>
			<c:forEach var="board" items="${BOARD}">
				<c:choose>
					<c:when test="${board.board_num> 0}">
						<div class="row pt-5">
							<div class="col-md-10">
								<div class="card card-stats card-round mb-0">
									<div class="card-body p-5 d-flex justify-content-between">
										<h3 class="card-title">
											<a href="BOARDONEPAGEACTION.do?board_num=${board.board_num}"
												class="link-dark"> ${board.board_title}</a>
										</h3>
										<div class="info-user">
											<div class="username">작성자 : ${board.board_writer_id}</div>
											<div class="status">글 조회수 : ${board.board_cnt}</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<h1>최신 글 목록이 없습니다...</h1>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<div class="row pt-5">
				<div class="col-md-10 d-flex justify-content-center">
					<nav aria-label="Page navigation">
						<ul id="pagination" class="pagination justify-content-center">
							
						</ul>
					</nav>
				</div>
			</div>
		</div>
		<!-- container end -->
		<div class="cookies d-none" id="cookies">
			<div class="cookies-header py-1 px-3 text-end">
				<a id="cookies-cancel" class="btn btn-toggle">x</a>
			</div>
			<div class="cookies-body p-3 text-center">
				<h2 class="text-white">최고의 클라이밍 슈즈</h2>
				<p class="text-white">모멘텀 암벽화</p>
				<img src="assets/img/shoes.png" />
				<h2 class="text-white">~ 50%</h2>
				<button class="btn btn-info btn-round">바로 구매하기</button>
				<a id="cookie-today-hide" class="btn btn-toogle text-white">오늘
					하루 보지 않기</a>
			</div>
		</div>
	</div>

	<!--   Core JS Files   -->
	<script src="assets/js/core/jquery-3.7.1.min.js"></script>
	<script src="assets/js/core/popper.min.js"></script>
	<script src="assets/js/core/bootstrap.min.js"></script>
	<script src="assets/js/core/jquery.cookie.js"></script>

	<script>
		//cookies
		var cookies = document.getElementById("cookies");
		var cancel = document.getElementById("cookies-cancel");
		var btnTodayHide = document.getElementById("cookie-today-hide");

		// 최초 쿠키배너 노출
		// cookie라는 이름의 쿠키가 존재하지 않으면 레이어 노출
		if (!$.cookie('cookie')) {
			cookieShow();
		}

		// 쿠키 배너 닫기 버튼 클릭
		cancel.addEventListener("click", function() {
			cookieHide(0);
		});

		// 쿠키 배너 오늘 하루 닫기 버튼 클릭
		btnTodayHide.addEventListener("click", function() {
			cookieHide(1);
		});

		// 쿠키 배너 노출
		function cookieShow() {
			cookies.classList.remove("d-none");
		}

		// 쿠키 배너 비노출
		function cookieHide(state) {
			cookies.classList.add("d-none");
			if (state === 1) {
				// cookie 처리
				// 'cookie' 이름의 쿠키가 있는지 체크해본다
				if ($.cookie('cookie') == undefined) {
					//'cookie'이름을 넣고 1일 후 쿠키가 삭제되도록 
					// path 값을 '/'를 주면 모든페이지에서 유효한 쿠키가 생성
					$.cookie('cookie', 'Y', {
						expires : 1,
						path : '/'
					});
				}

			}
		}

		// 페이지네이션 생성

		// 4가지 값
		// 화면에 보여질 페이지 그룹
		// 화면에 보여질 첫번째 페이지 = 화면에 그려질 마지막 페이지 - (한 화면에 나타낼 페이지 - 1)
		// 화면에 보여질 마지막 페이지 = 화면에 보여질 페이지 그룹 * 한 화면에 나타낼 페이지
		// 총 페이지 수 = Math.ceil(전체 개수 / 한 페이지에 나타낼 데이터 수)

		// 페이지네이션 생성 함수
		function renderpagination(currentPage, _totalCount,param) {
		    // 현재 게시물의 전체 개수가 10개 이하면 pagination을 숨깁니다.
		    if (_totalCount <= 10) return;
		    
		    let board_list = param.get('board_list');
			console.log(board_list);
		    let board_keyword = param.get('board_keyword'); // 서버에서 전달된 님 내용임 맞겠지
			console.log(board_keyword);
		    
		    // 총 페이지 수 계산 (전체 게시물 수를 한 페이지에 보여줄 게시물 수로 나눈 값의 올림)
		    const totalPage = Math.ceil(_totalCount / 10);
		    
		    // 현재 페이지 그룹 계산 (현재 페이지를 10으로 나눈 값의 올림)
		    const pageGroup = Math.ceil(currentPage / 10);
		
		    // 현재 페이지 그룹에서의 마지막 페이지 계산
		    let last = pageGroup * 10;
		
		    // 마지막 페이지가 총 페이지 수를 초과하지 않도록 조정
		    if (last > totalPage) last = totalPage;
		
		    // 현재 페이지 그룹에서의 첫 번째 페이지 계산
		    const first = last - (10 - 1) <= 0 ? 1 : last - (10 - 1);
		
		    // 다음 그룹의 첫 페이지 계산
		    const next = last + 1;
		
		    // 이전 그룹의 마지막 페이지 계산
		    const prev = first - 1;
		
		    // 페이지네이션 버튼을 담을 비어있는 DocumentFragment 객체 생성
		    const fragmentPage = document.createDocumentFragment();
		
		    // 이전 그룹으로 이동하는 버튼 생성 (prev가 0보다 크다면 생성)
		    if (prev > 0) {
		        const preli = document.createElement('li');
		        preli.id = 'prev-btn';
		        preli.className = 'page-item';
		        if(board_list != null){
			         preli.insertAdjacentHTML("beforeend",
			        	    "<a id='allprev' class='page-link' href='MAINPAGEACTION.do?page=" + prev + "&board_list="+board_list+"&board_keyword="+board_keyword+"' aria-label='Previous'>" + 
			        	    "<span aria-hidden='true'>&laquo;</span>" + 
			        	    "</a>"
			        	);
			        	
			        }
			        else{
				        preli.insertAdjacentHTML("beforeend",
						           "<a id='allprev' class='page-link' href='MAINPAGEACTION.do?page="+prev+"' aria-label='Previous'>" 
						                +"<span aria-hidden='true'>&laquo;</span> </a>");
			        }
		        fragmentPage.appendChild(preli);
		    }
		
		    // 현재 페이지 그룹의 페이지 번호 버튼 생성
		    for (let i = first; i <= last; i++) {
		        const li = document.createElement("li");
		        li.className = 'page-item';
		        if(board_list != null){
			        li.insertAdjacentHTML("beforeend",
			        	    "<a class='page-link m-2' href='MAINPAGEACTION.do?page=" + i + "&board_list="+board_list+"&board_keyword="+board_keyword+"' id='page-" + i + "' data-num='" + i + "'>" +
			        	    i +
			        	    "</a>"
			        	);
			        	
			        }
			        else{
				        li.insertAdjacentHTML("beforeend",
					              "<a class='page-link m-2' href='MAINPAGEACTION.do?page=" + i + "' id='page-" + i + "' data-num='" + i + "'>" +
					                i +
					            "</a>");
			        }


		        fragmentPage.appendChild(li);
		    }
		
		    // 다음 그룹으로 이동하는 버튼 생성 (last가 totalPage보다 작다면 생성)
		    if (last < totalPage) {
		        const endli = document.createElement('li');
		        endli.id = 'next-btn';
		        endli.className = 'page-item';
		        if(board_list != null){
			        endli.insertAdjacentHTML("beforeend",
			        	    "<a class='page-link' href='MAINPAGEACTION.do?page=" + next + "&board_list="+board_list+"&board_keyword="+board_keyword+"' id='allnext' aria-label='Next'>" + 
			        	    "<span aria-hidden='true'>&raquo;</span>" +
			        	    "</a>"
			        	);
			        	
			        }
			        else{
				        endli.insertAdjacentHTML("beforeend",
						     "<a class='page-link' href='MAINPAGEACTION.do?page=" + next +"' id='allnext' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a>");
				        
			        }

				fragmentPage.appendChild(endli);
		    }
		
		    // 생성된 페이지네이션 버튼들을 화면에 추가 
		   document.getElementById('pagination').appendChild(fragmentPage);
		   
		};
		
	    // 페이지 버튼 클릭 이벤트 처리
	    $("#pagination a").click(function (e) {
	        // 기본 동작(페이지 이동) 방지
	        e.preventDefault();
	        // 클릭된 페이지 링크 요소를 jQuery 객체로 저장
	        const $item = $(this);
	        // 클릭된 페이지 링크의 텍스트(페이지 번호)를 가져와 selectedPage 변수에 저장
	        let selectedPage = $item.text();
	
	        // 각 버튼의 ID에 따라 선택된 페이지 설정
	        if ($item.attr("id") === "next")
	            selectedPage = next;
	        if ($item.attr("id") === "prev")
	            selectedPage = prev;
	        if ($item.attr("id") === "allprev")
	            selectedPage = 1;
	        if ($item.attr("id") === "allnext")
	            selectedPage = totalPage;
	
	        // 페이지네이션 재생성 및 해당 페이지 데이터 로드
	        renderpagination(selectedPage, _totalCount);
	        list.search(selectedPage); // 이 함수가 제대로 정의되어 있는지 확인
	    });
	    
		// DOM이 완전히 로드된 후 페이지네이션을 생성
		document.addEventListener("DOMContentLoaded", function() {
		    const _totalCount = ${totalCount};  // 서버에서 전달된 전체 게시물 개수
		    const  currentPage = ${currentPage}; // 서버에서 전달된 현재 페이지 번호
		    
		    let query = window.location.search;
		    let param = new URLSearchParams(query);
		    
		    renderpagination(currentPage,_totalCount,param);      // 페이지네이션 생성 함수 호출
		    
		    // 현재 페이지를 표시하기 위해 active 클래스 추가
		    $("#pagination a").removeClass("active text-white");
		    $("#pagination a#page-" + currentPage).addClass("active text-white");
		    console.log(currentPage);
		});
		
	</script>
</body>

</html>