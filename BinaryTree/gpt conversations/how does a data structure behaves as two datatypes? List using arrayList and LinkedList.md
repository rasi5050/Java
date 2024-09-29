# 9/26/24

# Queue<Node<T>> queue = new LinkedList<>();

# in this line, i understand that, undesrstnad that it involves interfaces, but i dont understand the direction of interfacing and how does the same datastrucutre can have two different data types, here LinkedList can act as a Queue, say it can act as Stack, does it mean that LinkedList should have two set of implementations?


This is a great question! Let me break down what’s happening with `Queue<Node<T>> queue = new LinkedList<>()` and explain the relationship between **interfaces** and **implementations** in Java.

### 1. **Queue and LinkedList in Java**

In Java, `Queue` is an **interface**, and `LinkedList` is a **class** that implements the `Queue` interface (among other interfaces). When you write:

```java
Queue<Node<T>> queue = new LinkedList<>();
```

This means you're creating a `LinkedList` instance, but you're treating it as a `Queue` by referencing it via the `Queue` interface. You can only access the methods that are defined in the `Queue` interface, even though `LinkedList` might implement more methods from other interfaces.

### 2. **Interface vs. Implementation**

- **Interface (`Queue`)**: An interface in Java defines a set of methods that a class must implement but doesn't provide the actual implementation. The `Queue` interface defines methods like `offer()`, `poll()`, `peek()`, etc., which are typical operations for a queue (FIFO: First-In-First-Out).

- **Implementation (`LinkedList`)**: The `LinkedList` class implements multiple interfaces, such as `Queue`, `List`, and `Deque`. It provides the actual code for the methods defined in these interfaces.

### 3. **Polymorphism in Action**

The line `Queue<Node<T>> queue = new LinkedList<>()` shows **polymorphism** in Java, where a specific class (`LinkedList`) is treated as a more generic type (`Queue`), allowing you to use it according to the rules defined by the `Queue` interface.

- You can think of the `LinkedList` as being able to play different "roles," depending on which interface you reference it by. In this case, it's playing the role of a `Queue`, but it can also play the role of a `List` or `Deque`.

### 4. **How LinkedList Can Behave as Different Data Structures**

Now, to your main question: **How can `LinkedList` act as both a `Queue` and a `Stack`? Does it have two sets of implementations?**

No, `LinkedList` doesn't have **two separate implementations** for `Queue` and `Stack`. It implements the common behaviors required by different interfaces (such as `Queue`, `Deque`, and `List`) using a **single internal structure**.

Here’s how this works:
- **Queue**: When used as a `Queue`, methods like `offer()`, `poll()`, and `peek()` (which are defined in the `Queue` interface) operate on the front and back of the `LinkedList`, giving it FIFO behavior.
- **Stack**: When used as a `Stack`, methods like `push()`, `pop()`, and `peek()` (from the `Deque` interface) make the `LinkedList` behave like a LIFO structure (Last-In-First-Out). This is done by manipulating the list from one end (either the head or the tail).

### 5. **LinkedList Implements Multiple Interfaces**

`LinkedList` implements multiple interfaces, each with its own set of methods. Here's a breakdown:

- **List**: Provides list operations like `get()`, `add()`, `remove()`, etc.
- **Queue**: Provides queue operations like `offer()`, `poll()`, `peek()`.
- **Deque**: Provides double-ended queue operations, such as `addFirst()`, `addLast()`, `removeFirst()`, `removeLast()`, which allows `LinkedList` to act like both a queue (FIFO) and a stack (LIFO).

### 6. **Single Internal Implementation**

`LinkedList` doesn't maintain separate structures for each interface (e.g., it doesn't have one structure for queue behavior and another for stack behavior). Instead, it maintains a **single internal doubly-linked list**, and the different behaviors (queue, stack, list) are achieved by calling different methods on the same structure.

### Example of How `LinkedList` Acts as Both a Queue and Stack:

#### Acting as a Queue (FIFO):
```java
Queue<Integer> queue = new LinkedList<>();
queue.offer(1);  // Enqueue
queue.offer(2);
System.out.println(queue.poll());  // Dequeue (outputs 1)
System.out.println(queue.poll());  // Dequeue (outputs 2)
```

- Here, `offer()` adds elements to the tail (end), and `poll()` removes them from the head (front), making it work as a queue.

