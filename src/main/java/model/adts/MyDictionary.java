package model.adts;

import exception.MyException;

import java.util.Hashtable;
import java.util.Map;

public class MyDictionary<K, T> implements MyIDictionary<K, T> {
    private final Map<K, T> SymTable;

    public MyDictionary() {
        this.SymTable = new Hashtable<K, T>();
    }

    public MyDictionary(Map<K, T> SymTable) {
        this.SymTable = SymTable;
    }

    public void add(K key, T element) {
        this.SymTable.put(key, element);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (K key : this.SymTable.keySet())
        {
            result.append(key.toString()).append("-->").append(this.SymTable.get(key).toString()).append("\n");
        }

        return result.toString();
    }

    public T getValue(K key) {
        return this.SymTable.get(key);
    }

    @Override
    public boolean isDefined(K key) {
        return this.SymTable.containsKey(key);
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
    public Map<K, T> getContent()
    {
        return this.SymTable;
    }
}
