package rollerCoaster;

import java.util.concurrent.Semaphore;

public class Passenger extends Thread {
	
	Semaphore allIn, allOut, mutex, car, lastStop;
	int K;	
	RollerCoaster rc;
	
	public Passenger(Semaphore allIn, Semaphore allOut, Semaphore mutex, Semaphore car, Semaphore lastStop, RollerCoaster rc) {
		this.allIn = allIn;
		this.allOut = allOut;
		this.mutex = mutex;
		this.car = car;	
		this.K = rc.K;	
		this.lastStop = lastStop;
		this.rc = rc;
	}
	
	@Override
	public void run() {
		while(true)
		{
			boardCar();
			ride();
			leaveCar();
		}
	}
	
	private void leaveCar() {
		lastStop.acquireUninterruptibly();
		mutex.acquireUninterruptibly();
		rc.cnt--;
		System.out.println("Putnik je izasao iz vozila");
		if (rc.cnt == 0)
		{
			allOut.release();
		}
		mutex.release();
	}

	private void ride() {
		try {
			Thread.sleep((int)Math.random() * 2500 + 100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void boardCar() {
		car.acquireUninterruptibly();
		mutex.acquireUninterruptibly();
		System.out.println("Putnik je usao u vozilo");
		rc.cnt++;
		if (rc.cnt == K)
		{
			allIn.release();
		}
		mutex.release();
	}

}
