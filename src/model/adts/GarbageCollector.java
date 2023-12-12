package model.adts;

import model.values.RefValue;
import model.values.Value;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GarbageCollector {

    public static Map<Integer, Value> safeGarbageCollector(List<Integer> addrFromBoth, Map<Integer, Value> heap)
    {
        return heap.entrySet().stream()
                .filter(e->addrFromBoth.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // This method is declared as public and static,
    // meaning it can be called without creating an instance of the class.
    public static List<Integer> getAddrFromSymTable(Collection<Value> symTableValues)
    {
        return symTableValues.stream()
                .filter(v->v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public static List<Integer> getAddrFromHeap(Collection<Value> heapValues)
    {
        return heapValues.stream()
                .filter(v->v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public static List<Integer> getAddrFromBoth(List<Integer> symTableAddr, List<Integer> heapAddr)
    {
        return Stream.concat(symTableAddr.stream(), heapAddr.stream())
                .collect(Collectors.toList());
    }
}
