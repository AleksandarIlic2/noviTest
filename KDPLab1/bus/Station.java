package bus;

public class Station {

	private String name;
	private int id;
	private int numP, numFP;
	private boolean busIN;
	
	private Bus bus;
	
	
	
	public Station(String name) {
		this.name = name;
		bus = null;
		busIN = false;
		
	}
	
	public synchronized Bus waitBus() {
		while(true)
		{
			while(busIN) {
				try {
					wait();
				}
				catch(InterruptedException e) {}
			}
			numP++;
			while(busIN == false)
			{
				try {
					wait();
				}
				catch(InterruptedException e) {}
			}
			numP--;
			if (numP == 0) notifyAll();
			if(numFP > 0) {
				numFP--;
				return bus;
			}
		}
	}
	
}
