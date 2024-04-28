// Davidovo resenje (I Pantovicevo je slicno)
Reentrant monitor mora da dozvoli da se iz jedne monitorske metode skoci u drugu monitorsku metodu (pretpostavljam istog monitora?)
//

monitor Reentrant {
    int TID;
    int level = 0;
    sem mutex, e = 1,1
    //ulaz
    wait(mutex);
    if (threadId == TID) {level++; signal(mutex);} // ako sam vec bio u nekoj monitorskoj metodi, puste me u sledecu samo povecaj level, threadId je id trenutne niti koja se izvrsava 
    else
    {
        signal(mutex);
        wait(e)
        wait(mutex); // mutex stiti leve i TID, ne zaboravljaj ga
        level = 0;
        TID = threadId;
        signal(mutex);
    }   

    // izlaz
    wait(mutex);
    level--;
    if (level == 0) {
        TID = -1; // OVO NIPOSTO NE ZABORAVLJAJ!! Sledeci koji dodje na ulaz proverava taj TID i on MORA DA SE RESTARTUJE!!!
        signal(mutex);
        signal(e);}
    else signal(mutex);

    CV.signal
    iny myLevel = level;
    cond.signal(); // Ovo cond je uslovna promenljiva nekog monitora koji nije reentrant pa kao ako je taj monitor SC onda je ovo SC resenje, ako je SC onda je SC resenje ovo
    // i ako je SUW onda je SUW resenje (Pantovic je radio sa semaforom ownership!)
    wait(mutex);
    level = myLevel; // vrati moj level opet sam ja
    TID = id // vrati moj id opet sam ja
    signal(mutex);

    CV.wait
    iny myLevel = level;
    cond.wait();
    wait(mutex);
    TID = id;
    level = myLevel;
    signal(mutex)

}

