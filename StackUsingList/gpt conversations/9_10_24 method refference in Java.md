```
public T peek() {

    Optional<T> peek = stack.get(stack.size()-1);
        return peek.isPresent() ? peek.get() : new NoSuchElementException();
}
```
## in this code what is NoSuchElementException::new

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


## so what happends if i simply pass the same of the constructor without ::new, isnt it passing. refference?

If you simply pass the name of the constructor class (like `NoSuchElementException`) without using `::new`, it will **not** work as expected because you are not providing a **constructor reference** in a form that Java understands. Let's clarify this with an example and explain the difference.

### Understanding Method References and Constructors

1. **Method References and Constructors in Java:**
    - **`NoSuchElementException::new`** is a **constructor reference** that tells Java, "When you need an instance of `NoSuchElementException`, use its no-argument constructor to create one."
    - The syntax `ClassName::new` explicitly refers to the constructor method for that class, providing a way to create a new instance whenever required.

2. **Why Simply Passing the Class Name Doesn't Work:**
    - Simply writing `NoSuchElementException` without `::new` is not a valid way to refer to a constructor in Java. Java expects a `Supplier` (a functional interface with a `get()` method that returns an object) in this context, and `NoSuchElementException` alone does not satisfy that requirement.

### What Happens if You Just Pass the Constructor Class Name?

If you pass just the class name (`NoSuchElementException`) without `::new`, the code will not compile. The compiler will raise an error because:

- **`NoSuchElementException`** on its own is just the name of the class, not a reference to a constructor or method.
- The `orElseThrow()` method requires a `Supplier` (a functional interface that produces a result without taking any input), and passing a class name without a method reference (`::new`) or a lambda expression does not fulfill this requirement.

### Example to Illustrate the Difference

Let's consider the example of using `orElseThrow()` with and without a method reference:

#### Correct Usage with Method Reference

```java
return peek.orElseThrow(NoSuchElementException::new);
```

- **Explanation:**
    - `NoSuchElementException::new` is a reference to the no-argument constructor of `NoSuchElementException`.
    - This correctly matches the `Supplier<? extends Throwable>` expected by `orElseThrow()`.

#### Incorrect Usage Without Method Reference

```java
return peek.orElseThrow(NoSuchElementException); // This will not compile
```

- **Why This Fails:**
    - `NoSuchElementException` alone does not tell the compiler how to create an instance of the exception.
    - It is just the name of the class, not a reference to the constructor.
    - The compiler expects a `Supplier` (like a method reference `NoSuchElementException::new` or a lambda expression `() -> new NoSuchElementException()`), but it receives a class name instead, leading to a compilation error.

### Alternative to Method Reference

If you're not using method references, you could achieve the same functionality using a **lambda expression**:

```java
return peek.orElseThrow(() -> new NoSuchElementException());
```

- This lambda expression explicitly creates a new instance of `NoSuchElementException` when called. It serves the same purpose as `NoSuchElementException::new` but is written in a slightly longer form.

### Summary

- **Method references** like `NoSuchElementException::new` provide a concise way to reference constructors or methods.
- Simply passing the class name (`NoSuchElementException`) does not work because it does not provide a valid reference to a constructor or satisfy the `Supplier` requirement.
- Use `::new` to reference a constructor or a lambda expression (like `() -> new NoSuchElementException()`) to provide the required supplier functionality.

## i understand this is method refference

NoSuchElementException::new

so can i write

CustomClass::itsMethod

Yes, you can write `CustomClass::itsMethod`, and it is called a **method reference** in Java. This allows you to reference an existing method of a class and pass it around as if it were a function, without invoking it at that moment. This is especially useful in functional programming contexts, such as when working with streams or passing methods as arguments to other functions.

### Types of Method References in Java

There are four main types of method references in Java:

1. **Reference to a Static Method:**
    - Syntax: `ClassName::staticMethodName`
    - Example: `Math::max`

2. **Reference to an Instance Method of a Particular Object:**
    - Syntax: `instance::instanceMethodName`
    - Example: `myObject::instanceMethod`

3. **Reference to an Instance Method of an Arbitrary Object of a Particular Type:**
    - Syntax: `ClassName::instanceMethodName`
    - Example: `String::toLowerCase`

4. **Reference to a Constructor:**
    - Syntax: `ClassName::new`
    - Example: `ArrayList::new`

### Example of `CustomClass::itsMethod`

Let's assume you have a custom class `CustomClass` with a method `itsMethod`.

#### Example with a Static Method Reference

```java
public class CustomClass {
    public static void itsMethod() {
        System.out.println("CustomClass::itsMethod was called");
    }
}

public class Example {
    public static void main(String[] args) {
        // Static method reference
        Runnable ref = CustomClass::itsMethod;
        ref.run(); // Output: CustomClass::itsMethod was called
    }
}
```

- **Explanation:**
    - `CustomClass::itsMethod` is a reference to the static method `itsMethod` of `CustomClass`.
    - The method reference is assigned to a `Runnable` because `Runnable`'s `run()` method matches the signature of `itsMethod` (no arguments, no return value).
    - When `ref.run()` is called, it invokes `itsMethod`.

#### Example with an Instance Method Reference

```java
public class CustomClass {
    public void itsMethod() {
        System.out.println("Instance method was called");
    }
}

public class Example {
    public static void main(String[] args) {
        CustomClass customObject = new CustomClass();

        // Instance method reference
        Runnable ref = customObject::itsMethod;
        ref.run(); // Output: Instance method was called
    }
}
```

- **Explanation:**
    - `customObject::itsMethod` is a reference to the instance method `itsMethod` of a specific `CustomClass` instance (`customObject`).
    - This reference is assigned to a `Runnable`, which can be executed by calling `run()`.

#### Example with an Instance Method of an Arbitrary Object of a Particular Type

```java
import java.util.function.Function;

public class Example {
    public static void main(String[] args) {
        Function<String, Integer> lengthFunction = String::length;
        
        int length = lengthFunction.apply("Hello");
        System.out.println(length); // Output: 5
    }
}
```

- **Explanation:**
    - `String::length` is a reference to the `length` instance method of the `String` class.
    - This method reference is used with a `Function` that takes a `String` and returns an `Integer` (the length of the string).

### When to Use Method References

- **Simplifying Lambdas:**
    - Method references can simplify lambda expressions. If you find yourself writing a lambda that merely calls a method, consider replacing it with a method reference.

- **Functional Interfaces:**
    - Method references work with functional interfaces (interfaces with a single abstract method). For example, `Runnable`, `Function`, `Consumer`, `Supplier`, etc.

- **Code Readability:**
    - Method references can make your code more concise and readable, especially when used in streams or other functional programming constructs.

### Summary

- Yes, you can write `CustomClass::itsMethod` as a method reference in Java.
- Method references are used to pass around and invoke methods without executing them immediately.
- Depending on whether the method is static or instance, and whether it's specific to an object or applicable to all instances of a class, you can use different forms of method references.
- Method references are a powerful tool in Java for making functional code more readable and concise.