package atomicBroadcastAtomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends Thread{
	
	private AtomicInteger[] niz; // ovo je niz za sinhronizaciju za readerima
	private AtomicInteger buffer; // odavde se citaju elementi, jednoelementni je buffer
	private int N;
	public Producer(AtomicInteger[] niz, int N, AtomicInteger buffer) {
		this.niz = niz;
		this.N = N;
		this.buffer = buffer;
		
		
	}
	
	@Override
	public void run() {
		while (true)
		{
			for (int i = 0; i < N; i++)
			{
				while (niz[i].get() == 0)
					Thread.onSpinWait();
			}
			int item = produce();
			buffer.set(item); // jednoelementni je buffer
			for (int i = 0; i < N; i++)
			{
				niz[i].set(1);
			}
		}
	
		
		
	
	}
	
	private int produce() {
		int item =(int) (1 + Math.random() * 100);
		try {
			Thread.sleep((int)Math.random() * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

}
