struct Lift
{
    int enter[N];
    int exit[N];

    int en = false;
    int ex = false;

    int level = 0;
}



Lift lift;


void putnik(int zeljeniSprat, int trenutniSprat) {

region(lift)
{
    enter[trenutniSprat] ++;
    await(trenutniSprat == level && en = true)
    enter[trenutniSprat]--;
    exit[zeljeniSprat]++;
}

vozi...

region(lift)
{
    await(level == zeljeniSprat && ex = true)
    exit[level]--;
}

}



void lift()
{

    level = new Level()
    ex = true;
    await(exit[level] == 0)
    ex = false;
    en = true;
    await(enter[level] == 0)
    en = false
}












