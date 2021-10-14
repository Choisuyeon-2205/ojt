/**
 * juminCheck.js 파일
 * 주민번호 체크 자바스크립트 파일
 */

function juminCheck(jumin1, jumin2){
	var juminNum = jumin1+jumin2;
	var jumin_pattern = new RegExp('^[0-9]{6}[1234][0-9]{6}');
	
	if(!jumin_pattern.test(juminNum)){
		alert('주민번호가 정규 표현식에 적합하지 않습니다.');
		return;
	}else{
		alert('주민번호가 정규 표현식 패턴에 적합합니다.');
		
		/*
		 * 주민번호 체크 공식 적용
		 */
		var sum= 0; //누계 변수는 0으로 초기화
	    var weight= [2,3,4,5,6,7,8,9,2,3,4,5]; //가중치 배열 초기화
	    var temp, result;
	    
		//1단계=> sum 구하기
        for(var i=0;i<12;i++) {
            var gop=(juminNum.charAt(i))* weight[i]; //0의 ASCII코드 값(48)을 빼줌
            sum+= gop;
        }
        
      //2단계 공식=> temp값이 두 자리인 주민번호를 가진 경우가 있다. 10 or 11
        temp= 11- (sum%11);

        //3단계 공식=> temp값이 두 자리인 주민번호인 경우를 대비해서 ... 한번 더!
        result= temp%10;
  
        //주민번호 정상 유무 체크=> return
        if(result==(juminNum.charAt(12))){ //주민번호가 정상일 때
            alert('주민번호 체크결과 정상입니다.');
        }else{
        	alert('주민번호가 정확하지 않습니다. 다시 확인하세요.')
        }
	}
	
}