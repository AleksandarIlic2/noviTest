package unisexBathroomSemaphore;

import java.util.concurrent.Semaphore;

public class UnisexBathroom {

	Semaphore mutexW, mutexM, toiletUse, ticket, entry;
	
	int cntM, cntW;
	public UnisexBathroom() {
		super();
		this.mutexM = new Semaphore(1);
		this.mutexW = new Semaphore(1);
		this.toiletUse = new Semaphore(1);
		this.ticket = new Semaphore(5);
		this.entry = new Semaphore(1);
		this.cntM = 0;
		this.cntW = 0;
	}
	
	
	
	
	
	
	
	
}
