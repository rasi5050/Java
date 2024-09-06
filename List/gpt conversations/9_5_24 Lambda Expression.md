## what are lambda expressions?

**Lambda expressions** in Java, introduced in Java 8, provide a concise way to write **anonymous functions**—functions that do not have a name and can be treated as an instance of a functional interface (an interface with a single abstract method). Lambda expressions are primarily used to enable functional programming in Java, making it easier to write more expressive and concise code, especially when working with collections or performing operations like filtering, mapping, and reducing.

### What is a Lambda Expression?

A **lambda expression** is a short block of code that takes in parameters and returns a value. Lambda expressions are often used to implement functional interfaces in a more readable and less verbose way compared to anonymous classes.

#### Basic Syntax of a Lambda Expression

The syntax of a lambda expression is:

```java
(parameters) -> expression

// or

(parameters) -> { statements; }
```

- **Parameters**: The input parameters for the lambda expression, enclosed in parentheses. Parentheses are optional if there is only one parameter.
- **Arrow Token (`->`)**: Separates the parameters from the body of the lambda expression.
- **Expression or Block**: The body of the lambda expression, which can be a single expression or a block of statements. If it's a single expression, the result is implicitly returned; if it's a block, you use the `return` keyword to return a value.

### Examples of Lambda Expressions

1. **Lambda Expression with No Parameters:**

If the functional interface has a method with no parameters, you can use an empty parentheses `()`:

```java
Runnable runnable = () -> System.out.println("Hello, Lambda!");
new Thread(runnable).start();
```

- **Explanation:**
  - `Runnable` is a functional interface with a single method `run()`.
  - The lambda expression `() -> System.out.println("Hello, Lambda!")` provides the implementation for `run()`.

2. **Lambda Expression with One Parameter:**

If there is only one parameter, you don't need parentheses:

```java
Consumer<String> greeter = name -> System.out.println("Hello, " + name);
greeter.accept("Alice"); // Output: Hello, Alice
```

- **Explanation:**
  - `Consumer<String>` is a functional interface with a method `accept(String t)`.
  - The lambda expression `name -> System.out.println("Hello, " + name)` implements the `accept` method.

3. **Lambda Expression with Multiple Parameters:**

If there are multiple parameters, you use parentheses:

```java
Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
int result = comparator.compare(5, 10); // Output: -1 (5 is less than 10)
```

- **Explanation:**
  - `Comparator<Integer>` is a functional interface with a method `compare(Integer o1, Integer o2)`.
  - The lambda expression `(a, b) -> a.compareTo(b)` provides the implementation for `compare`.

4. **Lambda Expression with a Block of Code:**

If the body of the lambda expression contains multiple statements, you must enclose them in curly braces `{}` and use a return statement if necessary:

```java
Function<Integer, Integer> square = n -> {
    int result = n * n;
    return result;
};
System.out.println(square.apply(5)); // Output: 25
```

- **Explanation:**
  - `Function<Integer, Integer>` is a functional interface with a method `apply(T t)`.
  - The lambda expression `n -> { int result = n * n; return result; }` provides the implementation for `apply`.

### Functional Interfaces in Java

Lambda expressions are used in conjunction with **functional interfaces**—interfaces that have a single abstract method (often called SAM interfaces). Some commonly used functional interfaces in Java are:

- **`Runnable`**: Represents a task that takes no arguments and returns no result.
- **`Callable<V>`**: Represents a task that takes no arguments and returns a result.
- **`Comparator<T>`**: Represents a comparison between two objects.
- **`Consumer<T>`**: Represents an operation that takes a single input and returns no result.
- **`Function<T, R>`**: Represents a function that takes an input of type `T` and returns a result of type `R`.
- **`Predicate<T>`**: Represents a boolean-valued function of one argument.
- **`Supplier<T>`**: Represents a supplier of results (takes no arguments and returns a result).

Java provides many built-in functional interfaces in the `java.util.function` package.

### Lambda Expressions with Streams

Lambda expressions are often used with the **Java Streams API** to perform operations on collections in a more functional style.

#### Example: Filtering and Printing with Streams

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

// Use a stream and lambda expression to filter and print names
names.stream()
     .filter(name -> name.startsWith("A")) // Filters names that start with "A"
     .forEach(name -> System.out.println(name)); // Prints each filtered name
```

- **Explanation:**
  - `filter` takes a `Predicate<String>` functional interface, which is implemented using a lambda expression `name -> name.startsWith("A")`.
  - `forEach` takes a `Consumer<String>` functional interface, which is implemented using a lambda expression `name -> System.out.println(name)`.

### Key Points About Lambda Expressions

1. **Conciseness and Readability:**
   - Lambda expressions make your code more concise and readable by removing the need for boilerplate code, such as anonymous inner classes.

2. **Type Inference:**
   - The Java compiler can infer the type of the parameters based on the context, so you do not need to specify them explicitly in most cases.

3. **Functional Style:**
   - Lambda expressions support a functional programming style, making it easier to work with streams and perform operations like mapping, filtering, and reducing collections.

4. **Used with Functional Interfaces:**
   - Lambdas can only be used where a functional interface is expected. Java makes use of lambdas in libraries like the Java Collections Framework (e.g., `Comparator`, `Runnable`).

### Summary

Lambda expressions provide a powerful and concise way to implement functional interfaces in Java, enabling more expressive and readable code, especially when working with collections, streams, and functional-style programming patterns. They help reduce boilerplate code, improve code clarity, and encourage a more declarative style of programming.