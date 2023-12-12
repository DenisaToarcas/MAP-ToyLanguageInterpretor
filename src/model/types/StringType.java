package model.types;

import exception.MyException;
import model.values.StringValue;
import model.values.Value;

public class StringType implements Type{
    public boolean equals(Object another){

        return another instanceof StringType;
    }

    public String toString() { return "string";}

    public Value defaultValue()
    {
        return new StringValue("");
    }

    public static void checkString(Type type) throws MyException {
        if (!type.equals(new StringType()))
            throw new MyException("The type is not STRING!");
    }

    public Type deepCopy()
    {
        return new StringType();
    }
}
