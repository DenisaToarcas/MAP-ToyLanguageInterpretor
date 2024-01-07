package model.types;

import exception.MyException;
import model.values.IntValue;
import model.values.Value;

public class IntType implements Type{

    public boolean equals(Object another){
        return another instanceof IntType;
    }

    public String toString() { return "int";}

    public Value defaultValue()
    {
        return new IntValue(0);
    }

    public static void checkInt(Type type) throws MyException
    {
        if (!type.equals(new IntType()))
            throw new MyException("The type is not INT!");
    }

    public Type deepCopy()
    {
        return new IntType();
    }
}