#### Acting as a Stack (LIFO):
```java
Deque<Integer> stack = new LinkedList<>();
stack.push(1);  // Push onto the stack
stack.push(2);
System.out.println(stack.pop());  // Pop from the stack (outputs 2)
System.out.println(stack.pop());  // Pop from the stack (outputs 1)
```

- Here, `push()` adds elements to the head (start), and `pop()` removes them from the head, making it work as a stack.

### 7. **Summary:**

- **`LinkedList`** implements multiple interfaces, such as `Queue`, `Deque`, and `List`.
- It can act as a **Queue** (FIFO) or a **Stack** (LIFO) because of its ability to handle different behaviors through the methods defined in these interfaces.
- **Internally**, it uses a **single doubly-linked list** structure, and the behavior changes depending on the methods called (e.g., `offer()` for queue vs. `push()` for stack).
- There’s no need for `LinkedList` to have two different implementations. It simply provides methods to interact with its single structure in different ways, depending on the interface being used.

In conclusion, `LinkedList` doesn’t have two separate implementations for different data structures; it uses a single implementation (a doubly-linked list) and adapts its behavior based on the methods you call, which are defined by the different interfaces (`Queue`, `Deque`, `List`) it implements.


# similarly explain how does List work with arraylist and linked list?

Great question! The `List` interface in Java provides a **common contract** for how lists should behave, while `ArrayList` and `LinkedList` are two different implementations of this contract. Even though both implement the `List` interface, they work very differently under the hood.

Let me break down how the `List` interface works with both `ArrayList` and `LinkedList` and how their internal implementations differ.

### 1. **`List` Interface Overview**

`List` is an interface in Java that defines the general contract for a sequence of elements. It defines methods for adding, removing, retrieving, and modifying elements, along with other useful operations like searching and iterating.

Some of the important methods defined in the `List` interface are:
- `add(E element)`
- `remove(int index)`
- `get(int index)`
- `set(int index, E element)`
- `size()`
- `contains(Object o)`

The `List` interface doesn't specify **how** the list is implemented. It just defines the behavior and expectations. `ArrayList` and `LinkedList` provide different implementations of this behavior.

### 2. **How `ArrayList` Works**

`ArrayList` is an implementation of the `List` interface that uses a **resizable array** under the hood. Here's how it works:

#### Key Characteristics:
- **Internal Structure**: It uses an **array** to store the elements.
- **Resizing**: When the array is full and you try to add an element, `ArrayList` increases the size of the array by creating a new, larger array and copying all the elements from the old array to the new one. This resizing typically happens when you exceed the capacity.
- **Index-based Access**: Since `ArrayList` is backed by an array, it provides **constant time (O(1)) access** to elements via the `get()` and `set()` methods because accessing an element in an array by index is very fast.
- **Insertion and Removal**: Inserting or removing an element **in the middle** of an `ArrayList` can be expensive because it may require shifting elements to the right or left to maintain the array's order. The complexity of insertion/removal at arbitrary positions is **O(n)**.

#### Example of `ArrayList`:
```java
List<Integer> arrayList = new ArrayList<>();
arrayList.add(10);  // Adds element at the end, O(1)
arrayList.add(20);
arrayList.add(1, 15);  // Inserts element at index 1, O(n)
System.out.println(arrayList.get(2));  // Accesses element at index 2, O(1)
```

- **Advantages of `ArrayList`**:
    - **Fast random access** due to array indexing.
    - Suitable for scenarios where most operations involve accessing elements by index or adding elements at the end.

- **Disadvantages**:
    - Inserting or removing elements in the middle of the list is slow because it requires shifting elements.
    - Resizing the array involves copying elements, which is computationally expensive.

### 3. **How `LinkedList` Works**

`LinkedList`, on the other hand, is implemented as a **doubly-linked list**. It works very differently from `ArrayList`.

#### Key Characteristics:
- **Internal Structure**: It uses a chain of **nodes**, where each node contains a reference to the next node and the previous node. Each node holds the element (data) and references to its neighbors (previous and next).
- **No Resizing**: Since there is no array, there's no need for resizing, so you don't incur the cost of copying elements when the list grows.
- **Efficient Insertion/Removal**: Inserting or removing an element **at the start or middle** of a `LinkedList` is faster than `ArrayList` (O(1) for the start, O(n) for middle). This is because it just needs to change the pointers of neighboring nodes, without the need to shift elements like in `ArrayList`.
- **No Index-based Access**: `LinkedList` does not have constant-time access by index because you need to traverse the list to reach a specific index (O(n) time complexity for `get()` and `set()`).

