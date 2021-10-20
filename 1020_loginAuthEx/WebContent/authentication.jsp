<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 추가 -->
<%
	//자바 코드 기술
	//인증 가능 사용자 및 패스워드 목록 생성
	String[] users = {"suyeon7807","jimin1013","suga0309"};
	String[] password= {"s789","j345","s901"};
	
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	//인증 처리
	int i;
	for(i=0;i<users.length;i++){
		if(users[i].equals(id)&&password[i].equals(pw)){
			//세션 값 등록
			session.setAttribute("signedUser", id);
			session.setAttribute("signedId", session.getId());
			response.sendRedirect("welcome.jsp");
		}
	}
	
	if(i==users.length){
		out.println("<script>alert('아이디가 일치하지 않습니다.');history.go(-1);</script>");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증 페이지</title>
</head>
<body>

</body>
</html>