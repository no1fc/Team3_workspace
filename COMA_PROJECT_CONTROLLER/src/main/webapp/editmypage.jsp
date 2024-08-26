<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>

<!-- Fonts and icons -->
<script src="assets/js/plugin/webfont/webfont.min.js"></script>
<script>
        WebFont.load({
            google: { families: ["Public Sans:300,400,500,600,700"] },
            custom: {
                families: [
                    "Font Awesome 5 Solid",
                    "Font Awesome 5 Regular",
                    "Font Awesome 5 Brands",
                    "simple-line-icons",
                ],
                urls: ["assets/css/fonts.min.css"],
            },
            active: function () {
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
         $("#check-pw-btn").click(function(event) { // 
            
            var passwordCheckPageField = document.getElementById('passwordCheckPage');
            var editmyPageField = document.getElementById('editmyPage');
            var passwordCheckField = document.getElementById('member_password');
            var passwordCheck = passwordCheckField.value; // 비밀번호 확인 input 입력값
            passwordCheckPageField.classList.add("d-none");
               editmyPageField.classList.remove("d-none");
            
            $.ajax({
                  type: "POST",
                  url: "", // 서버에서 비밀번호 맞는지 검사를 처리하는 URL
                  data: { // POST로 보낼때에는 data로 보낸다~!
                      member_password: passwordCheck
                  },
                  dataType: "text",
                  success: function(data) {
                      if (data != null) {
                         epasswordCheckPageField.classList.add("d-none");
                         editmyPageField.classList.remove("d-none");
                      } 
                      else {
                         alert("비밀번호가 맞지 않습니다!");
                         return false;
                      }
                  },
                  error: function(error) {
                      console.log("응답 실패...");
                      console.log(error);
                  }
              });
          });
      });
   </script>
   
   <script>
      $(document).ready(function() {
            $("#uploadPhoto").click(function(event) {
               var fileInput = document.getElementById('photoUpload');
               var file = fileInput.files[0];
               var fileError = document.getElementById('fileError');
               fileError.textContent = '';
   
               if (file) {
                  
                   var fileTypes = ['image/jpeg', 'image/png'];
                  
                   if (!fileTypes.includes(file.type)) {
                      $(fileError).text("jpg파일 또는 png파일만 업로드 가능합니다!!");
                       fileInput.value = '';
                       return;
                   }
   
                   if (file.size > 50 * 1024) {
                      $(fileError).text("파일 크기는 50KB를 넘을 수 없습니다.");
                       fileInput.value = '';
                       return;
                   }
                   else {
                      $('#photoModal').modal('hide');
                   }
               }
           });
        });
    </script>
    
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
         $("#update").click(function(event) { // 수정 버튼 클릭했을 때의 함수
              if (!passwordCheckPassed) { // 비밀번호 같지않다면
                 event.preventDefault(); // 폼 제출을 막는 메서드 실행 후
                  alert("비밀번호를 다시 확인해주세요."); // alert창 나타낸다.
                  return false; // false 반환
              }
          });
      });
   </script>



   <!-- gnb body -->
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
                  <li class="nav-item"><a class="nav-link" href="#">상점</a></li>
                  <li class="nav-item"><a class="nav-link" href="#">암벽장</a></li>
                  <li class="nav-item"><a class="nav-link" href="#">크루</a></li>
                  <li class="nav-item"><a class="nav-link" href="#">선수페이지</a></li>
                  <li class="nav-item"><a class="nav-link" href="#">뉴스페이지</a></li>
               </ul>
            </nav>

            <ul class="navbar-nav topbar-nav ms-md-auto align-items-center">
               <li class="nav-item"><a class="nav-link" href="#">login</a></li>
               <li class="nav-item"><a class="nav-link" href="#">마이페이지</a></li>
            </ul>
         </div>
      </nav>
      <!-- End Navbar -->
   </div>
   <!-- container start -->
   <div class="container pt-3">
      <div class="page-inner">
         <div class="card card-stats card-round p-3" id="passwordCheckPage">
            <div class="card-header">
               <h3 class="text-center">비밀번호 확인</h3>
            </div>
            <div class="card-body">
               <div class="row">
                  <div class="col-md-2 d-flex align-items-center">
                     <p class="mb-0">비밀번호</p>
                  </div>
                  <div class="col-md-10">
                     <div class="form-group">
                        <input type="password" class="form-control" id="member_password"
                           placeholder="비밀번호를 입력해주세요" />
                     </div>
                  </div>
               </div>
               <div class="card-action text-center">
                  <button type="submit" class="btn btn-primary px-5"
                     id="check-pw-btn" >확인</button>
               </div>
            </div>
         </div>
         <form action="SIGNUPACTION.do">
         <div class="card card-stats card-round p-3 d-none" id="editmyPage"
            >
            <div class="card-header">
               <h3 class="text-center">회원 정보 수정</h3>
            </div>
            
            <div class="card-body">
               <div class="row my-3">
                  <div class="col-12 d-flex justify-content-center">
                     <div class="avatar avatar-xxl">
                        <img src= "${data.member_profile}"  alt="profile"
                           class="avatar-img rounded-circle">
                     </div>
                  </div>
                  <div class="row pt-3">
                     <div class="col-12 d-flex justify-content-center">
                        <button type="button" class="btn btn-secondary"
                           id="changePhotoBtn">사진 변경</button>
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-2 d-flex align-items-center">
                     <p class="mb-0">이름 : ${data.member_name}</p>
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-2 d-flex align-items-center">
                     <p class="mb-0">전화번호</p>
                  </div>
                  <div class="col-md-10">
                     <div class="form-group">
                        <input type="text" class="form-control" id="phone"
                           value="${data.member_phone}"
                           placeholder="전화번호를 입력해주세요" />
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
                        <p class="mb-0">변경할 비밀번호</p>
                     </div>
                     <div class="col-md-10">
                        <div class="form-group">
                           <input type="password" class="form-control"
                              id="member_password" name="member_password"
                              placeholder="변경할 비밀번호를 입력해주세요"/>
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
                              name="password_check" placeholder="변경할 비밀번호를 확인해주세요"/>
                           <small id="errorHelp" class="form-text text-muted"
                              style="display: none;"></small>
                        </div>
                     </div>
                  </div>
            </div>
            <div class="card-action text-center">
               <button type="button"
                  class="btn btn-black px-5 mb-3 mb-sm-0 me-0 me-sm-4">취소</button>
               <button type="submit" id="update" class="btn btn-primary px-5">수정</button>
            </div>
         </div>
         </form>
      </div>

      <!-- modal -->
      <div class="modal fade" id="photoModal" tabindex="-1" role="dialog"
         aria-labelledby="photoModalLabel" aria-hidden="true">
         <div class="modal-dialog" role="document">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="photoModalLabel">사진 변경</h5>
                  <button type="button" class="close" data-dismiss="modal"
                     aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <div class="modal-body">
                  <form>
                     <div class="form-group">
                        <label for="photoUpload">새 프로필 사진 업로드</label> <input type="file"
                           class="form-control-file" id="photoUpload" name="photoUpload">
                     </div>
                     <div class="form-group">
                        <button type="button" class="btn btn-primary" id="uploadPhoto">업로드</button>
                        <small class="error" id="fileError"></small>
                     </div>
                  </form>
               </div>
               <div class="modal-footer">
                  <button type="button" class="btn btn-secondary"
                     data-dismiss="modal" id="photoClose">닫기</button>
               </div>
            </div>
         </div>
      </div>
   </div>

   <!-- Core JS Files -->
   <script src="assets/js/core/jquery-3.7.1.min.js"></script>
   <script src="assets/js/core/popper.min.js"></script>
   <script src="assets/js/core/bootstrap.min.js"></script>

   <script>
        $(document).ready(function() {
            $('#changePhotoBtn').click(function() {
                $('#photoModal').modal('show');
            });
            $('#photoModal .close').click(function() {
                $('#photoModal').modal('hide');
            });
            $('#photoClose').click(function() {
                $('#photoModal').modal('hide');
            });
        });
    </script>
</body>
</html>
