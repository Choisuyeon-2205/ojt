<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style type="text/css">
	.title { margin: 40px 30px 30px 30px; }
	.container { width: 900px; } 
	input { width: 150px; height: 20px; }
</style>
</head>
<body>
	<form name="memberForm" method="POST" action="Member_login.jsp">
		<div align="center" style="background-color:#fece10; padding:50px;" >
			<h1 class="title">LOGIN</h1>
			<div class="container">
				아이디 <input type="text" name="mem_id" required autofocus/><br><br>
				비밀번호 <input type="password" name="mem_passwd" maxlength="12" minlength="8" required/><br><br>
				<input type="submit" value="로그인" style="background-color: orange; font-size: 15px; border-color: orange; "/>
			</div>
		</div>
	</form>
</body>
</html>