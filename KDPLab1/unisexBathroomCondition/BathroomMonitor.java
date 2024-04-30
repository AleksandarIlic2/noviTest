package unisexBathroomCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BathroomMonitor {

	Lock lock = new ReentrantLock();
	
	Condition condWoman = lock.newCondition();
	Condition condMan = lock.newCondition();
	
	int cntM, cntW = 0;
	int capacity = 10;
	
	public void m_enter() {
		lock.lock();
		try {
			while(cntW > 0 || cntM >= capacity)
			{
				condMan.awaitUninterruptibly();
			}
			cntM++;	
		}
		finally {
			lock.unlock();
		}		
	}
	
	public void m_exit() {
		lock.lock();
		try {
			cntM--;
			if (cntM == 0) condMan.signalAll();
			else condWoman.signal();
		}
		finally {
			lock.unlock();
		}		
	}
	
	public void w_enter() {
		lock.lock();
		try {
			while(cntM > 0 || cntW >= capacity)
			{
				condMan.awaitUninterruptibly();
			}
			cntW++;	
		}
		finally {
			lock.unlock();
		}

		
		
	}
	
	public void w_exit() {
		lock.lock();
		try {
			cntW--;
			if (cntW == 0) condWoman.signalAll();
			else condMan.signal();
		}
		finally {
			lock.unlock();
		}
		
		
	}
	
}
