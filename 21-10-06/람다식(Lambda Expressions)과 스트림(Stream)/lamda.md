## 람다식(Lambda Expressions)
현대적 프로그래밍 기법= 객체지향 프로그래밍 + 함수적 프로그래밍  
⇒ 자바 8버전부터 함수적 프로그래밍 지원  
  
자바에서 람다식을 수용한 이유  
- 코드가 매우 간결해진다.  
- 컬렉션 요소(대용량 데이터)를 필터링 또는 매핑해서 쉽게 집계 처리할 수 있다.  

  
### 람다식 실습1  
  
프로젝트 명: 1006_람다식_스트림_실습  
패키지 명: 람다식예제  
클래스 명: StreamEx01 ⇒ main() 포함 O  
  
```java
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StreamEx01 {

	public static void main(String[] args) {
		/*
		 * 자바7 이전까지는 List<Integer> 컬렉션에서 요소를 순차적으로
		 * 처리하기 위해 Iterator 반복자를 사용해왔다.
		 */

		List<Integer> list= (List)Arrays.asList(10,20,30,40,50);
		Iterator<Integer> iterator = list.iterator();
		
		while(iterator.hasNext()) {
			Integer value= iterator.next();
			System.out.print(value+" "); //10 20 30 40 50 
		}
	}

}
```
  
  
```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamEx01 {

	public static void main(String[] args) {
		/*
		 * 자바 8에 람다식과 스트림(내부 반복자)를 적용하여 코딩
		 * [결론] Stream 내부 반복자는 컬렉션 내부에서 요소들을 반복
		 * 개발자는 요소당 처리해야 할 코드만 제공하는 코드 패턴을 말한다.
		 */
		List<Integer> list= (List)Arrays.asList(10,20,30,40,50);
		Stream<Integer> stream= list.stream();
		
		stream.forEach(value->{
			System.out.print(value+" ");
		});
	}

}
```
  
  
**<표준 API의 함수적 인터페이스>**
1. Consumer 소비자 ⇒ 매개 값은 있고, 리턴 값은 없다.  
2. Supplier 공급자 ⇒ 매개 값은 없고, 리턴 값은 있다.  
3. Function ⇒ 매개 값은 있고, 리턴 값도 있다.  
4. Operator ⇒ 매개 값은 있고, 리턴 값도 있다.  
5. Predicate 조사 ⇒ 매개 값은 있고, 리턴 타입이 Boolean  
  
  
---------------------------------------------------------------------------------------------------------------------------------------------

   
   
## 스트림(Stream) 
⇒ **속도가 빨라짐**, 병렬처리 가능해짐  
자바 8부터 추가된 컬렉션(배열 포함)의 저장 요소를 하나씩 참조해서 람다식(함수적 스타일)으로 처리할 수 있도록 해주는 반복자  
  
  
**스트림의 특징**  
1. 람다식으로 요소 처리 코드를 제공한다.  
2. 내부 반복자를 사용하므로 병렬처리가 쉽다.  
⇒  이점: 컬렉션 내부에서 어떻게 요소(즉, 객체) 를 반복 시킬 것인가는 컬렉션에게 맡겨두고, 개발자는 요소처리 코드에만 집중 할 수 있다.  
3. 스트림은 '중간 처리'와 '최종 처리'를 할 수 있다.


**스트림을 얻어오는 방법**
1. 컬렉션으로부터 스트림 얻기 ⇒ List  컬렉션의 참조변수를 통해  
ex) Stream<Integer> stream= list.stream();  

2. 배열로부터 스트림 얻기  
ex) Arrays.stream(String[ ] 참조변수);   
  
3. 숫자 범위로 부터 스트림 얻기  
IntStream.range(1,100);  
  
4. Random 클래스 객체의 ints() 메소드로부터 스트림 얻기  
IntStream intStream = new Random().ints(1,46);  
  
5. 파일로부터 스트림 얻기  
Files 또는 BufferedReader 클래스의 lines() 메소드를 통해서 스트림 얻어옴  
  
  
<스트림 메소드(매개변수)의 람다식 표현>  
1. filter(Predicate 람다식 표현) 메소드  
2. anyMatch(Predicate 람다식 표현) 메소드  
3. mapToInt(ToIntFucntion mapper) 메소드  
ex) mapToInt(클래스이름::getter메소드이름)  
4. forEach(Consumer 람다식 표현) 메소드  

<스트림 '중간처리' 메소드>   
스트림 필터링: filter(), distinct()  
스트림 변환(매핑): map(), mapToInt()  
스트림 제한: limit(), skip()  
스트림 정렬: sorted()  
스트림 연산, 결과 확인(루핑): peek()  
    
  
<스트림 '최종처리' 메소드>  
요소의 출력: forEach()  
요소의 검사(매칭): anyMatch(), allMatch()  
요소의 검색: findAny(), findFirst()  
요소의 통계(집계): count(), min(), max()  
요소의 연산(집계): sum(), average()  
요소의 수집: collect()  
요소의 소모: reduce()  
  
  
### 람다식 실습2  
  
패키지 명: 요소처리를위한람다식예제  
클래스 명: Customer ⇒ main() 포함 X  
          LambdaEx ⇒ main() 포함 O  
  
```java
public class Customer {
	//멤버변수
	private String name; //이름
	private int point_score; //포인트 점수
	
	//생성자
	public Customer(String name, int point_score) {
		this.name= name;
		this.point_score= point_score;
	}

	public String getName() {
		return name;
	}

	public int getPoint_score() {
		return point_score;
	}
	
	
}
```
  
  
  
```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LambdaEx {

	public static void main(String[] args) {
		List<Customer> list= (List)Arrays.asList(new Customer("민윤기",1500), new Customer("박지민", 700)); 

		/*
		 * [중요] 컬렉션으로부터 스트림 얻기 => List 컬렉션의 참조변수를 통해서
		 */
		Stream<Customer> stream= list.stream(); //리스트로부터 스트림 얻기
		//한 줄 일 때 람다식 중괄호 생략 가능!!!
		//stream.forEach(customer->System.out.println(customer.getName()+" "+customer.getPoint_score()));
		stream.forEach(s->{
			String name= s.getName();
			int score= s.getPoint_score();
			
			System.out.println(name+"=>점수 " +score+"점");
		});
	}

}
```
   
![1.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/75e29e11-5c50-4192-a700-c3f7a7d69987/1.png)  
	 
     

### 람다식 실습3  
패키지 명: 스트림응용예제  
클래스 명: Member ⇒ main() 포함 X  
          StreamPipelinesEx ⇒ main() 포함 O  
  
```java
public class Member {
	public static int MALE= 0; //남자
	public static int FEMALE= 1; //여자
	
	private String name; //이름
	private int gender; //성별
	private int age; //나이
	
	//생성자
	public Member(String name, int gender, int age) {
		this.name= name;
		this.gender= gender;
		this.age= age;
	}

	//getter 메소드만 구현
	public String getName() {return name;}
	public int getGender() {return gender;}
	public int getAge() {return age;}
	
}
```
  
    
```java
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
```
  
    

