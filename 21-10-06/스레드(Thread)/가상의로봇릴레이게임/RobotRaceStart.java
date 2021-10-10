class RobotRace extends Thread{
	//생성자
	public RobotRace(String name) {
		super(name);
	}

	@Override
	public void run() {
		//가상 로봇 100m 릴레이 => 10m씩 전진
		for(int i=0;i<11;i++) {
			System.out.println(getName()+"가"+i*10+"m 전진");
			try {
				sleep(1000); //1초 일시정지
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			}
		}
	}

}

public class RobotRaceStart {

	public static void main(String[] args) {
		RobotRace robotA= new RobotRace("로봇A");
		RobotRace robotB= new RobotRace("로봇B");
		RobotRace robotC= new RobotRace("로봇C");
		
		robotA.start();
		robotB.start();
		robotC.start();

	}

}
