package model.adts;

import exception.MyException;
import model.values.Value;

import java.util.HashMap;
import java.util.Map;

public interface MyIHeap<K, T>{
    void add(K key, T element);

    T getValue(K key);

    boolean isDefined(K key);

    T lookup(K key) throws MyException;

    void update(K key, T element);

    Map<Integer, Value> getContent();

    void setContent(HashMap<Integer, Value> Heap);

    int IncAndGet();
}
