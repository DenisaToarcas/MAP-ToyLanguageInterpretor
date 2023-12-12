package model.adts;

import exception.MyException;

import java.util.*;

public class MyDictionary<K, T> implements MyIDictionary<K, T> {
    private final Dictionary<K, T> SymTable;

    public MyDictionary() {
        this.SymTable = new Hashtable<K, T>();
    }

    public MyDictionary(Dictionary<K, T> SymTable) {
        this.SymTable = SymTable;
    }

    public void add(K key, T element) {
        this.SymTable.put(key, element);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Enumeration<K> keys = this.SymTable.keys();

        while (keys.hasMoreElements()) {
            K key = keys.nextElement();
            result.append(key).append("-->").append(this.SymTable.get(key)).append("\n");
        }

        return result.toString();
    }

    public T getValue(K key) throws MyException {
        return this.SymTable.get(key);
    }

    @Override
    public boolean isDefined(K key) {
        Enumeration<K> keys = this.SymTable.keys();

        while (keys.hasMoreElements()) {
            if (key.equals(keys.nextElement()))
                return true;
        }
        return false;
    }

    @Override
    public T lookup(K key) throws MyException
    {
        if (isDefined(key))
            return getValue(key);
        throw new MyException("Variable " + key.toString() + " is not defined!!!");
    }

    @Override
    public MyDictionary<K, T> update(K key, T element) {
        this.SymTable.put(key, element);
        return new MyDictionary<K, T>(this.SymTable);
    }

    @Override
    public MyIDictionary<K, T> getContent()
    {
        return this;
    }

    @Override
    public List<T> values()
    {
        List<T> result = new ArrayList<>();

        Enumeration<K> keys = this.SymTable.keys();

        while (keys.hasMoreElements()) {
            K key = keys.nextElement();
            T value = SymTable.get(key);
            result.add(value);
        }

        return result;
    }
}
