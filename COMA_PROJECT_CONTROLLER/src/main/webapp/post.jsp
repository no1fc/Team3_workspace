<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
   <div class="main-header">
      <div class="main-header-logo">
         <!-- Logo Header -->
         <div class="logo-header" data-background-color="dark">
            <a href="index.html" class="logo"> <img
               src="assets/img/kaiadmin/logo_light.svg" alt="navbar brand"
               class="navbar-brand" height="20" />
            </a>
            <div class="nav-toggle">
               <button class="btn btn-toggle toggle-sidebar">
                  <i class="gg-menu-right"></i>
               </button>
               <button class="btn btn-toggle sidenav-toggler">
                  <i class="gg-menu-left"></i>
               </button>
            </div>
            <button class="topbar-toggler more">
               <i class="gg-more-vertical-alt"></i>
            </button>
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
                  <li class="nav-item"><a class="nav-link" href="">상점</a></li>
                  <li class="nav-item"><a class="nav-link" href="">암벽장</a></li>
                  <li class="nav-item"><a class="nav-link" href="">크루</a></li>
                  <li class="nav-item"><a class="nav-link" href="">선수페이지</a></li>
                  <li class="nav-item"><a class="nav-link" href="">뉴스페이지</a></li>
               </ul>
            </nav>

            <ul class="navbar-nav topbar-nav ms-md-auto align-items-center">
               <li class="nav-item"><a class="nav-link" href="#"> login </a></li>
               <li class="nav-item"><a class="nav-link" href="#"> 마이페이지 </a></li>
            </ul>
         </div>
      </nav>
      <!-- End Navbar -->
   </div>
   <!-- container start -->
   <div class="container">
        <div class="page-inner">
            <div class="row py-3">
                <div class="col-12">
                    <h1 class="text-center">글제목</h1>
                </div>
            </div>
            <div class="row border-bottom border-dark pb-3">
                <div class="col-md-1">
                    <div class="avatar avatar-sm">
                        <img src="assets/img/profile.jpg" alt="profile" class="avatar-img rounded-circle"/>
                    </div>
                </div>
                <div class="col-md-11">
                    <p>작성자: 코마</p>
                </div>
            </div>
            <div class="row py-5">
                <div class="col-12 d-flex justify-content-center">
                    <div class="w-75">
                        <p class="text-start">
                            글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.
                            글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.
                            글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.글 내용 입니다.
                        </p>
                    </div>
                </div>
            </div>
            <div class="row border-top border-dark py-3">
                <div class="col-11">
                    <div class="form-group">
                        <input
                          type="text"
                          class="form-control"
                          id="comment"
                          placeholder="댓글를 입력해주세요"
                        />
                      </div>
                </div>
                <div class="col-1 d-flex align-items-center">
                    <button type="button" class="btn btn-secondary">댓글</button>
                </div>
            </div>
            <div class="row border-top border-bottom py-3 px-5">
                <div class="col-md-2">
                    <p>작성자: 주예나</p>
                </div>
                <div class="col-md-9">
                    <p>댓글입니다. 내용은 없습니다</p>
                </div>
                <div class="col-1">
                    <div class="dropdown">
                        <button
                          class="btn btn-icon btn-clean me-0"
                          type="button"
                          id="dropdownMenuButton"
                          data-bs-toggle="dropdown"
                          aria-haspopup="true"
                          aria-expanded="false"
                        >
                          <i class="fas fa-ellipsis-h"></i>
                        </button>
                        <div
                          class="dropdown-menu"
                          aria-labelledby="dropdownMenuButton"
                        >
                          <a class="dropdown-item" href="#">수정</a>
                          <a class="dropdown-item" href="#">삭제</a>
                        </div>
                      </div>
                </div>
            </div>
            <div class="row border-top border-bottom py-3 px-5">
                <div class="col-md-2">
                    <p>작성자: 주예나</p>
                </div>
                <div class="col-md-9">
                    <p>댓글입니다. 내용은 없습니다</p>
                </div>
                <div class="col-1">
                    <div class="dropdown">
                        <button
                          class="btn btn-icon btn-clean me-0"
                          type="button"
                          id="dropdownMenuButton"
                          data-bs-toggle="dropdown"
                          aria-haspopup="true"
                          aria-expanded="false"
                        >
                          <i class="fas fa-ellipsis-h"></i>
                        </button>
                        <div
                          class="dropdown-menu"
                          aria-labelledby="dropdownMenuButton"
                        >
                          <a class="dropdown-item" href="#">수정</a>
                          <a class="dropdown-item" href="#">삭제</a>
                        </div>
                      </div>
                </div>
            </div>
        </div>
   </div>

   <!--   Core JS Files   -->
   <script src="assets/js/core/jquery-3.7.1.min.js"></script>
   <script src="assets/js/core/popper.min.js"></script>
   <script src="assets/js/core/bootstrap.min.js"></script>
      
   </script>
</body>
</html>