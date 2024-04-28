

monitor Bumbercars{

cond slobodniAutomobili;
cond ljudiCekaju;
int carTicket = 0;
int visitorTicket = 0;
// Odovjeni tiketi!

void releaseCar() {

    int myTicket = carTicket++; // ne zaboravi myTicket na taj nacin ih odrzavas u poretku, NE ZABORAVLJAJ TICKET !
    if (ljudiCekaju.queue()) {
        signa(lljudiCekaju) // pretpostavljam da signal izbacuje iz reda, tako da ako 2 puta za redom uradi neko requestCar a ceka samo jedan auto, prvi ljudi
        // slobodniAutomobili,signal() izbacuju iz reda taj 1 auto i posle drugi poziv requestCar ce da zavrsi u else grani na wait
        // u resenju sa semaforima ovog zad morao si da se dobacujes preko globalne promenljive mutexId (kocka, semafori resenje), ovde zbog gorenavedenog razloga to ne mora
        // veliko je pitanje dal je to tako to je tvoje razmisljanje samo na prvu
    }
    else slobodniAutomobili.wait(myTicket) // ovo wait moze da se koristi i da ih samo poredja u red po myTicket, dakle ne mora da ide u paru sa minrank
    // posto carTicket raste za 1 kako koji proces naidje taj prioritetni red ce biti kao da je FIFO (pominjao si negde na drugim zad. tu logiku da se u wait() stavlja kao par
    // tiket koji raste jer ce tacno da se odrzi poredak)
}

void requestCar() {

    int myTicket = visitorTicket++; // ne zaboravi myTicket na taj nacin ih odrzavas u poretku!
    /*
     if (visitorQueue.queue())
        {
            visitorQueue.wait(myTicket);
        }
    Ovaj dodataj stoji kod Pantovica a nema ga u resenju onog drugog lika inace sve ostalo isto
    */
    if (slobodniAutomobili.queue()) 
    {
        slobodniAutomobili.signal() // ako ima slobodnih automobila uzmi ih
    }
    else{
        ljudiCekaju.wait(myTicket) 
        }
}

}