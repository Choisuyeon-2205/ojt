import java.awt.Toolkit;

/*
 * BeepTask 구현 클래스 => '작업스레드' 역할
 */

public class BeepPrintTask implements Runnable {

	@Override
	public void run() { //재정의
		Toolkit toolkit= Toolkit.getDefaultToolkit();
		
		for(int i=0;i<5;i++) {
			System.out.println("띵");
			toolkit.beep(); //5초에 한번씩 beep(경보) 발생
			try {
				Thread.sleep(1000); //1초간 cpu를 잠시 일시 정지
			}catch (Exception e) {
				
			}
		}
		
	}

}