#### Example of `LinkedList`:
```java
List<Integer> linkedList = new LinkedList<>();
linkedList.add(10);  // Adds element at the end, O(1)
linkedList.add(20);
linkedList.add(1, 15);  // Inserts element at index 1, O(n)
System.out.println(linkedList.get(2));  // Accesses element at index 2, O(n)
```

- **Advantages of `LinkedList`**:
    - Insertion and deletion of elements at the **beginning** or **middle** are faster compared to `ArrayList` (O(1) at the head, O(n) elsewhere).
    - No resizing costs as in `ArrayList`.

- **Disadvantages**:
    - **Slow random access**: You can't access elements directly by index in constant time. You need to traverse the list to reach an element, so `get()` and `set()` are O(n).
    - Higher memory overhead because each element stores references to the next and previous elements (additional memory for the pointers).

### 4. **How Does `List` Work with `ArrayList` and `LinkedList`?**

The `List` interface defines the behavior, and both `ArrayList` and `LinkedList` implement this behavior differently:

- When you use `List` as a reference type, you can work with the `List` methods, but the actual behavior will depend on whether the underlying implementation is `ArrayList` or `LinkedList`.

```java
List<Integer> list = new ArrayList<>();  // or new LinkedList<>();
list.add(10);  // Works the same for both ArrayList and LinkedList, but the internal operation is different
list.get(0);   // Works for both, but it's O(1) for ArrayList and O(n) for LinkedList
```

- **Polymorphism**: By using the `List` interface, you can write code that is agnostic to whether the list is an `ArrayList` or `LinkedList`. The implementation details (how the list is maintained internally) are hidden behind the `List` interface, but the performance characteristics differ.

### 5. **Summary:**

| Feature                | `ArrayList`                              | `LinkedList`                                |
|------------------------|------------------------------------------|---------------------------------------------|
| **Underlying Structure**| Resizable array                          | Doubly linked list                          |
| **Access time**         | O(1) for `get()` and `set()`             | O(n) for `get()` and `set()`                |
| **Insertion at end**    | O(1) (amortized)                         | O(1)                                        |
| **Insertion/removal at start or middle** | O(n) (requires shifting elements) | O(1) at the start, O(n) in the middle (requires traversing) |
| **Memory overhead**     | Less (just stores elements in an array)  | More (each node has pointers to next and previous nodes) |
| **Resizing**            | Needs resizing when full (costly)        | No resizing needed                          |
| **Use cases**           | Good for random access and adding elements at the end | Good for frequent insertions/removals at the start/middle |

### Conclusion:
- **`ArrayList`** is best for scenarios where you frequently access elements by index or append to the end of the list.
- **`LinkedList`** is better when you need to frequently insert or delete elements at the beginning or in the middle of the list, and you don't require fast random access.

Both `ArrayList` and `LinkedList` implement the `List` interface, but they have different performance characteristics because of their underlying data structures (array vs. doubly linked list).


<h1> so can i assume that, i would find seperate implements for each like?

class LinkedList implements Queue {}
class LinkedList implements List {}
class LinkedList implements someOtherDataStructure {}


so that I can use Queue<Node<T>> queue = new LinkedList<>();
or
someOtherDataStructure<Node<T>> queue = new LinkedList<>();

also is does there exist just pure version of LinkedList without implementing anything

and which is further implementing other datastructures, using a composition of pure LinkedList class?
</h1>

Your understanding is on the right track, but let me clarify how **interfaces** and **implementations** work in Java, specifically with respect to `LinkedList` and how it interacts with different interfaces like `List`, `Queue`, and `Deque`.

### 1. **LinkedList Implements Multiple Interfaces at Once**

In Java, a class like `LinkedList` can implement **multiple interfaces** at the same time. However, rather than having multiple independent `LinkedList` classes, Java uses a single `LinkedList` class that **implements multiple interfaces**. This allows the same `LinkedList` object to behave as a `List`, a `Queue`, or a `Deque` depending on how it's referenced.

