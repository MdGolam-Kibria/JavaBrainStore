package com.CrackCode.IIT_DU.DS.Queue;

public class LinearQueueUsingArray {
    /**
     * Remember one thing that the Queue concept always work in [FIFO(first in first out)] model
     */
    private int font = -1, rear = -1, max_size = 10;
    private int[] queue;

    public LinearQueueUsingArray() {
        this.queue = new int[max_size];
    }

    private void enqueue(int data) {
        /**
         * @Algorithm
         * 1) We need to check the font and rear both is -1 then we need to make both to 0,
         *    because array index should start with 0
         * 2) Need to overflow condition, because in case of queue is full then system will throw exception.
         *    So, We need to handle it. (throw queue is full or something like this or ensure the capacity)
         * 3) After touch above conditions we can add the data to the array.
         * 4) Increase the rear value and font should be same as per the queue flow
         */
        if (isEmpty()) {
            font = rear = 0;
        } else if (rear == max_size)
            //ensureCapacity();
            throw new RuntimeException("Queue is full");
        queue[rear] = data;
        ++rear;
    }

    private void ensureCapacity() {
        int newCapacity = max_size * 2;
        int[] newArr = new int[newCapacity];
        System.arraycopy(queue,0,newArr,0,queue.length);
        queue = newArr;
    }

    private int dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty!");
        return queue[font++];
    }

    private int peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty!");
        return queue[font];
    }

    private boolean isEmpty() {
        return font == -1 && rear == -1;
    }

    public static void main(String[] args) {
        LinearQueueUsingArray queueObj = new LinearQueueUsingArray();
        queueObj.enqueue(1);
        queueObj.enqueue(2);
        queueObj.enqueue(3);
        queueObj.enqueue(4);

      /*  queueObj.enqueue(5);
        queueObj.enqueue(6);
        queueObj.enqueue(7);
        queueObj.enqueue(8);
        queueObj.enqueue(9);
        queueObj.enqueue(10);
        queueObj.enqueue(11);*/

        for (int i = queueObj.font; i <= queueObj.rear - 1; i++) {
            System.out.println(queueObj.dequeue());
        }
//        System.out.println(queueObj.peek());
    }
}
