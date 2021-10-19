<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 추가 -->
<%@page import="java.util.*"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현식 응용 예제</title>
</head>
<body>
	<%	//자바코드 기술
		String[] str = {"자바", "HTML", "CSS", "JavaScript", "React", "NodeJs"};
	
		Random rnd = new Random();
		int i = rnd.nextInt(6); //난수를 0~5까지 발생
	%>
	
	<%= str[i] %>
</body>
</html>