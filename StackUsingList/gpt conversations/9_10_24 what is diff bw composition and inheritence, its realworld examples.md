## what is composition and inheritance? what is diff? when to use which? what is a realworld analogy for each?

**Composition** and **inheritance** are two fundamental concepts in object-oriented programming (OOP) that describe relationships between classes and objects. Both are used to promote code reuse, but they achieve this in different ways and have different implications for the design and flexibility of your code.

### What is Composition?

**Composition** is a design principle where a class is composed of one or more objects from other classes, meaning it contains references to other objects as its members. This is also known as a "has-a" relationship.

- **Definition:**  
  Composition means that a class is made up of one or more other classes, rather than inheriting from them. It allows objects to be composed of other objects, creating a "part-of" or "has-a" relationship.

- **Real-World Analogy:**  
  Think of a **Car**. A car is composed of several components, such as an **Engine**, **Wheels**, and **Seats**. The car does not inherit from the engine; rather, it has an engine as a part of its overall structure. If the engine is replaced, the car remains the same.

- **Example in Java:**
  ```java
  class Engine {
      // Engine-related methods and properties
  }

  class Car {
      private Engine engine; // Car has an Engine

      public Car() {
          this.engine = new Engine();
      }

      // Car-related methods
  }
  ```

### What is Inheritance?

**Inheritance** is a design principle where a new class (called a subclass or derived class) is created based on an existing class (called a superclass or base class). This new class inherits attributes and behaviors (methods) from the parent class, allowing for code reuse and establishing an "is-a" relationship.

- **Definition:**  
  Inheritance allows a class to inherit fields and methods from another class, creating a hierarchical relationship. It represents an "is-a" relationship where the child class is a type of the parent class.

- **Real-World Analogy:**  
  Think of an **Animal** class. You might have a **Dog** class that inherits from **Animal** because a dog **is an** animal. The dog inherits characteristics (like `breathing`, `eating`, etc.) from the animal class but might also have unique behaviors (`bark`, `wagTail`, etc.).

- **Example in Java:**
  ```java
  class Animal {
      public void eat() {
          System.out.println("Animal is eating");
      }
  }

  class Dog extends Animal { // Dog is an Animal
      public void bark() {
          System.out.println("Dog is barking");
      }
  }
  ```

### Key Differences Between Composition and Inheritance

| Feature                  | Composition                                           | Inheritance                                              |
|--------------------------|-------------------------------------------------------|----------------------------------------------------------|
| **Relationship Type**    | "Has-a" relationship                                   | "Is-a" relationship                                       |
| **Coupling**             | Looser coupling; components can be replaced easily     | Tighter coupling; subclasses are dependent on superclasses|
| **Flexibility**          | More flexible; changes in component classes don't affect the composed class | Less flexible; changes in the superclass affect all subclasses |
| **Code Reuse**           | Reuse by containing instances of other classes         | Reuse by inheriting methods and fields from a superclass  |
| **Extensibility**        | Easier to extend with new behavior by adding components| Harder to extend; adding new behavior might require changes in the base class hierarchy |
| **Design Complexity**    | Easier to understand and maintain due to clear separation | Can become complex with deep inheritance hierarchies     |

### When to Use Composition vs. Inheritance

#### Use **Composition** When:

1. **Behavioral Flexibility:**
   - You want your classes to be more flexible and allow behaviors to change at runtime by swapping components.
   
2. **Encapsulation:**
   - You want to encapsulate the implementation details and keep classes loosely coupled, promoting better modular design.
   
3. **Avoiding Inheritance Pitfalls:**
   - You want to avoid the potential pitfalls of deep inheritance hierarchies (such as tight coupling and reduced flexibility).

4. **Has-a Relationship:**
   - The relationship between classes is best described as "has-a" rather than "is-a." For example, a car **has-a** engine, but it is not an engine.

#### Use **Inheritance** When:

1. **Clear "Is-a" Relationship:**
   - The relationship between the classes is naturally hierarchical and represents an "is-a" relationship (e.g., `Dog` is an `Animal`).

2. **Code Reuse and Common Functionality:**
   - You want to reuse code where subclasses share common methods and properties provided by a superclass.

3. **Polymorphism:**
   - You need polymorphic behavior where different subclasses are treated uniformly through their common superclass (e.g., a method accepting an `Animal` can work with any subclass like `Dog` or `Cat`).

4. **Simplicity and Clarity:**
   - The class hierarchy is simple and unlikely to change frequently, making inheritance a straightforward and clear choice.

### Real-World Analogy for Each

#### **Composition: The Computer Example**

- **Computer** as a class:
  - A computer **has** a motherboard, CPU, RAM, and storage. These components are distinct objects (classes) that can be combined to form a computer.
  - If you want to upgrade the RAM, you can simply replace the RAM object without changing the entire computer object. This is a **composition** example, where the computer is composed of different components.

#### **Inheritance: The Vehicle Example**

