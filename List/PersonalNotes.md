## 9/4/24

## Implement a list using LinkedList - iteration1

current limitations:
1. only takes int, should be made as generics
2. no Java idioms
3. no iterarator
4. equals() should be implemented
5. Node should a private inner class of List


```
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
```


## 9/4/24
## after implementing modern Java Idioms with help of Claude
```
package com.rasi.list;

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
}
```

## 9/5/24
## learnings
# Generics:

```
// without generics
class Node {
    Integer value;
    public Node(Integer value) {
        this.value = value;
    }
}
````
with Generics, the same class be worked with any type of class parameter; basically generalizing the class parameter type with a place holder type T, so that compiler can replace the type with definite type at compile time, do type checking at compile time rather than runtime

```
class Node<T> {
    // with generics
    T value;
    public Node(T value) {
        this.value = value
    }
}

```

Exception: 
say List is a generic class of type T
```
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
}

```

it should be ideally initialized something like

List<Integer> list = new List<>();

but instead I inititialized as
List list = new List();
and added, list.add(1); list.add("hello");

this is still valid because, in this case type is 'raw', to be backward compactible and type checking is bypassed;


# Lambda expressions
anonymous functions in the form
```
() -> some code;

or 

() -> {
    more lines
    of code
}

or 

singleParameter -> some code;
```

sample code:
```
Function<Integer, Integer> num = (input) -> input + 1;  //return a function with input Integer and output Integer
System.out.println(num.apply(5)); // output will be 6
```

# type bounds
```
<? extends Number>    is upper bound, ? should be Within Number

<? super Number>      is lower bound, ? should atleast Number or anything above
````

# Optional

efficient way to handle possible null values to avoid NullPointerException
Once the Optional Container class returns the object. you can apply methods on it. infact you should use the Optional methods on it, essentially enforcing null handling effectively, which otherwise we have to manually

```
    public Optional<T> get(int index) {
        if (index < 0 || index >= size) {
            return Optional.empty();
        }
        return Optional.ofNullable(getNode(index).getValue());
    }

// Example of using the get method and handling the Optional result

Optional<T> optionalResult = get(1); // Call the get method with an index
System.out.println(optionalResult.orElse(1)); // Use orElse on the returned Optional

```

# Consumer (refer "gpt conversations/9_5_24 Consumer, Function.md")
takes one input returns no results

```
public void forEach(Consumer<? super T> action) {
    Node<T> current = tail.getNext();
    while (current != head) {
        action.accept(current.getValue());
        current = current.getNext();
    }
}
```

so consumer is just a way to accept another function as parameter.

in python this would be

def iterFunction(anotherFn):
    for each in nums:
        anotherFn(each)

basically all these mess is because Java has to create a type of whatever it has to do

## if Python and Haskell is both statically typed why doesnt Haskell has better type inference and Java doesnt

although both are statically typed Haskell is stronger type Inference. Java on the other hand has weeaker type inference, also contrained by Object oriented complexities and Backward compactability. (refer: "gpt conversations/9_6_24 var type and why cant it infer complex types.md")