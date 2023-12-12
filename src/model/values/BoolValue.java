package model.values;

import model.types.BoolType;
import model.types.Type;

public class BoolValue implements Value{
    private final boolean val;

    public BoolValue(boolean v)
    {
        this.val = v;
    }

    @Override
    public boolean equals(Object another) {
        if (this == another)
            return true;

        if (another instanceof BoolValue) {
            if (this.val == ((BoolValue) another).val)
                return true;
        }
        return false;
    }

    public boolean getValue()
    {
        return this.val;
    }

    @Override
    public String toString()
    {
        return this.val + "";
    }

    public Type getType()
    {
        return new BoolType();
    }

    public Value deepCopy()
    {
        return new BoolValue(this.val);
    }
}