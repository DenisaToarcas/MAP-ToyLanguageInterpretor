package model.adts;

import exception.MyException;
import model.types.Type;
import model.values.Value;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public interface MyIDictionary<K, T> {
    void add(K key, T element);

    T getValue(K key) throws MyException;

    boolean isDefined(K key);

    T lookup(K key) throws MyException;

    MyDictionary<K, T> update(K key, T element);

    Map<K, T> getContent();
}
