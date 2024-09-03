package com.rasi.list;

public class Node {
    private Integer val;
    private Node prev;
    private Node next;
    public Node(Integer val, Node prev, Node next) {
        this.val=val;
        this.prev = prev;
        this.next=next;
    }
    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return val != null ? val.toString() : "null";
    }
}
