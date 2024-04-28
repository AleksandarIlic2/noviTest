Struct Molekul{
    int cntH = 0;
    int cntO = 0;
    boolean sacekajO = false;
    boolean sacekajH = false;
    int ticketO = 0;
    int ticketH = 0; // moraju odvojeni tiketi, jer zamisli da dodje jedan atom H pa 10 atoma O, tada bi nastavo deadlock jer nemaju kako da prodju 
    int nextO = 0;
    int nextH = 0;
}


Molekul molekul 



void H(int id) {

    region(molekul)
    {
        int myTicket = ticketH++;
        await(cntH <= 1 && myTicket == nextH)
        cntH++;
        nextH++;
        if (cntH == 2) sacekajH = true;
        await(sacekajO)
        await(sacekajH)        
        cntH = 0;
        sacekajO = false;
    }

    bond()
    
 

}


void O(int id) {

    region(molekul)
    {
        int myTicket = ticketO++;
        await(cntO <= 0 && myTicket == nextO)
        cntO++;
        nextO++;
        sacekajO = true;
        await(sacekajH)
        cntO = 0;        
        sacekajH = false;
    }
    bond()
 
}


/// Pantovic
//----------PRACTICE------------

srtuct Barrier
{
    int ticketO = 0;
    int nextO = 0;
    int ticketH = 0;
    int nextH = 0;
    List ids;
    int curr;
    int readIds;
};

Barrier barrier;

void H(int id)
{
    List molecule;
    int myTicket;
    region(barrier)
    {
        myTicket = ticketH++;
        await(myTicket == nextH && curr < 2);
        ids.add(id);
        curr++;
        nextH++;
        await(ids.size() == 3);
        molecule = ids;
        readMolecule(molecule);
        readIds++;
        if (readIds == 3) // samo jedan od dva procesa ce ovo da izvrsi ili drugi H ili prvi O zavisi ko poslednji prodje await(nije greska sto je isti kod)
        {
            curr = 0;
            readIds = 0;
            ids.clear();
            nextO++;
        }
    }
}

void O(int id)
{
    List molecule;
    int myTicket;
    region(barrier)
    {
        myTicket = ticketO++;
        await(myTicket == nextO); // primeti da ovde nema promenljive cntO, razlog za to je sto se nextO++ pomera na kraju pa nema potrebe za tom promenljivom
        ids.add(id);
        await(ids.size() == 3);
        molecule = ids;
        readMolecule(molecule);
        readIds++;
        if (readIds == 3) // samo jedan od dva procesa ce ovo da izvrsi ili drugi H ili prvi O zavisi ko poslednji prodje await (nije greska sto je isti kod)
        {
            curr = 0;
            readIds = 0;
            ids.clear();
            nextO++;
        }
    }
}



