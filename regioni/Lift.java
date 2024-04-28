// Pogledati kod Pantovica, bolje je ovo resenje vrv kad si dodao en i ex boolean da se tako radi kao kod njega

struct Lift{
 int enter[N] = 0;
 int exit[N] = 0;
 int level = 0;
 boolean en = 0;
 boolean ex = 0;
}

Lift lift;

void putnik(int trenutniSprat, int zeljeniSprat) {

    region(lift){

        await(level == trenutniSprat && en == true)
        exit[zeljeniSprat]++;
        enter[trenutniSprat]--;
        await(enter[trenutniSprat] == 0)
    }

    putujeLift()

    region() {
        await(level = zeljeniSprat && ex == true)
        exit[zeljeniSprat]--;

    }
}

void lift() {

    region(lift)
    {        
        ex = true;
        await(exit[level] == 0)
        ex = false;
        en = true;
        await(enter[level] == 0)
        en = false;
    } 

}
