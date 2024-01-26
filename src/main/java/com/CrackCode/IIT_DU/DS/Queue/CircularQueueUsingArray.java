package com.CrackCode.IIT_DU.DS.Queue;

class CircularQueueUsingArray {
    private int front = -1, rear = -1;
    private final int max_size = 2;
    private final int[] queue;


    public CircularQueueUsingArray() {
        queue = new int[max_size];
    }

    public boolean isEmpty() {
        return front == -1 && rear == -1;
    }

    public boolean isFull() {
        return (rear + 1) % max_size == front;
    }

    private int getFrontOrRear(int frontOrRear, int maxSize) {
        return (frontOrRear + 1) % maxSize;
    }

    public void enqueue(int data) {
        if (isFull())
            System.out.println("Queue is full. Cannot enqueue.");
        else {
            if (isEmpty())
                front = 0;
            rear = getFrontOrRear(rear, max_size);
            queue[rear] = data;
            System.out.println(data + " enqueued to the queue");
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Empty queue!");
            return -1;
        }
        int currentFront = queue[front];
        System.out.println("dequeue item : " + currentFront);
        if (front == rear)
            front = rear = -1;
        else
            front = getFrontOrRear(front, max_size);
        return currentFront;
    }

    private int peek() {
        return queue[front];
    }

    public static void main(String[] args) {
        CircularQueueUsingArray queue = new CircularQueueUsingArray();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.dequeue();
        System.out.println(queue.peek());
        queue.enqueue(3);
        queue.dequeue();
        queue.enqueue(4);
        queue.enqueue(5);
    }
}