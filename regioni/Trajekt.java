Трајект за превоз аутомобила, камиона и аутобуса превози возила са обале на обалу. Трајект поседује N позиција које су линеарно постављене једна иза друге.
 Камион заузима три, аутобус две, а аутомобил једну позицију. Возила чекају на трајект у реду и на њега улазе једно по једно по редоследу у којем чекају у реду, 
 док на трајекту има места. Када наредно возило у реду за трајект нема места да се укрца или када је трајект пун, трајект започиње превоз возила на другу обалу.
  На другој обали возила се искрцавају у редоследу супротном од редоследа у којем су се укрцала.
 Када се сва возила искрцају, празан трајект се враћа на почетну обалу. Користећи регионе написати програм који решава овај проблем.

 struct Trajekt{
    int brMesta = N;
    int ticket = 0;
    int next = 0;
    boolean odobrenPolazak = false;
    boolean vracanjeTrajekta = false;
    boolean prazanTrajekt = false;
    int ticket2 = 0;
 }


Trajekt trajekt;


void vozilo(int zahtevanoMesta) {

    region(trajekt)
    {
        int myTicket = ticket++;
        if (zahtevanoMesta > brMesta)
            odobrenPolazak = true;
        await(brMesta - zahtevanoMesta >= 0 && myTicket == next)
        brMesta = brMesta - zahtevanoMesta
        if (brMesta == 0) odobrenPolazak = true;
        next++;
        await(odobrenPolazak)
        ticket2 = ticket;
    }

    vrsiSePrevoz()

    region(trajekt)
    {
        await(ticket2 - myTicket < 2)
        ticket2--;
        if (ticket2 == 0) {
            prazanTrajekt = true;
            await(vracanjeTrajekta)
            brMesta = N;
            odobrenPolazak = false;
            vracanjeTrajekta = false;
            prazanTrajekt = false;
        }
    }
}

void trajekt() {

    region(trajekt) {
        await(odobrenPolazak)
    }
    prevoziSe()
    region(trajekt)
    {
        await(prazanTrajekt)
        vracanjeTrajekta = true;
    }

}