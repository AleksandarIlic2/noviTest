package philosopherMonitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosopherMonitor {

	public int N;
	Lock lock = new ReentrantLock();
	Condition[] cond;
	private int states[]; // 0 thinking 1 eating

	public PhilosopherMonitor(int N) {
		this.N = N;
		this.cond = new Condition[N];
		this.states = new int[N];
		
		for(int i = 0; i < N; i++)
		{
			cond[i] = lock.newCondition();
			states[i] = 0;
		}
	}
	
	public void tryEat(int id) {
		lock.lock();
		try {
			int levi = (id - 1 + N) % N;
			int desni = (id + 1) % N;			
			if (states[levi] == 1 || states[desni] == 1) {
				cond[id].awaitUninterruptibly();
			}
			System.out.println("Filozof " + id + " pocinje da ruca");
			states[id] = 1;
			//...
			
		}
		finally {
			lock.unlock();
		}		
		
	}
	
	public void putDown(int id) {
		lock.lock();
		try {
			System.out.println("Filozof " + id + " ne ruca vise");
			states[id] = 0;			
			int levi = (id - 1 + N) % N;
			int desni = (id + 1) % N;
			int leviOdLevog = (levi - 1 + N) % N;
			int desniOdDesnog = (desni + 1) % N;
			
			if (states[leviOdLevog] == 0) {
				states[levi] = 1;
				cond[levi].signal();
			}
			if (states[desniOdDesnog] == 0)
			{
				states[desni] = 1;
				cond[desni].signal();
			}
		}
		finally {
			lock.unlock();
		}
	
	}

}
