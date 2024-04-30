package oneLaneBridge;

public class North extends Car implements Runnable {

	public North(Bridge bridge) {
		super(bridge);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		while(true)
		{
			synchronized (bridge) {
				
				while (bridge.southCnt > 0 || bridge.crossN > 3)
				{					
					bridge.waitingN++;
					try {
						bridge.wait();
						bridge.waitingN--;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}		
				
			
				if (bridge.waitingN > 0) {					
					bridge.notify();
				}
				
				if (bridge.waitingS > 0) {		
					bridge.crossN += 1;
				}				
				System.out.println("Automobil " + this.id + "N je na mostu");
				bridge.northCnt++;
			}
			crossing();
			synchronized (bridge) {
				bridge.northCnt--;
				System.out.println("Automobil " + this.id + "N vise nije na mostu");
				if (bridge.northCnt == 0)
				{
					bridge.crossS = 0;		
					bridge.notify();
				}
					
			}
		}
		
	}
	

}
