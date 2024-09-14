package com.rasi.queueusinglist;

public class Main {
    public static void main(String[] args) {

    Queue<Integer> queue = new Queue<>();

    queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(4);

        queue.enqueue(6);

        System.out.println(queue.isEmpty());
        System.out.println(queue.peek());

        System.out.println(queue.size());

        System.out.println(queue);



    }
}