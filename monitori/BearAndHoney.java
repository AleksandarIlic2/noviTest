

// SW
// Ovo je tvoje resenje koje nije skroz neispravno vrv ali ima jednu gresku - po ovom resenju poslednja pcela koja napuni samo prodje i NE PROBUDI medveda, 
// vec ga budi ona naredna kad uvidi da je kosnica puna, sto nema bas smisla treba da budi PRVA PCELA KOJA JE NAPUNILA!

monitor BearAndHoneys{
int honeyCnt = 0;
cond bear, honey;
void bearM() {

    while (honey == 0) bear.wait()
    honeyCnt = 0;
    honey.signal()
}
void honeyM() {
  
    if (honeyCnt == N)
    {
        if (bear.queue()) bear.signal()
        honey.wait()
    }
    honeyCnt++;
    if (honey.queue() && honeyCnt != N) honey.signal()
}
}


// SW monitor
monitor BearAndHoneys{
int honeyCnt = 0;
cond bear, honey;


void bearM() 
{
    if (honeyCnt != N) bear.wait()
    honeyCnt == 0;
    honey.signal()
}

void honeyM()
{
    if (honeyCnt == N) honey.wait()

    if (honeyCnt < N)
    {
        honeyCnt++;// MORA TA PCELA DA BUDI MEDVEDA, NE NAREDNA! 
        if (honeyCnt == N) bear.signal()
    }        

    if (honey.queue() && honeyCnt < N) honey.signal()   // ajde za mnom ako mozete 

}
}



// SC Pantovic

monitor Hive
{
    int cnt = 0;
    bool readyToEat = false;
    cond hiveFull;
    cond hiveEmpty;

    void putHoney()
    {
        while (cnt == N)
            hiveEmpty.wait();
        if (++cnt == N)
            hiveFull.signal();
        // izbacen poslednji slucaj odavde jer se on upravo odnosio na pustanje naredne pcele ako moze, umesto toga radi se signalAll pa nek provere na while uslovi opet pcele da li mogu dalje    
    }

    void eatHoney()
    {
        if (cnt < N)
            hiveFull.wait();
        cnt = 0;
        hiveEmpty.signalAll();
    }
}