package com.CrackCode.interviewQuestion.threadProgramming.multipleThread;

import java.util.List;
import java.util.LongSummaryStatistics;

public class CountAverageWorker extends Thread {
    private volatile double average;
    private Object workObject;

    public void myWork(Object work) {
        this.workObject = work;
    }

    @Override
    public void run() {
        List<Integer> currentWork = (List<Integer>) workObject;//current work

        LongSummaryStatistics statistics = currentWork
                .stream().mapToLong(value -> value).summaryStatistics();

        average = statistics.getAverage();
        System.out.println(average);
    }

    public double getAverage() {
        return average;
    }
}
