// Signal and Urgent Wait

monitor SC {

//ulaz

wait(mutex)

//izlaz

if (next.count > 0) signal(mutex)
else signal(next)

CV.wait

CV_count++;
if (next.count > 0) signal(next)
else signal (mutex) 
wait(CV_sem)
CV_count--;

CV.signal
if (CV_count > 0){
    next.count++;
    signal(CV_sem)
    wait(next)
    next.count--;

}


}

Napomena: Dakle ovo ulaz i izlaz SE ZOVE NA POCETKU I KAD SE ZAVRSI TVOJA MONITORSKA METODA, NE NA KRAJU WAITA ILI SIGNALA,
monitorska metoda je kad imas recimo monitor ConsumerProducer i u njoj metode get, put TO SU MONITORSKE METODE i na NJIHOVOM POCETKU I KRAJU se generise ovo ulaz i izlaz 
Ovo sto i u CV.wait moras da radis signal(next) ako je next.count > 0 to je sledeca situacija: Tvoja monitorska metoda pozove wait() zabodes, probudite te druga nit koja uradi signal,
ti zavrsis svoj wait ali je u tvojoj monitorskoj metodi jos jedan wait recimo, pa kad udjes u taj drugi wait MORAS da signaliziras nit koja te je probudila sa prethodnog WAITA
jer je ovo Signal and Urgent wait

pr: monitorskaMetoda{
    //nesto...
    CV.wait()
    CV.wait()
    //nesto..
}

a neka druga nit poziva CV.signal i kad nastavis ti opet ides u drugi CV.wait A NIJE SE IZGENERISAO DEO za izlaz jer nisi jos napustio monitorsku metodu, radis drugi wait
i pre nego sto se zabodes na drugom waitu duzan si da pustis sa next semafora onog sto te je odblokirao sa prvog waita.