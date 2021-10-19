/**
 * idCheck.js 파일 => 아이디 중복 체크 자바스크립트 파일
 */

function idCheck(id){
	if(id==""){
		alert('아이디를 입력해주세요!');
	}else{
		url = "IdCheck.jsp?mem_id="+id;
		window.open(url,"get","width=350, height=150");
	}
}