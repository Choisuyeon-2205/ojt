<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  추가 -->
<%@ page errorPage="error_page.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error_test.jsp 페이지</title>
</head>
<body>
	<div align="center">
		<h2>error_test.jsp 페이지</h2>
		<hr>
		<%= 10/0 %> <!-- 어떤 수를 0으로 나누기(논리적 성립x -> error!) -->
	</div>
</body>
</html>