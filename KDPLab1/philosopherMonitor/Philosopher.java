package philosopherMonitor;

public class Philosopher extends Thread {
	private PhilosopherMonitor pMonitor;
	private int id;
	private static int idS = 0;
	
	
	public Philosopher(PhilosopherMonitor pMonitor) {
		this.pMonitor = pMonitor;
		id = idS++;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		super.run();
		while(true)
		{
			thinking();
			pMonitor.tryEat(id);
			eating();
			pMonitor.putDown(id);			
		}
	
}
	
	private void thinking() {
		try {
			Thread.sleep((int)Math.random() * 1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void eating() {
		try {
			Thread.sleep((int)Math.random() * 2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
