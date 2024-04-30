package h2oCyclicBarrier;

public class Main {
	
	
	public static void main(String[] args) {
		MyBarrier mb = new MyBarrier();
		int N = 50;
		Thread[] O = new Thread[N];
		Thread[] H = new Thread[N];
		
		for (int i = 0; i < N; i++)
		{
			O[i] = new O(mb);
			H[i] = new H(mb);			
		}
		for (int i = 0; i < N; i++)
		{
			O[i].start();
			H[i].start();;			
		}
	}

}
