package model.types;

import exception.MyException;
import model.values.StringValue;
import model.values.*;

public class RefType implements Type{
    private final Type inner;

    public RefType(Type inner)
    {
        this.inner = inner;
    }

    public Type getInner()
    {
        return this.inner;
    }

    @Override
    public boolean equals(Object another){

        if (another instanceof RefType)
            return this.inner.equals(((RefType) another).getInner());
        else
            return false;
    }

    @Override
    public String toString() { return "(Ref " +inner.toString()+")";}

    @Override
    public Value defaultValue()
    {
        return new RefValue(0,this.inner);
    }

    @Override
    public Type deepCopy()
    {
        return new RefType(this.inner.deepCopy());
    }
}
