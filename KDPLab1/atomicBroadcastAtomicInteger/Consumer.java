package atomicBroadcastAtomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer extends Thread{
	
	private AtomicInteger[] niz; // ovo je niz za sinhronizaciju za readerima
	private AtomicInteger buffer; // odavde se citaju elementi, jednoelementni je buffer
	private int N;
	private int id;
	private static int idS = 0;
	
	public Consumer(AtomicInteger[] niz, int N, AtomicInteger buffer) {
		this.niz = niz;
		this.N = N;
		this.buffer = buffer;
		this.id = idS++;
		
	}
	
	
	@Override
	public void run() {	
		while(true)
		{
			while(niz[id].get() == 0)
				Thread.onSpinWait();
			
			int item = buffer.get();
			niz[id].set(1);
			
			
		}
		
	}

}
