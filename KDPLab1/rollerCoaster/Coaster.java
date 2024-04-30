package rollerCoaster;

import java.util.concurrent.Semaphore;

public class Coaster extends Thread {
	
	Semaphore allIn, allOut, mutex, car, lastStop;
	int K;


	public Coaster(Semaphore allIn, Semaphore allOut, Semaphore mutex, Semaphore car, Semaphore lastStop, RollerCoaster rc) {
		this.allIn = allIn;
		this.allOut = allOut;
		this.mutex = mutex;
		this.car = car;	
		this.K = rc.K;
		this.lastStop = lastStop;
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
		lastStop.release(K);
		allOut.acquireUninterruptibly();
		System.out.println("Svi su napustili vozilo");
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
		car.release(K);
		allIn.acquireUninterruptibly();
		System.out.println("Svi su usli u vozilo");
	}
	

}
