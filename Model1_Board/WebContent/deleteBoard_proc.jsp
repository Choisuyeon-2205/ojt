<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
    
<!--  자바 클래스 import -->
<%@ page import = "board.BoardDO" %>
<%@ page import = "board.BoardDAO" %>

<%
	request.setCharacterEncoding("UTF-8");

	String seq = request.getParameter("seq");
	
	BoardDO boardDO = new BoardDO();
	boardDO.setSeq(Integer.parseInt(seq));
	
	BoardDAO boardDAO = new BoardDAO();
	int result = boardDAO.deleteBoard(boardDO);
	
	if(result!=0)
		response.sendRedirect("getBoardList.jsp");
	else
		System.out.println("삭제 실패");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteBoard.jsp => "삭제" 처리 컨트롤러 페이지</title>
</head>
<body>

</body>
</html>