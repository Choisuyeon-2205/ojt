import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * 회원(Member) 객체에서 '남자' 회원의 평균 나이를 람다식과 스트림을 적용하여 프로그램 작성
 */
public class StreamPipelinesEx {

	public static void main(String[] args) {
		List<Member> list= (List)Arrays.asList(new Member("박태호", Member.MALE, 30),
											   new Member("김연경", Member.FEMALE, 29), 
											   new Member("손유일", Member.MALE, 32),
											   new Member("안재흥", Member.MALE, 27));				
		/*
		
		//[1 방법] 외부 반복자를 적용하여 해결
		//index를 이용하는 for문 그리고 Iterator를 이용하는 while문은 모두
		//외부 반복자를 이용한다.
		double ageAvg=0;
		double sum=0;
		int count=0;
		for(Member m: list) {
			if(m.getGender()==Member.MALE) {
				sum+=m.getAge();
				count++;
			}
		}
		ageAvg= sum/count;
		System.out.println("남자 회원의 평균 나이: "+ageAvg); //29.6
		
		*/
		
		//[2 방법] 내부 반복자를 적용하여 해결
		double ageAvg= list.stream()
				.filter(m-> m.getGender()==Member.MALE) //중간 처리 스트림
				.mapToInt(Member::getAge) //중간 처리 스트림
				.average()  //최종 처리 스트림
				.getAsDouble();
		System.out.println("남자 회원의 평균 나이: "+Math.round(ageAvg)); //29.6
		
	}

}
