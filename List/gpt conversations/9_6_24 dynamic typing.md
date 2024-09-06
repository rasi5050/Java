## What does dynamic typing means. Also include examples from Haskell and python 
**Dynamic typing** is a feature of some programming languages where the type of a variable is determined at **runtime** rather than at **compile-time**. In dynamically-typed languages, you do not need to explicitly declare the type of a variable when you define it; the type is inferred from the value assigned to it and can change as the program executes.

### Dynamic Typing Explained

1. **Type Determination at Runtime:**
   - In dynamically-typed languages, types are associated with values rather than variables. This means a variable can hold any type of value, and its type can change during execution.
   - Type errors in dynamically-typed languages are typically discovered only when the erroneous code is executed, not at compile time.

2. **Flexible and Less Verbose:**
   - Dynamic typing allows for more flexibility in writing code because you don't have to declare variable types explicitly. This can lead to less verbose code but at the cost of reduced type safety, since errors related to types will only surface at runtime.

3. **Examples of Dynamically-Typed Languages:**
   - Languages like **Python**, **JavaScript**, and **Ruby** are dynamically typed, whereas languages like **Java**, **C++**, and **Haskell** are statically typed.

### Examples of Dynamic Typing in Python

Python is a dynamically-typed language, so you don't need to declare variable types. Here are some examples:

```python
# Example 1: Assigning different types to the same variable
x = 5          # `x` is an integer
print(type(x)) # Output: <class 'int'>

x = "Hello"    # Now, `x` is a string
print(type(x)) # Output: <class 'str'>

x = [1, 2, 3]  # Now, `x` is a list
print(type(x)) # Output: <class 'list'>
```

- In the above example, the variable `x` can hold an integer, a string, or a list at different points in time, and its type is inferred from the value assigned to it.

#### Dynamic Typing with Function Arguments

In Python, functions are also dynamically typed. You can pass arguments of any type to a function without having to declare the type beforehand:

```python
def print_value(value):
    print(value)

print_value(5)       # Pass an integer
print_value("Hello") # Pass a string
print_value([1, 2])  # Pass a list
```

- The `print_value` function can accept any type of argument (`int`, `str`, `list`, etc.), and Python dynamically handles the type at runtime.

### Statically-Typed Language: Haskell

Haskell, unlike Python, is a **statically-typed** language, which means the type of every variable or expression is known at compile time. The Haskell compiler will infer types, but the types remain fixed and cannot change once inferred.

#### Examples of Static Typing in Haskell

In Haskell, you must either declare the type explicitly or let the compiler infer it, but once a type is assigned, it cannot change.

```haskell
-- Example 1: Explicitly declaring types
x :: Int
x = 5           -- `x` is an integer

y :: String
y = "Hello"     -- `y` is a string

-- Example 2: Function with typed arguments
add :: Int -> Int -> Int
add a b = a + b  -- Both `a` and `b` must be integers

main = do
  print (add 5 3)   -- Valid: both arguments are integers
  -- print (add 5 "Hello") -- Invalid: will cause a compile-time error
```

- In the above Haskell example:
  - The variable `x` is explicitly declared as `Int` and can only hold integer values.
  - The variable `y` is explicitly declared as `String` and can only hold string values.
  - The function `add` is explicitly declared to accept two integers and return an integer. If you try to pass a string to `add`, the Haskell compiler will raise an error at compile time.

### Key Differences Between Dynamic and Static Typing

1. **Type Checking:**
   - **Dynamic Typing:** Type checking is done at runtime. The types of variables can change, and errors related to types are only caught when the relevant line of code is executed.
   - **Static Typing:** Type checking is done at compile time. The types of variables are fixed after they are declared or inferred, and type-related errors are caught before the program runs.

2. **Flexibility vs. Safety:**
   - **Dynamic Typing:** More flexibility since you can change types on the fly, but this can lead to runtime errors if not handled carefully.
   - **Static Typing:** Less flexibility since types are fixed, but it offers greater type safety and helps prevent errors at compile time.

3. **Performance:**
   - **Dynamic Typing:** Can be slower because type checks are performed at runtime.
   - **Static Typing:** Often results in faster performance because types are known at compile time, allowing the compiler to optimize the code.

### Comparison of Haskell and Python in Terms of Typing

- **Python**: Allows variables to change types dynamically, enabling more flexibility but potentially leading to runtime errors if not managed correctly.
- **Haskell**: Requires that types are known at compile time, which means fewer surprises at runtime but requires you to think carefully about the types your program uses upfront.

### Summary

- **Dynamic Typing** allows variables to change their types at runtime, offering flexibility but potentially causing runtime errors.
- **Static Typing** ensures that variable types are determined at compile time, providing safety and performance benefits.
- In dynamically-typed languages like **Python**, type checks are done at runtime, allowing for more flexible code. In statically-typed languages like **Haskell**, types are checked at compile time, catching errors early and ensuring type safety.