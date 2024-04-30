package consumerProducer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BoundedBufferV3<T> {


	public static final int N = 100;
	
	final BlockingQueue<T> queue = new ArrayBlockingQueue<>(N);
	
	
	public void put(T elem) throws InterruptedException {		
		queue.put(elem);		
	}
	
	public T get() throws InterruptedException {
		return queue.take();		
	}

	
	
}