So, rather than having:
```java
class LinkedList implements Queue {}
class LinkedList implements List {}
class LinkedList implements someOtherDataStructure {}
```

Java has **one** `LinkedList` class that implements **multiple interfaces** simultaneously:

```java
public class LinkedList<E> extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable {
    // LinkedList implementation here
}
```

- **`List`**: This interface defines standard list behavior (e.g., `add`, `get`, `remove`).
- **`Deque`**: This interface defines a double-ended queue (deque), allowing operations at both ends of the list (e.g., `addFirst`, `removeLast`). It extends the `Queue` interface, so by implementing `Deque`, `LinkedList` also indirectly implements `Queue`.
- **`Cloneable`**: This allows `LinkedList` objects to be cloned.
- **`Serializable`**: This allows `LinkedList` to be serialized (converted into a byte stream).

So, there’s just one `LinkedList` class in Java that implements all of these interfaces. When you declare:

```java
Queue<Node<T>> queue = new LinkedList<>();
```

You are using a `LinkedList` object, but the compiler treats it as a `Queue`. Similarly, you can use the same `LinkedList` class as a `List` or a `Deque`.

### 2. **LinkedList as Different Data Structures**

Because `LinkedList` implements multiple interfaces, you can treat it as different data structures:

- As a **`List`**: You can treat a `LinkedList` like a standard list, accessing elements by index, adding/removing elements at specific positions, etc.
  ```java
  List<String> list = new LinkedList<>();
  list.add("a");
  list.get(0);
  ```

- As a **`Queue`**: You can treat it as a queue, using methods like `offer()`, `poll()`, and `peek()` to follow FIFO (First-In-First-Out) behavior.
  ```java
  Queue<String> queue = new LinkedList<>();
  queue.offer("a");
  queue.poll();
  ```

- As a **`Deque`**: You can treat it as a double-ended queue, allowing operations on both ends of the list, such as `addFirst()`, `addLast()`, `removeFirst()`, `removeLast()`.
  ```java
  Deque<String> deque = new LinkedList<>();
  deque.addFirst("a");
  deque.addLast("b");
  deque.removeFirst();
  deque.removeLast();
  ```

This means **you don't need separate implementations** for each interface—`LinkedList` is a **single class** that can act like different data structures depending on how it's used.

### 3. **Pure LinkedList without Implementing Anything**

Java's `LinkedList` class **always implements** the interfaces we've discussed (`List`, `Deque`, `Queue`). There's no built-in version of a **"pure" LinkedList** that doesn't implement any interfaces.

However, you could create your own custom "pure" linked list that doesn’t implement any interfaces, and then use **composition** to implement other data structures on top of it.

Here’s an example of how you could create your own pure `LinkedList` class and then build other data structures like a queue or stack on top of it using composition:

#### Pure LinkedList:
```java
class MyLinkedList<T> {
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public MyLinkedList() {
        this.head = null;
    }

    public void add(T data) {
        if (head == null) {
            head = new Node(data);
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(data);
        }
    }

    public T get(int index) {
        Node temp = head;
        for (int i = 0; i < index && temp != null; i++) {
            temp = temp.next;
        }
        return temp == null ? null : temp.data;
    }
}
```

This `MyLinkedList` class is a **pure linked list** implementation. It doesn't implement any interfaces and is just a custom class for storing nodes in a linked structure.

#### Using Composition to Implement a Queue:
```java
class MyQueue<T> {
    private MyLinkedList<T> list = new MyLinkedList<>();

    public void offer(T data) {
        list.add(data);  // Use linked list's add method to enqueue
    }

    public T poll() {
        // Use custom logic to dequeue the first element
        T data = list.get(0);
        // Remove logic for the first element here
        return data;
    }
}
```

In this case, you're using **composition** (i.e., a `MyQueue` object **contains** a `MyLinkedList` object) to implement a queue on top of the pure `MyLinkedList`.

### 4. **Why Java Uses Interfaces**

Java uses interfaces to provide **flexibility**. By having `LinkedList` implement multiple interfaces, you can treat the same data structure as different types of collections depending on the context (e.g., list, queue, deque), without needing to duplicate code.

