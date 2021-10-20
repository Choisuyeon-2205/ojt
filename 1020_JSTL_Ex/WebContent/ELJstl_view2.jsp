<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ELJstl_view2 페이지</title>
</head>
<body>
 <%-- [문제] JSTL, EL을 적용하여 코딩한다. 
 폼에서 넘어온 이름과 선택한 과목들을 크롬 브라우저에 출력   --%>
이름은 ${param.name} 입니다. <br><br>

선택한 과목은<br>
<c:forEach var="item" items="${paramValues.lang}">
	<c:out value="${item}" />
</c:forEach>
</body>
</html>