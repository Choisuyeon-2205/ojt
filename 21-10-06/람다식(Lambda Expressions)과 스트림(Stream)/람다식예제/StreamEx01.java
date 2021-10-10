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