- **List**: Random access and ordered collection.
- **Queue**: FIFO behavior.
- **Deque**: Double-ended queue allowing operations at both ends.

### Summary:
- **`LinkedList` in Java is a single class** that implements multiple interfaces (`List`, `Deque`, `Queue`). It doesn't require multiple implementations for each data structure; it simply implements these interfaces, allowing the same class to behave in different ways depending on how it's used.
- You can declare it as a `Queue`, `List`, or `Deque` based on your use case.
- There is **no pure built-in `LinkedList`** in Java that doesn’t implement anything. However, you can write your own custom linked list class and use composition to build more complex data structures like a queue or stack on top of it.

By using a single `LinkedList` class that implements multiple interfaces, Java provides a powerful, flexible way to work with different data structures, without duplicating functionality.


<h1>
so my understanding is a LinkedList will implement different interfaces and exposes only specific methods if its used for a specifc type, like
public class LinkedList<E> implements Queue<E> {
method1() {}
method2() {}

}
so that if return type of LinkedList is Queue, this above class is initialized and its objects will only have methods for Queue.

but int he above reply u told, all the implementations are done toghether, then how does the code segregate between the methods beings mixed of a different data type?

</h1>

Your understanding is getting closer, but let me clarify how **interface-based polymorphism** works in Java, and how it allows `LinkedList` to implement multiple interfaces without "segregating" the methods based on the context (i.e., `Queue`, `List`, `Deque`, etc.).

### Key Idea: **Polymorphism Through Interfaces**

When a class like `LinkedList` implements multiple interfaces (e.g., `List`, `Queue`, `Deque`), **all the methods** from those interfaces are implemented in the **same class**. There is no separate class for each interface (`List`, `Queue`, or `Deque`). Instead, **the same class** has the ability to act as multiple types depending on how it is referenced.

### Example:

Consider this single `LinkedList` class that implements both the `List` and `Queue` interfaces:

```java
public class LinkedList<E> implements List<E>, Queue<E> {
    // Common methods for both List and Queue
    public boolean add(E e) {
        // Implementation of adding an element (part of both List and Queue)
        return true;
    }

    // Specific methods for List interface
    public E get(int index) {
        // Implementation of list access by index
        return null;
    }

    // Specific methods for Queue interface
    public E poll() {
        // Implementation of queue-specific behavior (removing head element)
        return null;
    }

    // Other methods for both interfaces...
}
```

### 1. **One Class Implements All Methods Together**
When `LinkedList` implements both `List` and `Queue`, it includes **all the methods** from both interfaces in a single class implementation. The class does not segregate or choose which methods are available based on how you are using it.

Instead, **polymorphism** comes into play when you decide how you are going to reference the `LinkedList` object. The type of the reference (`List`, `Queue`, `Deque`) determines **which methods are accessible** at compile time.

### 2. **How Methods Are Segregated**
Java doesn’t "segregate" the methods within the class itself. Instead, it depends on **how you reference** the object. The methods you can call are determined by the **type of reference** (interface) you use to access the object.

For example:

#### When Using `List`:
```java
List<String> list = new LinkedList<>();
list.add("Hello");   // Allowed (defined in List)
list.get(0);         // Allowed (defined in List)
list.poll();         // Not allowed (not in List interface)
```

- `list.add()` and `list.get()` work because they are part of the `List` interface.
- `list.poll()` **won't compile** because `poll()` is not a method in the `List` interface, even though the actual object (`LinkedList`) implements it.

#### When Using `Queue`:
```java
Queue<String> queue = new LinkedList<>();
queue.add("Hello");  // Allowed (defined in Queue)
queue.poll();        // Allowed (defined in Queue)
queue.get(0);        // Not allowed (not in Queue interface)
```

- `queue.add()` and `queue.poll()` work because they are part of the `Queue` interface.
- `queue.get()` **won't compile** because `get()` is not a method in the `Queue` interface.

#### The Object is the Same, But the Methods You Can Call Depend on the Reference:
Both `list` and `queue` refer to the same underlying object (a `LinkedList`), but the **type of reference** (`List` or `Queue`) determines which methods are available at compile time.

### 3. **The Role of Interfaces**
In your example, you mentioned:
```java
public class LinkedList<E> implements Queue<E> {
    method1() {}
    method2() {}
}
```

