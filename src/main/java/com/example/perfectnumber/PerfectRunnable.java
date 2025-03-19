package com.example.perfectnumber;

import javafx.collections.ObservableList;
import javafx.scene.control.ProgressIndicator;

public class PerfectRunnable implements Runnable {
    int start, end;
    ProgressIndicator progInd;
    ObservableList<Integer> items;

    public PerfectRunnable(int start, int end, ProgressIndicator progInd, ObservableList<Integer> items) {
        this.start = start;
        this.end = end;
        this.progInd = progInd;
        this.items = items;
    }

    @Override
    public void run() {
        for(int i = start; i <= end; i++){
            progInd.setProgress((double) (i-start) / (end-start));
            if(isPerfect(i)){
                items.add(i);
            }
        }
    }

    private boolean isPerfect(int n) {
        int sum = 1;
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n % i == 0){
                sum += i + (n/i == i ? 0 : n/i);
            }
        }
        return sum == n;
    }
}
