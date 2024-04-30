package rollerCoaster;

import java.util.concurrent.Semaphore;

public class Main {

	
	
	public static void main(String[] args) {
		Semaphore allIn = new Semaphore(0);
		Semaphore allOut = new Semaphore(0);
		Semaphore mutex = new Semaphore(1);
		Semaphore lastStop = new Semaphore(0);
		Semaphore car = new Semaphore(0);
		
		RollerCoaster rc = new RollerCoaster(allIn,allOut,mutex, car, 5);
		Coaster c = new Coaster(allIn, allOut, mutex, car, lastStop, rc);
		Passenger[] putnici = new Passenger[20];
		
		c.start();
		for (int i = 0; i < 20; i++)
		{
			putnici[i] = new Passenger(allIn, allOut, mutex, car, lastStop, rc);
			putnici[i].start();
		}
	}
	
}