This would only work if the class implements **only the `Queue` interface**. In such a case, it would only expose the methods defined in the `Queue` interface. But Java's `LinkedList` implements **multiple interfaces simultaneously**, which means the same class has methods defined by all the interfaces it implements (`List`, `Queue`, `Deque`, etc.).

### 4. **How Java Handles It**

Here's a simple breakdown of how it works:

- **LinkedList** implements all methods defined in the `List`, `Queue`, and `Deque` interfaces in the **same class**.
- The **reference type** (whether you use `List`, `Queue`, or `Deque`) determines which methods are available to you.
- The Java compiler checks the **reference type** (e.g., `List`, `Queue`) to ensure you only call methods that are part of that interface.

So, for instance:

```java
LinkedList<String> linkedList = new LinkedList<>();

List<String> listRef = linkedList;
Queue<String> queueRef = linkedList;

// Using listRef, which is of type List
listRef.add("A");    // Works
listRef.get(0);      // Works
listRef.poll();      // Compile-time error: poll() is not part of List

// Using queueRef, which is of type Queue
queueRef.add("B");   // Works
queueRef.poll();     // Works
queueRef.get(0);     // Compile-time error: get() is not part of Queue
```

Both `listRef` and `queueRef` refer to the **same object** (`linkedList`), but the methods available depend on how the object is **referenced**.

### 5. **Does a "Pure" LinkedList Exist?**

No, Java’s built-in `LinkedList` class does not have a "pure" version that exists independently of any interfaces. In Java, the `LinkedList` class is specifically designed to implement multiple interfaces (`List`, `Deque`, `Queue`), so it always includes methods from all of these interfaces.

However, you could create your own custom linked list class that **does not** implement any interfaces and only contains the functionality of a basic linked list (like adding, removing, traversing nodes). Then, if needed, you could create different classes that use this custom linked list to implement `Queue`, `Stack`, or other data structures via **composition** (as shown in the previous example).

### Conclusion:
- **`LinkedList` implements multiple interfaces** (`List`, `Deque`, `Queue`), and all methods from these interfaces are implemented in a **single class**.
- The **reference type** (`List`, `Queue`, `Deque`) determines which methods are available at **compile time**. This is achieved through **polymorphism**, where the same object can be referenced in different ways depending on the interface.
- Java doesn’t segregate methods into separate classes for different data structures; it implements all methods in the same class, and the interface type determines what methods are accessible.
- There’s no "pure" `LinkedList` in the built-in Java API that doesn’t implement any interfaces, but you can create your own custom linked list and use it with other data structures via composition.

Let me know if you'd like more details on any specific part!

# so compiler segregated valid object methods based on the return type of interface?

Yes, that's correct! The **Java compiler segregates** the methods that are available based on the **reference type** (which is determined by the interface or class type used in the variable declaration).

Here's how it works:

### 1. **Interface Reference Type Determines Available Methods**
In Java, when you create an object of a class (like `LinkedList`), that object has **all the methods** implemented by that class, including those from all interfaces it implements (`List`, `Queue`, `Deque`, etc.). However, the **reference type** (the variable type) determines **which methods** are visible and callable from that object.

If the reference type is:
- `List`, you'll only have access to the methods defined in the `List` interface.
- `Queue`, you'll only have access to the methods defined in the `Queue` interface.
- `Deque`, you'll only have access to the methods defined in the `Deque` interface.

Even though the underlying object (`LinkedList`) has methods for `List`, `Queue`, and `Deque`, the **reference type** limits what you can access.

### Example to Illustrate:
Here’s a simple example to make this clearer:

```java
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Example {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        // Reference the LinkedList as a List
        List<String> listRef = linkedList;
        listRef.add("A");  // Works (add() is part of List)
        listRef.get(0);    // Works (get() is part of List)
        // listRef.poll(); // Compile-time error: poll() is not part of List

        // Reference the LinkedList as a Queue
        Queue<String> queueRef = linkedList;
        queueRef.add("B");  // Works (add() is part of Queue)
        queueRef.poll();    // Works (poll() is part of Queue)
        // queueRef.get(0); // Compile-time error: get() is not part of Queue
    }
}
```

