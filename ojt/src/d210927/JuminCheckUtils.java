package d210927;

public class JuminCheckUtils {
	
	/*
	 * 입력받은 주민번호에 체크공식 적용
	 */
	public static boolean checkNum(String s) {
		int sum= 0; //누계 변수는 0으로 초기화
		int[] weight= {2,3,4,5,6,7,0,8,9,2,3,4,5}; //가중치 배열 초기화
		int temp, result;
		
		//1단계=> sum 구하기
		for(int i=0;i<s.length()-1;i++) {
			if(s.charAt(i)=='-') continue;
			int gop=(s.charAt(i)-'0')* weight[i]; //0의 ASCII코드 값(48)을 빼줌
			sum+= gop;
		}
		
		//2단계 공식=> temp값이 두 자리인 주민번호를 가진 경우가 있다. 10 or 11
		temp= 11- (sum%11);
		
		//3단계 공식=> temp값이 두 자리인 주민번호인 경우를 대비해서 ... 한번 더!
		result= temp%10;
				
		//비밀번호 정상 유무 체크=> return
		return result==(s.charAt(s.length()-1)-'0');
	}
}