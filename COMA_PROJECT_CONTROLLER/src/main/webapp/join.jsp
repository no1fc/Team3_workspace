<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>

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
         
         let passwordCheckPassed = false; // 전역변수 - 비밀번호가 같다 true false
         var passwordField = document.getElementById('member_password'); // 비밀번호 input
         var passwordCheckField = document.getElementById('password_check'); // 비밀번호 확인 input
         var errorHelp = document.getElementById('errorHelp'); // 비밀번호 input밑에 숨겨져있는 small 태그

         function checkPasswords() { // 비밀번호 일치하는 지 확인하는
            var password = passwordField.value; // 비밀번호 input 입력값
            var passwordCheck = passwordCheckField.value; // 비밀번호 확인 input 입력값
            
            if (passwordCheck !== "") { // 비밀번호 확인 input값이 입력되면
               if (password == passwordCheck) { // 비밀번호 입력값과 비밀번호확인 입력값이 같다면
                  passwordCheckField.classList.remove('input-error'); // input창 색깔 빨강 지우고
                  passwordCheckField.classList.add('input-success'); // input창 색깔 초록 추가해
                  errorHelp.style = "display: block;" // 그리고 small태그 나타나게해줘
                  errorHelp.textContent = '비밀번호가 일치합니다.^^'; // small태그내용은 이렇게 해주고
                  passwordCheckPassed = true; // true값으로 설정
               } 
               else {
                  passwordCheckField.classList.remove('input-success'); // input창 색깔 초록 지우고
                  passwordCheckField.classList.add('input-error'); // input창 색깔 빨강 추가해
                  errorHelp.style = "display: block;" // 그리고 small태그 나타나게해줘
                  errorHelp.textContent = '비밀번호가 일치하지 않습니다. 다시 확인해 주세요.'; // small태그내용은 이렇게 해주고
                  passwordCheckPassed = false; // false값으로 설정
               }
            }
            else { // 비밀번호 확인 input 값이 없다면
               passwordCheckField.classList.remove('input-error'); // input창 색깔 빨강 지우고
               errorHelp.style = "display: none;" // 그리고 small태그 다시 숨겨줘
            }
         }

         // 비밀번호 입력 필드와 비밀번호 확인 필드의 입력 이벤트
         passwordField.addEventListener('input', checkPasswords);
         passwordCheckField.addEventListener('input', checkPasswords);

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
         $("#joinbtn").click(function(event) { // 가입 버튼 클릭했을 때의 함수
              if (!passwordCheckPassed) { // 비밀번호 같지않다면
                 event.preventDefault(); // 폼 제출을 막는 메서드 실행 후
                  alert("비밀번호를 다시 확인해주세요."); // alert창 나타낸다.
                  return false; // false 반환
              }
          });
      });
   </script>
   
   <script>
   $(document).ready(function() {
       let idCheckPassed = false; // 전역변수 - 유효한 아이디(이메일) 입력 true false
       let idCheckBtnPassed = false; // 전역변수 - 중복검사 버튼 눌렀을 때 중복인지 true false
      
       function checkEmail() { // 이메일 확인 함수
          var idField = document.getElementById('member_id'); // 아이디 input
           var errorHelp02 = document.getElementById('errorHelp02'); // 아이디 input밑에 숨겨져있는 small 태그
           var idCheck = $(this).val(); // 아이디 input의 입력값
           
           var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // 간단한 이메일 형식 정규 표현식
           
           if (!emailPattern.test(idCheck)) { // 아이디 input의 입력값이 이메일 형식 표현식에 맞지 않다면
               idField.classList.add('input-error'); // input창 색깔 빨강 추가해
               errorHelp02.style = "display: block;"; // 그리고 small태그 나타나게해줘
               errorHelp02.textContent = '유효하지 않은 이메일 형식입니다. 다시 입력해 주세요.'; // small태그내용은 이렇게 해주고
               idCheckPassed = false; // false값으로 설정
               $('#check-id-btn').prop('disabled', true); // 버튼 비활성화
           } 
           else {
               // 이메일 형식이 올바르면 중복 검사 버튼 활성화
               idField.classList.remove('input-error'); // input창 색깔 빨강 지워
               errorHelp02.style = "display: block;";  // 그리고 small태그 나타나게해줘
               errorHelp02.textContent = '유효한 이메일 형식입니다.^^'; // small태그내용은 이렇게 해주고
               idCheckPassed = true; // true값으로 설정
               $('#check-id-btn').prop('disabled', false); // 버튼 활성화
           }
       }

       // 이메일 형식 검사 시 checkEmail 함수 호출
       $('#member_id').on('input', checkEmail);
      
       
       $("#check-id-btn").click(function() { // 중복 검사 버튼 누르면
           var idField = document.getElementById('member_id'); // 아이디 input
           var errorHelp02 = document.getElementById('errorHelp02'); // 아이디 input밑에 숨겨져있는 small 태그
           var idCheck = $('#member_id').val(); // 아이디 input의 입력값
           
         
           $.ajax({
               type: "POST", 
               url: "idcheck", // 서버에서 이메일 중복 검사를 처리하는 URL
               data: { // POST로 보낼때에는 data로 보낸다~!
                   member_id: idCheck
               },
               dataType: "text",
               success: function(data) { // 데이터 받는데 성공한 함수
                   if (data === 'true') { // data가 true 값이라면
                       idField.classList.remove('input-error'); // input창 색깔 빨강 지우고 
                       idField.classList.add('input-success'); // input창 색깔 초록 추가해
                       errorHelp02.style = "display: block;"; // 그리고 small태그 나타나게해줘 
                       errorHelp02.textContent = '사용가능 합니다.^^'; // 내용은 사용가능~~~!
                       idCheckBtnPassed = true; // 전역변수 true값 반환
                   } 
                   else { // false 값이라면
                       idField.classList.remove('input-success'); // input창 색깔 초록 지우고
                       idField.classList.add('input-error'); // input창 색깔 빨강 추가해 
                       errorHelp02.style = "display: block;"; // 그리고 small태그 나타나게해줘 
                       errorHelp02.textContent = '중복됩니다. 다시 입력해 주세요.'; // 내용은 중복실패
                       idCheckBtnPassed = false; // 전역변수 false값 반환
                   }
               },
               error: function(error) { // 데이터 받는데 실패한 함수 == 확인용도
                   console.log("응답 실패...");
                   console.log(error);
               }
           });
       });
       // 가입 버튼 클릭 이벤트
       $("#joinbtn").click(function(event) { // 가입 버튼 눌렀을때
           if(!idCheckBtnPassed) { // 전역변수 false라면
              event.preventDefault(); // 폼 제출을 막는 메서드 후
              alert("아이디 중복 확인을 완료해주세요."); // alert창 띄워줘
              return false;
           }
       });
   });
   </script>
   
   <script>
      $(document).ready(function() {
          var phoneNumberField = $('#member_phoneNumber');
   
          // 전화번호 포맷팅 함수
          function formatPhoneNumber(value) {
              // 숫자가 아닌 문자를 제거
              var cleaned = value.replace(/\D/g, '');
   
              // 010-1234-5678 형식으로 포맷팅
              if (cleaned.length <= 3) {
                 $('#check-btn').prop('disabled', true); // 버튼 비활성화
                  return cleaned;
              } else if (cleaned.length <= 7) {
                 $('#check-btn').prop('disabled', true); // 버튼 비활성화
                  return cleaned.slice(0, 3) + '-' + cleaned.slice(3);
              } else {
                 if(cleaned.length >= 11) { // 11자리 다 채웠으면
                    $('#check-btn').prop('disabled', false); // 버튼 활성화
                 }
                 else {
                    $('#check-btn').prop('disabled', true); // 버튼 비활성화
                 }
                  return cleaned.slice(0, 3) + '-' + cleaned.slice(3, 7) + '-' + cleaned.slice(7, 11);
              }
              
          }
   
          // 전화번호 필드의 입력 이벤트 핸들러
          phoneNumberField.on('input', function() {
              var value = $(this).val();
   
              // 포맷팅된 값을 입력 필드에 설정
              $(this).val(formatPhoneNumber(value));
          });
      });
   </script>
   
   <script>
      $(document).ready(function() {
         $("#check-btn").click(function() { // 인증번호 받기 버튼 누르면
            $('#phone_check').prop('disabled', false); // 버튼 활성화
            $('#member_phoneNumber').prop('readonly', true); // 버튼 비활성화
              
          });
      });
   </script>
   
   <script>
      // 전화번호 보내는 함수
      $(document).ready(function() {
         let phoneCheckPassed = false; // 전역변수
         
         $("#check-btn").click(function() { // 인증 버튼 
            var phoneCheck = $('#member_phoneNumber').val(); // 전화번호 input 입력값
            var phoneCheckField = document.getElementById('phone_check'); // 인증번호 input창
              var errorHelp03 = document.getElementById('errorHelp03'); // small 태그
            $.ajax({
               type : "POST",
               url : "smscheck", // 서버에서 이메일 중복 검사를 처리하는 URL
               data : { // POST로 보낼때에는 data로 보낸다~!
                  member_phoneNumber : phoneCheck
               },
               dataType : "text",
               success : function(data) { // data받는데 성공한 함수
                  $("#check_num").click(function() { // 인증 버튼 눌렀을 때 함수
                     if (data == $('#phone_check').val()) { // 받아온 data값과 인증번호 input창 입력값이 같다면
                        phoneCheckField.classList.remove('input-error'); // input창 색깔 빨강 지우고
                        phoneCheckField.classList.add('input-success'); // input창 색깔 초록 추가해
                        errorHelp03.style = "display: block;" // 그리고 small태그 나타나게해줘 
                        errorHelp03.textContent = '인증번호가 일치합니다.^^'; // 내용은 인증번호 일치~~~!
                        phoneCheckPassed = true; // 전역변수 값은 true로
                     }
                     else {
                        phoneCheckField.classList.remove('input-success'); // input창 색깔 초록 지우고
                        phoneCheckField.classList.add('input-error'); // input창 색깔 빨강 추가해
                        errorHelp03.style = "display: block;" // 그리고 small태그 나타나게해줘 
                        errorHelp03.textContent = '인증번호가 일치하지 않습니다. 다시 확인해 주세요.'; // 내용은 인증번호 불일치
                        phoneCheckPassed = false; // 전역변수 값은 false로
                     }
                  });
               },
               error : function(error) { // data 못받아 왔을 때 == 확인 용도
                  console.log("응답 실패...");
                  console.log(error);
               }
            });
         });
         $("#joinbtn").click(function(event) { // 가입 버튼 눌렀을 때
              if (!phoneCheckPassed) { // 전역변수 값 false 라면
                 event.preventDefault(); // 폼 제출을 막는메서드 후
                  alert("인증번호를 다시 확인해주세요."); // alert 창 띄워줘
                  return false;
              }
          });
      });
   </script>
   <!--  -->

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
               <form id="signup-form" action="SIGNUPACTION.do" method="post">
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
                              <option>무건국</option>
                              <option>서울특별시</option>
                              <option>세종특별자치도</option>
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
                              <option>함경북도</option>
                              <option>함경남도</option>
                              <option>평안남도</option>
                              <option>평안북도</option>
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
                              name="member_phoneNumber" placeholder="하이폰(-)을 제외한 전화번호 11자리를 입력해주세요" required />
                        </div>
                     </div>
                     <div class="col-md-3  d-flex align-items-center ">
                        <button type="button" id="check-btn" class="w-100 btn btn-secondary" disabled>인증번호
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
                              name="phone_check" placeholder="인증번호를 입력해주세요" disabled required />
                              <small id="errorHelp03" class="form-text text-muted"
                              style="display: none;"> 인증번호가 일치하지 않습니다. 다시 확인해 주세요. </small>
                        </div>
                     </div>
                     <div class="col-md-3  d-flex align-items-center ">
                        <button type="button" id="check_num" class="w-100 btn btn-secondary">인증</button>
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
   
</body>
</html>