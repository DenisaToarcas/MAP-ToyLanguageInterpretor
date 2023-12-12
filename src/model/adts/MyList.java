package model.adts;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T> {
    private final ArrayList<T> Out;

    public MyList() {
        this.Out = new ArrayList<T>();
    }

    @Override
    public void add(T element) {
        this.Out.add(element);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (T element : this.Out) {
            result.append(element.toString()).append("\n");
        }
        return result.toString();
    }
}
