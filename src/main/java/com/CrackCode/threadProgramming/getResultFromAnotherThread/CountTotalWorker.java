package com.CrackCode.threadProgramming.getResultFromAnotherThread;

import java.util.List;
import java.util.LongSummaryStatistics;

public class CountTotalWorker extends Thread {
    private volatile long total;//for store total {result}
    Object object;

    public void myWork(Object object) {
        this.object = object;
    }


    @Override
    public void run() {
        List<Integer> currentWork = (List<Integer>) object;
        LongSummaryStatistics logic = currentWork.stream().mapToLong(value -> value).summaryStatistics();
        total = logic.getSum();
    }

    public long getTotal() {
        return total;
    }
}
