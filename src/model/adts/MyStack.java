package model.adts;

import exception.MyException;
import java.util.*;

public class MyStack<T> implements MyIStack<T>{
    private final Stack<T> ExeStack;
    //a field whose type CollectionType is an appropriate
    // generic java library collection

    public MyStack()
    {
        this.ExeStack = new Stack<T>();
    }

    @Override
    public T pop() throws MyException
    {
        return this.ExeStack.pop();
    }

    @Override
    public void push(T element) {
        this.ExeStack.push(element);
    }

    @Override
    public boolean isEmpty()
    {
        return this.ExeStack.isEmpty();
    }

    public String toString() {

        StringBuilder result = new StringBuilder();
        for (T element : this.ExeStack)
            result.append(element.toString()).append("\n");

        return result.toString();

        /*ArrayList<String> result = new ArrayList<>();
        for (T element: this.ExeStack)
            result.add(element.toString() + "\n");

        result.reversed();

        return result.toString();
         */
    }
}
