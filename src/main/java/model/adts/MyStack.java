package model.adts;

import exception.MyException;

import java.util.List;
import java.util.Stack;

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

    public List<String> getToString() {
        return ExeStack.stream().map(Object::toString).toList();
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for (T value: ExeStack
        ) { result.insert(0, value.toString() + "\n"); }
        return result.toString();
    }
}
