package philosopherMonitor;

public class Main {

	
	
	public static void main(String[] args) {
		int N = 5;
		PhilosopherMonitor pMonitor = new PhilosopherMonitor(N);
		Philosopher[] filozofi = new Philosopher[N];
		
		for (int i = 0; i < N; i++)
		{
			filozofi[i] = new Philosopher(pMonitor);
			filozofi[i].start();
		}
	}
	
	
	
}
