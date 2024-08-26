<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Fonts and icons -->
<script src="assets/js/plugin/webfont/webfont.min.js"></script>
<script>
   WebFont.load({
      google : {
         families : [ "Public Sans:300,400,500,600,700" ]
      },
      custom : {
         families : [ "Font Awesome 5 Solid", "Font Awesome 5 Regular",
               "Font Awesome 5 Brands", "simple-line-icons", ],
         urls : [ "assets/css/fonts.min.css" ],
      },
      active : function() {
         sessionStorage.fonts = true;
      },
   });
</script>

<!-- CSS Files -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/css/plugins.min.css" />
<link rel="stylesheet" href="assets/css/kaiadmin.css" />

<style>
.input-success {
   border-color: #28a745;
   background-color: #e9fbe9;
}

.input-error {
   border-color: #dc3545;
   background-color: #fbe9e9;
}

.form-text {
   display: none;
}

.form-text.show {
   display: block;
}

.alert-success {
   color: #28a745;
}

.alert-danger {
   color: #dc3545;
}
</style>

</head>
<body>
   <script src="https://code.jquery.com/jquery-3.7.1.min.js"
      integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
      crossorigin="anonymous"></script>
   <!-- json 연결 -->



   <script>
   $(document).ready(function() {
       let idCheckPassed = false;

       function validateEmail() {
           var idField = document.getElementById('member_id');
           var errorHelp02 = document.getElementById('errorHelp02');
           var idCheck = $(this).val();
           
           // 간단한 이메일 형식 정규 표현식
           var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
           
           if (!emailPattern.test(idCheck)) {
               idField.classList.remove('input-success');
               idField.classList.add('input-error');
               errorHelp02.classList.remove('form-text');
               errorHelp02.classList.add('show');
               errorHelp02.style = "display: block;";
               errorHelp02.textContent = '유효하지 않은 이메일 형식입니다. 다시 입력해 주세요.';
               idCheckPassed = false;
               $('#check-id-btn').prop('disabled', true); // 버튼 비활성화
           } else {
               // 이메일 형식이 올바르면 중복 검사 버튼 활성화
               idField.classList.remove('input-error');
               idField.classList.add('input-success');
               errorHelp02.classList.remove('show');
               errorHelp02.classList.add('form-text');
               errorHelp02.style = "display: block;";
               errorHelp02.textContent = '유효한 이메일 형식입니다.^^';
               idCheckPassed = true;
               $('#check-id-btn').prop('disabled', false); // 버튼 활성화
           }
       }

       // 이메일 형식 검사 시 validateEmail 함수 호출
       $('#member_id').on('input', validateEmail);

       // 중복 검사 버튼 클릭 이벤트
       $("#check-id-btn").click(function() {
           var idField = document.getElementById('member_id');
           var errorHelp02 = document.getElementById('errorHelp02');
           var idCheck = $('#member_id').val();

           if (!idCheckPassed) {
               alert("이메일 형식이 올바르지 않습니다. 이메일 형식으로 작성해주세요.");
               return false;
           }

           $.ajax({
               type: "POST",
               url: "checkId", // 서버에서 이메일 중복 검사를 처리하는 URL
               data: {
                   member_id: idCheck
               },
               dataType: "text",
               success: function(data) {
                   if (data === 'true') {
                       idField.classList.remove('input-error');
                       idField.classList.add('input-success');
                       errorHelp02.classList.remove('form-text');
                       errorHelp02.classList.add('show');
                       errorHelp02.style = "display: block;";
                       errorHelp02.textContent = '사용가능 합니다.^^';
                       idCheckPassed = true;
                   } else {
                       idField.classList.remove('input-success');
                       idField.classList.add('input-error');
                       errorHelp02.classList.remove('form-text');
                       errorHelp02.classList.add('show');
                       errorHelp02.style = "display: block;";
                       errorHelp02.textContent = '중복됩니다. 다시 입력해 주세요.';
                       idCheckPassed = false;
                   }
               },
               error: function(error) {
                   console.log("응답 실패...");
                   console.log(error);
               }
           });
       });

       // 가입 버튼 클릭 이벤트
       $("#joinbtn").click(function() {
           if (!idCheckPassed) {
               alert("아이디 중복 확인을 완료해주세요.");
               return false;
           }
       });
   });
   </script>

   <script>
      
      document.addEventListener('DOMContentLoaded', function() {
         var passwordField = document.getElementById('member_password');
         var passwordCheckField = document.getElementById('password_check');
         var errorHelp = document.getElementById('errorHelp');

         function validatePasswords() {
            var password = passwordField.value;
            var passwordCheck = passwordCheckField.value;
            if (passwordCheck !== "") {
               if (password === passwordCheck) {
                  passwordCheckField.classList.remove('input-error');
                  passwordCheckField.classList.add('input-success');
                  errorHelp.classList.remove('show');
                  errorHelp.style = "display: block;"
                  errorHelp.classList.add('form-text');
                  errorHelp.textContent = '비밀번호가 일치합니다.^^';

               } else {
                  passwordCheckField.classList.remove('input-success');
                  passwordCheckField.classList.add('input-error');
                  errorHelp.classList.remove('form-text');
                  errorHelp.classList.add('show');
                  errorHelp.style = "display: block;"
                  errorHelp.textContent = '비밀번호가 일치하지 않습니다. 다시 확인해 주세요.';

               }
            }

         }

         // 비밀번호 입력 필드와 비밀번호 확인 필드의 입력 이벤트
         passwordField.addEventListener('input', validatePasswords);
         passwordCheckField.addEventListener('input', validatePasswords);

         // 다른 입력 필드나 선택 박스에서 포커스가 이동할 때 비밀번호를 확인
         var otherInputs = document.querySelectorAll('input, select');
         otherInputs.forEach(function(input) {
            input.addEventListener('blur', function(event) {
               // 비밀번호 필드가 아닌 경우에는 validatePasswords를 호출하지 않음
               if (event.target.id !== 'password_check'
                     && event.target.id !== 'member_password') {
                  validatePasswords();
               }
            });
         });
         
         $("#sendSMS").click(function(event) {
     		event.preventDefault();//기본 동작(폼 제출 막기)
     		var member_phoneNumber = $("#member_phoneNumber").val();
     		console.log(member_phoneNumber);
     		$.ajax({
     			type:"POST",
     			url:"smsCheck",
     			data:{"member_phoneNumber":member_phoneNumber}, // 서버에 데이터를 전송하는 방식
     			dataType:"text", // 서버로 부터 데이터를 받아오는 방식
     			success: function(data) {
     				//data 로그
     				console.log("data 로그임 "+data);
     				//사용자가 입력한 id를 받아오고
     				var checkSMS = $("#phone_check").val();
     				
     					//만약 사용자가 입력한 아이디와 DB안에 있는 아이디가 같으면 
     					if(data == checkSMS){
							console.log("인증완료");
     					}
     					else{
     						console.log("인증 실패");
     					}
     				
     			},
     			error: function(error) {
     				console.log("ㅎㅎ 실패함...");
     			}
     		});
     	});
      });
   </script>

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
   <div class="container pt-3">
      <div class="page-inner">
         <div class="card card-stats card-round p-3">
            <div class="card-header">
               <h3 class="text-center">회원가입</h3>
            </div>
            <div class="card-body">
               <form id="signup-form" action="main.jsp" method="post">
                  <div class="row">
                     <div class="col-md-2 d-flex align-items-center">
                        <p class="mb-0">아이디</p>
                     </div>
                     <div class="col-md-7">
                        <div class="form-group">
                           <input type="email" class="form-control" id="member_id"
                              name="member_id" placeholder="이메일을 입력해주세요" required /> <small
                              id="errorHelp02" class="form-text text-muted"
                              style="display: none;"></small>

                        </div>
                     </div>
                     <div class="col-md-3  d-flex align-items-center">
                        <button id="check-id-btn" type="button"
                           class="w-100 btn btn-secondary" disabled>아이디 중복 검사</button>
                        <!-- 결과 메시지를 표시할 요소 -->

                        <!-- datas / json으로 중복검사 후에 중복되면 빨간 insert창으로 변경 아이디가 중복됩니다~! 문구 띄우기 -->
                        <!-- json 수/목에 진도 나가니까 그때 -->
                     </div>
                  </div>
                  <div class="row">
                     <div class="col-md-2 d-flex align-items-center">
                        <p class="mb-0">비밀번호</p>
                     </div>
                     <div class="col-md-10">
                        <div class="form-group">
                           <input type="password" class="form-control"
                              id="member_password" name="member_password"
                              placeholder="비밀번호를 입력해주세요" required />
                        </div>
                     </div>
                  </div>
                  <div class="row">
                     <div class="col-md-2 d-flex align-items-center">
                        <p class="mb-0">비밀번호 확인</p>
                     </div>
                     <div class="col-md-10">
                        <div id="password-check-container" class="form-group">
                           <input type="password" class="form-control" id="password_check"
                              name="password_check" placeholder="비밀번호를 확인해주세요" required />
                           <small id="errorHelp" class="form-text text-muted"
                              style="display: none;"> 비밀번호가 일치하지 않습니다. 다시 확인해 주세요. </small>
                        </div>
                     </div>
                  </div>
                  <div class="row">
                     <div class="col-md-2 d-flex align-items-center">
                        <p class="mb-0">이름</p>
                     </div>
                     <div class="col-md-10">
                        <div class="form-group">
                           <input type="text" class="form-control" id="member_name"
                              name="member_name" placeholder="이름을 입력해주세요" required />
                        </div>
                     </div>
                  </div>
                  <div class="row">
                     <div class="col-md-2 d-flex align-items-center">
                        <p class="mb-0">지역</p>
                     </div>
                     <div class="col-md-10">
                        <div class="form-group">
                           <select id="member_location" name="member_location">
                              <option>서울시</option>
                              <option>부산광역시</option>
                              <option>대구광역시</option>
                              <option>대전광역시</option>
                              <option>인천광역시</option>
                              <option>광주광역시</option>
                              <option>울산광역시</option>
                              <option>경기도</option>
                              <option>충청남도</option>
                              <option>충청북도</option>
                              <option>전라남도</option>
                              <option>전라북도</option>
                              <option>경상남도</option>
                              <option>경상북도</option>
                              <option>강원도</option>
                              <option>제주도</option>
                              <option>독도</option>
                              <option>울릉도</option>
                              <option>함경북도</option>
                              <option>함경남도</option>
                              <option>평양</option>
                           </select>
                        </div>
                     </div>
                  </div>
                  <div class="row">
                     <div class="col-md-2 d-flex align-items-center">
                        <p class="mb-0">전화번호</p>
                     </div>
                     <div class="col-md-7">
                        <div class="form-group">
                           <input type="text" class="form-control" id="member_phoneNumber"
                              name="member_phoneNumber" placeholder="전화번호를 입력해주세요" required />
                        </div>
                     </div>
                     <div class="col-md-3  d-flex align-items-center ">
                        <button id="sendSMS" type="button" class="w-100 btn btn-secondary">인증번호
                           받기</button>
                     </div>
                  </div>
                  <div class="row">
                     <div class="col-md-2 d-flex align-items-center">
                        <p class="mb-0">인증확인</p>
                     </div>
                     <div class="col-md-7">
                        <div class="form-group">
                           <input type="text" class="form-control" id="phone_check"
                              name="phone_check" placeholder="인증번호를 입력해주세요" required />
                        </div>
                     </div>
                     <div class="col-md-3  d-flex align-items-center ">
                        <button type="button" class="w-100 btn btn-secondary">인증</button>
                     </div>
                  </div>
            </div>
            <div class="card-action text-center">
               <button type="button"
                  class="btn btn-black px-5 mb-3 mb-sm-0 me-0 me-sm-4">취소</button>
               <button type="submit" class="btn btn-primary px-5" id="joinbtn">가입</button>
            </div>
            </form>
         </div>
      </div>
      <!-- container end -->
   </div>
   <!--   Core JS Files   -->
   <script src="assets/js/core/jquery-3.7.1.min.js"></script>
   <script src="assets/js/core/popper.min.js"></script>
   <script src="assets/js/core/bootstrap.min.js"></script>


   <!-- jQuery Scrollbar -->
   <script src="assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

   <!-- Chart JS -->
   <script src="assets/js/plugin/chart.js/chart.min.js"></script>

   <!-- jQuery Sparkline -->
   <script src="assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

   <!-- Chart Circle -->
   <script src="assets/js/plugin/chart-circle/circles.min.js"></script>

   <!-- Datatables -->
   <script src="assets/js/plugin/datatables/datatables.min.js"></script>

   <!-- Bootstrap Notify -->
   <script src="assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>

   <!-- jQuery Vector Maps -->
   <script src="assets/js/plugin/jsvectormap/jsvectormap.min.js"></script>
   <script src="assets/js/plugin/jsvectormap/world.js"></script>

   <!-- Sweet Alert -->
   <script src="assets/js/plugin/sweetalert/sweetalert.min.js"></script>

   <!-- Kaiadmin JS -->
   <script src="assets/js/kaiadmin.min.js"></script>

   <!-- Kaiadmin DEMO methods, don't include it in your project! -->
   <script src="assets/js/setting-demo.js"></script>
   <script src="assets/js/demo.js"></script>
   <script>
      
   </script>
</body>
</html>