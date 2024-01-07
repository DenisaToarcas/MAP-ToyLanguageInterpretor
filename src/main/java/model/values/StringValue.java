package model.values;

import model.types.StringType;
import model.types.Type;

import java.util.Objects;

public class StringValue implements Value{
    private final String val;

    public StringValue(String v)
    {
        this.val = v;
    }

    @Override
    public boolean equals(Object another) {
        if (this == another)
            return true;

        if (another instanceof StringValue) {
            if (Objects.equals(this.val, ((StringValue) another).val))
                return true;
        }
        return false;
    }

    public String getValue()
    {
        return this.val;
    }

    @Override
    public String toString()
    {
        return this.val;
    }

    public Type getType()
    {
        return new StringType();
    }

    public Value deepCopy()
    {
        return new StringValue(this.val);
    }
}
