// Pantovic resenje sa ovim turn

void man() {

	region(WC) {
		mWait++;
		await(wCnt == 0 && turn != "W" && mCnt < N);
		mWait--;
		
		mCNnt++;
		if (wWait > 0) turn = "W";
	}
	useBathroom();
	region(WC) {
		mCnt--;
		if (mCnt == 0 && wWait == 0)
			turn = "-";
	}
}