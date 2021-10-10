public class BeepPrintEx02 {
	
	/*
	 * 메인 스레드
	 */

	public static void main(String[] args) {
		// 작업스레드 만드는 방법
		Runnable beepTask= new BeepPrintTask();
		Thread thread= new Thread(beepTask);
		
		thread.start(); //이 시점에서 작업 스레드와 메인 스레드가 동시에  실행!!

		for(int i=0;i<5;i++) {
			System.out.println("띵2"); //메인스레드에서   띵2를 1초에 한번 실행
			try {
				Thread.sleep(1000); //1초간 cpu를 잠시 일시 정지
			}catch (Exception e) {
				
			}
		}
	}

}
