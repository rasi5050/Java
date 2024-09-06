## what is Optional? why use it?
`Optional` is a container class in Java introduced in Java 8 that is used to represent a value that may or may not be present. It is a way to avoid dealing directly with `null` values and helps in writing cleaner, more readable, and safer code by explicitly indicating that a value might be absent.

### What is `Optional`?

`Optional<T>` is a generic class that wraps a value of type `T` (or no value at all) and provides methods to safely access that value or handle its absence. Instead of returning `null` when a value is missing or not found, you can return an `Optional` that either contains a value or is empty. 

### Key Benefits of Using `Optional`

1. **Avoids `NullPointerException`:**
   - `Optional` helps prevent `NullPointerException` by providing methods to handle the absence of values safely.
   
2. **Explicitly Conveys Absence of Value:**
   - By returning an `Optional`, you explicitly indicate that a value may or may not be present, making your code easier to understand and reason about.

3. **Encourages Proper Handling of Missing Values:**
   - The use of `Optional` encourages developers to handle the possibility of an absent value, reducing the likelihood of runtime errors.

4. **Supports Functional Programming Style:**
   - `Optional` provides methods like `map`, `filter`, `flatMap`, `ifPresent`, and `orElse`, enabling a more functional programming style.

### Common Methods of `Optional`

- **`Optional.empty()`**: Returns an empty `Optional` instance, representing a missing value.
- **`Optional.of(T value)`**: Creates an `Optional` containing the specified non-null value. Throws `NullPointerException` if the value is `null`.
- **`Optional.ofNullable(T value)`**: Creates an `Optional` that can contain a non-null value or be empty if the provided value is `null`.
- **`isPresent()`**: Returns `true` if the `Optional` contains a value; `false` otherwise.
- **`get()`**: Returns the contained value if present; throws `NoSuchElementException` if the `Optional` is empty.
- **`ifPresent(Consumer<? super T> consumer)`**: Executes the given action if a value is present.
- **`orElse(T other)`**: Returns the contained value if present; otherwise, returns the provided default value.
- **`orElseGet(Supplier<? extends T> supplier)`**: Returns the contained value if present; otherwise, returns the result produced by the supplier.
- **`orElseThrow(Supplier<? extends X> exceptionSupplier)`**: Returns the contained value if present; otherwise, throws an exception produced by the supplier.

### Example Usage of `Optional`

Here is an example that shows how `Optional` can be used to handle the absence of a value:

```java
public Optional<String> findUserNameById(int id) {
    if (id == 1) {
        return Optional.of("Alice"); // User found
    } else {
        return Optional.empty(); // No user found
    }
}

// Usage
Optional<String> userName = findUserNameById(2);
userName.ifPresentOrElse(
    name -> System.out.println("User found: " + name),
    () -> System.out.println("User not found")
);
```

### Explanation

1. **`findUserNameById` Method:**
   - This method returns an `Optional<String>` representing the username associated with the given ID.
   - If the user is found (`id == 1`), it returns `Optional.of("Alice")`, an `Optional` containing the username "Alice".
   - If the user is not found, it returns `Optional.empty()`, an empty `Optional` instance.

2. **Usage of `Optional`:**
   - The `ifPresentOrElse` method is used to handle both cases—when the value is present (`name -> ...`) and when it is absent (`() -> ...`).

### Why Use `Optional`?

Using `Optional` improves code quality by:

- Reducing the risk of `NullPointerException`.
- Making the code more explicit about the possibility of absent values.
- Encouraging proper handling of missing values.
- Supporting a functional programming style that can make the code more concise and expressive.

### Summary

`Optional` is a useful class in Java for dealing with potentially absent values more safely and clearly than using `null`. It provides a set of methods that help handle the absence of values in a functional and expressive way, promoting more robust and readable code.