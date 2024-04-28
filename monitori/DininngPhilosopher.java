


monitor DinnigPhilosopher()
{

int states[N] = {0} // 0 thinking, 1 eating
cond canEat;
int ticket = 0;

void tryEat(int id) {
    left = (id - 1 + N) % N;
    right = (id + 1) % N;
    int myTicket == N * (ticket++) + id; // ides bukvalno po jedan filozof u intervalu od 0 do N, pa od N do 2N neki broj uzima drugi za myTicket, pa od 2N do 3N treci...
    if (canEat.queue() || states[left] == 1 || states[right] == 1)
        canEat.wait(myTicket)

    states[id] = 1;

}

void putDown(int id){
    states[id] = 0 ;
    while (canEat.queue())
    {
        int x = canEat.minrank() % 2N
        if (states[(x + 1) % N] == 0 || states[(x - 1 + id) % N] == 0)
            canEat.signal()
        else
            break;    

    }

}




}