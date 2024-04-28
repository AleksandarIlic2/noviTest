REENTRANT SC
// Ovde se koristi semafor ownership umesto neke uslovne promenljive cond koju je David koristion;
thread owner = -1;
Map<thread, int> depth[] = 0;

wait(mutex);
if (owner != null && threadId != owner) {
	waiting++
	signal(mutex);
	wait(ownership);
	waiting--;
}
owner = threadId;
depth[owner] += 1;
signal(mutex);
.....
wait(mutex);
if (--depth[owner] == 0)
	owner = -1;
	if (waiting>0) signal(ownership);
	else signal(mutex);
else signal(mutex);


CV.wait()
---------
wait(mutex);
CV.count++;
if (waiting >0) signal(ownership);
else signal(mutex);
wait(CV.sem);
wait(mutex);
if (owner != -1) {
	waiting++;
	signal(mutex);
	wait(ownership);
	waiting--;
}
owner = threadId
signal(mutex);

CV.signal()
-----------
wait(mutex);
if (CV.count > 0) {
	CV.count--;
	signal(CV.sem);
} 
singal(mutex);

CV.signalAll()
-----------
wait(mutex);
while (CV.count > 0) {
	CV.count--;
	signal(CV.sem);
}
signal(mutex);