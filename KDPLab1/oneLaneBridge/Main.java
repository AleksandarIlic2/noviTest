package oneLaneBridge;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		/*int N = 5;
		PhilosopherMonitor pMonitor = new PhilosopherMonitor(N);
		Philosopher[] filozofi = new Philosopher[N];
		
		for (int i = 0; i < N; i++)
		{
			filozofi[i] = new Philosopher(pMonitor);
			filozofi[i].start();
		}*/
		
		int N = 5;
		Bridge bridge = new Bridge();
		
		Thread[] vozilaJug = new Thread[N];
		Thread[] vozilaSever = new Thread[N];
	
		for (int i = 0; i < N; i++)
		{
			vozilaJug[i] = new Thread(new South(bridge));
			vozilaJug[i].start();
			vozilaSever[i] = new Thread(new North(bridge));
			vozilaSever[i].start();
		}
		
		
	}

}