- **Vehicle** as a class:
  - You might have a base class `Vehicle` and derived classes like `Car`, `Bike`, and `Truck`. Here, `Car` is a type of `Vehicle` and inherits common properties and methods, like `start()`, `stop()`, and `move()`.
  - This is an **inheritance** example where the `Car` class reuses the common behavior of the `Vehicle` class and also extends it with specific methods and properties.

### Summary

- **Composition** ("has-a"): Use it when you want to build complex objects from simpler ones, and when flexibility, loose coupling, and encapsulation are essential.
- **Inheritance** ("is-a"): Use it when you have a natural hierarchy, need polymorphic behavior, and want to reuse code among closely related classes.

Both are valuable tools in object-oriented design, and choosing between them depends on the specific problem and design requirements.


## in the arugument for composition and inheritance you made the below argument 

compoisition: Flexibility	More flexible; changes in component classes don't affect the  composed class	

inheritance: Less flexible; changes in the superclass affect all subclasses 

however 
say the code using compoistion is 
``` 
public class Stack<T>{
    private final List<T> stack;
    public Stack() {
        stack = new List<>();
    }

    public void push(T element) {
        stack.add(element);
    }
``` 
if i chance the orginal implementation of add() in List class, it also affects stack right? hence i would claim inheritance and composition has the effect if parent methods  are changed

______

You raise a good point! Indeed, both **composition** and **inheritance** can be affected when the underlying class or method they depend on changes. However, there are still important differences in how these changes propagate and impact the flexibility of your code. Let's clarify this with your example and explain why composition is still generally considered more flexible than inheritance.

### Key Differences Between Composition and Inheritance Regarding Flexibility

1. **Inheritance:**
   - Inheritance creates a strong relationship between the subclass and the superclass.
   - When a subclass inherits from a superclass, it automatically inherits all its fields and methods. If the superclass changes (e.g., its methods or behavior are modified), these changes **directly impact** all subclasses.
   - **Less Flexibility:** Changes in the superclass can lead to unintended consequences for all subclasses, even if they don't use or depend on the modified behavior. You often have less control over which changes are adopted by the subclass.

2. **Composition:**
   - Composition, on the other hand, involves creating objects that **contain references** to other objects (components) rather than inheriting from them.
   - In your example, the `Stack` class **uses** the `List` class via composition. If the `List` class changes, the `Stack` class is affected only if it **depends** on the changed behavior.
   - **More Flexibility:** The key difference is that you have more control over how and when to adopt these changes. You can choose to replace or modify the behavior of the component (`List` in this case) without altering the `Stack` class itself.

### Your Example Revisited

Let's revisit your example with these concepts in mind:

#### Example Using Composition

```java
public class Stack<T> {
    private final List<T> stack;  // Composition: Stack contains a List

    public Stack() {
        stack = new List<>();  // Composition: List is a component of Stack
    }

    public void push(T element) {
        stack.add(element);  // Delegates the call to List's add method
    }

    // Other methods...
}
```

- **How Changes Affect Composition:**
  - If the `add()` method in `List` is modified, the `Stack` class is affected **only if** it depends on the modified behavior. For example, if the behavior of `add()` now throws an exception in certain cases, the `Stack` class would need to handle it.
  - However, with composition, you have the flexibility to **replace** the `List` implementation with another class that provides the desired behavior. For example, you could swap out `List` with `ArrayList`, `LinkedList`, or any other class that implements the same behavior, without changing the `Stack` class itself.

#### Example Using Inheritance

```java
public class CustomList<T> extends List<T> {
    // Inherits all methods and fields from List

    @Override
    public void add(T element) {
        // Custom implementation or reuse the original one
        super.add(element); 
    }

    // Additional methods or overrides...
}
```

- **How Changes Affect Inheritance:**
  - In this example, `CustomList` inherits from `List`. If the `add()` method in `List` is changed, **all subclasses** (like `CustomList`) are directly affected by these changes.
  - `CustomList` has less flexibility because it is bound to the behavior of its superclass (`List`). If the `add()` method changes in a way that is incompatible or undesired for `CustomList`, you would need to override it explicitly in every subclass affected.

### Why Composition is Considered More Flexible

- **Control Over Changes:**
  - With composition, you have the choice to **adopt, adapt, or replace** the components (like `List`) if their behavior changes, providing greater flexibility.
  - Inheritance, however, directly ties the subclass to its superclass, making it harder to change the superclass's behavior without affecting all subclasses.

- **Encapsulation:**
  - Composition allows you to encapsulate behavior by hiding the details of the components' internal implementation. If the `List` class changes internally, you can still use it as a component without exposing its internal changes to the `Stack` class.

- **Interchangeability:**
  - Composition enables you to swap out one implementation for another (like replacing `List` with another data structure) without modifying the class that uses it (`Stack`).

### Conclusion

- **Your Insight is Correct:** Both inheritance and composition can be affected by changes in the parent or component classes.
- **Key Difference:** Composition offers more flexibility because it allows you to control how and when to adopt these changes, replace components, or modify behavior without tightly coupling your class (`Stack`) to a specific implementation (`List`).

In summary, while both composition and inheritance can be affected by changes in the classes they rely on, composition generally provides more control and flexibility in managing these changes.