2019 jan

2. (20) К Користећи Signal and Continue дисциплину, 
написати монитор са следећом операцијом zameni(int * vrednost).
 Та операција за два узастопна позивајућа процеса треба да замени вредности прослеђене као аргумент, 
 тј. први процес треба да добије вредност другог, а други првог; трећи позивајући процес треба да добије вредност четвртог, а четврти трећег, итд.


S & C
monitor RendezVous {
	bool first = true, active = false;
	int curr, ticket = 0, next = 0;
	cond second, queue;
	
	void rv(int val){
		int ret;
		int myTicket = ticket++; // ovde ticket treba jer ces da uradis next++ kad je sve gotovo, kao sto si kod ovakvog zad sa semaforima oslobadjao mutex na kraju tek
        // e tako ces i ovde tek na kraju next++ (prvo next++ je samo da pusti sledeceg tj. tvog para, ova recenica se odnosi na drugi next++);

		if (myTicket != next) queue.wait(myTicket);
		if (first) {
			curr = val;
			first = false;
			next++;
			if (queue.queue()) queue.signal();  // na prvi pogled ovaj uslov deluje suvisan, kao radi i bez njega, ali zapravo bez njega moze da nastane deadlock
			// zamisli situaciju: nit1 dobije tiket 1 dodje odradi sve sto treba zablokira se na second.wait() dodje nit2 i ona ode u else i signalizira ni1 koja ceka na second.wait
			// nakon toga dodje jos 5 niti i zablokiraju se na queue.wait(myTicket) jer jos nije red. Nit1 se probudi i uradi sta je ostalo posle second.wait() i signalizira 
			// jednu od tih 5 niti sto cekaju(if (queue.queue()) queue.signal()). Nakon toga dodje ta prva od 5 odblokiranih i udje u if(first)  i uradi next++ i zabode na second.wait()
			// DA NIJE PRE second.wait uradio if (queue.queue()) queue.signal(); bez obzira sto si uradio next++ ti NE MOZES DA PUSTIS SLEDECEG JER IAKO JE SLEDECEM myTicket == next
			// ON JE VEC ZABLOKIRAN NA WAIT, MORA PRVO DA DOBIJE JEDAN SIGNAL!
			second.wait();
			
			ret = curr;
			first = true;
			next++;
			if (queue.queue()) queue.signal();
			
		} else {
			ret = curr;
			curr = val;
			second.signal();

		}
		
		return ret;
	
	}

}


S & W - ne moze Passing the Condition // moze bez ticket! jer ovde znas da se bas taj sto ti treba naredan budi (ne moze niko da uleti izmedju ta 2 procesa)!
monitor RendezVous {
	bool first = true, active = false;
	int curr, ticket = 0, next = 0;
	cond second, queue;
	
	void rv(int val) {
		int ret;
		
		if (first) {
			curr = val;
			first = false;
			second.wait();
			
			ret = curr;
			first = true;
			
		} else {
			ret = curr;
			curr = val;
			second.signal();
			
		}
		
		return ret;
		
	}
	
}
