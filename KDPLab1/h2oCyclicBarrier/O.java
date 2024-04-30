package h2oCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;

public class O extends Thread{

	private MyBarrier b;
	
	@Override
	public void run() {
		
			try {
				b.oBarrier.await();
			} catch (InterruptedException e) {
				System.out.println("GRESKA 1");
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				System.out.println("GRESKA 2");
				e.printStackTrace();
			}
			
	}
	
	public O(MyBarrier b) {
		this.b = b;
	}
	
}
