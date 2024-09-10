```
public T peek() {

    Optional<T> peek = stack.get(stack.size()-1);
        return peek.isPresent() ? peek.get() : new NoSuchElementException();
}

```


Your `peek` method has a small issue. The code is attempting to return a `new NoSuchElementException()` directly, which is incorrect because the method signature specifies that it returns a value of type `T`, not an exception. Instead, you should throw the `NoSuchElementException` when the `Optional` is empty.

Here's the corrected version:

### Corrected `peek` Method

```java
public T peek() {
    Optional<T> peek = stack.get(stack.size() - 1);

    // If the value is present, return it; otherwise, throw an exception
    return peek.orElseThrow(NoSuchElementException::new);
}
```

### Explanation

1. **Using `orElseThrow`:**
   - The `orElseThrow()` method checks if the `Optional` contains a value. If it does, it returns the value. If it doesn't, it throws an exception, in this case, a `NoSuchElementException`.

2. **Throwing the Exception Correctly:**
   - You should **throw** an exception using `orElseThrow()` instead of attempting to return the exception object. In Java, exceptions should be thrown when a method encounters an unexpected situation, not returned like a regular value.

### Additional Considerations

- **`stack.get()` Method:**
  Make sure your `stack.get()` method returns an `Optional<T>` as expected. Otherwise, this code will not work correctly.

- **`stack.size() - 1`:**
  Ensure that the calculation `stack.size() - 1` properly reflects the index of the top element in the stack. If your stack is empty (`size() == 0`), it will correctly handle the `NoSuchElementException`.

### Example Usage

If the stack contains elements, `peek()` will return the last element added without removing it

## there is another doubt i have, the return type is supposed to be T right? so how is the return T when an exception is returned istead of the value?

Great question! Let's clarify how the return type works when dealing with exceptions in Java.

### Understanding Return Types and Exceptions in Java

1. **Return Type vs. Exceptions:**
    - The return type of a method (e.g., `T` in your example) specifies the type of value that the method is expected to return under **normal circumstances**.
    - However, if a method encounters an error or exceptional situation, it can **throw an exception** instead of returning a value. When an exception is thrown, the normal flow of the method is interrupted, and the method does not return any value. Instead, control is transferred to the nearest exception handler that can catch that exception.

2. **`orElseThrow()` Method in `Optional`:**
    - The `orElseThrow()` method of `Optional` has a return type of `T` (the generic type of the `Optional` object). This method is used to either return the value contained in the `Optional` or to throw an exception if the value is not present.
    - The `orElseThrow(Supplier<? extends X> exceptionSupplier)` method takes a `Supplier` of an exception. If the `Optional` contains a value, it returns that value (type `T`). If the `Optional` is empty, it **throws** the exception provided by the `Supplier`.

### How Does Throwing an Exception Work with a Generic Return Type?

When you use `orElseThrow()`, the code works like this:

```java
Optional<T> optional = ...;
T value = optional.orElseThrow(NoSuchElementException::new);
```

#### Scenario 1: Value is Present

- If `optional` contains a value, the method returns that value of type `T`.
- **Example:**
    - If `optional` contains a `String` value `"Hello"`, `optional.orElseThrow(...)` returns `"Hello"`, and the return type `T` is `String`.

#### Scenario 2: Value is Absent (Empty)

- If `optional` is empty, the `orElseThrow()` method will **throw an exception** instead of returning a value.
- The exception interrupts the normal flow of the program, so the method does **not** return any value; control is transferred to the nearest `catch` block or to the caller if no `catch` block exists.

### Why Throwing an Exception is Different from Returning a Value

- **When an Exception is Thrown:**
    - Throwing an exception means that the method **exits prematurely** and does **not return** any value.
    - Even though the method signature indicates that it returns `T`, an exception does not conflict with this signature because throwing an exception is an alternative flow that bypasses the return statement.

### Example to Illustrate

Consider the following example:

```java
public T peek() {
    Optional<T> peek = stack.get(stack.size() - 1);

    // If the value is present, return it; otherwise, throw an exception
    return peek.orElseThrow(NoSuchElementException::new);
}
```

- **If the Stack is Not Empty:**
    - `peek` contains a value, and `orElseThrow()` returns that value of type `T`.
    - The method completes normally and returns a value of type `T`.

- **If the Stack is Empty:**
    - `peek` is empty, and `orElseThrow()` calls `NoSuchElementException::new` to create a new `NoSuchElementException` and **throws** it.
    - The method does not complete normally. Instead, it throws the exception, and no value is returned.
    - The calling code must handle this exception either by catching it or propagating it further.

### Conclusion

- The return type `T` is honored when the method completes normally and returns a value.
- If an exception is thrown, the method does **not** return a value, and the control flow is transferred to an exception handler. Throwing an exception is an alternative to returning a value and does not violate the method's return type contract.