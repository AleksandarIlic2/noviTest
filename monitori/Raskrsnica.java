2017 sept
2018 jul

1. (20) К Обична раскрсница две улице нема ниједан саобраћајни знак, па важи правило да десно возило има предност.
 Четири долазна правца означите ознакама од 1 до 4 идући у правцу сказаљке на сату. Из свих праваца је забрањено лево скретање. 
 Ако возач скреће десно, пре изласка из раскрснице, већ даје сигнал десном возилу да може да уђе у раскрсницу.
  Када излази из раскрснице, возило даје сигнал да више не заузима раскрсницу. Возила из супротних смерова, а на истом правцу могу истовремено да буду у раскрсници. 
  Напишите монитор који регулише саобраћај на оваквој раскрсници.



monitor Raskrsnica {


	void enter(int i, boolean rightTurn) {
        
        // Ako sam ja 1, i ako je:
        // 1) neko vec ispred mene u mojoj traci - queue[i].queue()
        // 2) neko vec ceka desno od mene, ja sam njemu levi pa zato on ima prednost - queue[right(i)].queue()
        // 3) ako su 2 ili 4 (to je pravac kontra od mog, onaj sto sece moj pravac) vec u raskrsnici, ne smem da ih secem - inside[right(i)] || inside[left(i)]
		while (queue[i].queue() || queue[right(i)].queue() || inside[right(i)] || inside[left(i)])
			queue[i].wait();

		inside[i] = true; // ja sam u raskrsnici
		
		if (!inside[right(right(i))] && queue[right(i)].empty()) // uslov !inside[right(right(i))] kaze nema svrhe da te budim ako ovaj sto ide pravcem suprotnog od naseg ide ()
        // u ovom pr ako 1 izlazi iz raskrsnice ali 3 vozi, nema sanse da udje neko iz 2 ili 4 pa necemo signal
			queue[right(i)].signal();
		if (!inside[left(left(i))] && queue[left(i)].empty())
			queue[right(i)].signal();
		
	}



}
