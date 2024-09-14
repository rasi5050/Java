package com.rasi.stackusinglist;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * dynamic sized list; implemented using doubly linked list from scratch; list grows towards head; (prev.tail.next) --> nodes --> (prev.head.next)
 * assumption: left is prev, next is right; list should be visualized as (prev.tail.next) --> nodes --> (prev.head.next)
 * @param <T> The type of elements in this list
 */
public class List<T> {

    private final Node<T> head;
    private final Node<T> tail;
    private int size;

    public List() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, null, null);
        head.setPrev(tail);
        tail.setNext(head);
        size = 0;
    }


    public void add(T element) {
        addBefore(head, element);
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        addBefore(getNode(index), element);
    }

    private void addBefore(Node<T> succ, T element) {
        Node<T> pred = succ.getPrev();
        Node<T> newNode = new Node<>(element, pred, succ);
        succ.setPrev(newNode);
        pred.setNext(newNode);
        size++;
    }


    public void remove(int index) {
        Node<T> node = getNode(index);
        removeNode(node);
    }

    private void removeNode(Node<T> node) {
        Node<T> pred = node.getPrev();
        Node<T> succ = node.getNext();
        pred.setNext(succ);
        succ.setPrev(pred);
        size--;
    }

    public Optional<T> get(int index) {
        if (index < 0 || index >= size) {
            return Optional.empty();
        }
        return Optional.ofNullable(getNode(index).getValue());
    }


    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index < (size >> 1)) {
            return iterateForward(index);
        } else {
            return iterateBackward(index);
        }
    }

    private Node<T> iterateForward(int index) {
        Node<T> x = tail.getNext();
        for (int i = 0; i < index; i++) {
            x = x.getNext();
        }
        return x;
    }

    private Node<T> iterateBackward(int index) {
        Node<T> x = head.getPrev();
        for (int i = size - 1; i > index; i--) {
            x = x.getPrev();
        }
        return x;
    }

    public int size() {
        return size;
    }

    public void clear() {
        tail.setNext(head);
        head.setPrev(tail);
        size = 0;
    }

    public boolean contains(T element) {
        return indexOf(element).isPresent();
    }

    public Optional<Integer> indexOf(T element) {
        Node<T> current = tail.getNext();
        for (int i = 0; i < size; i++) {
            if (element == null ? current.getValue() == null : element.equals(current.getValue())) {
                return Optional.of(i);
            }
            current = current.getNext();
        }
        return Optional.empty();
    }

    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        Node<T> last = head.getPrev();
        removeNode(last);
        return last.getValue();
    }

    public void forEach(Consumer<? super T> action) {
        Node<T> current = tail.getNext();
        while (current != head) {
            action.accept(current.getValue());
            current = current.getNext();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        forEach(item -> sb.append(item).append(", "));
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.append("]").toString();
    }
    //package com.rasi.list;


    /*
     * Represents a node in a doubly-linked list.
     * @param <T>: The type of value stored in the node
     */
    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;
        public Node(T value, Node<T> prev, Node<T> next) {
            this.value=value;
            this.prev = prev;
            this.next=next;
        }
        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return value != null ? value.toString() : "null";
        }



    }
}




