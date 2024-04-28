monitor Semaphore{
    int s = 0;
    cond cond;
    List<int> lista; // posto je cond FIFO promenljiva mozes da koristis listu (jer znas da ce cond.wait da doda na kraj liste isto kao i list.add)
    // ako promenljiva nije FIFO NEMA SMISLA KORISTITI PARALELNU listu, tad jedino da se nadas da imas pravo na prioritetnu metodu minrank pa da sa njom gvirnes i 
    //utvrdis da li neko treba da se budi, jer sta je problem  u ovom zad,
    // treba ti da mogucnost da gvirnes jer ako bi samo radio signal pa da probudjeni proverava uslov ako uslov nije ispunjen probudjeni zove cond.wait() i ide NA KRAJ reda
    // tako da se gubi FIFO poredak - potreban ti je nacin da gvirnes, posto ti je zabranjen minrank koristi pralelnu listu!
    public Semaphore(int init)
    {
        s = init;
    }

    void signalOne()
    {
        signalSem(1); // ne zaboravi i ove metode iako samo pozivaju tvoje
    }

    void waitOne()
    {
        waitSem(1); // ne zaboravi i ove metode iako samo pozivaju tvoje
    }

    void signalSem(int n) 
    {
        s = s + n;
        while (cond.queue() && list.get(0) <= s) {list.remove(0) cond.signal();} // OVDE SI BIO ZABORAVIO KAD SI RADI - MORA WHILE JER MOZDA MOZE DA SE PUSTI I VISE OD 1 KOJI JE CEKAO
                                                                 // I NE ZABORAVI USLOV DA UOPSTE NEKO CEKA DA ODBLOKIRANJE POPUT cond.queue ili list not empty       
    }

    void waitSem(int n) 
    {
        while (n > s || cond.queue()) {list.add(n); cond.wait();} // trebalo bi da moze i if umesto while
        s = s - n;
       
    }


}






monitor Semaphore {
	
	int v = 0;
	List<int> needed;
	cond q;
	

	void signal() {
		signal(1);
	}
	
	void wait() {
		wait(1);
	}

	void signal(int n) {
		v += n;
		while (q.queue() && needed.peek() <= v){
			v -= needed.pop();
			q.signal();;
		}
	}

	void wait(int n){
		if (value >= n) value -= n; // ako moze da prodje neka prodje 
		else {
			needed.add(n);
			queue.wait();
		}
	}

}








