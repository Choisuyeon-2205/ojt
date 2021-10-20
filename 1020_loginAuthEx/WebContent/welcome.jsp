<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 추가 -->
<%	//자바 코드
	//인증된 세션이 없는 경우
	if(session.getAttribute("signedUser")==null){
		response.sendRedirect("logout.jsp");
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%=session.getAttribute("signedUser") %> 님 환영합니다!</h1>
	<h3>현재 세션 ID: <%=session.getId() %></h3>
	<h3>저장했던 세션 ID: <%=session.getAttribute("signedId") %></h3>
	
	</h1><a href="logout.jsp">로그아웃</a>  <!-- 로그아웃누르면 logout.jsp이동 -->

</body>
</html>