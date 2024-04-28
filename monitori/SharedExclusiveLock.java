К2 2022, 1. задатак
Reader Writer 


monitor RW_SC {
	
	int sh = 0, ex = 0;
	cond lock;
	int ticket = 0;
	
	void lockShared()  // lockShared ti je kao start_read()
    {
		if (ex > 0 || lock.queue()) {
			lock.wait(ticket++ * 2); // shared ili ti citaoci neka budu parni
		} else 
			sh++;
	}
	void lockExclusive() // locExclusive ti je kao end_write()
    {
		if (ex > 0 || sh > 0) {
			lock.wait(ticket++ * 2 + 1); // exclusive ili pisci neka budu neparni
		} else 
			sh++;
	}
	void unlock() // unlock ti objedinjuje end_read() i end_write()
    {
		if (sh > 0) // note: Ako je sh > 0 sigurno je ex == 0 jer ne mogu oba istovremeno biti veca od 0, isto vazi i obratno ako je ex > 0 sigurno je sh = 0
        {
			sh--;
		} else 
			ex--;
		
		while (lock.queue()) // ne zaboravi da je ovde while a ne if, jer imas pravo da pustis vise citalaca
        {
			if (minrank(lock) % 2 == 0 && ex == 0) {
				sh++;
				lock.signal();
			} else if (minrank(lock) % 2 == 1 && ex == 0 && sh == 0) {
				ex++;
				lock.signal();
			} else 
				break;
		}
	}
	void upgradeLock(){
		sh--; // jedan manje sa sharedLockom jer se prebacujes sa sharedLock na exclusiveLock!
		if (sh > 0) {
			lock.wait(ticket++ * 2 + 1); // vidis da je ovde ticket++ * 2 + 1 TJ. SAD SAM NEPARAN, UBACI ME U RED ZA ONE KOJI TRAZE EXCLUSIVE LOCK
		} else 
			ex++;
	}
	
}
