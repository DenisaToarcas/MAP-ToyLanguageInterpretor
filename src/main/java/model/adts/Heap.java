package model.adts;

import exception.MyException;
import model.values.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Heap implements MyIHeap<Integer, Value>{
    private Map<Integer, Value> Heap;
    private static final AtomicInteger freePosition = new AtomicInteger(0);
    //AtomicInteger is especially useful in multithreaded environments where multiple
    // threads may be accessing and modifying the same variable concurrently.
    // The atomic operations provided by AtomicInteger help avoid race conditions
    // and ensure thread safety.
    //for the next assignment

    public Heap()
    {
        this.Heap = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized void add(Integer address, Value content)
    {
        this.Heap.put(address, content);
    }

    @Override
    public synchronized Value getValue(Integer address)
    {
        return this.Heap.get(address);
    }

    @Override
    public synchronized boolean isDefined(Integer address)
    {
        return this.Heap.containsKey(address);
    }

    @Override
    public synchronized Value lookup(Integer address) throws MyException
    {
        if (this.isDefined(address))
            return this.getValue(address);
        else
            throw new MyException("The address was not defined yet!");
    }

    @Override
    public synchronized void update(Integer address, Value content)
    {
        this.Heap.put(address, content);
    }

    @Override
    public synchronized String toString()
    {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, Value> entry: this.Heap.entrySet())
            result.append(entry.getKey().toString()).append("-->").append(entry.getValue().toString()).append("\n");

        return result.toString();
    }

    public synchronized Map<Integer, Value> getContent()
    {
        return this.Heap;
    }

    public synchronized void setContent(HashMap<Integer, Value> Heap)
    {
        this.Heap = Heap;
    }

    public synchronized int IncAndGet(){
        return freePosition.incrementAndGet();
    }
}
