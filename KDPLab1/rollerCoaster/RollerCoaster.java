package rollerCoaster;

import java.util.concurrent.Semaphore;

public class RollerCoaster {

	int cnt = 0;
	private Semaphore allIn, allOut, mutex, car;
	int K;
	
	
	public RollerCoaster(Semaphore allIn, Semaphore allOut, Semaphore mutex, Semaphore car, int K) {
		this.allIn = allIn;
		this.allOut = allOut;
		this.mutex = mutex;
		this.car = car;
		this.cnt = 0;
		this.K = K;
	}
	
	
	
	
	
	
	
}
