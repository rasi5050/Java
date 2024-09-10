package com.rasi.stackusinglist;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Stack<T>{
    private final List<T> stack;
    public Stack() {
        stack = new List<>();
    }

    public void push(T element) {
        stack.add(element);
    }
    public T pop() {
        return stack.pop();
    }


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

public boolean isEmpty() {
        return stack.size()==0;
}
    public int size() {
        return stack.size();
}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        for (int i = 0; i < stack.size(); i++) {
            Optional<T>  val = stack.get(i);
            // Handle possible empty values
            val.ifPresent(sb::append);
            if (i < stack.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
