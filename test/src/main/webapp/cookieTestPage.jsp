<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie Test</title>
</head>
<body>
<%
// 쿠키 읽기
Cookie[] cookies = request.getCookies();
String lastCategory = null;

if (cookies != null) {
    for (Cookie cookie : cookies) {
        if ("lastCategory".equals(cookie.getName())) {
            lastCategory = cookie.getValue();
            out.print(lastCategory);
            break;
        }
    }
}

// 카테고리에 따른 광고 표시
if (lastCategory != null) {
    out.print(lastCategory);
    if ("Electronics".equals(lastCategory)) {
%>
        <h3>광고: 최신 전자 제품 할인!</h3>
        <img src="profile_img\KakaoTalk_20240815_121109553.png" alt="전자 제품 광고">
<%
    } else if ("Books".equals(lastCategory)) {
%>
        <h3>광고: 베스트셀러 도서 할인!</h3>
        <img src="books_ad.jpg" alt="도서 광고">
<%
    }
} else {
%>
    <h3>광고: 다양한 할인 혜택을 확인하세요!</h3>
    <img src="default_ad.jpg" alt="기본 광고">
<%
}
%>

</body>
</html>