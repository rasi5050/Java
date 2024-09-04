package com.rasi.list;

import java.util.NoSuchElementException;

import static java.lang.Math.max;

/**
 * dynamic sized list; implemented using linked list from scratch; list grows towards head; (prev.tail.next) --> nodes --> (prev.head.next)
 * assumption: left is prev, next is right; list should be visualized as (prev.tail.next) --> nodes --> (prev.head.next)
 * currently implemented for Integer for simplicity; will generalize into all objects later
 */
public class List{

    private Node head = new Node(null, null, null);
    private Node tail = new Node(null, null, null);

    private int size=0;
    public List() {

        head.setPrev(tail);
        tail.setNext(head);
//        Node firstNode = new Node(4321, tail, null);
//        Node secondNode = new Node(5432, firstNode, head);
//        firstNode.setNext(secondNode);
//
//        head.setPrev(firstNode);
//        tail.setNext(firstNode);
    }


    /**
     * Adds a new node to the linked list at the end(just before head).
     * @param node  The new node to insert.
     */
    void _add(Node node) {
//        _add(size, node);
        Node headPrev = head.getPrev();
        node.setPrev(headPrev);
        node.setNext(head);
        headPrev.setNext(node);
        head.setPrev(node);
        size++;

    }

    /**
     * iterate to the req index from the closest side from either tail or head; if index is closer to tail start from tail, else start from head
     * @param index The required index
     */
    Node iterateToIndex(int index) {
        if (index>size || index<0) {
            throw new IndexOutOfBoundsException();
        }

        if (size == 0 || index == 0) {
            return tail.getNext(); // Return the first node after the tail, which should be head in an empty list
        }

//        if (size==0) return head;
        int currIdx;
        Node currNode;
        if (index < size / 2) {
            currIdx = 0;
            currNode = tail.getNext();
            while (currIdx != index) {
                currNode = currNode.getNext();
                currIdx++;
            }
        } else {
            currIdx = size-1;
            currNode = head.getPrev();
            while (currIdx != index) {
                currNode = currNode.getPrev();
                currIdx--;
            }
        }
        return currNode;
    }
    /**
     * Adds a new node to the linked list at the specified index.
     *
     * @param index The position at which to insert the new node.
     * @param node  The new node to insert.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    void _add(int index, Node node) {
    //I initially made the condition as index > size -1, gpt corrected me as it should be index > size to be able to insert to the end of list
        if (index < 0 || index>size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }

        // Handle adding the first element
        if (size == 0 || index==size) {
//            node.setPrev(tail);
//            node.setNext(head);
//            tail.setNext(node);
//            head.setPrev(node);
//            size++;
//            return;
            _add(node);
            return;
        }

        Node currNode = iterateToIndex(index);

        Node currNodePrev = currNode.getPrev();
        currNodePrev.setNext(node);
        node.setPrev(currNodePrev);
        currNode.setPrev(node);
        node.setNext(currNode);

        size++;
    }



    //removes current node from linkedlist from head side; ties connection with previous and next nodes of given node
    void _remove(Node node) {
        Node nodeNext = node.getNext();
        Node nodePrev = node.getPrev();
        nodePrev.setNext(nodeNext);
        nodeNext.setPrev(nodePrev);
        size--;
    }

    void _remove(int index) {
        Node currNode = iterateToIndex(index);
        _remove(currNode);
    }
    //insert new element
    //add at the end
    void add(Integer i){
        _add(new Node(i, null, null));
    }

    void add(int index, Integer i){
        _add(index, new Node(i, null, null));
    }


    //delete by index
    void remove(Integer index) {
        _remove(index);
    }

    //get by index
    int get(int index) {
        Node currNode = iterateToIndex(index);
        return currNode.getVal();
    }

    int pop() {
        if (size==0) throw new NoSuchElementException();
        Node currNode = iterateToIndex(size-1);
        _remove(currNode);
        return currNode.getVal(); // return last element
    }

    int size() {
        return size;
    }

    void clear() {
//        head.setNext(tail);
//        tail.setPrev(head);
        tail.setNext(head);
        head.setPrev(tail);
        size=0;
    }

    /*
    * start searching from both end simultaneously to reduce processing time
    */
    boolean contains(int element) {
//        +1 is added if the total length is odd, and middle item should be also checked
        int count = (size+1)/2;
        Node start = tail.getNext();
        Node end = head.getPrev();
        while (count>0) {
            if (start.getVal()==element || end.getVal()==element) {
                return true;
            }
            start = start.getNext();
            end = end.getPrev();
            count--;
        }

        return false; //change this
    }

    /**
     * Finds the index of the specified element in the list.
     * The search starts from both ends simultaneously to reduce processing time.
     *
     * @param element The element to search for in the list.
     * @return The index of the element if found.
     * @throws NoSuchElementException If the element is not found in the list.
     */
    int indexOf(int element) throws NoSuchElementException {
        int countStart = 0;
        int countEnd = size-1;
        Node start = tail.getNext();
        Node end = head.getPrev();
        while (countStart<=countEnd) {

            if (start.getVal()==element) {
                return countStart;
            } else if (end.getVal()==element) {
                return countEnd;
            }
            start=start.getNext();
            end=end.getPrev();
            countStart++;
            countEnd--;
        }
        throw new NoSuchElementException("Element not found in the list."); // change this
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node currNode = tail.getNext();
        while (currNode!=head) {
            sb.append(currNode.toString()).append(", ");
            currNode = currNode.getNext();
        }
//        to remove the last ", "
        if (size>0) sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
