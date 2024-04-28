//Unisex Bathroom Problem - Fifo Sa vezbi kod

struct WC 
{
    int cntM = 0;
    int cntW = 0;
    int ticket = 0;
    int next = 0;
}
WC wc;

void Women()
{
    //ulasku u WC
    int myTicket = 0;
    region(wc) 
    {
        myTicket = ticket ++;
        await(cntM == 0 && cntW < N && myTicket == next);
        cntW++;
        next++;
    }
    usingToilet();
    
    //izlazak iz WCa
    region(wc)
    {
        cntW--;
    }
}

void Men()
{
    //ulasku u WC
    int myTicket = 0;
    region(wc) 
    {
        myTicket = ticket ++;
        await(cntW == 0 && cntM < N && myTicket == next);
        cntM++;
        next++;
    }

    usingToilet();

    //izlazak iz WCa
    region(wc)
    {
        cntM--;
    }
}