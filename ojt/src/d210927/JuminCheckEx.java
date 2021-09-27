package d210927;

import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class JuminCheckEx {

	public static void main(String[] args) {
		//준비 단계: 필요 변수, 배열의 초기화
		System.out.print("주민번호 입력('-'포함)>>> ");
		
		Scanner sc= new Scanner(System.in);
		String juminNum= sc.next(); //문자열로 입력 받음=> '-'포함하여 입력
		
		/*
		 * 입력 받은 주민번호를 유효성 검사 => 정규 표현식(regex) 패턴 적용
		 */
		String regex= "^[0-9]{6}-[1234][0-9]{6}$"; //주민 번호 정규표현식
		//[첫번째 방법]
		//boolean check= Pattern.matches(regex, juminNum);
		
		//[두번째 방법]
		boolean check= juminNum.matches(regex);
		
		if(check) {
			/*
			 * 입력받은 주민 번호에 체크 공식 적용
			 */
			boolean result= JuminCheckUtils.checkNum(juminNum);
			
			if(result) {
				System.out.println("입력된 주민번호가 정상입니다.");
				//나이, 성별, 출신도, 생년월일 추출하기
				//1. "나이" 추출
				int isBefore= juminNum.charAt(7)-'0';
				String year="";
				if(isBefore==1||isBefore==2) { //isBefore<3
					//00전
					year+= "19";
				}else {
					//00이후
					year+= "20";
				}
				year+=juminNum.substring(0, 2); //0 index부터 2-0=2자리 추출
				int age= Calendar.getInstance(Locale.KOREA).get(Calendar.YEAR)- Integer.parseInt(year)+1;
				System.out.println("나이: "+age);
				
				//2. "성별" 추출
				boolean isFemale=false;
				if(isBefore==2||isBefore==4) { //isBefore%2==0
					isFemale= true;
				}
				System.out.println("성별: "+(isFemale?"여":"남"));
				
				//3. "출신지역" 추출
				int area= Integer.parseInt(juminNum.substring(8, 10)); //0 index부터 2-0=2자리 추출
				String area_name= AreaCheckUtils.checkArea(area);
				System.out.println("지역: "+area_name);
				
				//4. "생년월일" 추출
				System.out.println("생년월일: "+Integer.parseInt(year));
				
			}else {
				System.out.println("입력된 주민번호가 올바르지 않습니다.");
			}
		}else {
			System.out.println("입력된 주민번호는 정규 표현식의 형식에 맞지 않습니다.");
			return;
		}
		

	}

}