### 2. **Compile-Time Type Checking**
The **compiler** looks at the **reference type** (e.g., `List`, `Queue`, `Deque`) to determine which methods can be called. It doesn’t matter what the actual object type is (`LinkedList`, in this case); the compiler uses the reference type to enforce the method availability.

So, in the example:
- **`listRef`** is declared as a `List<String>`. The compiler checks the methods in the `List` interface. Even though `LinkedList` also implements `Queue`, you can only call methods from the `List` interface on `listRef`.
- **`queueRef`** is declared as a `Queue<String>`. The compiler checks the methods in the `Queue` interface. Even though `LinkedList` also implements `List`, you can only call methods from the `Queue` interface on `queueRef`.

### 3. **Polymorphism in Action**
This is a key feature of **polymorphism** in Java:
- You can refer to the same object (`LinkedList` in this case) using different interfaces (`List`, `Queue`, `Deque`).
- The methods you can call depend on **how you refer to the object**, not the actual object type.

### 4. **Why Does This Work?**
When a class implements multiple interfaces, Java allows the object to **"wear different hats"** depending on the context in which it's used.

For example, when a `LinkedList` object is referenced as a `List`, it wears the "List hat" and only exposes the methods defined in the `List` interface. When it’s referenced as a `Queue`, it wears the "Queue hat" and exposes only the methods from the `Queue` interface.

### 5. **Runtime Behavior**
At runtime, all the methods implemented by the class are still there, but you can only call the methods that the reference type allows.

For example:
- `List` reference: Only `List` methods are callable.
- `Queue` reference: Only `Queue` methods are callable.
- `Deque` reference: Both `Queue` and `Deque` methods are callable because `Deque` extends `Queue`.

### Example of What Happens at Compile Time:
```java
List<String> listRef = new LinkedList<>();
listRef.add("A");      // Allowed because add() is part of the List interface
listRef.get(0);        // Allowed because get() is part of the List interface
// listRef.poll();     // Compile-time error: poll() is not in the List interface

Queue<String> queueRef = new LinkedList<>();
queueRef.add("B");     // Allowed because add() is part of the Queue interface
queueRef.poll();       // Allowed because poll() is part of the Queue interface
// queueRef.get(0);    // Compile-time error: get() is not in the Queue interface
```

