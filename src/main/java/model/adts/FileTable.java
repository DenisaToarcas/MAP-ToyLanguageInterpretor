package model.adts;

import exception.MyException;
import model.values.IntValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class FileTable implements MyIFileTable<String, BufferedReader>{
    private final Dictionary<String, BufferedReader> FileTable;

    public FileTable()
    {
        this.FileTable = new Hashtable<String, BufferedReader>();
    }
    @Override
    public void add(String id, BufferedReader fileDescriptor)
    {
        this.FileTable.put(id, fileDescriptor);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Enumeration<String> keys = this.FileTable.keys();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            result.append(key).append("\n");
        }

        return result.toString();
    }

    @Override
    public BufferedReader getValue(String key)
    {
        return this.FileTable.get(key);
    }

    @Override
    public boolean isDefined(String key)
    {
        Enumeration<String> keys = this.FileTable.keys();

        while (keys.hasMoreElements()) {
            if (key.equals(keys.nextElement()))
                return true;
        }
        return false;
    }

    @Override
    public BufferedReader lookup(String key) throws MyException
    {
        if (isDefined(key))
            return getValue(key);
        throw new MyException("File " + key + " is not defined!!!");
    }

    @Override
    public void update(String key, BufferedReader fileDescriptor)
    {
        this.FileTable.put(key, fileDescriptor);
    }

    @Override
    public void open(String fileName) throws Exception
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        update(fileName, bufferedReader);
    }

    @Override
    public void close(String fileName) throws Exception
    {
        BufferedReader bufferedReader = lookup(fileName);
        bufferedReader.close();
        this.FileTable.remove(fileName);
    }

    @Override
    public IntValue read(String fileName) throws Exception
    {
        BufferedReader bufferedReader = getValue(fileName);
        String line = bufferedReader.readLine();

        if (line == null || line.isEmpty()) return new IntValue(0);
        else
            return new IntValue(Integer.parseInt(line));
    }
}
