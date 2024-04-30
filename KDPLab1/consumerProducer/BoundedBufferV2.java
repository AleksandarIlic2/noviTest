package consumerProducer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBufferV2<T> {

	
	public static final int  N = 100;
	private T[] buffer = (T[]) new Object[N];
	private int ri, wi, count;
	
	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	
	public void put(T elem) throws InterruptedException {
		lock.lock();
		try {
			while(count == N)
				notFull.await();
			buffer[wi] = elem;
			wi = (wi + 1) % N;
			count--;
			notEmpty.signal();
		}
		finally {
			lock.unlock();
		}
		
		
	}
	
}
