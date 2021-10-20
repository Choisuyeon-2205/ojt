<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProductList.jsp 페이지</title>
</head>
<body>
	<h2>제품 목록 보기</h2>
	<hr>
	<form name="myForm" method="POST" action="ProductSelect.jsp">
		<jsp:useBean id="pro" scope="page" class="Product.ProductTest" />
		<%--  주석=> 웹사이트에서 안보임 
		<select name="sel">
			<%
				for(String item: pro.getProductList()){
					out.println("<option>"+item+"</option>");
				}
			%>
		</select>
		--%>
		
		<%-- 위 소스를 표현언어와 JSTL을 적용하여 코딩 --%>
		<select name="sel">
			<c:forEach var="item" items="${pro.getProductList()}">
				<option>${item}</option>
			</c:forEach>
		</select>
		
		<input type="submit" value="제품 선택"/>
	</form>
</body>
</html>