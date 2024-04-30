package oneLaneBridge;

public class Car {

	Bridge bridge;
	int id;
	static int idS = 0;
	public Car(Bridge bridge)
	{
		this.bridge = bridge;
		this.id = idS++;
	}
	
	
	public void crossing() {
		try {
			Thread.sleep(2000 + (int)Math.random() * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void starting() {
		try {
			Thread.sleep(2000 + (int)Math.random() * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
