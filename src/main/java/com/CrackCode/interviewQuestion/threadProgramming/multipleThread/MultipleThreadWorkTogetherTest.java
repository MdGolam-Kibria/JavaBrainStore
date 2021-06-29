package com.CrackCode.interviewQuestion.threadProgramming.multipleThread;

import com.CrackCode.interviewQuestion.threadProgramming.getResultFromAnotherThread.CountTotalWorker;

import java.util.Arrays;

public class MultipleThreadWorkTogetherTest {
    public static void main(String[] args) throws InterruptedException {
         double mainThreadWork = 0;


        //-------- First thread-----------
        CountAverageWorker averageWorker = new CountAverageWorker();
        averageWorker.myWork(Arrays.asList(1, 2, 3, 4, 5));//work
        averageWorker.start();//give instruction for start the given work (Start work)


        for (int i = 0; i < 5; i++) {//assign work to main thread
            System.out.println("main thread working");
            mainThreadWork += i;
        }

        //------------Second thread---------
        CountTotalWorker countTotalWorker = new CountTotalWorker();
        countTotalWorker.myWork(Arrays.asList(1, 2, 3, 4, 5));
        countTotalWorker.start();



        /**
         * Now collect [Main Thread , CountTotalWorker Thread , CountAverageWorker ] result and sum
         */
        averageWorker.join();
        countTotalWorker.join();

        double average = averageWorker.getAverage();
        System.out.println("Your First thread average  result = " + average);

        long workerTotal = countTotalWorker.getTotal();
        System.out.println("Your Second thread total result = " + workerTotal);

        double sumOfAllThreadResult = average + workerTotal + mainThreadWork;

        System.out.println("Your All Thread Work sum is  =  " + sumOfAllThreadResult);

     }
}
