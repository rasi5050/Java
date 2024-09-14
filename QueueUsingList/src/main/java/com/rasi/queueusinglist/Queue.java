package com.rasi.queueusinglist;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Queue<T> {
    private final List<T> queue;
    Queue() {queue = new List<T>();
    }

    public void enqueue(T item) {
        queue.add(item);
    }
    public T dequeue() {
        Optional<T> item = queue.get(0);
        queue.remove(0);
        return item.orElseThrow(NoSuchElementException::new);
    }

    public T peek() {
        Optional<T> peek = queue.get(queue.size() - 1);
        return peek.orElseThrow(NoSuchElementException::new);
    }

    public boolean isEmpty() {
        return queue.size()==0;
    }

    public int size() {
        return queue.size();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: [");
        for (int i = 0; i < queue.size(); i++) {
            Optional<T>  val = queue.get(i);
            // Handle possible empty values
            val.ifPresent(sb::append);
            if (i < queue.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
