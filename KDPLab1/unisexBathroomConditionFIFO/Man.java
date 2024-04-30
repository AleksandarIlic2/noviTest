package unisexBathroomConditionFIFO;


public class Man extends Thread {
	private BathroomMonitor bath;
	private int id;
 
	public Man(BathroomMonitor b) {
		// TODO Auto-generated constructor stub
		this.bath = b;
		this.id = bath.id++;
	}
 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		bath.mEnter();
		
		try {
			Thread.sleep((int) (Math.random() * 1000) + 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bath.mExit();
		
	}
}
 
