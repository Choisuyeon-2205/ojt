<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dynamicMultiForm 페이지</title>
<script>
	var count = 1; //전역 변수
	
	function addRow(){
		//table에 행 하나를 추가
		var dynamicTable = document.getElementById('dynamic_table'); //테이블 객체
		var newRow = dynamicTable.insertRow(); //row 삽입
		var cell1 = newRow.insertCell(); //row-> cell 삽입
		var cell2 = newRow.insertCell();
		
		cell1.innerHTML = '<input type="checkbox" name="checkboxObj"/>';
		cell2.innerHTML = '<input type="file" name="fileupload'+count+'" size="70" onchange="checkSize(this)"/>';
		count ++;
	}
	
	function checkSize(input){
		if(input.files && input.files[0].size>(20*1024*1024)){
			alert('파일 사이즈가 20MB 초과되었습니다.');
			input.value = null;
		}
	}
	
	function deleteRow(){
		var table = document.getElementById('dynamic_table'); //테이블 객체
		var checkboxArray = document.getElementsByName('checkboxObj');
		
		//checkbox 수만큼 돌면서, 체크되어 있는 것만 삭제 처리
		for(var i=checkboxArray.length-1;i>=0;i--){
			var check =  checkboxArray[i].checked;
			
			if(check){
				table.deleteRow(i); //row 삭제
			}
		}
	}
</script>
</head>
<body>
	<h2>동적 다중 파일 업로드 구현하기</h2>
	<form action="register.jsp" method="POST" enctype="multipart/form-data" name="dymamicForm">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td>작성자</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" /></td>
			</tr>
		</table>
		<br>
		<input type="button" value="행추가" onclick="addRow();" />&nbsp;&nbsp;&nbsp; 
		<input type="button" value="행삭제" onclick="deleteRow();" /><br><br>
		Check&nbsp;&nbsp;&nbsp;&nbsp;업로드할 파일 이름
		<br>
		<table id="dynamic_table" border="1" cellpadding="0" cellspacing="0"></table>
		<h4><font color="red">업로드할 파일은 최대 20MBB까지 가능합니다!!</font></h4>
		<input type="submit" value="파일 올리기"/>
		
	</form>
</body>
</html>