<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/test/Fileupload" method="post"  enctype="multipart/form-data">
    <input type="file" name="image"/>
    <input type="submit" value="Upload Image" />
</form>

${imgPath}
<div>
	<img alt="test" src="${imgPath}">
</div>
</body>
</html>