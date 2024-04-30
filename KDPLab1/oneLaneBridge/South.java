package oneLaneBridge;

public class South extends Car implements Runnable{

	public South(Bridge bridge) {
		super(bridge);
		
		
	}

	@Override
	public void run() {
		while(true)
		{
			synchronized (bridge) {
				while (bridge.northCnt > 0 || bridge.crossS > 5)
				{
					bridge.waitingS++;
					try {
						bridge.wait();
						bridge.waitingS--;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
			
				if (bridge.waitingS > 0) bridge.notify();
				if (bridge.waitingN > 0) bridge.crossS += 1;
				System.out.println("Automobil " + this.id + "S je na mostu");
				bridge.southCnt++;
			}
			crossing();
			synchronized (bridge) {
				bridge.southCnt--;
				System.out.println("Automobil " + this.id + "S vise nije na mostu");
				if (bridge.southCnt == 0)
				{
					bridge.crossN = 0;
					bridge.notify();
				}
					
			}
		}
		
	}

}
