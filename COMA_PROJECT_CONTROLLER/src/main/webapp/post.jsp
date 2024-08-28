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
			<div class="row py-3">
				<div class="col-12">
					<h1 class="text-center">${BOARD.board_title}</h1>
				</div>
			</div>
			<div class="row border-bottom border-dark pb-3">
				<div class="col-md-1">
					<div class="avatar avatar-sm">
						<img src="assets/img/profile.jpg" alt="profile"
							class="avatar-img rounded-circle" />
					</div>
				</div>
				<div class="col-md-11">
					<p>작성자: ${BOARD.member_name}</p>
				</div>
			</div>
			<div class="row py-5">
				<div class="col-12 d-flex justify-content-center">
					<div class="w-75">
						<p class="text-start">${BOARD.board_content}</p>
					</div>
				</div>
			</div>
			<div class="row border-top border-dark py-3">
				<form action="REPLYACTION.do" method="POST">
					<div class="row">
						<div class="col-11">
							<div class="form-group">
								<input name="BOARD_CONTENT" type="text" class="form-control"
									id="comment" placeholder="댓글를 입력해주세요" />
							</div>
						</div>
						<div class="col-1 d-flex align-items-center">
							<button type="submit" class="btn btn-secondary">댓글</button>
						</div>
					</div>
				</form>
			</div>
			<c:if test="${REPLY_ID >= 0}">
				<div class="row border-top border-bottom py-3 px-5 comment-item">
					<div class="col-md-2">
						<p>작성자: ${REPLY.reply_writer}</p>
					</div>
					<div class="col-md-9">
						<form action="REPLYUPDATEACTION.do" method="POST">
							<input type="hidden" name="board_id" value="${BOARD.board_num}" />
							<input type="hidden" name="reply_id" value="${REPLY.reply_id}" />
							<p class="comment-text">${REPLY.reply_content}</p>
							<div class="edit-form d-none">
								<div class="row">
									<div class="col-8">
										<input type="text" class="form-control comment-edit" />
									</div>
									<div class="col-4">
										<a href="REPLYUPDATE.do">
											<button type="submit" class="btn btn-primary save-edit">변경완료</button>
										</a>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="col-1">
						<c:if test="${MEMBER_ID eq REPLY_ID}">
							<div class="dropdown">
								<button class="btn btn-icon btn-clean me-0" type="button"
									id="dropdownMenuButton" data-bs-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">
									<i class="fas fa-ellipsis-h"></i>
								</button>
								<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
									<a class="dropdown-item edit-comment">수정</a>
									<a class="dropdown-item" href="REPLYDELETEACTION.do?replyId=${REPLY.reply_id}&board_num=${BOARD.board_num}">삭제</a>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</c:if>
		</div>
	</div>

	<!--   Core JS Files   -->
	<script src="assets/js/core/jquery-3.7.1.min.js"></script>
	<script src="assets/js/core/popper.min.js"></script>
	<script src="assets/js/core/bootstrap.min.js"></script>
	
	<script>
	// DOMContentLoaded 이벤트가 발생하면 콜백 함수를 실행
	// 즉, DOM이 완전히 로드된 후에 이 코드가 실행
	document.addEventListener('DOMContentLoaded', () => {

	    // .comment-item 클래스를 가진 모든 요소를 선택하여 comments라는 변수에 저장
	    const comments = document.querySelectorAll('.comment-item');

	    // 각 댓글(comment-item)에 대해 반복 작업을 수행합니다.
	    comments.forEach(comment => {

	        // 현재 댓글의 편집 버튼(.edit-comment)을 선택하여 editButton 변수에 저장
	        const editButton = comment.querySelector('.edit-comment');

	        // 현재 댓글의 편집 폼(.edit-form)을 선택하여 editForm 변수에 저장합니다.
	        const editForm = comment.querySelector('.edit-form');

	        // 현재 댓글의 텍스트 영역(.comment-text)을 선택하여 commentText 변수에 저장
	        const commentText = comment.querySelector('.comment-text');

	        // 현재 댓글의 편집 입력란(.comment-edit)을 선택하여 commentEdit 변수에 저장
	        const commentEdit = comment.querySelector('.comment-edit');

	        // 현재 댓글의 저장 버튼(.save-edit)을 선택하여 saveEditButton 변수에 저장
	        const saveEditButton = comment.querySelector('.save-edit');

	        // editButton 클릭 시 실행될 이벤트 리스너를 추가
	        editButton.addEventListener('click', (event) => {
	            // 기본 클릭 동작을 막습니다
	            event.preventDefault();

	            // commentText 요소에 'd-none' 클래스를 추가하여 댓글 텍스트를 숨기기
	            commentText.classList.add('d-none');

	            // editForm 요소에서 'd-none' 클래스를 제거하여 편집 폼을 표시
	            editForm.classList.remove('d-none');

	            // commentEdit 입력란에 현재 댓글의 텍스트 내용을 설정
	            commentEdit.value = commentText.textContent.trim();
	        });

	        // saveEditButton 클릭 시 실행될 이벤트 리스너를 추가
	        saveEditButton.addEventListener('click', () => {
	            // commentEdit 입력란의 값을 commentText 요소에 설정하여 변경된 댓글을 표시
	            commentText.textContent = commentEdit.value;

	            // editForm 요소에 'd-none' 클래스를 추가하여 편집 폼을 숨기기
	            editForm.classList.add('d-none');

	            // commentText 요소에서 'd-none' 클래스를 제거하여 댓글 텍스트를 다시 표시
	            commentText.classList.remove('d-none');
	        });
	    });
	});
	</script>
</body>
</html>