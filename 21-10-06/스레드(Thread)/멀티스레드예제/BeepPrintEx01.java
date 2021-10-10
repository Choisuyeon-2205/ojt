import java.awt.Toolkit;

/*
 * 싱글 스레드(즉, 메인 스레드) 애플리케이션
 */
public class BeepPrintEx01 {

	public static void main(String[] args) {
		//Toolkit객체 얻어오기
		//Toolkit클래스는 '시스템 정보'를 얻는 것으로 시스템의 자원에 접근할 수 있는 클래스
		Toolkit toolkit= Toolkit.getDefaultToolkit();
		
		for(int i=0;i<5;i++) {
			toolkit.beep(); //5초에 한번씩 beep(경보) 발생
			try {
				Thread.sleep(1000); //1초간 cpu를 잠시 일시 정지
			}catch (Exception e) {
				
			}
		}
		
		
		for(int i=0;i<5;i++) {
			System.out.println("띵"); //5초에 한번씩 띵(경보) 발생=> 5초가 다 지난다음에 출력 => 싱글 스레드
			try {
				Thread.sleep(1000); //1초간 cpu를 잠시 일시 정지
			}catch (Exception e) {
				
			}
		}
		
	}

}
