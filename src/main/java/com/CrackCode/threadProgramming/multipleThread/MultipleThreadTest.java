package com.CrackCode.threadProgramming.multipleThread;

import com.CrackCode.threadProgramming.getResultFromAnotherThread.CountTotalWorker;

import java.util.Arrays;

public class MultipleThreadTest {
    public static void main(String[] args) throws InterruptedException {

        //-------- First thread-----------
        CountAverageWorker averageWorker = new CountAverageWorker();
        averageWorker.myWork(Arrays.asList(1, 2, 3, 4, 5));//work
        averageWorker.start();//give instruction for start the given work (Start work)
        averageWorker.join();
        double average = averageWorker.getAverage();
        System.out.println("Your First thread average  result = " + average);


        //------------Second thread---------
        CountTotalWorker countTotalWorker = new CountTotalWorker();
        countTotalWorker.myWork(Arrays.asList(1, 2, 3, 4, 5));
        countTotalWorker.start();
        countTotalWorker.join();

        long workerTotal = countTotalWorker.getTotal();
        System.out.println("Your Second thread total result = " + workerTotal);

        /**
         * Now count 2 thread result sum
         */
        double totalTwoThreadAverageAndSumResultSum = average + workerTotal;
        System.out.println("Your Creating Two thread result sum is  = "+totalTwoThreadAverageAndSumResultSum);





    }
}
