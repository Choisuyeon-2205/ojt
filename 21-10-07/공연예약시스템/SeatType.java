import java.util.Scanner;

public class SeatType {
	private char type;
	private Seat[] aSeat; //연관관계 -> 멤버 변수로 참조하겟다.
	
	//생성자
	public SeatType(char type, int num) { //좌석 유형, 좌석 수
		this.type= type;
		aSeat= new Seat[num]; //배열객체 생성
		
		for(int i=0;i<aSeat.length;i++) {
			aSeat[i]= new Seat();
			
		}
	}
	
	//사용자 정의 메소드 구현
	public boolean seatReserve() {
		Scanner in= new Scanner(System.in);
		int seatNum;
		String name;
		show();
		System.out.print("이름: ");
		name= in.next();
		System.out.print("좌석번호: ");
		seatNum= in.nextInt();
		
		if(seatNum<1 || seatNum > aSeat.length) {
			System.out.println("잘못된 좌석 번호입니다.");
			return false;
		}
		if(aSeat[seatNum-1].isOccupied()) {
			System.out.println("이미 예약된 좌석입니다.");
			return false;
		}
		aSeat[seatNum-1].reserveName(name);
		return true;
	}
	
	public boolean cancel() {
		Scanner in= new Scanner(System.in);
		show();
		System.out.println("이름: ");
		String name= in.next(); //취소할 예약자 이름 입력
		
		if(name!=null) {
			for(int i=0;i<aSeat.length;i++) {
				if(aSeat[i].match(name)) {
					aSeat[i].cancel();
					return true;
				}
			}
			System.out.println("취소할 예약자 이름을 찾을 수 없습니다.");
		}
		return false;
		
	}
	
	public void show() {
		System.out.println(type+"석 예약: ");
		
		for(int i=0;i<aSeat.length;i++) {
			if(aSeat[i].isOccupied())
				System.out.print(aSeat[i].getName());
			else
				System.out.print("---");
			System.out.print(" ");
		}
		System.out.println();
	}
}
