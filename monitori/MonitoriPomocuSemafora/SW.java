// Signal and Wait

monitor SW{

// ulaz
wait(mutex)


//izlaz
signal(mutex);

CV.wait
CV_count++;
signal(mutex);
wait(CV_sem);
CV_count--;

CV.signal
if (CV_count > 0) {
    signal(CV_sem);
    wait(mutex);
}

}