
 // SC je disciplina, probudjeni ce najverovatnije da ima while (ne mora, moze i passing the condition da se uradi kao Pantovic)
monitor Resource{

int currRess = 3;
cond cond1, cond2;

int request(int num){
    
    while (currRess < num)
    {
        if (num == 1) cond1.wait()
        else if (num == 2) cond2.wait()
    }
    currRess = currRess - num;
}

int release(int num){

    currRess += num; //  note: ako uvecavas pri svakom signalu i kad nema i kad ima blokiranih onda mora i u request(tj wait) metodi da skidas u svakom slucaju 
    if (cond2.queue() && currRess >= 2) cond2.signal() // i ovde bi isao while da nije maks 3 resursa pa fizicki ne mozes da odblokiras 2 koja su trazila 2 jer je to vise od 3
    else 
    {
    while(cond1.queue() && currRess >= 1) // OPET ISTI DETALJ KAO KOD SemaphoreProblem KOJI ZABORAVLJAS, PUSTAJ VISE AKO MOZE VISE!
        cond1.signal()
    }

}

}
// Kao Pantovic passing the condition

monitor Resource{

int currRess = 3;
cond cond1;
cond cond2;

void request(int num) {
    if (num <= currRess) {currRess = currRess - num} // ti prolazis normalno bez umanjivanja kad ima dovoljno
    else if (num > currress && num == 1) 
    {
        cond1.wait()
    }
    else if (num > currress && num == 2) 
    {
        cond2.wait()
    }
}

void release(int num){
    currRess = currRess + num; // ja dodajem normalno kad nema nikog da ceka
    if (cond2.queue() && currRess >= 2) { currRess -= 2; cond2.signal()}
    else 
    {
        while (cond1.queue() && currRess >= 1) {currRess -= 1; cond1.signal()}
    }
}
}




