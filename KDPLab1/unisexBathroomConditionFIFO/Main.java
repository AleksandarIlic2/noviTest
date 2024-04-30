package unisexBathroomConditionFIFO;



public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BathroomMonitor ubrl = new BathroomMonitor();

		int M = 50, W = 50;
		Thread[] men = new Thread[M];
		for (int i = 0; i < M; i++)
			men[i] = new Man(ubrl);
		Thread[] women = new Thread[W];
		for (int i = 0; i < W; i++)
			women[i] = new Woman(ubrl);

		for (int i = 0; i < M; i += 2)
			men[i].start();
		for (int i = 0; i < W; i += 2)
			women[i].start();
		for (int i = 1; i < M; i += 2)
			men[i].start();
		for (int i = 1; i < W; i += 2)
			women[i].start();

	}

	
}
