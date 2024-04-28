//Sw monitor

monitor ChildCare{

int cntNanny = 0;
int cntChildren = 0;


cond nanny, parents;

void childEnter(int n){
    if (cntNanny * 3 <  cntChildren + n)
        parents.wait(n)
    cntChildren += n;    

}

void childrenExit(int n) {
    cntChildren = cntChildren - n;
    while (parents.queue() && cntNanny * 3 >= cntChildren + parents.min_rank())
        parents.signal()
    while (nanny.queue() && (cntNanny - 1)*3 >= cntChildren)    
        cntNanny--; //?
        nunny.signal()
}

void nunnyEnter() {
    cntNanny++;
    while (parents.queue() && cntNanny * 3 >= cntChildren)
        parents.signal()
    while (nanny.queue() && cntNanny*3 >= cntChildren)    
        nunny.signal()    

}

void nunnyExit()
{


}



}
















S & W FIFO

monitor Childcare {
	int nannies = 0, children = 0, ticket = 0;
	cond childrenArriving, nanniesLeaving;
	List<int> childrenComingCnt;

    void childrenArrive(int cnt) {
	
		if (children + cnt > 3 * nannies || childrenArriving.queue()) {
			
			childrenComingCnt.add(cnt);
			childrenArriving.wait(ticket++); // jel ovo fora kako da napravis FIFO? Kao ako ticket ide redom 0,1,2,3.. a tim redom moraju i elementi u listi
            // odlicna ideja zapravo, dakle koristis to sto .wait moze da primi parametar i da poredja na osnovu njega, posto ne mozes da prosledis broj dece
            // jer on moze biti bilo koji broj, moze cnt biti 1,7,2,5,1,2,3 i to bi rezultovalo 1,1,2,2,3,5,7 a to nije po redosledu dolazaka
            // ti uzmes lepo i ides po ticketu koji se samo uvecava za jedan pa ce biti FIFO
			childrenComingCnt.remove(0);
		}
	
		children += cnt;
		if (childrenArriving.queue() && children + childrenComingCnt.get(0) <= 3 * nannies) childrenArriving.signal(); // pusti sledeceg ako moze
	}
	
	
	void childrenLeave(int cnt) {
		children -= cnt;
		if (childrenArriving.queue() && children + childrenComingCnt.get(0) <= 3 * nannies) childrenArriving.signal();
		else if (nanniesLeaving.queue()) nanniesLeaving.signal();
			
	
	}

	void nannyArrives() {
		nannies++;
		if (childrenArriving.queue() && children + childrenComingCnt.get(0) <= 3 * nannies) childrenArriving.signal();
		else if (nanniesLeaving.queue()) nanniesLeaving.signal();
	}
	
	void nannyLeaves() {
		if (nanniesLeaving.queue() || children > 3 * (nannies-1)) nanniesLeaving.wait();
		nannies--;
		if (nanniesLeaving.queue() && children <= 3 * (nannies-1)) nanniesLeaving.signal(); // opet kao gore ideja pusti jos jednu ako moze
	}
	
	


}
