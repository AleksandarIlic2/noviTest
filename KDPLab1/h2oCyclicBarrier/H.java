package h2oCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;

public class H extends Thread {
	private MyBarrier b;
	
	public H(MyBarrier b) {
		this.b = b;
	}
	
	@Override
	public void run() {
		try {
			b.hBarrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	
}
