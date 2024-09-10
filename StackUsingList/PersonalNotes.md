9/10/2024
refer: gpt conversations/9_10_24 Handling Optional.md, gpt conversations/9_10_24 method refference in Java.md,
gpt conversations/9_10_24 Return type when exception is thrown.md, gpt conversations/9_10_24 throwing exception in ternary statement.md

## ternary operator

return a ? a>b : b;

each element has to be a expression, which can be evaluated and returned as a value

return a ? a>b : new Exception();
 is invalid because statements can be included in ternary operator + exceptions are supposed to be thrown not returned

so is this correct

return a ? a>b : throw new Exception();
no, ternary operator cant do statements

hence this has to be 

if (a>b) 
 return a;

else 
    throw new Exception


## method reference

```
public T peek() {
Optional<T> peek = stack.get(stack.size() - 1);
/*
refer: gpt conversations/9_10_24 Handling Optional.md, gpt conversations/9_10_24 method refference in Java.md,
gpt conversations/9_10_24 Return type when exception is thrown.md, gpt conversations/9_10_24 throwing exception in ternary statement.md

        // If the value is present, return it; otherwise, throw an exception

        NoSuchElementException::new is passing my refference
        below call also be written as

        return peek.orElseThrow(() -> new NoSuchElementException());

        or else

    if (peek.isPresent()) {
        return peek.get();
    } else {
        throw new NoSuchElementException();
    }
     or

       return peek.orElseThrow(NoSuchElementException::new);
*/
return peek.orElseThrow(NoSuchElementException::new);
}
```
below are the method refferences

return peek.orElseThrow(NoSuchElementException::new)

or 

return peek.orElseThrow(() -> new NoSuchElementException())

or 

if (peek.isPresent) {
    return peek.get()
}
else {

}


this can be generalized as 

CustomClass::itsMethod

for all type of references refer gpt conversations/9_10_24 method refference in Java.md


## Composition v/s inheritance: (generally composition is more flexibile and has more benefits) (refer: gpt conversations/9_10_24 what is diff bw composition and inheritence, its realworld examples.md)

If engine part of the car, i should say car is composed of engine, hence its a composition and **"has a"** relationship

if Cat is a type of Animal, i can say its a **"is a"** relationship

**I cannot say Car is a engine or Cat has a Animal, because its wrong**

Inheritance: inherits all the methods and fields.

composition: takes only whats required.



