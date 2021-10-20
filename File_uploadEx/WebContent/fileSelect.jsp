<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileSelect 페이지</title>
<script>
	//들어온 파일 사이즈가 20MB보다 크면 경고창 띄우기!
	function fileCheckSize(input){
		if(input.files && input.files[0].size>(20*1024*1024)){
			alert("파일 사이즈가 20MB를 초과했습니다.");
		}
	}
</script>
</head>
<body>
	<form name="fileUploadForm" method="POST" enctype="multipart/form-data"  action="viewPage.jsp">
		작성자<br>
		<input type="text" name="name" /><br>
		
		제목<br>
		<input type="text" name="subject" /><br>
		
		<h4><font color="red">*업로드할 파일은 최대 20MB까지 가능합니다.</font></h4>
		
		업로드할 파일<br>
		<input type="file" name="uploadFile" onchange="fileCheckSize(this)" /> 
		<!-- type="file": 파일 업로드 객체! -->
		
		<input type="submit" value="파일 올리기" />
	</form>
</body>
</html>