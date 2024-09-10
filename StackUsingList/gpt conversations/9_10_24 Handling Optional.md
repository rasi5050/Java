## How to handle Optional?

To handle `Optional` in Java, you use various methods provided by the `Optional` class to safely manage the presence or absence of a value. The `Optional` class, introduced in Java 8, is designed to provide a type-safe way to deal with potentially null values and avoid the common `NullPointerException` errors.

### What is `Optional`?

An `Optional<T>` is a container object which may or may not contain a non-null value. It provides methods to:
- Check if a value is present.
- Retrieve the value if present.
- Provide a default value if the value is absent.
- Execute a block of code if the value is present.

### Common Methods to Handle `Optional`

Here are some of the key methods provided by the `Optional` class and how to use them:

#### 1. **`Optional.of(value)` and `Optional.ofNullable(value)`**

- **`Optional.of(value)`**: Creates an `Optional` containing the given non-null value.
  ```java
  Optional<String> nonEmptyOptional = Optional.of("Hello"); // Throws NullPointerException if null
  ```

- **`Optional.ofNullable(value)`**: Creates an `Optional` that may contain a null value.
  ```java
  Optional<String> nullableOptional = Optional.ofNullable(getValue()); // Allows null values
  ```

#### 2. **`isPresent()` and `isEmpty()`**

- **`isPresent()`**: Returns `true` if the value is present; otherwise, `false`.
  ```java
  if (nullableOptional.isPresent()) {
      System.out.println(nullableOptional.get());
  }
  ```

- **`isEmpty()`**: Returns `true` if the value is absent; otherwise, `false`.
  ```java
  if (nullableOptional.isEmpty()) {
      System.out.println("No value present");
  }
  ```

#### 3. **`ifPresent(Consumer<? super T> action)`**

- **`ifPresent()`**: Executes the given action if a value is present.
  ```java
  nullableOptional.ifPresent(value -> System.out.println("Value: " + value));
  ```

#### 4. **`get()`**

- **`get()`**: Returns the value if present; throws `NoSuchElementException` if no value is present.
  ```java
  String value = nonEmptyOptional.get(); // Use with caution, may throw exception if value is absent
  ```

- **Best Practice:** Avoid using `get()` without checking if a value is present. Use `orElse()`, `orElseGet()`, or `orElseThrow()` instead.

#### 5. **`orElse(T other)`**

- **`orElse()`**: Returns the value if present; otherwise, returns the specified default value.
  ```java
  String value = nullableOptional.orElse("Default Value");
  System.out.println(value);
  ```

#### 6. **`orElseGet(Supplier<? extends T> supplier)`**

- **`orElseGet()`**: Returns the value if present; otherwise, invokes the provided supplier and returns the result.
  ```java
  String value = nullableOptional.orElseGet(() -> "Computed Default Value");
  ```

#### 7. **`orElseThrow()` and `orElseThrow(Supplier<? extends X> exceptionSupplier)`**

- **`orElseThrow()`**: Returns the value if present; otherwise, throws `NoSuchElementException`.
  ```java
  String value = nonEmptyOptional.orElseThrow();
  ```

- **`orElseThrow(Supplier<? extends X> exceptionSupplier)`**: Returns the value if present; otherwise, throws an exception provided by the supplier.
  ```java
  String value = nullableOptional.orElseThrow(() -> new IllegalArgumentException("No value present"));
  ```

#### 8. **`map(Function<? super T, ? extends U> mapper)`**

- **`map()`**: Applies the provided mapping function to the value if present, and returns an `Optional` describing the result.
  ```java
  Optional<Integer> length = nullableOptional.map(String::length);
  ```

#### 9. **`flatMap(Function<? super T, Optional<U>> mapper)`**

- **`flatMap()`**: Similar to `map()`, but the mapping function returns an `Optional`. Useful for chaining operations that return `Optional`.
  ```java
  Optional<Integer> length = nullableOptional.flatMap(value -> Optional.of(value.length()));
  ```

#### 10. **`filter(Predicate<? super T> predicate)`**

- **`filter()`**: Returns an `Optional` containing the value if it matches the provided predicate; otherwise, returns an empty `Optional`.
  ```java
  Optional<String> filteredOptional = nullableOptional.filter(value -> value.startsWith("H"));
  ```

### Examples of Handling `Optional`

Here's how you can handle `Optional` values effectively:

#### Example 1: Avoiding Null Checks

```java
Optional<String> optionalValue = Optional.ofNullable(getValue());

String result = optionalValue
        .filter(value -> value.startsWith("H"))
        .map(String::toUpperCase)
        .orElse("DEFAULT VALUE");

System.out.println(result);
```

- This example uses `filter()` to check if the value starts with `"H"`, `map()` to transform it to uppercase, and `orElse()` to provide a default value if the `Optional` is empty.

#### Example 2: Executing Code If Present

```java
optionalValue.ifPresent(value -> System.out.println("Found: " + value));
```

- This example prints the value only if it is present, avoiding the need for an explicit null check.

#### Example 3: Throwing an Exception If Absent

```java
String value = optionalValue.orElseThrow(() -> new IllegalArgumentException("No value present"));
```

- This example throws an exception if the value is not present.

### When to Use `Optional`

- **Return Type for Methods:** Use `Optional` as a return type for methods that may not return a value. This signals to the caller that a value may be absent and encourages them to handle that case.
- **Avoid Null Pointer Exceptions:** Use `Optional` to handle potentially null values without risking `NullPointerException`.
- **Avoid Using `Optional` for:**
  - **Fields or Method Parameters:** Using `Optional` for fields or parameters can lead to unnecessary complexity. Stick to using it for return types.

### Summary

- **`Optional`** is a container for potentially absent values, providing a safer alternative to using `null`.
- Use methods like `orElse()`, `ifPresent()`, `map()`, and `filter()` to handle `Optional` values effectively.
- Avoid directly using `get()` unless you are certain the `Optional` is not empty, as it may throw an exception.

By using `Optional`, you can write more robust, readable, and maintainable Java code that gracefully handles the absence of values.