package model.adts;

import exception.MyException;
import model.values.IntValue;

import java.io.BufferedReader;

public interface MyIFileTable<K, T> {
    void add(K key, T element);

    T getValue(K key);

    boolean isDefined(K key);

    T lookup(K key) throws MyException;

    void update(String key, BufferedReader fileDescriptor);

    void open(K key) throws Exception;

    void close(K key) throws Exception;

    IntValue read(K key) throws Exception;
}
