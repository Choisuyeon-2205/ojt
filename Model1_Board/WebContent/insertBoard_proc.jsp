<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
    
<!--  자바 클래스 import -->
<%@ page import = "board.BoardDO" %>
<%@ page import = "board.BoardDAO" %>
    
<%
	request.setCharacterEncoding("UTF-8");

	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	
	BoardDO boardDO = new BoardDO();
	boardDO.setTitle(title);
	boardDO.setWriter(writer);
	boardDO.setContent(content);
	
	BoardDAO boardDAO = new BoardDAO();
	int result = boardDAO.insertBoard(boardDO);
	
	if(result!=0)
		response.sendRedirect("getBoardList.jsp");
	else
		System.out.println("입력 실패");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>