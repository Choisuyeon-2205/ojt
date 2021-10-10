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
