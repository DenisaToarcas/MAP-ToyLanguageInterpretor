package model.values;

import model.types.IntType;
import model.types.Type;

public class IntValue implements Value{
    private final int val;

    public IntValue(int v){val=v;}

    @Override
    public boolean equals(Object another) {
        if (this == another)
            return true;

        if (another instanceof IntValue) {
            if (this.val == ((IntValue) another).val)
                return true;
        }
        return false;
    }

    public int getVal() {return val;}

    @Override
    public String toString() {
        return val + "";
    }

    public Type getType() {
        return new IntType();}

    public Value deepCopy()
    {
        return new IntValue(this.val);
    }
}
