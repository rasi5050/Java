The syntax `NoSuchElementException::new` is called a **method reference** in Java. It's a shorthand notation for referring to a method or constructor without actually invoking it. Let's break down what this syntax does and why it is used in the context of the `orElseThrow()` method.

### What is `NoSuchElementException::new`?

1. **Method Reference Syntax:**
   - `NoSuchElementException::new` is a **constructor reference** in Java. It's a specific type of method reference that points to the constructor of the `NoSuchElementException` class.
   - The `::` operator is used to reference a method or constructor without calling it. The syntax generally takes the form `ClassName::methodName` or `ClassName::new` for constructors.

2. **Usage with `orElseThrow()`:**
   - The `orElseThrow()` method of `Optional` takes a `Supplier` functional interface as an argument. A `Supplier` is a functional interface that represents a function that **takes no arguments and returns a value**.
   - `NoSuchElementException::new` is a method reference that fits the `Supplier` interface because it points to a constructor that takes no arguments and returns a new instance of `NoSuchElementException`.

### How `orElseThrow(NoSuchElementException::new)` Works

- **`orElseThrow()`**:
  - This method checks if the `Optional` has a value:
    - If the value is present, it returns that value.
    - If the value is absent (empty), it uses the provided `Supplier` to create and throw an exception.

- **`NoSuchElementException::new`**:
  - This part is a constructor reference. It's a shorthand way of saying "if the value is absent, throw a new `NoSuchElementException`."

#### The Full Equivalent of `orElseThrow(NoSuchElementException::new)`

To understand what `orElseThrow(NoSuchElementException::new)` does, let's rewrite it without using a method reference:

```java
return peek.orElseThrow(() -> new NoSuchElementException());
```

- Here, `() -> new NoSuchElementException()` is a **lambda expression** that creates a new instance of `NoSuchElementException` if the `Optional` is empty.
- The method reference `NoSuchElementException::new` is equivalent to the lambda expression `() -> new NoSuchElementException()`.

### When to Use Method References

Method references like `NoSuchElementException::new` are often used in Java to make the code more concise and readable. They are particularly useful when you want to pass a method or constructor as an argument to a higher-order function (a function that takes another function as an argument).

#### Types of Method References in Java

There are four types of method references in Java:

1. **Reference to a Static Method:**
   ```java
   // Reference to a static method
   Function<String, Integer> stringToInteger = Integer::parseInt;
   ```

2. **Reference to an Instance Method of a Particular Object:**
   ```java
   // Reference to an instance method of a particular object
   String text = "Hello";
   Supplier<Integer> lengthSupplier = text::length;
   ```

3. **Reference to an Instance Method of an Arbitrary Object of a Particular Type:**
   ```java
   // Reference to an instance method of an arbitrary object
   Function<String, Integer> stringLength = String::length;
   ```

4. **Reference to a Constructor:**
   ```java
   // Reference to a constructor
   Supplier<NoSuchElementException> exceptionSupplier = NoSuchElementException::new;
   ```

### Summary

- `NoSuchElementException::new` is a **constructor reference** that refers to the no-argument constructor of the `NoSuchElementException` class.
- It is used with `orElseThrow()` to create and throw an exception if the `Optional` is empty.
- Method references provide a concise and readable way to refer to methods or constructors, especially in functional programming contexts in Java. 

By using `NoSuchElementException::new` with `orElseThrow()`, you write more concise code while still achieving the desired functionality of throwing an exception when the `Optional` is empty.