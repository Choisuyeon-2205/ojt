<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팝업창 만들기 예제</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<style>
	.modal {
		display: none;
		position: fixed;
		width: 100%;
		height: 100%;
	}
	.modal_content {
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translate(-50%,-50%);
		width: 300px;
		height: 300px;
		border: 10px solid white;
		background-color: LightSkyBlue;
		overflow: auto;
	}
	.btn_close {
		text-decoration: none;
		color: black;
		display: inline-block;
		width: 90px;
		text-align: center;
		background-color: coral;
	}
</style>
<script>
	function fade_in() {
		$(".modal").fadeIn();
	}
	function fade_out() {
		$(".modal").fadeOut();
	}
</script>
</head>
<body onload="fade_in()">
    <h1 align="center">우리은행</h1>
    <div class="modal">
    	<p class="modal_content">
    		공모주 청약 준비<br>
    		<b>증권거래 가능 통장으로</b><br><br>
    		업그레이드하면, <br>
    		주식 1주만큼의 축하금 행운이!<br><br><br>
    		<span><b>오늘하루 보지 않기</b>&nbsp;&nbsp;&nbsp;&nbsp;
    		<a href="#" class="btn_close" onclick="fade_out()">닫기</a></span>
    	</p>
    </div>
</body>
</html>