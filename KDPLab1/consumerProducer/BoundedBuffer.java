package consumerProducer;

public class BoundedBuffer<T> {
	
	
	public static final int N = 100;
	private T[] buffer = (T[])new Object[N];
	int ri, wi = 0;
	int count = 0;
	
	
	public synchronized void put(T x) {
		
		while (count == N) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		buffer[wi] = x;
		wi = (wi + 1) % N;
		count++;
		notifyAll();
	}
	
	public synchronized T get() {
		while (count == 0)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		T item = buffer[ri];
		ri = (ri + 1) % N;
		count--;
		notifyAll();		
		return item;
	}
}
