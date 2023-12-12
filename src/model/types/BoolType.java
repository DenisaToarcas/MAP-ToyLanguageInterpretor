package model.types;

import exception.MyException;
import model.values.BoolValue;
import model.values.Value;

public class BoolType implements Type {
    public boolean equals(Object another){

        return another instanceof BoolType;
    }

    public String toString() { return "bool";}

    public Value defaultValue()
    {
        return new BoolValue(false);
    }

    public static void checkBool(Type type) throws MyException {
        if (!type.equals(new BoolType()))
            throw new MyException("The type is not BOOL!");
    }

    public Type deepCopy()
    {
        return new BoolType();
    }
}