In both cases (`listRef` and `queueRef`), the actual object (`LinkedList`) contains all the methods for both interfaces. However, the **compiler restricts** the methods you can call based on the **reference type** (whether it's `List`, `Queue`, or `Deque`).

### 6. **How Is This Useful?**
This polymorphic behavior is useful because:
- It allows the same object to be used in multiple ways, depending on the context.
- It enforces **compile-time safety** by restricting access to methods that are appropriate for the reference type. This makes the code cleaner and easier to reason about.

### Conclusion:
Yes, you can say that the **compiler segregates the available methods** based on the **reference type** (e.g., `List`, `Queue`, `Deque`). The **underlying object** (`LinkedList`) has all the methods, but which methods you can call is determined by how you declare the variable (the reference type).

This is an example of **interface-based polymorphism** in Java, where the **type of the reference** (interface) defines which methods are accessible, even though the underlying object contains all the methods implemented by the class.

Let me know if you need any more clarification!

# hence this type checking is the way to reduce code and use polymorphism

Yes, exactly! This type checking at compile-time is a core feature of **polymorphism** in Java, and it's one of the main ways to **reduce code duplication** while enabling flexible and reusable designs. By using polymorphism, you can define **generic behaviors** (via interfaces or abstract classes) and then provide **specific implementations** without needing to rewrite the same logic for each new data structure or class.

Here’s how type checking and polymorphism work together to reduce code and improve flexibility:

### 1. **Polymorphism and Code Reusability**
Polymorphism allows you to write code that can operate on objects of different classes (or implementations) **using a common interface or superclass**. This means you can:
- Write more **generalized code** that works across different implementations.
- Reduce the need to write different methods for each implementation.

#### Example of Polymorphism:
Let's say you have a common interface `Shape` that different shape classes (e.g., `Circle`, `Square`, `Triangle`) implement. You can write code that operates on the `Shape` interface, regardless of the specific shape type.

```java
interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a square");
    }
}

public class Main {
    public static void main(String[] args) {
        Shape shape1 = new Circle();   // Polymorphism: treat Circle as Shape
        Shape shape2 = new Square();   // Polymorphism: treat Square as Shape

        shape1.draw();  // Calls Circle's draw method
        shape2.draw();  // Calls Square's draw method
    }
}
```

- **Common Interface (`Shape`)**: You define the behavior (`draw()`) in the `Shape` interface, and each class (`Circle`, `Square`) implements it differently.
- **Polymorphic Code**: In the `main()` method, you reference both `Circle` and `Square` as `Shape`. You can then call `draw()` without worrying about the specific shape type.
- **Reduced Code Duplication**: You don’t need separate logic for handling each shape; the interface allows you to treat them generically.

### 2. **Type Checking at Compile-Time**
Java’s compiler checks the **reference type** (like `Shape`, `List`, or `Queue`) to enforce **which methods** are available. This compile-time checking ensures:
- **Type safety**: You cannot call methods that don't exist for a given reference type. This prevents runtime errors related to calling undefined methods.
- **Code clarity**: By restricting the accessible methods based on the reference type, the code is easier to read and maintain.

#### Example with `List` and `LinkedList`:
```java
List<String> list = new LinkedList<>();
list.add("A");  // Allowed: add() is in the List interface
list.get(0);    // Allowed: get() is in the List interface
// list.poll(); // Compile-time error: poll() is not in the List interface
```

In this case:
- The compiler ensures that only methods defined in `List` can be called on the `list` reference, even though `LinkedList` implements both `List` and `Queue`.
- This **type checking at compile-time** prevents errors and encourages good design by limiting method access based on the interface contract.

### 3. **Code Flexibility with Polymorphism**
Polymorphism not only reduces code duplication but also makes the code more **flexible** and **extensible**:
- You can swap different implementations **without changing the client code**.
- You can extend the system by adding new implementations that follow the same interface, without modifying the existing logic.

#### Example: Swapping Implementations
```java
public class Main {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("A");
        list.add("B");
        processList(list);  // We can pass any List implementation

        // Later, we switch to ArrayList without changing the client code
        list = new ArrayList<>();
        list.add("X");
        list.add("Y");
        processList(list);  // processList still works with ArrayList
    }

    public static void processList(List<String> list) {
        for (String item : list) {
            System.out.println(item);
        }
    }
}
```

Here, the `processList()` method is written to accept any `List`. It doesn't care whether the `List` is a `LinkedList` or `ArrayList` because the behavior of `add()` and `get()` is defined in the `List` interface. You can swap implementations freely without changing the core logic, which greatly increases flexibility.

### 4. **Design Patterns That Leverage Polymorphism**
Polymorphism is the foundation of many design patterns, such as:
- **Factory Pattern**: Use polymorphism to return different object types that implement a common interface.
- **Strategy Pattern**: Swap between different algorithms or behaviors at runtime.
- **Decorator Pattern**: Wrap objects with additional behavior without modifying the underlying class.

By using interfaces or abstract classes and polymorphism, you can separate concerns, making your code more maintainable and easier to extend.

### 5. **Polymorphism Reduces Code Duplication**
Without polymorphism, you would end up writing different methods or logic for every class, even if they share similar behaviors. Polymorphism allows you to:
- **Abstract common behavior** into interfaces or base classes.
- Write **generic algorithms** that operate on the interface (or abstract class), not the specific implementation.

#### Without Polymorphism:
```java
public void drawCircle(Circle circle) {
    circle.draw();
}

public void drawSquare(Square square) {
    square.draw();
}
```

This leads to code duplication for every new shape you want to support. Compare that with using polymorphism:

#### With Polymorphism:
```java
public void drawShape(Shape shape) {
    shape.draw();
}
```

This version only needs **one method** to draw any shape, and new shapes can be added without modifying the method.

### Conclusion:

- **Yes**, type checking based on the reference type (such as an interface) is a key feature of polymorphism that **reduces code duplication** and improves flexibility.
- **Polymorphism** allows you to define common behaviors in interfaces and have specific implementations in different classes, enabling you to write generic, reusable code.
- **Compile-time type checking** ensures that only valid methods for the reference type are called, making the code safer and more maintainable.

This is why polymorphism is one of the cornerstones of **object-oriented programming** in Java. It helps in **code reuse**, **flexibility**, and **extensibility**. Let me know if you'd like to dive deeper into any specific aspect!