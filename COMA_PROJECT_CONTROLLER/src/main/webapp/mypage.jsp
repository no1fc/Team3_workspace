<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<!-- responsive side menu -->
	<!-- mobile offcanvas -->
	<div class="offcanvas-lg offcanvas-start d-lg-none" tabindex="-1"
		id="offcanvasResponsive" aria-labelledby="offcanvasResponsiveLabel">
		<div class="offcanvas-header">
			<h5 class="offcanvas-title" id="offcanvasResponsiveLabel"></h5>
			<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
				data-bs-target="#offcanvasResponsive" aria-label="Close"></button>
		</div>
		<div class=" offcanvas-body py-3">
			<nav class="navbar h-100 flex-column justify-content-between">
				<ul class="navbar-nav text-center">
					<li class="nav-item p-3"><a class="nav-link" href="#">상점</a></li>
					<li class="nav-item p-3"><a class="nav-link" href="#">암벽장</a></li>
					<li class="nav-item p-3"><a class="nav-link" href="CREWPAGE.do">크루</a></li>
					<li class="nav-item p-3"><a class="nav-link" href="RANKPAGE.do">랭킹</a></li>
					<li class="nav-item p-3"><a class="nav-link" href="COMMUNITYPAGE.do">커뮤니티</a></li>
				</ul>
				<ul class="navbar-nav text-center">
					<li class="nav-item p-3"><a class="nav-link" href="MYPAGEPAGE.do">마이페이지</a></li>
					<c:if test="${empty MEMBER_ID}">
						<li class="nav-item p-3"><a class="nav-link" href="LOGINPAGE.do">로그인</a></li>					
					</c:if>
					<c:if test="${not empty MEMBER_ID}">
						<li class="nav-item p-3"><a class="nav-link" href="LOGOUTPAGE.do">로그아웃</a></li>					
					</c:if>
				</ul>
			</nav>
		</div>
	</div>

	<div class="main-header">
		<div class="main-header-logo">
			<!-- Logo Header -->
			<div class="logo-header" data-background-color="dark">
				<a href="index.html" class="logo"> <img
					src="assets/img/kaiadmin/logo_light.svg" alt="navbar brand"
					class="navbar-brand" height="20" />
				</a>
				<div class="nav-toggle" data-bs-toggle="offcanvas"
					data-bs-target="#offcanvasResponsive"
					aria-controls="offcanvasResponsive">
					<button class="btn btn-toggle toggle-sidebar">
						<i class="gg-menu-right"></i>
					</button>
					<button class="btn btn-toggle sidenav-toggler">
						<i class="gg-menu-left"></i>
					</button>
				</div>
			</div>
			<!-- End Logo Header -->
		</div>
		<!-- Navbar Header -->
		<nav
			class="navbar navbar-header navbar-header-transparent navbar-expand-lg border-bottom">
			<div class="container-fluid justify-content-between">
				<nav
					class="navbar navbar-header-left navbar-expand-lg navbar-form nav-search p-0 d-none d-lg-flex">
					<a class="navbar-brand"> <img
						src="assets/img/kaiadmin/logo_light.svg" alt="navbar brand"
						class="navbar-brand" height="20" />
					</a>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="#">상점</a></li>
						<li class="nav-item"><a class="nav-link" href="#">암벽장</a></li>
						<li class="nav-item"><a class="nav-link" href="CREWPAGE.do">크루</a></li>
						<li class="nav-item"><a class="nav-link" href="RANKPAGE.do">랭킹</a></li>
						<li class="nav-item"><a class="nav-link" href="COMMUNITYPAGE.do">커뮤니티</a></li>
					</ul>
				</nav>
				<ul class="navbar-nav topbar-nav ms-md-auto align-items-center">
					<c:if test="${empty MEMBER_ID}">
						<li class="nav-item">
							<a class="nav-link" href="LOGINPAGE.do"> login </a>
						</li>
					</c:if>
					<c:if test="${not empty MEMBER_ID}">
						<li class="nav-item">
							<a class="nav-link" href="LOGINPAGE.do"> logout </a>
						</li>
					</c:if>
					<li class="nav-item">
						<a class="nav-link" href="#"> 마이페이지 </a>
					</li>
				</ul>
			</div>
		</nav>
		<!-- End Navbar -->
	</div>
	<!-- container start -->
	<div class="container">
		<div class="page-inner">
			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="card card-stats card-round p-5 ">
						<div class="row">
							<div class="col-12 d-flex justify-content-center">
								<div class="avatar avatar-xxl">
									<img src="assets/img/jm_denis.jpg"
										class="avatar-img rounded-circle" alt="profile image" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<div class="info-user pt-4 pb-4">
									<h5>닉네임 : ${MEMBERDATA.member_name}</h5>
									<h5>전화번호 : ${MEMBERDATA.member_phone}</h5>
									<h5>이메일 : ${MEMBERDATA.member_id}</h5>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 text-center">
								<a href="CHANGEMEMBERPAGEACTION.do">
									<button class="btn btn-secondary">회원정보 변경</button>
								</a>
							</div>
							<div class="col-md-6 text-center">
								<a href="DELETEMEMBERACTION.do">
									<button class="btn btn-danger">회원 탈퇴</button>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row justify-content-center ">
				<div class="col-md-12">
					<div class="card card-stats card-round pt-3 px-5 pb-5">
						<ul class="nav nav-tabs nav-line nav-color-secondary"
							id="line-tab" role="tablist">
							<li class="nav-item">
								<a class="nav-link active"
								id="line-post-tab" data-bs-toggle="pill" href="#line-post"
								role="tab" aria-controls="pills-post" aria-selected="true">
									내가 작성한 글 관리 
								</a>
							</li>
							<c:if test="${MEMBERDATA.member_role == 'T'}" >
								<li class="nav-item"><a class="nav-link" id="line-crew-tab"
									data-bs-toggle="pill" href="#line-crew" role="tab"
									aria-controls="pills-crew" aria-selected="false"> 신규 회원 관리 </a>
								</li>
							</c:if>
						</ul>
						<div class="tab-content mt-3 mb-3" id="line-tabContent">
							<div class="tab-pane fade show active" id="line-post"
								role="tabpanel" aria-labelledby="line-post-tab">
								<table class="table table-hover">
									<tbody>
										<c:forEach var="board" items="${BOARD}">
											<tr>
												<td><a href="BOARDONE.do?num=${board.num}"
													class="text-muted"> ${board.TITLE} </a></td>
												<td align="right">
													<button class="btn btn-primary me-3"
														onclick="location.href='BOARDUPDATEPAGEACTION.do?num=${board.BOARD_ID}'">수정</button>
													<button class="btn btn-danger"
														onclick="deleteBoard(${board.BOARD_ID})">삭제</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="tab-pane fade" id="line-crew" role="tabpanel"
								aria-labelledby="line-crew-tab">
								<div class="row">
									<div class="col-12 text-center">
										<h3>신규 회원 목록</h3>
										<c:forEach var="crew" items="${MEMBER_LIST}">
											<p>${crew.member_name}</p>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- container end -->
	</div>
	<!--   Core JS Files   -->
	<script src="assets/js/core/jquery-3.7.1.min.js"></script>
	<script src="assets/js/core/popper.min.js"></script>
	<script src="assets/js/core/bootstrap.min.js"></script>
	<script type="text/javascript">
		function deleteBoard(boardNum){
			if(confirm('정말 삭제하시겠습니까?')){
				location.href='BOARDDELETE.do?num='+boardNum;
			}
		}
	
	</script>
</body>

</html>