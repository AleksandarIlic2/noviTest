package unisexBathroomSemaphore;

public class Woman extends Thread{
	
	UnisexBathroom b;
	public Woman(UnisexBathroom b) {
		this.b = b;
	}
	
	private void useToilet() {
		try {
			Thread.sleep(2 + (int)Math.random() + 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true)
		{
			b.entry.acquireUninterruptibly();
			b.mutexW.acquireUninterruptibly();
			b.cntW++;
			
			if (b.cntW == 1) b.toiletUse.acquireUninterruptibly();
			b.mutexW.release();
			b.entry.release();
			b.ticket.acquireUninterruptibly();
			System.out.println("Usla je zena");
			useToilet();
			b.ticket.release();
			System.out.println("Izasla je zena");
			b.mutexW.acquireUninterruptibly();
			b.cntW--;		

			if (b.cntW == 0)
			{
				b.toiletUse.release();
			}
			b.mutexW.release();
			
		}
	}

}
