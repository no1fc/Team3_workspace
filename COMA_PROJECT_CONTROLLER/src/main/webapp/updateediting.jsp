<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코마 : 글수정</title>

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
 	<!-- GNB 커스텀태그 -->
	<mytag:gnb member_id="${MEMBER_ID}"></mytag:gnb>
   <!-- container start -->
   <div class="container">
      <div class="page-inner">
         <div class="row py-3">
            <div class="col-12">
               <h1 class="text-center">글수정</h1>
            </div>
         </div>
        

         <!-- 게시글 번호가 없거나 빈 경우 메시지 표시 -->
         <c:if test="${empty BOARD_NUM}">
            <div class="alert alert-danger" role="alert">게시글 번호가 누락되었습니다.
            </div>
         </c:if>

         <!-- 게시글 번호가 있는 경우 -->
         <c:if test="${not empty BOARD_NUM}">
            <form action="BOARDUPDATEACTION.do?board_num=${BOARD_NUM}" method="POST"
               name="updateediting" onsubmit="return validateForm()">
               <div class="row">
                  <div
                     class="col-md-1 d-flex align-items-center justify-content-center justify-content-md-end">
                     <h3>제목</h3>
                  </div>
                  <div class="col-md-11">
                     <div class="form-group">
                        <!-- C에서 DATA 가져오기 -->
                        <input type="text" class="form-control" id="title" name="title"
                           value="${BOARD_TITLE}" required
                           placeholder="글의 제목을 입력해주세요 ( 제한 : 100자 )" maxlength="100" />
                        <div id="titleError" class="byte-error">제목은 100자를 넘을 수
                           없습니다.</div>
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div
                     class="col-md-1 d-flex justify-content-center justify-content-md-end">
                     <h3>내용</h3>
                  </div>
                  <div class="col-md-11">
                     <div class="form-group">
                        <div class="input-group">
                           <!-- C에서 DATA 가져오기 -->
                           <textarea id="content" class="form-control" name="content"
                              style="height: 500px !important;" required maxlength="1000"
                              placeholder="글의 내용을 입력해주세요 ( 제한 : 1000자 )">${BOARD_CONTENT}</textarea>
                        </div>
                     </div>
                     <!-- 바이트 제한을 넘어서면 작게 안내문구 보이도록 설정 -->
                     <div id="contentError" class="byte-error">내용은 1000자를 넘을 수
                        없습니다.</div>
                  </div>
               </div>
               <div class="row pt-5">
                  <div class="col-12 d-flex justify-content-center">
                     <!-- 취소 클릭시 cancelEditing 함수 호출 -->
                     <button type="button" class="btn btn-black me-4"
                        onclick="cancelEditing()">취소</button>
                     <!-- 버튼 클릭시 폼을 제출 (C로 보냄) -->
                     <button type="submit" class="btn btn-primary">수정</button>
                  </div>
               </div>
            </form>
         </c:if>
      </div>
   </div>

   <script type="text/javascript">
    // 바이트 계산 함수
    function getByteLength(str) {
        // TextEncoder를 사용하여 문자열의 바이트 길이 계산
        return new TextEncoder().encode(str).length; // 바이트 길이 반환
    }

    // 제목과 내용의 입력 체크 함수
    function updateByteCount() {
        // 제목과 내용 입력 필드 값 가져오기
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;

        // 가져온 입력 값들의 바이트 길이 계산
        const titleByteCount = getByteLength(title);
        const contentByteCount = getByteLength(content);

        // 오류 메시지를 표시할 요소를 가져옴
        const titleError = document.getElementById('titleError');
        const contentError = document.getElementById('contentError');

        // 제목 바이트가 100을 넘었다면
        if (titleByteCount > 100) {
            // 바이트 초과 오류 메시지 표시
            titleError.style.display = 'block';
            // 초과된 문자를 제거하여 제목의 길이를 100 이하로 강제로 설정
            document.getElementById('title').value = title.slice(0, 100); 
        } else {
            // 기본값 : none, 오류메시지 숨김
            titleError.style.display = 'none';
        }

        // 내용 바이트가 1000을 넘었다면
        if (contentByteCount > 1000) {
            // 바이트 초과 오류 메시지 표시
            contentError.style.display = 'block';
            // 초과된 문자를 제거하여 내용의 길이를 1000 이하로 강제로 설정
            document.getElementById('content').value = content.slice(0, 1000); 
        } else {
            // 기본값 : none, 오류메시지 숨김
            contentError.style.display = 'none';
        }
    }

    // 제목 입력 필드와 내용 입력 필드에 대한 'input' 이벤트 리스너
    // 실시간 바이트 체크
    document.getElementById('title').addEventListener('input', updateByteCount);
    document.getElementById('content').addEventListener('input', updateByteCount);

    // 폼 검증 함수
    function validateForm() {
        // 폼 제출 전에 바이트 수를 업데이트하여 현재 상태를 반영
        updateByteCount(); 
        // 제목과 내용의 오류 메시지 표시 상태를 확인하여 폼 제출을 허용할지 결정
        const titleError = document.getElementById('titleError').style.display === 'block';
        const contentError = document.getElementById('contentError').style.display === 'block';
       
        if(titleError || contentError) { // 제목과 길이 제한을 초과한 채로 제출한다면
            alert('제목과 내용의 길이 제한을 다시 확인해주세요!'); // 경고창 띄우기
            return false; // 폼 제출 방지
        }
        return true; // 에러가 없으면 폼 제출
    }

    function cancelEditing() {
        // 취소버튼을 누르면 메인페이지 C로 이동
        window.location.href = 'MAINPAGEACTION.do';
    }

    // 페이지 로드 시 초기 바이트 수 업데이트
    window.onload = updateByteCount;
    </script>

   <!-- Core JS Files -->
   <script src="assets/js/core/jquery-3.7.1.min.js"></script>
   <script src="assets/js/core/popper.min.js"></script>
   <script src="assets/js/core/bootstrap.min.js"></script>

</body>
</html>