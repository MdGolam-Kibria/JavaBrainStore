package com.CrackCode.threadProgramming.getResultFromAnotherThread;

import java.util.Arrays;

public class GetResultFromAnotherThread {
    public static void main(String[] args) throws InterruptedException {
        CountTotalWorker countTotalWorker = new CountTotalWorker();
        countTotalWorker.myWork(Arrays.asList(1,2,3,4,5));
        countTotalWorker.start();
        countTotalWorker.join();

        long threadResult = countTotalWorker.getTotal();
        System.out.printf("you thread result is = "+threadResult);

    }
}
