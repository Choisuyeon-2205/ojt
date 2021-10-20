<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  한글 인코딩 -->
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ELJstl_view 페이지</title>
</head>
<body>
 <%-- [문제] 먼저 스크립트릿(<% %>)과 표현식(<%= %>), request 내장객체, out객체를 적용하여 코딩한다. 
 폼에서 넘어온 이름과 선택한 과목들을 크롬 브라우저에 출력   --%>
 이름은 <%=request.getParameter("name") %> 입니다. <br><br>
 
 선택한 과목은<br>
 <% 
 	String[] langs= request.getParameterValues("lang");
 	for(String lang: langs){
 		out.println(lang+"<br>");
 	}
 %>
</body>
</html>