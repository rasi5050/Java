## the line of reasoning was that exceptions should be thrown not returned, 
what is the problem with below line

    return peek.isPresent() ? peek.get() : new NoSuchElementException();

this also return value if its present, else it returns exception


The line you provided:

```java
return peek.isPresent() ? peek.get() : new NoSuchElementException();
```

is incorrect in Java because of the following reasons:

1. **Mismatched Return Types:**
    - The method is expected to return a value of type `T` (the type parameter of your stack), but the expression `new NoSuchElementException()` creates and returns an **exception object**, which is not of type `T`.
    - This causes a **type mismatch** error because an exception is not a valid return value for a method that is expected to return a value of type `T`.

2. **Exceptions Should Be Thrown, Not Returned:**
    - In Java, exceptions are meant to be **thrown** to indicate an error or exceptional situation, not returned as values.
    - When you use `new NoSuchElementException()`, it should be used with a `throw` statement to indicate an exceptional condition, like this:
   ```java
   throw new NoSuchElementException();
   ```
    - Returning an exception object directly as a value does not trigger the exception-handling mechanism in Java. It would simply return an instance of the exception object, which is not useful and leads to confusion.

### Correct Way to Handle This

To properly handle this situation, you should **throw the exception** if the `Optional` is empty. You can use the `orElseThrow()` method of `Optional` to achieve this cleanly:

#### Corrected Code

```java
public T peek() {
    Optional<T> peek = stack.get(stack.size() - 1);

    // If the value is present, return it; otherwise, throw an exception
    return peek.orElseThrow(NoSuchElementException::new);
}
```

#### Explanation of the Corrected Code

- **`peek.orElseThrow(NoSuchElementException::new)`**:
    - This expression checks whether the `Optional` `peek` contains a value.
    - If `peek` contains a value, it returns that value (`T`).
    - If `peek` is empty (i.e., no value is present), it **throws** a `NoSuchElementException` by invoking its constructor (`NoSuchElementException::new`).

### Summary of the Problem

- The original line `return peek.isPresent() ? peek.get() : new NoSuchElementException();` incorrectly tries to **return** an exception as a value.
- The corrected approach using `orElseThrow()` correctly returns the value if present or **throws** an exception if absent.
- In Java, you **throw** exceptions to handle errors, not return them as normal values.


## then what if i write as

    return peek.isPresent() ? peek.get() : throw new NoSuchElementException();

The code snippet you provided:

```java
return peek.isPresent() ? peek.get() : throw new NoSuchElementException();
```

is **not valid** Java syntax. Java does not support the use of a `throw` statement in a ternary conditional (`?:`) expression like this. The `throw` statement must be used on its own line outside of the ternary expression.

### Why This Code is Invalid

1. **`throw` is a Statement, Not an Expression:**
    - In Java, `throw` is a **statement** that indicates an exception should be thrown. It is not an **expression** that can be evaluated and returned as a value.
    - The ternary operator (`?:`) expects an expression for both the "true" and "false" branches. Since `throw` is not an expression, using it inside a ternary operator will result in a syntax error.

2. **Syntax Error:**
    - The code snippet will result in a compilation error because the Java language syntax does not allow `throw` to be used in that context.

### Correct Way to Handle This Situation

If you want to throw an exception if the value is not present, you should use an `if` statement or use the `orElseThrow` method provided by `Optional`. Here are the correct ways to handle it:

#### Using `if` Statement

```java
public T peek() {
    Optional<T> peek = stack.get(stack.size() - 1);
    
    if (peek.isPresent()) {
        return peek.get();
    } else {
        throw new NoSuchElementException();
    }
}
```

- **Explanation:**
    - This code checks if `peek` has a value using `isPresent()`.
    - If a value is present, it returns that value using `peek.get()`.
    - If no value is present, it throws a `NoSuchElementException`.

#### Using `orElseThrow`

You can use the `orElseThrow` method, which is a more concise way to handle the same situation:

```java
public T peek() {
    Optional<T> peek = stack.get(stack.size() - 1);

    // Return the value if present; otherwise, throw NoSuchElementException
    return peek.orElseThrow(NoSuchElementException::new);
}
```

- **Explanation:**
    - The `orElseThrow` method is designed to return the value if present or throw an exception provided by the `Supplier` (`NoSuchElementException::new` in this case) if no value is present.
    - This is the most idiomatic and concise way to handle the situation in Java.

### Summary

- The expression `throw new NoSuchElementException()` cannot be used in a ternary operator because `throw` is a statement, not an expression.
- To handle the case where an `Optional` is empty, use either an `if` statement to throw the exception or use `orElseThrow` to achieve the same in a concise way.
- Both approaches are valid, but `orElseThrow` is more idiomatic and takes advantage of Java's functional style introduced in Java 8.