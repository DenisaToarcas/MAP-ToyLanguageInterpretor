package model.adts;

import exception.MyException;

import java.util.Map;

public interface MyIDictionary<K, T> {
    void add(K key, T element);

    T getValue(K key);

    boolean isDefined(K key);

    T lookup(K key) throws MyException;

    MyDictionary<K, T> update(K key, T element);

    Map<K, T> getContent();
}
