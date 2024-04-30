package unisexBathroomSemaphore;

public class Man extends Thread{

	UnisexBathroom b;
	public Man(UnisexBathroom b) {
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
			b.mutexM.acquireUninterruptibly();
			b.cntW++;
		
			if (b.cntW == 1) b.toiletUse.acquireUninterruptibly();
			b.mutexM.release();
			b.entry.release();
			b.ticket.acquireUninterruptibly();
			System.out.println("Usao je muskarac");
			useToilet();
			b.ticket.release();
			System.out.println("Izasao je muskarac");
			b.mutexM.acquireUninterruptibly();
			b.cntW--;		
	
			if (b.cntW == 0)
			{
				b.toiletUse.release();
			}
			b.mutexM.release();
			
		}
	}
	
}
