// Signal and Continue - Pantovic


monitor SC {

//ulaz
wait(mutex);

//izlaz

signal(mutex);

CV.wait
CV_count++;
signal(mutex);
wait(CV_sem)
wait(mutex);

CV.signal {
    if (CV_count > 0) {
        CV_count--;
        signal(CV_sem)
    }
}

CV.signalAll{
    while (CV_count > 0){
        CV_count--;
        signal(CV_sem)
    }
}
}