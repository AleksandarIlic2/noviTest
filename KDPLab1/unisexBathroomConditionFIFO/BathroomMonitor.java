package unisexBathroomConditionFIFO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BathroomMonitor {
//FIFO, svako ima svoj conditio

	Lock lock = new ReentrantLock();
	public static int id = 0;
	
	
	public BathroomMonitor() {
		// TODO Auto-generated constructor stub
		id = 0;
	}
	private class Help {
		Condition cond;
		Character ch;

		public Help(Condition cond, Character ch) {
			// TODO Auto-generated constructor stub
			this.ch = ch;
			this.cond = cond;

		}
	}

	int men = 0;
	int women = 0;
	int capacity = 10;

	List<Help> list = new ArrayList<>();

	public void mEnter() {

		lock.lock();
		try {

			while (women > 0 || men == capacity) {
				Condition myCond = lock.newCondition();
				list.add(new Help(myCond, 'M'));
				myCond.awaitUninterruptibly();
			}
			System.out.println("Muskarac je usao u wc!");
			men++;
		} finally {
			lock.unlock();
		}

	}

	public void mExit() {
		lock.lock();
		try {
			men--;
			System.out.println("Muskarac je izasao iz wc!");
			if(men > 0 && !list.isEmpty() && list.get(0).ch == 'M') {
				while(men > 0 && !list.isEmpty() && list.get(0).ch == 'M') {
					Help help = list.remove(0);
					Condition cond = help.cond;
					cond.signal();
				}
			}else if(men ==0 && !list.isEmpty()) {
				char sex = list.get(0).ch;
				while(!list.isEmpty() && sex == list.get(0).ch) {
					Help help = list.remove(0);
					Condition cond = help.cond;
					cond.signal();
				}
			}
		} finally {
			lock.unlock();
		}
	}
	
	public void wEnter() {
		lock.lock();
		try {
			while( men>0 || women== capacity) {
				Condition cond = lock.newCondition();
				Help help = new Help(cond, 'W');
				list.add(help);
				cond.awaitUninterruptibly();
			}
			women++;
			System.out.println("Zena je usla u wc!");
		}finally {
			lock.unlock();
		}
	}
	
	public void wExit() {
		lock.lock();
		try {
			women--;
			System.out.println("Zena je izasla u wc!");
			if(women > 0 && !list.isEmpty() && list.get(0).ch == 'W') {
				while(women > 0 && !list.isEmpty() && list.get(0).ch == 'W'){
					
					Help help = list.remove(0);
					Condition cond = help.cond;
					cond.signal();
					
					
				}
			}else if (women == 0 && !list.isEmpty()) {
				char sex = list.get(0).ch;
				while(!list.isEmpty() && sex == list.get(0).ch) {
					Help help = list.remove(0);
					Condition cond = help.cond;
					cond.signal();
				}
			}
			
		}finally {
			lock.unlock();
		}
	}

}
