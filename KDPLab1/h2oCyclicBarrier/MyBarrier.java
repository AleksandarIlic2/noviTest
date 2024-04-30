package h2oCyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyBarrier {
	
	AtomicBoolean hPassed = new AtomicBoolean(false);
	AtomicBoolean oPassed = new AtomicBoolean(false);
	
	CyclicBarrier water = new CyclicBarrier(2, new Runnable() {		
		@Override
		public void run() {
			hPassed.set(false);
			oPassed.set(false);			
		}
	});
	
	CyclicBarrier hBarrier = new CyclicBarrier(2, new Runnable() {		
		@Override
		public void run() {
			//svaki put kada vrati compareAndSet false ja trebam da nastavim da se vrtim u petlji
			// dakle ova petlja obezbedjuje da samo prvi put kad sed aktivira barijera hPassed(stigla 2 H),
			// prodjes while petlju,a svaka sledeca aktivacija (svaka sledeca 2 H) ce samo da se vrte u while petlji
			// dok water ne resetuje hPassed na false, tad ce proci jos 2 vodonika itd
			while(!hPassed.compareAndSet(false, true))
			{
				Thread.yield();
			}
			while (true) // nisam siguran zasto mora ova petlja a ne samo water.await();
			{
				try {
					water.await();
					System.out.print("HH");
					break;
				}
				catch (Exception e) {
					System.out.println("PUKLO"); break;
				}
			}

		}
	});
	
	CyclicBarrier oBarrier = new CyclicBarrier(1, new Runnable() {		
		@Override
		public void run() {
			while (!oPassed.compareAndSet(false, true))
			{
				Thread.yield();
			}
			while(true) {
				try {
					water.await();					
					System.out.print("O");
					break;
				}
				catch (Exception e) {System.out.println("PUKLO");break;
					// TODO: handle exception
				}
			}
		}
	});
	
	

}
