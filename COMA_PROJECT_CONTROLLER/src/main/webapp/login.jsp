<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코마 : 로그인 페이지</title>
<!-- Fonts and icons -->
<script src="assets/js/plugin/webfont/webfont.min.js"></script>
<script src="https://kit.fontawesome.com/7f7b0ec58f.js" crossorigin="anonymous"></script>
<!-- CSS Files -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/css/plugins.min.css" />
<link rel="stylesheet" href="assets/css/kaiadmin.css" />
</head>
<body>
   <!-- Core JS Files -->
   <script src="assets/js/core/jquery-3.7.1.min.js"></script>
   <script src="assets/js/core/popper.min.js"></script>
   <script src="assets/js/core/bootstrap.min.js"></script>
   <!-- 카카오 로그인 API 스크립트 -->
   <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
   <!-- 네이버 로그인 API 스크립트 -->
   <script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>

   <div class="main-header">
      <div class="main-header-logo">
         <!-- Logo Header -->
         <div class="logo-header" data-background-color="dark">
            <a href="index.jsp" class="logo"> <img src="assets/img/kaiadmin/logo_light.svg" alt="navbar brand" class="navbar-brand" height="20" /></a>
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
      <nav class="navbar navbar-header navbar-header-transparent navbar-expand-lg border-bottom">
         <div class="container-fluid justify-content-between">
            <nav class="navbar navbar-header-left navbar-expand-lg navbar-form nav-search p-0 d-none d-lg-flex">
               <a class="navbar-brand"> <img src="assets/img/kaiadmin/logo_light.svg" alt="navbar brand" class="navbar-brand" height="20" /></a>
               <ul class="navbar-nav">
                  <li class="nav-item"><a class="nav-link" href="#">상점</a></li>
                  <li class="nav-item"><a class="nav-link" href="#">암벽장</a></li>
                  <li class="nav-item"><a class="nav-link" href="#">크루</a></li>
                  <li class="nav-item"><a class="nav-link" href="#">선수페이지</a></li>
                  <li class="nav-item"><a class="nav-link" href="#">뉴스페이지</a></li>
               </ul>
            </nav>
            <ul class="navbar-nav topbar-nav ms-md-auto align-items-center">
               <li class="nav-item"><a class="nav-link" href="#"> 로그인 </a></li>
               <li class="nav-item"><a class="nav-link" href="#"> 마이페이지 </a></li>
            </ul>
         </div>
      </nav>
      <!-- End Navbar -->
   </div>
   <!-- container start -->
   <div class="container">
      <div class="page-inner w-100 vh-100 d-flex justify-content-center align-items-center">
         <div class="card card-stats card-round p-3">
            <div class="card-body">
               <h3 class="text-center">로그인</h3>
               <form id="loginForm" action="LOGINACTION.do" method="POST">
                  <div class="row">
                     <div class="col-md-3 d-flex align-items-center">
                        <h6 class="m-0">아이디</h6>
                     </div>
                     <div class="col-md-9">
                        <div class="form-group">
                           <input type="email" class="form-control" id="email" name="email" placeholder="아이디를 입력해주세요" />
                        </div>
                     </div>
                  </div>
                  <div class="row">
                     <div class="col-md-3 d-flex align-items-center">
                        <h6 class="m-0">비밀번호</h6>
                     </div>
                     <div class="col-md-9">
                        <div class="form-group">
                           <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력해주세요" />
                        </div>
                     </div>
                  </div>
                  자동로그인 &nbsp;<input type = "checkbox" name = "AUTO_LOGIN" value = "checkLogin">
                  <!-- 네이버 로그인 버튼 -->
                  <div class="row py-4">
                     <div class="col-md-6">
                        <button type="button" class="btn btn-success" id="naverLoginBtn">네이버 로그인</button>
                     </div>
                     <div class="col-md-6">
                        <!-- 카카오 로그인 버튼 -->
                        <button type="button" class="btn btn-warning" id="kakaoLoginBtn">카카오 로그인</button>
                     </div>
                  </div>
                  
                  <div class="row">
                     <div class="col-12">
                        <button type="button" class="btn btn-secondary w-100">회원가입</button>
                     </div>
                  </div>
                  <div class="row pt-3">
                     <div class="col-12">
                        <button type="submit" class="btn btn-primary w-100">로그인</button>
                     </div>
                  </div>
               </form>
            </div>
         </div>
      </div>
   </div>

   <script type="text/javascript">
        // 네이버 로그인 초기화
        var naver_id_login = new naver_id_login("kQSIom2rw1yt29HcbNc8", "NAVER.do"); // clint id, 콜백 url
         var state = naver_id_login.getUniqState();
         naver_id_login.setDomain("NAVER.do");
         naver_id_login.setState(state);
         naver_id_login.setPopup();
         naver_id_login.init_naver_id_login();

        function initNaverLogin() {
            // 네이버 로그인 객체 생성 및 초기화
            naverLogin = new naver.LoginWithNaverId({
                clientId: "kQSIom2rw1yt29HcbNc8",
                callbackUrl: "MAINPAGEACTION.do",
                isPopup: false,
                callbackHandle: true
            });
            naverLogin.init();
        }

        // 카카오 로그인 초기화
        Kakao.init('f68644c7e9866ef898677d5e1a260265'); // 발급받은 JavaScript 키 사용
        console.log('Kakao SDK 초기화 여부:', Kakao.isInitialized());

        // 네이버 로그인 버튼 클릭 이벤트
        document.getElementById('naverLoginBtn').onclick = function() {
            naverLogin.getLoginStatus(function(status) {
                if (status) {
                    // 로그인 성공 시 사용자 정보 가져오기
                    var email = naverLogin.user.getEmail();
                    // 서버로 사용자 정보 전송
                    sendToController({
                        email: email,
                    });
                } else {
                    console.log("callback 처리에 실패하였습니다.");
                }
            });
        };

        // 카카오 로그인 버튼 클릭 이벤트
        document.getElementById('kakaoLoginBtn').onclick = function() {
            Kakao.Auth.login({
                success: function(authObj) {
                    console.log(authObj); // 인증 성공 후 authObj 확인
                    Kakao.Auth.setAccessToken(authObj.access_token); // access 토큰값 저장
                    // 사용자 정보 요청
                    Kakao.API.request({
                        url: '/v2/user/me',
                        success: function(res) {
                            var email = res.kakao_account.email;
                            console.log('카카오 로그인 성공:', email);
                            // 서버로 사용자 정보 전송
                            sendToController({
                                email: email,
                            });
                        },
                        fail: function(error) {
                            console.error('카카오 로그인 사용자 정보 요청 실패:', error);
                        }
                    });
                },
                fail: function(error) {
                    console.error('카카오 로그인 실패:', error);
                }
            });
        };

        // 서버로 사용자 정보 전송
        function sendToController(userInfo) {
            $.ajax({
                url: 'LOGINACTION.do', // 서버의 로그인 처리 URL
                method: 'POST',
                data: userInfo,
                success: function(response) {
                    console.log('서버 응답:', response);
                    // 로그인 성공 후 페이지 이동
                    window.location.href = 'MAINPAGEACTION.do'; // 성공 후 페이지 이동
                },
                error: function(error) {
                    console.error('통신 오류:', error);
                    alert('로그인에 실패했습니다. 다시 시도해 주세요.');
                }
            });
        }

    </script>
    
</body>
</html>
